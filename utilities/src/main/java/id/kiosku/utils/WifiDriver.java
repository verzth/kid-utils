package id.kiosku.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import static id.kiosku.utils.WifiDriver.DNS.DNS1;

/**
 * Created by Dodi on 09/23/2016.
 */

public class WifiDriver {
    private Context context;
    private WifiManager manager;
    private WifiInfo info;
    private DhcpInfo dhcpInfo;
    private WifiReceiver receiver;
    private OnWifiChange onWifiChange;

    private class WifiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
            if(onWifiChange!=null && ConnectionReader.with(context).isConnectedToWifi())onWifiChange.onChange();
        }
    }

    public enum DNS{
        DNS1,DNS2
    }

    public WifiDriver(Context context){
        this.context=context;
        manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        receiver = new WifiReceiver();
        refresh();
    }
    public interface OnWifiChange{
        void onChange();
    }
    @SuppressWarnings("MissingPermission")
    public void refresh(){
        info = manager.getConnectionInfo();
        dhcpInfo = manager.getDhcpInfo();
    }

    public static WifiDriver with(Context context){
        return new WifiDriver(context);
    }

    public String getSSID(){
        return info.getSSID();
    }
    public String getBSSID(){
        return info.getBSSID()  ;
    }
    public String getMacAdress(){
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        }catch (Exception e){}
        return info.getMacAddress();
    }
    public int getNetworkID(){
        return info.getNetworkId();
    }
    public String getIP(){
        return Formatter.formatIpAddress(info.getIpAddress());
    }

    public String getDNS(){
        return getDNS(DNS1);
    }
    public String getDNS(DNS dns){
        switch (dns){
            case DNS1 : {
                return Formatter.formatIpAddress(dhcpInfo.dns1);
            }
            case DNS2 : {
                return Formatter.formatIpAddress(dhcpInfo.dns2);
            }
            default: return getDNS(DNS1);
        }
    }
    public String getDefaultGateway(){
        return Formatter.formatIpAddress(dhcpInfo.gateway);
    }
    public String getNetmask(){
        return String.valueOf(dhcpInfo.netmask);
    }
    public String getServerAddress(){
        return Formatter.formatIpAddress(dhcpInfo.serverAddress);
    }

    /**
     * Turn off receiver to prevent memory leaked
     */
    public void turnOff(){
        try{
            if(receiver!=null)context.unregisterReceiver(receiver);
        } catch (Exception e){}
    }

    /**
     * Turn on receiver to detect Access Point Changes
     */
    public void turnOn(){
        turnOff();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(receiver,filter);
    }
    public void turnOn(OnWifiChange onWifiChange){
        this.onWifiChange = onWifiChange;
        turnOn();
    }
}
