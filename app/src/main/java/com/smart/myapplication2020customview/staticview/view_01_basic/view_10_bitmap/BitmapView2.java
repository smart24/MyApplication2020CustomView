package com.smart.myapplication2020customview.staticview.view_01_basic.view_10_bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;


public class BitmapView2 extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mRadius;
    private Bitmap mBitmap;

    public BitmapView2(Context context) {
        this(context, null);
    }

    public BitmapView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(getResources().getColor(R.color.red_400));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        mRadius = (mWidth < mHeight ? mWidth : mHeight) / 6;

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_blue_flag);
        mBitmap = mBitmap.createScaledBitmap( mBitmap, (int)(mRadius * 2), (int)(mRadius * 2), true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.drawCircle(0, 0, mStrokeWidth, mPaint);
        canvas.drawBitmap(mBitmap, mBitmap.getWidth(), mBitmap.getHeight(), mPaint);
        canvas.drawCircle(mBitmap.getWidth(), mBitmap.getHeight(), mStrokeWidth, mPaint);
    }

}

































































































































