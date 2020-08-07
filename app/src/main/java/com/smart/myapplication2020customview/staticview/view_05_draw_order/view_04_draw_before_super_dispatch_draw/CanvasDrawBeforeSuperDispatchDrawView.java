package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_04_draw_before_super_dispatch_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasDrawBeforeSuperDispatchDrawView
 *
 * Des: Canvas draw before super.dispatchDraw()
 *
 *      把绘制代码写在 super.dispatchDraw() 的上面，这段绘制就会在 onDraw() 之后、 super.dispatchDraw() 之
 *      前发生，也就是绘制内容会出现在主体内容和子 View 之间。而这个……
 *
 *      其实和重写 onDraw() 并把绘制代码写在 super.onDraw() 之后的做法，效果是一样的。
 *
 * Version:
 *
 * State:
 *
 * Optimize:
 *
 * Time: 2020/6/9 2:20 PM
 */
public class CanvasDrawBeforeSuperDispatchDrawView extends LinearLayout {

    private Paint mPaint;
    private float mSmallRadius, mMiddleRadius, mLargeRadius;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;

    public CanvasDrawBeforeSuperDispatchDrawView(Context context) {
        this(context, null);
    }

    public CanvasDrawBeforeSuperDispatchDrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawBeforeSuperDispatchDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawCircle(mCenterX - mCenterX/2, mCenterY, mSmallRadius, mPaint);

        canvas.drawCircle(mCenterX, mCenterY, mMiddleRadius, mPaint);

        canvas.drawCircle(mCenterX + mCenterX/2, mCenterY, mLargeRadius, mPaint);

        super.dispatchDraw(canvas);
    }

}
