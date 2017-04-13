package com.travelexperts.travelpackages;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by 468364 on 4/11/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this).init();
    }
}
