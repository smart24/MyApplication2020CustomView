package com.smart.myapplication2020customview.staticview.view_01_basic.view_05_oval;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PetalView
 *
 * Des: 椭圆
 *
 * To Do:
 *
 *  V 1.0: 椭圆  👌
 *
 *  V 2.0: 菊花  👈
 *
 *  V 3.0: 奥运
 *
 * Time: 2020/5/7 2:41 PM
 */
public class PetalView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private float mLeft, mTop, mRight, mBottom;

    public PetalView(Context context) {
        this(context, null);
    }

    public PetalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PetalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.padding_micro_x));
        mPaint.setColor(getResources().getColor(R.color.blue_400));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
        mLeft = mCenterX - mWidth/64;
        mTop = mCenterY - mHeight/4;
        mRight = mCenterX + mWidth/64;
        mBottom = mCenterY;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 单个叶子
        //canvas.drawOval(mLeft, mTop, mRight, mBottom, mPaint);

        //2. 菊花
        for (int i = 0; i < 360; i+=15) {
            if(i == 0){
                mPaint.setColor(getResources().getColor(R.color.red_400));
            }else if(i == 15 || i == 345){
                mPaint.setColor(getResources().getColor(R.color.green_400));
            }else{
                mPaint.setColor(getResources().getColor(R.color.blue_400));
            }
            canvas.save();
            canvas.rotate(i, mCenterX, mCenterY);
            canvas.drawOval(mLeft, mTop, mRight, mBottom, mPaint);
            canvas.restore();
        }
    }

}




































































