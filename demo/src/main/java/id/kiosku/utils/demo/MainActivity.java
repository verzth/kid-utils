package id.kiosku.utils.demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import id.kiosku.utils.LocationDriver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED){
            LocationDriver.getInstance().watch(new LocationDriver.WatchLocation() {
                @Override
                public void onUpdate(Location location) {
                    Log.e("Lat", LocationDriver.getInstance().getLatitude()+"");
                    Log.e("Long", LocationDriver.getInstance().getLongitude()+"");
                    Log.e("Admin", LocationDriver.getInstance().getAddress(LocationDriver.AddressType.ADMIN));
                    Log.e("SubAdmin", LocationDriver.getInstance().getAddress(LocationDriver.AddressType.SUBADMIN));
                    Log.e("Locality", LocationDriver.getInstance().getAddress(LocationDriver.AddressType.LOCALITY));
                    Log.e("SubLocality", LocationDriver.getInstance().getAddress(LocationDriver.AddressType.SUBLOCALITY));
                    Log.e("ADD1", LocationDriver.getInstance().getAddress(LocationDriver.AddressType.ADDRESS));
                    Log.e("ADD2", LocationDriver.getInstance().getAddress(LocationDriver.AddressType.ADDRESS_SECOND));
                    Log.e("NAME", LocationDriver.getInstance().getAddress(LocationDriver.AddressType.NAME));
                    Log.e("Thoroughfare", LocationDriver.getInstance().getAddress(LocationDriver.AddressType.THOROUGHFARE));
                }
            });
        }else if (Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocationDriver.getInstance().triggerService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationDriver.getInstance().unwatch();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            LocationDriver.init(this);
            Log.e("Lat", LocationDriver.getInstance().getLatitude()+"");
            Log.e("Long", LocationDriver.getInstance().getLongitude()+"");
        }
    }
}
