package com.smart.myapplication2020customview.staticview.view_01_basic.view_05_oval;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

/**
 * FileName: OvalView
 *
 * Des: 椭圆
 *
 * To Do:
 *
 *  V 1.0: 椭圆  👈
 *
 *  V 2.0: 菊花
 *
 *  V 3.0: 奥运
 *
 * Time: 2020/5/7 2:41 PM
 */
public class OvalView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private float mLeft, mTop, mRight, mBottom;
    private RectF mRectF;

    public OvalView(Context context) {
        this(context, null);
    }

    public OvalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.padding_micro_x));
        mPaint.setColor(getResources().getColor(R.color.green_400));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
        mLeft = mCenterX - mWidth/4;
        mTop = mCenterY - mHeight/4;
        mRight = mCenterX + mWidth/4;
        mBottom = mCenterY + mHeight/4;

        mRectF = new RectF();
        mRectF.set(mCenterX - mHeight/4, mCenterY - mWidth/4, mCenterX + mHeight/4, mCenterY + mWidth/4);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(getResources().getColor(R.color.green_400));
        //第一种方式：
        //
        //drawOval(float left, float top, float right, float bottom, @NonNull Paint paint)
        canvas.drawOval(mLeft, mTop, mRight, mBottom, mPaint);


        mPaint.setColor(getResources().getColor(R.color.blue_400));
        //第二种方式：
        //
        //drawOval(@NonNull RectF oval, @NonNull Paint paint)
        canvas.drawOval(mRectF, mPaint);
    }

}
