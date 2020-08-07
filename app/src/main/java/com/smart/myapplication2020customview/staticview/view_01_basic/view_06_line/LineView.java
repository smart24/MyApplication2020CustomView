package com.smart.myapplication2020customview.staticview.view_01_basic.view_06_line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;


public class LineView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mLeft, mTop, mRight, mBottom;
    private float [] mLines;

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
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
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
        mLeft = mCenterX - mWidth/8;
        mTop = mCenterY - mHeight/8;
        mRight = mCenterX + mWidth/8;
        mBottom = mCenterY + mHeight/8;

        mLines = new float []{mCenterX - mWidth/8 - mWidth/16, mCenterY - mHeight/8, mCenterX- mWidth/8 - mWidth/16, mCenterY,
                              mCenterX- mWidth/8 - mWidth/16, mCenterY, mCenterX - mWidth/8 * 2 - mWidth/16, mCenterY + mHeight/8,
                              mCenterX- mWidth/8 - mWidth/16, mCenterY, mCenterX - mWidth/16, mCenterY + mHeight/8,
                              mCenterX + mWidth/16, mCenterY - mWidth/8, mCenterX + mWidth/16, mCenterY + mWidth/8,
                              mCenterX + mWidth/16, mCenterY - mWidth/8, mCenterX + mWidth/8 * 2 + mWidth/16, mCenterY - mWidth/8,
                              mCenterX + mWidth/8 * 2 + mWidth/16, mCenterY - mWidth/8, mCenterX + mWidth/8 * 2 + mWidth/16, mCenterY + mWidth/8,
                              mCenterX + mWidth/16, mCenterY + mWidth/8, mCenterX + mWidth/8 * 2 + mWidth/16, mCenterY + mWidth/8};
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 单条线
//        canvas.drawLine(mLeft, mTop, mRight, mBottom, mPaint);

        //2. 多条线（旋转画布）
//        for (int i = 0; i < 360; i+=15) {
//            if(i == 0){
//                mPaint.setColor(getResources().getColor(R.color.red_400));
//            }else if(i == 15 || i == 345){
//                mPaint.setColor(getResources().getColor(R.color.green_400));
//            }else{
//                mPaint.setColor(getResources().getColor(R.color.blue_400));
//            }
//            canvas.save();
//            canvas.rotate(i, mCenterX, mCenterY);
//            canvas.drawLine(mLeft, mTop, mRight, mBottom, mPaint);
//            canvas.drawCircle(mRight, mTop, mWidth/64, mPaint);
//            canvas.restore();
//        }

        //3. 多条线
        canvas.drawLines(mLines, mPaint);
    }

}





































































