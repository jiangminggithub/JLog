package com.jm.jlog.simple;

import android.app.Application;

import com.jm.jlog.simple.utils.AppLog;

public class App extends Application {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        AppLog.initAndroidAndFileLogConfig(getApplicationContext(), BuildConfig.DEBUG);
        AppLog.i(TAG, "onCreate: ");
    }
}
