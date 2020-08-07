package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_03_draw_after_super_dispatch_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasDrawAfterSuperDispatchDrawView
 *
 * Des: Canvas draw after super.dispatchDraw()
 *
 *      只要重写 dispatchDraw()，并在 super.dispatchDraw() 的下面写上你的绘制代码，这段绘制代码就会发生在子
 *      View 的绘制之后，从而让绘制内容盖住子 View 了。
 *
 * Version:
 *
 * State:
 *
 * Optimize:
 *
 * Time: 2020/6/9 2:20 PM
 */
public class CanvasDrawAfterSuperDispatchDrawView extends LinearLayout {

    private Paint mPaint;
    private float mSmallRadius, mMiddleRadius, mLargeRadius;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;

    public CanvasDrawAfterSuperDispatchDrawView(Context context) {
        this(context, null);
    }

    public CanvasDrawAfterSuperDispatchDrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawAfterSuperDispatchDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.orange_400));

        mSmallRadius = getResources().getDimension(R.dimen.padding_small);
        mMiddleRadius = getResources().getDimension(R.dimen.padding_medium);
        mLargeRadius = getResources().getDimension(R.dimen.padding_large);
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
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawCircle(mCenterX - mCenterX/2, mCenterY, mSmallRadius, mPaint);

        canvas.drawCircle(mCenterX, mCenterY, mMiddleRadius, mPaint);

        canvas.drawCircle(mCenterX + mCenterX/2, mCenterY, mLargeRadius, mPaint);
    }

}
