package com.smart.myapplication2020customview.staticview.view_08_layout_baisc;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CustomSizeImageView
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
public class CustomSizeImageView extends AppCompatImageView {

    private int mExpectWidth, mExpectHeight;
    private int mWidth, mHeight;

    public CustomSizeImageView(Context context) {
        this(context, null);
    }

    public CustomSizeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSizeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mExpectWidth = (int) getResources().getDimension(R.dimen.avatar_size);
        mExpectHeight = (int) getResources().getDimension(R.dimen.avatar_size);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mWidth = mWidth <= mExpectWidth ? mExpectWidth : mWidth;
        mHeight = mHeight <= mExpectHeight ? mExpectHeight : mHeight;

        setMeasuredDimension(mWidth, mHeight);
    }

}
