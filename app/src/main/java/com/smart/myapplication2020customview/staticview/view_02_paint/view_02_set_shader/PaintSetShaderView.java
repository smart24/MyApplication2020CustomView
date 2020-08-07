package com.smart.myapplication2020customview.staticview.view_02_paint.view_02_set_shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;


public class PaintSetShaderView extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mItemHeight;
    private float mCenterX, mCenterY;
    private float mRadius;
    private int mStartColor, mMiddleColor, mEndColor;
    private Shader mLinearShader, mRadialShader, mSweepShader, mBitmapShader;
    private Bitmap mBitmap;

    public PaintSetShaderView(Context context) {
        this(context, null);
    }

    public PaintSetShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintSetShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mPaint.setStrokeWidth(mStrokeWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mItemHeight = mHeight / 4;
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        mRadius = (mWidth < mHeight ? mWidth : mHeight) / 6;

        mStartColor = Color.parseColor("#92fe9d");
        mEndColor = Color.parseColor("#00c9ff");

        mLinearShader = new LinearGradient(
                mCenterX - mRadius,
                mCenterY - mItemHeight - mItemHeight,
                mCenterX + mRadius,
                mCenterY - mItemHeight,
                mStartColor,
                mEndColor,
                Shader.TileMode.CLAMP);

        mRadialShader = new RadialGradient(
                mCenterX,
                mCenterY - mItemHeight / 2,
                mRadius,
                mStartColor,
                mEndColor,
                Shader.TileMode.CLAMP);

        mSweepShader = new SweepGradient(
                mCenterX,
                mCenterY + mItemHeight / 2,
                mStartColor,
                mEndColor);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_cute_mouse);
        mBitmap = mBitmap.createScaledBitmap( mBitmap, (int)(mRadius * 2), (int)(mRadius * 2), true);
        mBitmapShader = new BitmapShader(
                mBitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. LinearGradient
        mPaint.setShader(mLinearShader);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mCenterX, mCenterY - mItemHeight - mItemHeight / 2, mRadius, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mCenterX - mRadius, mCenterY - mItemHeight - mItemHeight, mCenterX + mRadius, mCenterY - mItemHeight, mPaint);

        //2. RadialGradient
        mPaint.setShader(mRadialShader);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mCenterX, mCenterY - mItemHeight / 2, mRadius, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mCenterX - mRadius, mCenterY - mItemHeight, mCenterX + mRadius, mCenterY, mPaint);

        //3. SweepGradient
        mPaint.setShader(mSweepShader);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mCenterX, mCenterY + mItemHeight / 2, mRadius, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mCenterX - mRadius, mCenterY, mCenterX + mRadius, mCenterY + mItemHeight, mPaint);

        //4. BitmapShader
        canvas.save();
        canvas.translate(mCenterX - mRadius, mCenterY + mItemHeight);
        mPaint.setShader(mBitmapShader);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mRadius, mRadius + (mItemHeight - 2 * mRadius)/2, mRadius, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, 2 * mRadius, mItemHeight, mPaint);
        canvas.restore();

    }

}

































































































































