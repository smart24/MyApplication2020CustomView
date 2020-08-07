package com.smart.myapplication2020customview.staticview.view_01_basic.view_08_arc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;


public class ArcView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mLeft, mTop, mRight, mBottom;
    private float[] mLines;

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.padding_micro_x));
        mPaint.setColor(getResources().getColor(R.color.blue_400));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        //1. 椭圆
//        mLeft = mCenterX - mWidth / 8;
//        mTop = mCenterY - mHeight / 8;
//        mRight = mCenterX + mWidth / 8;
//        mBottom = mCenterY + mHeight / 8;
        //2. 圆
        mLeft = mCenterX - mWidth / 3;
        mTop = mCenterY - mWidth / 3;
        mRight = mCenterX + mWidth / 3;
        mBottom = mCenterY + mWidth / 3;

        mLines = new float[]{
                mCenterX - mWidth / 8 - mWidth / 16, mCenterY - mHeight / 8, mCenterX - mWidth / 8 - mWidth / 16, mCenterY,
                mCenterX - mWidth / 8 - mWidth / 16, mCenterY, mCenterX - mWidth / 8 * 2 - mWidth / 16, mCenterY + mHeight / 8,
                mCenterX - mWidth / 8 - mWidth / 16, mCenterY, mCenterX - mWidth / 16, mCenterY + mHeight / 8,
                mCenterX + mWidth / 16, mCenterY - mWidth / 8, mCenterX + mWidth / 16, mCenterY + mWidth / 8,
                mCenterX + mWidth / 16, mCenterY - mWidth / 8, mCenterX + mWidth / 8 * 2 + mWidth / 16, mCenterY - mWidth / 8,
                mCenterX + mWidth / 8 * 2 + mWidth / 16, mCenterY - mWidth / 8, mCenterX + mWidth / 8 * 2 + mWidth / 16, mCenterY + mWidth / 8,
                mCenterX + mWidth / 16, mCenterY + mWidth / 8, mCenterX + mWidth / 8 * 2 + mWidth / 16, mCenterY + mWidth / 8
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 单个扇形
        //drawArc(@NonNull RectF oval, float startAngle, float sweepAngle, boolean useCenter, @NonNull Paint paint)
        //drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, @NonNull Paint paint)
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawArc(mLeft, mTop, mRight, mBottom, 0, 90, true, mPaint);
//        canvas.drawArc(mLeft, mTop, mRight, mBottom, 90, 90, false, mPaint);
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawArc(mLeft, mTop, mRight, mBottom, 190, 150, false, mPaint);

        //2. 多个扇形（旋转画布）
//        canvas.drawLine(mCenterX, mCenterY, mWidth, mCenterY, mPaint);
//        canvas.save();
//        canvas.rotate(-5, mCenterX, mCenterY);
//
//        for (int i = 0; i < 360; i+=20) {
//            if(i == 0){
//                mPaint.setColor(getResources().getColor(R.color.red_400));
//            }else if(i == 20 || i == 340){
//                mPaint.setColor(getResources().getColor(R.color.green_400));
//            }else{
//                mPaint.setColor(getResources().getColor(R.color.blue_400));
//            }
//            canvas.save();
//            canvas.rotate(i, mCenterX, mCenterY);
//            canvas.drawArc(mLeft, mTop, mRight, mBottom, 0, 10, true, mPaint);
//            canvas.restore();
//        }
    }

}





































































