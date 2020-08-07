package com.smart.myapplication2020customview.staticview.view_01_basic.view_04_point;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;


public class PointView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private float [] mPoints;

    public PointView(Context context) {
        super(context);
        init();
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                     设置 Paint                                          //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.item_height));
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setColor(getResources().getColor(R.color.cyan_400));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;

        mPoints = new float[]{mCenterX, mCenterY - mHeight/4,
                              mCenterX  - mWidth/4, mCenterY,
                              mCenterX, mCenterY,
                              mCenterX + mWidth/4, mCenterY,
                              mCenterX, mCenterY + mHeight/4};
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        mPaint.setStrokeCap(Paint.Cap.BUTT);
//        canvas.drawPoint(mCenterX, mCenterY - mHeight/4, mPaint);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(mCenterX, mCenterY, mPaint);
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);
//        canvas.drawPoint(mCenterX, mCenterY + mHeight/4, mPaint);

        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoints(mPoints, mPaint);
    }

}
