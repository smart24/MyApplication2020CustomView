package com.smart.myapplication2020customview.utils;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

public class ViewUtils {

    public static void expandViewTouchDelegate(final View view,
                                               final int left,
                                               final int top,
                                               final int right,
                                               final int bottom
    ) {
        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                bounds.left -= left;
                bounds.top -= top;
                bounds.right += right;
                bounds.bottom += bottom;

                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);

                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }

}
