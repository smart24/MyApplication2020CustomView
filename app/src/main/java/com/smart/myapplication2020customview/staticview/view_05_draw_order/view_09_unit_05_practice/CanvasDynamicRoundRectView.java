package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_09_unit_05_practice;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasDynamicRoundRectView
 *
 * Des: Canvas Dynamic Round Rect View
 *
 *      主要为了实现第一种 SubmitButton 效果（03_submit_button.gif ）做铺垫：
 *
 *      1. 画对号；✔︎
 *
 *      2. 画圆角矩形；👈
 *
 *      3. 画 SubmitButton；
 *
 * Version: V 1.0
 *
 * State: 完成圆角矩形到圆的形状变换更能。
 *
 * Optimize:
 *
 * Time: 2020/6/10 7:14 AM
 */
public class CanvasDynamicRoundRectView extends android.view.View implements View.OnClickListener {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mRectWidth, mRectHeight, mRectLeft, mRectTop, mRectRight, mRectBottom, mRectRadius;
    private float mRectStartDeltaX, mRectCurrentDeltaX, mRectEndDeltaX;
    private ValueAnimator mValueAnimator;
    private AnimatorSet mAnimatorSet;

    public CanvasDynamicRoundRectView(Context context) {
        this(context, null);
    }

    public CanvasDynamicRoundRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDynamicRoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(getResources().getColor(R.color.green_400));
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro);
        mPaint.setStrokeWidth(mStrokeWidth);

        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth/2;
        mCenterY = mHeight/2;

        mRectWidth = mWidth * 3/5;
        mRectHeight = getResources().getDimension(R.dimen.padding_forty_two);

        mRectLeft = mCenterX - mRectWidth/2;
        mRectTop = mCenterY - mRectHeight/2;
        mRectRight = mCenterX + mRectWidth/2;
        mRectBottom = mCenterY + mRectHeight/2;

        mRectRadius = mRectHeight/2;

        mRectStartDeltaX = getResources().getDimension(R.dimen.padding_zero);
        mRectCurrentDeltaX = mRectStartDeltaX;
        mRectEndDeltaX = mRectWidth/2 - mRectRadius;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.white));

        canvas.drawRoundRect(mRectLeft + mRectCurrentDeltaX, mRectTop, mRectRight - mRectCurrentDeltaX, mRectBottom, mRectRadius, mRectRadius, mPaint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        initAnimator();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initAnimator(){
        if(mValueAnimator == null){
            mValueAnimator = ValueAnimator.ofFloat(mRectStartDeltaX, mRectEndDeltaX);
            mValueAnimator.setDuration(getResources().getInteger(R.integer.four_hundred));
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRectCurrentDeltaX = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mAnimatorSet == null){
            mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playTogether(mValueAnimator);
            mAnimatorSet.start();
        }else if(mAnimatorSet.isPaused()){
            mAnimatorSet.resume();
        }else if(mAnimatorSet.isRunning()){
            mAnimatorSet.pause();
        }else if(!mAnimatorSet.isRunning()){
            mAnimatorSet.start();
        }
    }

}
