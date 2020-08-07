package com.smart.myapplication2020customview.staticview.view_02_paint.view_13_get_path;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

/**
 * FileName: PaintGetFillPathView
 *
 * Des: getTextPath & animation
 *
 * 通过 PathMeasure 绘制文字
 *
 * Time: 2020/6/2 5:34 PM
 */
public class PaintGetTextPathView2 extends android.view.View implements View.OnClickListener {

    private Paint mSrcPaint, mDstPaint;
    private float mSrcTextSize, mDstStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mDeltaX, mDeltaY;
    private String mContent;
    private Rect mContentRect;
    private Path mDstPath;
    private PathMeasure mPathMeasure;
    private float mLength;
    private float mStartD, mStopD;
    private float mAnimatedValue;
    private ValueAnimator mValueAnimator;

    public PaintGetTextPathView2(Context context) {
        this(context, null);
    }

    public PaintGetTextPathView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintGetTextPathView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setStyle(Paint.Style.FILL);
        mSrcTextSize = getResources().getDimension(R.dimen.font_thirty_two);
        mSrcPaint.setTextSize(mSrcTextSize);
        mSrcPaint.setTextAlign(Paint.Align.CENTER);

        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setStyle(Paint.Style.STROKE);
        mDstStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mDstPaint.setStrokeWidth(mDstStrokeWidth);

        mContent = getResources().getString(R.string.calorie_content);
        mContentRect = new Rect();
        mSrcPaint.getTextBounds(mContent, 0, mContent.length(), mContentRect);

        mStartD = 0;
        mStopD = 0;
        mAnimatedValue = 0;

        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mDeltaX = (mWidth / 2) / 2;
        mDeltaY = (mHeight / 2) / 2;

        mDstPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(Constants.TAG, "onDraw");

        //Src
        canvas.save();
        canvas.translate(0, - mDeltaY);
        canvas.drawText(mContent, mCenterX, mCenterY, mSrcPaint);
        canvas.restore();

        //Dst
        canvas.save();
        canvas.translate(mCenterX, (mCenterY + mDeltaY));
        canvas.drawPath(mDstPath, mDstPaint);
        canvas.restore();
    }

    @Override
    public void onClick(View v) {
        mSrcPaint.getTextPath(mContent, 0, mContent.length(), 0, 0, mDstPath);
        if(mPathMeasure == null){
            mPathMeasure = new PathMeasure(mDstPath, false);
        }else{
            mPathMeasure.setPath(mDstPath, false);
        }
        mDstPath.reset();
        animateIndicator();
    }

    private void animateIndicator(){
        Log.e(Constants.TAG, "animateIndicator");

        mLength = mPathMeasure.getLength();
        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.setInterpolator(new LinearInterpolator());
//        mValueAnimator.setDuration(getResources().getInteger(R.integer.number_four));
//        mValueAnimator.setDuration(getResources().getInteger(R.integer.number_ninety_six));
        mValueAnimator.setDuration(getResources().getInteger(R.integer.two_hundred_fifty_five));
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatedValue = (Float) animation.getAnimatedValue();
                mStopD = mAnimatedValue * mLength;
                mPathMeasure.getSegment(mStartD, mStopD, mDstPath, true);
                invalidate();

            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                if(mPathMeasure.nextContour()){
                    animateIndicator();
                }else{
                    Log.e(Constants.TAG, "绘制结束");
                }
            }
        });
        mValueAnimator.start();
    }

}


































































































































