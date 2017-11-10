package com.tyroo.tyroovastdemoapp;

import android.app.Application;
import android.content.Context;


public class DemoApplication extends Application{
    public final static String TAG = DemoApplication.class.getSimpleName();

    public static Context GLOBAL_CONTEXT;
    public static DemoApplication instance;



    @Override
    public void onCreate() {
        super.onCreate();
        GLOBAL_CONTEXT = getApplicationContext();
        //refWatcher = LeakCanary.install(this);
    }

    public static DemoApplication getInstance() {
        if (instance == null) {
            instance = new DemoApplication();
            return instance;
        } else {
            return instance;
        }
    }
}