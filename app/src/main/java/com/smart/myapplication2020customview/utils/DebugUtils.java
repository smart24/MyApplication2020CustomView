package com.smart.myapplication2020customview.utils;

public class DebugUtils {

    public static boolean isDebug(){
        return SharedPreferencesUtils.getInstance().getBoolean(Constants.DEBUG_KEY, Constants.DEBUG_SWITCH_DEFAULT_VALUE);
    }
}
