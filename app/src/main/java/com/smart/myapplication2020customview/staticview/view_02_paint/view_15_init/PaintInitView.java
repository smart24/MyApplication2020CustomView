package com.smart.myapplication2020customview.staticview.view_02_paint.view_15_init;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * @author zhangjianhui
 */
public class PaintInitView extends android.view.View {

    private Paint mPaint, mNewPaint;
    private float mStrokeWidth;
    private int mWidth, mHeight;
    private float mCenterX, mCenterY;

    public PaintInitView(Context context) {
        this(context, null);
    }

    public PaintInitView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintInitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.green_400));
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mPaint.setStrokeWidth(mStrokeWidth);

        mNewPaint = new Paint();
        mNewPaint.set(mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawCircle(mCenterX, mCenterY, (mCenterX < mCenterY ? mCenterX : mCenterY)/2, mPaint);

        //1. 重置 Paint 的所有属性为默认值。相当于重新 new 一个，不过性能当然高一些啦。
//        mPaint.reset();
//        canvas.drawCircle(mCenterX, mCenterY, (mCenterX < mCenterY ? mCenterX : mCenterY)/2, mPaint);

        //2. 把 src 的所有属性全部复制过来。相当于调用 src 所有的 get 方法，然后调用这个 Paint 的对应的 set 方法来设置它们。
//        mNewPaint.set(mPaint);
//        canvas.drawCircle(mCenterX, mCenterY, (mCenterX < mCenterY ? mCenterX : mCenterY)/2, mNewPaint);

        //3. 批量设置 flags。相当于依次调用它们的 set 方法。
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        canvas.drawCircle(mCenterX, mCenterY, (mCenterX < mCenterY ? mCenterX : mCenterY)/2, mPaint);
    }

}
































































































































































































































































































































































































































































































































































































