package com.smart.myapplication2020customview.staticview.view_01_basic.view_03_rect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;


public class RectView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private int mLeft, mTop, mRight, mBottom;
    private Bitmap mBitmap;
    private int mBitmapWidth, mBitmapHeight;
    private int mBitmapLeft, mBitmapTop;

    public RectView(Context context) {
        super(context);
        init();
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        //Paint Style
        //Paint Style Fill
//        mPaint.setStyle(Paint.Style.FILL);

        //Paint Style Stroke
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.padding_micro));
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        //Paint Style FillAndStroke
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.padding_small));
//        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mPaint.setColor(getResources().getColor(R.color.cyan_400));


        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                     设置 Bitmap                                         //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
        mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_aojiao);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
        mLeft = mCenterX - mWidth/4;
        mRight = mCenterX + mWidth/4;
        mTop = mCenterY - mHeight/4;
        mBottom = mCenterY + mHeight/4;

        mBitmapLeft = (mWidth - mBitmapWidth) /2;
        mBitmapTop = (mHeight - mBitmapHeight)/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mLeft, mTop, mRight, mBottom, mPaint);

        canvas.drawBitmap(mBitmap, mBitmapLeft, mBitmapTop, mPaint);
    }

}
























































































