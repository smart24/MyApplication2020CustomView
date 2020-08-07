package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_09_unit_05_practice;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasRightView
 *
 * Des: Canvas Right(✔) View 画对号
 *
 *      主要为了实现第一种 SubmitButton 效果（03_submit_button.gif ）做铺垫。
 *
 *      1. 画对号；👈
 *
 *      2. 画圆角矩形；
 *
 *      3. 画 SubmitButton；
 *
 * Version:
 *
 * State:
 *
 * Optimize:
 *
 * Time: 2020/6/9 5:57 PM
 */
public class CanvasRightView extends android.view.View implements View.OnClickListener {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Path mLeftPath, mRightPath;
    private float mLeftPathStartWidth, mLeftPathCurrentWidth, mLeftPathEndWidth;
    private float mRightPathStartWidth, mRightPathCurrentWidth, mRightPathEndWidth;
    private int mPathStartColor, mPathCurrentColor, mPathEndColor;
    private ValueAnimator mLeftPathAnimator, mRightPathAnimator, mPathColorAnimator;
    private AnimatorSet mAnimatorSet;

    public CanvasRightView(Context context) {
        this(context, null);
    }

    public CanvasRightView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasRightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(getResources().getColor(R.color.white));
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro);
        mPaint.setStrokeWidth(mStrokeWidth);

        mLeftPath = new Path();
        mRightPath = new Path();

        mLeftPathStartWidth = getResources().getDimension(R.dimen.padding_zero);
        mLeftPathCurrentWidth = mLeftPathStartWidth;
        mLeftPathEndWidth = getResources().getDimension(R.dimen.padding_large);

        mRightPathStartWidth = getResources().getDimension(R.dimen.padding_zero);
        mRightPathCurrentWidth = mRightPathStartWidth;
        mRightPathEndWidth = getResources().getDimension(R.dimen.item_height);

        mPathStartColor = getResources().getColor(R.color.green_400);
        mPathCurrentColor = mPathStartColor;
        mPathEndColor = getResources().getColor(R.color.white);

        setOnClickListener(this);
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
        canvas.drawColor(getResources().getColor(R.color.green_400));

        mPaint.setColor(mPathCurrentColor);

        mLeftPath.moveTo(mCenterX, mCenterY);
        mLeftPath.lineTo(mCenterX - mLeftPathCurrentWidth, mCenterY - mLeftPathCurrentWidth);
        canvas.drawPath(mLeftPath, mPaint);

        mRightPath.moveTo(mCenterX, mCenterY);
        mRightPath.lineTo(mCenterX + mRightPathCurrentWidth, (mCenterY - mRightPathCurrentWidth * 4/3));
        canvas.drawPath(mRightPath, mPaint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        initAnimator();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initAnimator(){
        if(mLeftPathAnimator == null){
            mLeftPathAnimator = ValueAnimator.ofFloat(mLeftPathStartWidth, mLeftPathEndWidth);
            mLeftPathAnimator.setDuration(getResources().getInteger(R.integer.four_hundred));
            mLeftPathAnimator.setInterpolator(new LinearInterpolator());
            mLeftPathAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mLeftPathCurrentWidth = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mRightPathAnimator == null){
            mRightPathAnimator = ValueAnimator.ofFloat(mRightPathStartWidth, mRightPathEndWidth);
            mRightPathAnimator.setDuration(getResources().getInteger(R.integer.four_hundred));
            mRightPathAnimator.setInterpolator(new LinearInterpolator());
            mRightPathAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRightPathCurrentWidth = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mPathColorAnimator == null){
            mPathColorAnimator = ValueAnimator.ofArgb(mPathStartColor, mPathEndColor);
            mPathColorAnimator.setDuration(getResources().getInteger(R.integer.four_hundred));
            mPathColorAnimator.setInterpolator(new LinearInterpolator());
            mPathColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mPathCurrentColor = (int)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mAnimatorSet == null){
            mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playTogether(mLeftPathAnimator, mRightPathAnimator, mPathColorAnimator);
            mAnimatorSet.start();
        }else if(mAnimatorSet.isPaused()){
            mAnimatorSet.resume();
        }else if(mAnimatorSet.isRunning()){
            mAnimatorSet.pause();
        }else if(!mAnimatorSet.isRunning()){
            mAnimatorSet.start();
            resetPath();
        }
    }

    private void resetPath(){
        mLeftPath.reset();
        mRightPath.reset();
        mLeftPathCurrentWidth = mLeftPathStartWidth;
        mRightPathCurrentWidth = mRightPathStartWidth;
        mPathCurrentColor = mPathStartColor;
    }

}
