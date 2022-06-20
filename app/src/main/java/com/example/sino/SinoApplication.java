package com.example.sino;

import android.app.Application;
import android.content.pm.PackageManager;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class SinoApplication extends Application {

    private static SinoApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized SinoApplication getInstance() {
        return mInstance;
    }

    public String getVersionName() {

        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "0.0";
    }
}
