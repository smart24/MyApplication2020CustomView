package com.smart.myapplication2020customview.staticview.view_07_property_animation.view_02_point_f_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PointFView
 *
 * Des:
 *
 * Version:
 *
 * State:
 *
 * Optimize:
 *
 * Time: 2020/6/16 7:51 AM
 */
public class PointFView extends android.view.View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private int mRadius;
    private PointF position;

    public PointFView(Context context) {
        this(context, null);
    }

    public PointFView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointFView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.blue_400));


    }

    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {
        this.position = position;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = getMeasuredWidth();
        this.mHeight = getMeasuredHeight();

        mCenterX = mWidth/2;
        mCenterY = mHeight/2;

//        mRadius = (mCenterX < mCenterY ? mCenterX : mCenterY) * 1/2;
//        mRadius = (mCenterX < mCenterY ? mCenterX : mCenterY) * 1/4;
//        mRadius = (mCenterX < mCenterY ? mCenterX : mCenterY) * 1/8;
        mRadius = (mCenterX < mCenterY ? mCenterX : mCenterY) * 1/16;

        position = new PointF(mCenterX, mCenterY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(position.x, position.y, mRadius, mPaint);
    }

}
