package com.smart.myapplication2020customview.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.smart.myapplication2020customview.framework.BaseApplication;


public class PackageUtils {

    public static String getAppVersion(){
        PackageManager pm = BaseApplication.getInstance().getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(BaseApplication.getInstance().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
