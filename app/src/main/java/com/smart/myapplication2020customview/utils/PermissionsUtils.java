package com.smart.myapplication2020customview.utils;

import android.os.Environment;

public class PermissionsUtils {

    public static boolean isExternalStorageAvailable(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
                return true;
            }
        }
        return false;
    }
}
