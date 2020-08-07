package com.smart.myapplication2020customview.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	
	private static Toast mToast;
	
	public static void showToast(Context context, String msg, int length) {
        if (mToast == null) {
        	mToast = Toast.makeText(context, msg, length);
        } else {
        	mToast.setText(msg);
        }
        mToast.show();
    }

}
