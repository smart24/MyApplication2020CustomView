package com.smart.myapplication2020customview.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

public class IntentUtils {

    public static void setResultString(Activity activity, String key, int resultCode, String content) {
        Intent intent = new Intent();
        if (!TextUtils.isEmpty(key)) {
            intent.putExtra(key, content);
        }
        activity.setResult(resultCode, intent);
        activity.finish();
    }
}
