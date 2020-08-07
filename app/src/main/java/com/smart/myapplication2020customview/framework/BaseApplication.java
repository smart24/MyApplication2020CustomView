package com.smart.myapplication2020customview.framework;

import android.app.Application;

public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApplication getInstance(){
        return instance;
    }
}
