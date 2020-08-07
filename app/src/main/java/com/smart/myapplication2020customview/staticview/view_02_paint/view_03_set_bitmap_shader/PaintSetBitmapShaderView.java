package com.smart.myapplication2020customview.staticview.view_02_paint.view_03_set_bitmap_shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;


public class PaintSetBitmapShaderView extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mRadius;
    private Shader mShader;
    private Bitmap mBitmap;
    private float mDeltaX, mDeltaY;

    public PaintSetBitmapShaderView(Context context) {
        this(context, null);
    }

    public PaintSetBitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintSetBitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        mRadius = (mWidth < mHeight ? mWidth : mHeight) / 6;

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_cold_drink);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_bird_woodpecker);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_cute_mouse);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_mountain_king);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_blue_flag);
        mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);

        mDeltaX = (mWidth - mBitmap.getWidth())/2;
        mDeltaY = (mHeight - mBitmap.getHeight())/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(mDeltaX, mDeltaY);
//        canvas.drawRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), mPaint);
        canvas.drawCircle(mBitmap.getWidth()/2, mBitmap.getHeight()/2, (mBitmap.getWidth() < mBitmap.getHeight() ? mBitmap.getWidth() : mBitmap.getHeight())/2, mPaint);
        canvas.restore();


    }

}


































































































































