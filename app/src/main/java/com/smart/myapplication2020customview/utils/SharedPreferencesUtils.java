package com.smart.myapplication2020customview.utils;

import android.content.SharedPreferences;

import com.smart.myapplication2020customview.framework.BaseApplication;


public class SharedPreferencesUtils {

    private static SharedPreferencesUtils instance;
    private SharedPreferences sp;

    private SharedPreferencesUtils() {
        sp = BaseApplication.getInstance().getSharedPreferences(BaseApplication.getInstance().getPackageName(), 0);
    }

    public static SharedPreferencesUtils getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (instance == null) {
                    instance = new SharedPreferencesUtils();
                }
            }
        }
        return instance;
    }

    public void putString(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public void putInt(String key, Integer value) {
        sp.edit().putInt(key, value).commit();
    }

    public Integer getInt(String key, Integer defValue) {
        return sp.getInt(key, defValue);
    }

    public void putFloat(String key, Float value) {
        sp.edit().putFloat(key, value).commit();
    }

    public Float getFloat(String key, Float defValue) {
        return sp.getFloat(key, defValue);
    }

    public void putLong(String key, Long value) {
        sp.edit().putLong(key, value).commit();
    }

    public Long getLong(String key, Long defValue) {
        return sp.getLong(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public Boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public void remove(String key) {
        try {
            sp.edit().remove(key).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
