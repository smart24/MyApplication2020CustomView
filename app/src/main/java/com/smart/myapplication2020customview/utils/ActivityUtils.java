package com.smart.myapplication2020customview.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class ActivityUtils {

    public static void startActivity(Fragment fragment, Class<?> cls) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        fragment.startActivity(intent);
    }

    public static void startActivity(Fragment fragment, Class<?> cls, String key, String param) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        intent.putExtra(key, param);
        fragment.startActivity(intent);
    }

    public static void startActivity(Fragment fragment, Class<?> cls, String key1, String param1, String key2, String param2) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        intent.putExtra(key1, param1);
        intent.putExtra(key2, param2);
        fragment.startActivity(intent);
    }

    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<?> cls, String key, String param) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, param);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<?> cls, String key1, String param1, String key2, String param2) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key1, param1);
        intent.putExtra(key2, param2);
        context.startActivity(intent);
    }

    /**
     * Activity 的阻塞作用
     * <p>
     * 如果在 Fragment 中直接调用 getActivity().startActivityForResult(intent, requestCode);
     * Fragment 将接收不到目标 Activity 返回的结果，即 Fragment 的 onActivityResult 不会被调用
     *
     * @param fragment
     * @param cls
     * @param requestCode
     */
    public static void startActivityForResult(Fragment fragment, Class<?> cls, final int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Fragment fragment, Class<?> cls, String key, String param, final int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        intent.putExtra(key, param);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Fragment fragment, Class<?> cls, String key1, String param1, String key2, String param2, final int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        intent.putExtra(key1, param1);
        intent.putExtra(key2, param2);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Activity activity, Class<?> cls, final int requestCode) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Activity activity, Class<?> cls, String key, String param, final int requestCode) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(key, param);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Activity activity, Class<?> cls, String key1, String param1,  String key2, String param2, final int requestCode) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(key1, param1);
        intent.putExtra(key2, param2);
        activity.startActivityForResult(intent, requestCode);
    }
}
