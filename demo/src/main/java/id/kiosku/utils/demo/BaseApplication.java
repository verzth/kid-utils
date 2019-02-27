package id.kiosku.utils.demo;

import android.app.Application;

import id.kiosku.utils.LocationDriver;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LocationDriver.init(this);
    }
}
