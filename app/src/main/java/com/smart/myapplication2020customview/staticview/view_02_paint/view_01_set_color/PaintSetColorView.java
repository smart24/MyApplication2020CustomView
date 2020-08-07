package com.smart.myapplication2020customview.staticview.view_02_paint.view_01_set_color;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintSetColorView
 *
 * Des: Paint setColor 解析
 *
 * Time: 2020/5/19 10:35 AM
 */
public class PaintSetColorView extends View {

    private Paint mPaint;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mRadius;

    public PaintSetColorView(Context context) {
        this(context, null);
    }

    public PaintSetColorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintSetColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
        mRadius = (mWidth < mHeight ? mWidth : mHeight) / 6;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 直接在颜色中设置透明度
        mPaint.setColor(getResources().getColor(R.color.green_400));
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);

        //2. 手动设置透明度
        mPaint.setARGB(64, 255,87,34);
        canvas.drawCircle(mCenterX, mCenterY - mRadius * 3/2, mRadius, mPaint);
    }

}
































































































































