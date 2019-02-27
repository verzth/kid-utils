package id.kiosku.utils.demo;

import android.Manifest;
import android.content.pm.PackageManager;
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
            Log.e("Lat", LocationDriver.getInstance().getLatitude()+"");
            Log.e("Long", LocationDriver.getInstance().getLongitude()+"");
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            LocationDriver.init(this);
            Log.e("Lat", LocationDriver.getInstance().getLatitude()+"");
            Log.e("Long", LocationDriver.getInstance().getLongitude()+"");
        }
    }
}
