package id.kiosku.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Dodi on 09/13/2016.
 */
@SuppressWarnings("MissingPermission")
public class ConnectionReader {
    private ConnectivityManager connectivityManager;
    private Context context;
    private NetworkInfo info;
    public ConnectionReader(Context context){
        this.context = context;
        connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
    public static ConnectionReader with(Context context){
        return new ConnectionReader(context);
    }
    public boolean isConnected(){
        info = connectivityManager.getActiveNetworkInfo();
        return info!=null && info.isConnected();
    }
    public boolean isConnectedToWifi(){
        info = connectivityManager.getActiveNetworkInfo();
        return info!=null && info.getType() == ConnectivityManager.TYPE_WIFI && info.isConnected();
    }
    public boolean isConnectedToMobile(){
        info = connectivityManager.getActiveNetworkInfo();
        return info!=null && info.getType() == ConnectivityManager.TYPE_MOBILE && info.isConnected();
    }
    @TargetApi(21)
    public boolean isConnectedToVPN(){
        info = connectivityManager.getActiveNetworkInfo();
        return info!=null && info.getType() == ConnectivityManager.TYPE_VPN && info.isConnected();
    }
}
