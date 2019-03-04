package id.kiosku.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

/**
 * Created by AMP on 27-Oct-15.
 */
public class LocationDriver implements LocationListener {
    private static LocationDriver anInstance;
    private Context context;
    private LocationManager manager;
    private Location location;
    public static final String PROVIDER_NAME = "MyLocations";
    private static final long MIN_DISTANCE = 10;
    private static final long MIN_TIME =  1000;
    public enum AddressType{THOROUGHFARE,SUBLOCALITY,LOCALITY,POSTAL,SUBADMIN,
        ADMIN,COUNTRY,COUNTRY_CODE}
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public LocationDriver(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MyLocations", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        triggerService();
    }

    public static LocationDriver getInstance() {
        return anInstance;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        LocationDriver.this.destroy();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void triggerService(){
        new Thread(new Runnable() {
            @Override
            @SuppressWarnings("MissingPermission")
            public void run() {
                Looper.prepare();
                if(LocationDriver.this.context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)) {
                    if (LocationDriver.this.context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        manager = (LocationManager) LocationDriver.this.context.getSystemService(Context.LOCATION_SERVICE);
                        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, LocationDriver.this);
                            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(location==null){
                                manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, LocationDriver.this);
                                location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                if(location!=null) LocationDriver.this.location = location;
                            }else LocationDriver.this.location = location;
                        } else {
                            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, LocationDriver.this);
                            Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if(location!=null) LocationDriver.this.location = location;
                        }
                    }
                }
            }
        }).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LocationDriver.this.destroy();
            }
        },3000);
    }

    @SuppressWarnings("MissingPermission")
    public void destroy() throws SecurityException{
        if(manager!=null){
            manager.removeUpdates(this);
        }
    }
    public static void init(Context context){
        anInstance = new LocationDriver(context);
    }
    public Location getLocation(){
        return location;
    }
    public Double getLongitude(){
        if(location!=null){
            return location.getLongitude();
        }
        return null;
    }
    public Double getLatitude(){
        if(location!=null) {
            return location.getLatitude();
        }
        return null;
    }
    public Double getAltitude(){
        if(location!=null) {
            return location.getAltitude();
        }
        return null;
    }
    public float getBearing(){
        if(location!=null) {
            return location.getBearing();
        }
        return 0;
    }
    public float getSpeed(){
        if(location!=null) {
            return location.getSpeed();
        }
        return 0;
    }
    public float getAccuracy(){
        if(location!=null) {
            return location.getAccuracy();
        }
        return 0;
    }

    /**
     * @param latitude Latitude coordinate in double
     * @param longitude Longitude coordinate in double
     * @return Distance in Meter
     */
    public float getDistanceTo(double latitude,double longitude){
        if(location!=null){
            Location destination = createLocation(latitude,longitude);
            return location.distanceTo(destination);
        }
        return -1;
    }

    /**
     * @param locDest android.Location.Location
     * @return Distance in Meter
     */
    public float getDistanceTo(Location locDest){
        if(location!=null){
            return location.distanceTo(locDest);
        }
        return -1;
    }
    public float getDistanceBetween(double latSource,double longSource,double latDest,double longDest){
        float[] res=new float[1];
        Location.distanceBetween(latSource,longSource,latDest,longDest,res);
        return res[0];
    }
    public float getDistanceBetween(Location source, double latDest, double longDest){
        Location destination = createLocation(latDest,longDest);
        return source.distanceTo(destination);
    }
    public float getDistanceBetween(double latDest,double longDest,Location source){
        return getDistanceBetween(source,latDest,longDest);
    }
    public float getDistanceBetween(Location source, Location destination){
        return source.distanceTo(destination);
    }
    public static Location createLocation(double latitude, double longitude){
        Location loc = new Location(PROVIDER_NAME);
        loc.setLatitude(latitude);
        loc.setLongitude(longitude);
        return loc;
    }
    public boolean isGranted(){
        PackageManager packageManager = context.getPackageManager();
        int check = packageManager.checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,context.getPackageName());
        if(check== PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    //LOCAL ADDRESSING
    public String getAddress(AddressType address){
        if(ConnectionReader.with(context).isConnected()) {
            if (location != null) {
                try {
                    if(sharedPreferences.getLong("timeout",0)< System.currentTimeMillis()){
                        Geocoder code = new Geocoder(context);
                        Address loc = code.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
                        editor.putString("thoroughfare",loc.getThoroughfare());
                        editor.putString("sublocality",loc.getSubLocality());
                        editor.putString("locality",loc.getLocality());
                        editor.putString("postal",loc.getPostalCode());
                        editor.putString("subadmin",loc.getSubAdminArea());
                        editor.putString("admin",loc.getAdminArea());
                        editor.putString("country",loc.getCountryName());
                        editor.putString("country_code",loc.getCountryCode());
                        editor.putLong("timeout", System.currentTimeMillis()+(5*60*1000));
                        editor.commit();
                    }
                    String result;
                    switch (address) {
                        case THOROUGHFARE:
                            result = sharedPreferences.getString("thoroughfare","UNKNOWN");
                            break;
                        case SUBLOCALITY:
                            result = sharedPreferences.getString("sublocality","UNKNOWN");
                            break;
                        case LOCALITY:
                            result = sharedPreferences.getString("locality","UNKNOWN");
                            break;
                        case POSTAL:
                            result = sharedPreferences.getString("postal","UNKNOWN");
                            break;
                        case SUBADMIN:
                            result = sharedPreferences.getString("subadmin","UNKNOWN");
                            break;
                        case ADMIN:
                            result = sharedPreferences.getString("admin","UNKNOWN");
                            break;
                        case COUNTRY:
                            result = sharedPreferences.getString("country","UNKNOWN");
                            break;
                        case COUNTRY_CODE:
                            result = sharedPreferences.getString("country_code","UNKNOWN");
                            break;
                        default:
                            result = "UNKNOWN";
                    }
                    return result;
                } catch (IOException e) {
                    editor.putLong("timeout", System.currentTimeMillis()+(60*1000));
                    editor.commit();
                    return "UNKNOWN";
                }
            }
        }else{
            return "UNKNOWN";
        }
        return "";
    }

    /**
     * Get Minimum Latitude for square around with radius 10 km
     * @param center latitude point
     * @return latitude minimum point
     */
    public static double getMinLat(double center){
        return getMinLat(5,center);
    }
    /**
     * Get Minimum Latitude for square around
     * @param range radius of minimum range in kilometer
     * @param center latitude point
     * @return latitude minimum point
     */
    public static double getMinLat(int range, double center){
        return center - (range*0.009);
    }
    /**
     * Get Maximum Latitude for square around with radius 10 km
     * @param center latitude point
     * @return latitude maximum point
     */
    public static double getMaxLat(double center){
        return getMaxLat(5,center);
    }
    /**
     * Get Maximum Latitude for square around
     * @param range radius of maximum range in kilometer
     * @param center latitude point
     * @return latitude maximum point
     */
    public static double getMaxLat(int range, double center){
        return center + (range*0.009);
    }

    /**
     * Get Minimum Longitude for square around with radius 10 km
     * @param center longitude point
     * @param relative latitude point
     * @return longitude minimum point
     */
    public static double getMinLong(double center, double relative){
        return getMinLong(5,center,relative);
    }
    /**
     * Get Minimum Longitude for square around
     * @param range radius of minimum range in kilometer
     * @param center longitude point
     * @param relative latitude point
     * @return longitude minimum point
     */
    public static double getMinLong(int range, double center, double relative){
        return center - ((range*0.009)/ Math.cos(relative*(Math.PI/180)));
    }
    /**
     * Get Maximum Longitude for square around with radius 10 km
     * @param center longitude point
     * @param relative latitude point
     * @return longitude maximum point
     */
    public static double getMaxLong(double center, double relative){
        return getMaxLong(5,center,relative);
    }
    /**
     * Get Maximum Longitude for square around
     * @param range radius of maximum range in kilometer
     * @param center longitude point
     * @param relative latitude point
     * @return longitude maximum point
     */
    public static double getMaxLong(int range, double center, double relative){
        return center + ((range*0.009)/ Math.cos(relative*(Math.PI/180)));
    }

    @TargetApi(18)
    public boolean isMock(){
        return location.isFromMockProvider();
    }
}