package com.smart.myapplication2020customview.staticview.view_08_layout_baisc;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * FileName: CustomSizeImageView2
 *
 * Des: 
 * 
 * Version: 
 * 
 * State: 
 * 
 * Optimize: 
 * 
 * Time: 2020/6/19 6:04 PM
 */
public class CustomSizeImageView2 extends AppCompatImageView {

    public CustomSizeImageView2(Context context) {
        this(context, null);
    }

    public CustomSizeImageView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSizeImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        if (measuredWidth > measuredHeight) {
            measuredWidth = measuredHeight;
        } else {
            measuredHeight = measuredWidth;
        }

//        if (measuredWidth > measuredHeight) {
//            measuredWidth = measuredHeight;
//        } else {
//            measuredWidth = measuredHeight;
//        }
        
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

}
