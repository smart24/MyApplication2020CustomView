package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_09_unit_05_practice;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

/**
 * FileName: CanvasDynamicRoundRectView
 *
 * Des: Canvas Dynamic Round Rect View
 *
 *      主要为了实现第一种 SubmitButton 效果（03_submit_button.gif ）做铺垫：
 *
 *      1. 画对号；✔︎
 *
 *      2. 画圆角矩形；✔
 *
 *      3. 画 SubmitButton；👈
 *
 * Version: V 2.0
 *
 * State:
 *
 *      1. 完成圆角矩形到圆的形状变换更能；
 *
 *      2. 将圆角矩形分为两部分：背景和框架；
 *
 *      3. 动态改变圆角矩形的背景和框架的颜色；
 *
 *      4. 在灰色圆圈上，绘制进度条；进度条绘制成功之后，改变 Submit Button 尺寸，改变矩形背景颜色，绘制对号；
 *
 *      TO DO
 *          - 点击时间过短时，按下的动画不完整播放——根据按下的时间长短，执行不同的动画逻辑；
 *              - 如果按下、抬起的时间差大于 400 ms，执行现在的逻辑；
 *              - 如果按下、抬起的时间差小于 400 ms，执行另外的逻辑；
 *
 *          - 优化；
 *
 * Optimize:
 *
 * Time: 2020/6/10 7:14 AM
 */
public class CanvasDynamicRoundRectView4 extends View {

    private Paint mRectPaint, mRectFramePaint, mRectTextPaint, mCircleProgressPaint, mRightSignalPaint;

    private float mWidth, mHeight;
    private float mCenterX, mCenterY;

    //圆角矩形背景
    private float mRectWidth, mRectHeight, mRectLeft, mRectTop, mRectRight, mRectBottom, mRectRadius;
    private int mRectStartColor, mRectCurrentColor, mRectEndColor;
    private ValueAnimator mRectColorAnimator;

    //圆角矩形边框
    private float mRectFrameStrokeWidth;
    private int mRectFrameStartColor, mRectFrameCurrentColor, mRectFrameEndColor;
    private ValueAnimator mRectFrameColorAnimator;

    //圆角矩形中间文字
    private String mRectText;
    private Rect mRectTextRect;
    private int mRectTextStartColor, mRectTextCurrentColor, mRectTextEndColor;
    private ValueAnimator mRectTextColorAnimator;
    private float mRectTextStartTextSize, mRectTextCurrentTextSize, mRectTextEndTextSize;
    private ValueAnimator mRectTextSizeAnimator;

    private AnimatorSet mActionDownColorAnimatorSet, mActionDownAnimatorSet;

    private int mRectReverseStartColor, mRectReverseEndColor;
    private ValueAnimator mRectColorReverseAnimator;

    private int mRectTextReverseStartColor, mRectTextReverseEndColor;
    private ValueAnimator mRectTextColorReverseAnimator;

    private float mRectWidthDecreaseStartDeltaX, mRectWidthDecreaseCurrentDeltaX, mRectWidthDecreaseEndDeltaX;
    private ValueAnimator mRectWidthDecreaseAnimator;

    private AnimatorSet mActionUpWidthAnimatorSet;

    private int mCircleProgressDecreaseColor, mCircleProgressCurrentColor, mCircleProgressIncreaseColor;
    private RectF mCircleProgressRect;
    private float mCircleProgressStartValue, mCircleProgressCurrentValue, mCircleProgressEndValue;
    private ValueAnimator mCircleProgressAnimator;

    private AnimatorSet mActionUpProgressAnimatorSet;

    private float mRectWidthIncreaseStartDeltaX, mRectWidthIncreaseEndDeltaX;
    private ValueAnimator mRectWidthIncreaseAnimator;

    private float mRightSignalStrokeWidth;
    private Path mRightSignalLeftPath, mRightSignalRightPath;
    private float mRightSignalLeftPathStartWidth, mRightSignalLeftPathCurrentWidth, mRightSignalLeftPathEndWidth;
    private float mRightSignalRightPathStartWidth, mRightSignalRightPathCurrentWidth, mRightSignalRightPathEndWidth;
    private int mRightSignalPathStartColor, mRightSignalPathCurrentColor, mRightSignalPathEndColor;
    private float mRightSignalVerticalDeltaY;
    private ValueAnimator mRightSignalLeftPathAnimator, mRightSignalRightPathAnimator, mRightSignalPathColorAnimator;

    private AnimatorSet mActionUpIncreaseAnimatorSet;

    public CanvasDynamicRoundRectView4(Context context) {
        this(context, null);
    }

    public CanvasDynamicRoundRectView4(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDynamicRoundRectView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setStrokeCap(Paint.Cap.ROUND);
        mRectPaint.setColor(getResources().getColor(R.color.white));

        mRectFramePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectFramePaint.setStyle(Paint.Style.STROKE);
        mRectFramePaint.setStrokeCap(Paint.Cap.ROUND);
        mRectFramePaint.setColor(getResources().getColor(R.color.green_400));
        mRectFrameStrokeWidth = getResources().getDimension(R.dimen.padding_micro);
        mRectFramePaint.setStrokeWidth(mRectFrameStrokeWidth);

        mRectTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectTextPaint.setStyle(Paint.Style.FILL);
        mRectTextPaint.setColor(getResources().getColor(R.color.green_400));
        mRectTextPaint.setTextAlign(Paint.Align.CENTER);
        mRectTextPaint.setFakeBoldText(true);

        mCircleProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleProgressPaint.setStyle(Paint.Style.STROKE);
        mCircleProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mRectFrameStrokeWidth = getResources().getDimension(R.dimen.padding_micro);
        mCircleProgressPaint.setStrokeWidth(mRectFrameStrokeWidth);

        mRightSignalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRightSignalPaint.setStyle(Paint.Style.STROKE);
        mRightSignalPaint.setStrokeCap(Paint.Cap.ROUND);
        mRightSignalStrokeWidth = getResources().getDimension(R.dimen.padding_micro);
        mRightSignalPaint.setStrokeWidth(mRightSignalStrokeWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth/2;
        mCenterY = mHeight/2;

        mRectWidth = mWidth * 3/5;
        mRectHeight = getResources().getDimension(R.dimen.padding_fifty_six);

        mRectLeft = mCenterX - mRectWidth/2;
        mRectTop = mCenterY - mRectHeight/2;
        mRectRight = mCenterX + mRectWidth/2;
        mRectBottom = mCenterY + mRectHeight/2;

        mRectRadius = mRectHeight/2;

        mRectStartColor = getResources().getColor(R.color.white);
        mRectCurrentColor = mRectStartColor;
        mRectEndColor = getResources().getColor(R.color.green_400);

        mRectFrameStartColor = getResources().getColor(R.color.green_400);
        mRectFrameCurrentColor = mRectFrameStartColor;
        mRectFrameEndColor = getResources().getColor(R.color.grey_400);

        mRectText = getResources().getString(R.string.submit);
        mRectTextRect = new Rect();

        mRectTextStartColor = getResources().getColor(R.color.green_400);
        mRectTextCurrentColor = mRectTextStartColor;
        mRectTextEndColor = getResources().getColor(R.color.white);
        mRectTextStartTextSize = getResources().getDimension(R.dimen.font_medium);
        mRectTextCurrentTextSize = mRectTextStartTextSize;
        mRectTextEndTextSize = getResources().getDimension(R.dimen.font_micro);

        mRectReverseStartColor = mRectEndColor;
        mRectReverseEndColor = mRectStartColor;

        mRectTextReverseStartColor = mRectTextEndColor;
        mRectTextReverseEndColor = getResources().getColor(R.color.transparent);

        mRectWidthDecreaseStartDeltaX = getResources().getDimension(R.dimen.padding_zero);
        mRectWidthDecreaseCurrentDeltaX = mRectWidthDecreaseStartDeltaX;
        mRectWidthDecreaseEndDeltaX = mRectWidth/2 - mRectRadius;

        mCircleProgressDecreaseColor = getResources().getColor(R.color.green_400);
        mCircleProgressCurrentColor = mCircleProgressDecreaseColor;
        mCircleProgressIncreaseColor = getResources().getColor(R.color.transparent);
        mCircleProgressRect = new RectF((mCenterX - mRectRadius), (mCenterY - mRectRadius), (mCenterX + mRectRadius), (mCenterY + mRectRadius));
        mCircleProgressStartValue = getResources().getInteger(R.integer.number_zero);
        mCircleProgressCurrentValue = mCircleProgressStartValue;
        mCircleProgressEndValue = getResources().getInteger(R.integer.number_three_hundred_and_sixty);

        mRectWidthIncreaseStartDeltaX = mRectWidthDecreaseEndDeltaX;
        mRectWidthIncreaseEndDeltaX = mRectWidthDecreaseStartDeltaX;

        mRightSignalLeftPath = new Path();
        mRightSignalRightPath = new Path();

        mRightSignalLeftPathStartWidth = getResources().getDimension(R.dimen.padding_zero);
        mRightSignalLeftPathCurrentWidth = mRightSignalLeftPathStartWidth;
        mRightSignalLeftPathEndWidth = mRectHeight/3 * 3/4 * 3/5;

        mRightSignalRightPathStartWidth = getResources().getDimension(R.dimen.padding_zero);
        mRightSignalRightPathCurrentWidth = mRightSignalRightPathStartWidth;
        mRightSignalRightPathEndWidth = mRectHeight/3 * 3/4;
        mRightSignalVerticalDeltaY = mRightSignalRightPathEndWidth * 4/3/2; //纠偏

        mRightSignalPathStartColor = getResources().getColor(R.color.transparent);
        mRightSignalPathCurrentColor = mRightSignalPathStartColor;
        mRightSignalPathEndColor = getResources().getColor(R.color.white);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.white));

        mRectPaint.setColor(mRectCurrentColor);
        canvas.drawRoundRect(mRectLeft + mRectWidthDecreaseCurrentDeltaX, mRectTop, mRectRight - mRectWidthDecreaseCurrentDeltaX, mRectBottom, mRectRadius, mRectRadius, mRectPaint);

        mRectFramePaint.setColor(mRectFrameCurrentColor);
        canvas.drawRoundRect(mRectLeft + mRectWidthDecreaseCurrentDeltaX, mRectTop, mRectRight - mRectWidthDecreaseCurrentDeltaX, mRectBottom, mRectRadius, mRectRadius, mRectFramePaint);

        mRectTextPaint.setColor(mRectTextCurrentColor);
        mRectTextPaint.setTextSize(mRectTextCurrentTextSize);
        mRectTextPaint.getTextBounds(mRectText, 0, mRectText.length(), mRectTextRect);
        canvas.drawText(mRectText, mCenterX, mCenterY + mRectTextRect.height()/2, mRectTextPaint);

        mCircleProgressPaint.setColor(mCircleProgressCurrentColor);
        canvas.drawArc(mCircleProgressRect, mCircleProgressStartValue - 90, mCircleProgressCurrentValue, false, mCircleProgressPaint);

        mRightSignalPaint.setColor(mRightSignalPathCurrentColor);
        mRightSignalLeftPath.moveTo(mCenterX, mCenterY + mRightSignalVerticalDeltaY);
        mRightSignalLeftPath.lineTo(mCenterX - mRightSignalLeftPathCurrentWidth, mCenterY - mRightSignalLeftPathCurrentWidth  + mRightSignalVerticalDeltaY);
        canvas.drawPath(mRightSignalLeftPath, mRightSignalPaint);
        mRightSignalRightPath.moveTo(mCenterX, mCenterY + mRightSignalVerticalDeltaY);
        mRightSignalRightPath.lineTo(mCenterX + mRightSignalRightPathCurrentWidth, (mCenterY - mRightSignalRightPathCurrentWidth * 4/3  + mRightSignalVerticalDeltaY));
        canvas.drawPath(mRightSignalRightPath, mRightSignalPaint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(Constants.TAG,"MotionEvent.ACTION_DOWN");
                startActionDownAnimator();
                break;
            case MotionEvent.ACTION_UP:
                Log.e(Constants.TAG,"MotionEvent.ACTION_UP");
                startActionUpAnimator();
                break;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startActionDownAnimator(){
        if(mRectColorAnimator == null){
            mRectColorAnimator = ValueAnimator.ofArgb(mRectStartColor, mRectEndColor);
            mRectColorAnimator.setDuration(getResources().getInteger(R.integer.two_hundred));
            mRectColorAnimator.setInterpolator(new LinearInterpolator());
            mRectColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRectCurrentColor = (int)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mRectTextColorAnimator == null){
            mRectTextColorAnimator = ValueAnimator.ofArgb(mRectTextStartColor, mRectTextEndColor);
            mRectTextColorAnimator.setDuration(getResources().getInteger(R.integer.two_hundred));
            mRectTextColorAnimator.setInterpolator(new LinearInterpolator());
            mRectTextColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRectTextCurrentColor = (int)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mActionDownColorAnimatorSet == null){
            mActionDownColorAnimatorSet = new AnimatorSet();
            mActionDownColorAnimatorSet.playTogether(mRectColorAnimator, mRectTextColorAnimator);
        }

        if(mRectTextSizeAnimator == null){
            mRectTextSizeAnimator = ValueAnimator.ofFloat(mRectTextStartTextSize, mRectTextEndTextSize, mRectTextStartTextSize);
            mRectTextSizeAnimator.setDuration(getResources().getInteger(R.integer.two_hundred));
            mRectTextSizeAnimator.setInterpolator(new LinearInterpolator());
            mRectTextSizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRectTextCurrentTextSize = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mActionDownAnimatorSet == null){
            mActionDownAnimatorSet = new AnimatorSet();
            mActionDownAnimatorSet.playSequentially(mActionDownColorAnimatorSet, mRectTextSizeAnimator);
            mActionDownAnimatorSet.start();
        }

//        else if(mActionDownAnimatorSet.isPaused()){
//            mActionDownAnimatorSet.resume();
//        }else if(mActionDownAnimatorSet.isRunning()){
//            mActionDownAnimatorSet.pause();
//        }else if(!mActionDownAnimatorSet.isRunning()){
//            mActionDownAnimatorSet.start();
//        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startActionUpAnimator(){
        if(mRectColorReverseAnimator == null){
            mRectColorReverseAnimator = ValueAnimator.ofArgb(mRectReverseStartColor, mRectReverseEndColor);
            mRectColorReverseAnimator.setDuration(getResources().getInteger(R.integer.two_hundred));
            mRectColorReverseAnimator.setInterpolator(new LinearInterpolator());
            mRectColorReverseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRectCurrentColor = (int)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mRectFrameColorAnimator == null){
            mRectFrameColorAnimator = ValueAnimator.ofArgb(mRectFrameStartColor, mRectFrameEndColor);
            mRectFrameColorAnimator.setDuration(getResources().getInteger(R.integer.two_hundred));
            mRectFrameColorAnimator.setInterpolator(new LinearInterpolator());
            mRectFrameColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRectFrameCurrentColor = (int)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mRectTextColorReverseAnimator == null){
            mRectTextColorReverseAnimator = ValueAnimator.ofArgb(mRectTextReverseStartColor, mRectTextReverseEndColor);
            mRectTextColorReverseAnimator.setDuration(getResources().getInteger(R.integer.two_hundred));
            mRectTextColorReverseAnimator.setInterpolator(new LinearInterpolator());
            mRectTextColorReverseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRectTextCurrentColor = (int)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mRectWidthDecreaseAnimator == null){
            mRectWidthDecreaseAnimator = ValueAnimator.ofFloat(mRectWidthDecreaseStartDeltaX, mRectWidthDecreaseEndDeltaX);
            mRectWidthDecreaseAnimator.setDuration(getResources().getInteger(R.integer.two_hundred));
            mRectWidthDecreaseAnimator.setInterpolator(new LinearInterpolator());
            mRectWidthDecreaseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRectWidthDecreaseCurrentDeltaX = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mActionUpWidthAnimatorSet == null){
            mActionUpWidthAnimatorSet = new AnimatorSet();
            mActionUpWidthAnimatorSet.playTogether(mRectColorReverseAnimator, mRectFrameColorAnimator, mRectTextColorReverseAnimator, mRectWidthDecreaseAnimator);
        }

        if(mCircleProgressAnimator == null){
            mCircleProgressAnimator = ValueAnimator.ofFloat(mCircleProgressStartValue, mCircleProgressEndValue);
            mCircleProgressAnimator.setDuration(getResources().getInteger(R.integer.six_hundred));
            mCircleProgressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            mCircleProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCircleProgressCurrentValue = (float)animation.getAnimatedValue();
                    if(animation.getAnimatedFraction() == getResources().getInteger(R.integer.number_one)){
                        mRectFrameCurrentColor = mRectFrameStartColor;
                        mCircleProgressCurrentColor = mCircleProgressIncreaseColor;
                    }
                    invalidate();
                }
            });
        }

        if(mRightSignalLeftPathAnimator == null){
            mRightSignalLeftPathAnimator = ValueAnimator.ofFloat(mRightSignalLeftPathStartWidth, mRightSignalLeftPathEndWidth);
            mRightSignalLeftPathAnimator.setDuration(getResources().getInteger(R.integer.two_hundred));
            mRightSignalLeftPathAnimator.setInterpolator(new LinearInterpolator());
            mRightSignalLeftPathAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRightSignalLeftPathCurrentWidth = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mRightSignalRightPathAnimator == null){
            mRightSignalRightPathAnimator = ValueAnimator.ofFloat(mRightSignalRightPathStartWidth, mRightSignalRightPathEndWidth);
            mRightSignalRightPathAnimator.setDuration(getResources().getInteger(R.integer.two_hundred));
            mRightSignalRightPathAnimator.setInterpolator(new LinearInterpolator());
            mRightSignalRightPathAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRightSignalRightPathCurrentWidth = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mRightSignalPathColorAnimator == null){
            mRightSignalPathColorAnimator = ValueAnimator.ofArgb(mRightSignalPathStartColor, mRightSignalPathEndColor);
            mRightSignalPathColorAnimator.setDuration(getResources().getInteger(R.integer.number_one_hundred));
            mRightSignalPathColorAnimator.setInterpolator(new LinearInterpolator());
            mRightSignalPathColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRightSignalPathCurrentColor = (int)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mRectWidthIncreaseAnimator == null){
            mRectWidthIncreaseAnimator = ValueAnimator.ofFloat(mRectWidthIncreaseStartDeltaX, mRectWidthIncreaseEndDeltaX);
            mRectWidthIncreaseAnimator.setDuration(getResources().getInteger(R.integer.two_hundred));
            mRectWidthIncreaseAnimator.setInterpolator(new LinearInterpolator());
            mRectWidthIncreaseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRectWidthDecreaseCurrentDeltaX = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mActionUpIncreaseAnimatorSet == null){
            mActionUpIncreaseAnimatorSet = new AnimatorSet();
            mActionUpIncreaseAnimatorSet.playTogether(mRightSignalLeftPathAnimator, mRightSignalRightPathAnimator, mRightSignalPathColorAnimator, mRectColorAnimator, mRectWidthIncreaseAnimator);
        }

        if(mActionUpProgressAnimatorSet == null){
            mActionUpProgressAnimatorSet = new AnimatorSet();
            mActionUpProgressAnimatorSet.playSequentially(mActionUpWidthAnimatorSet, mCircleProgressAnimator, mActionUpIncreaseAnimatorSet);
            mActionUpProgressAnimatorSet.start();
        }

//        else if(mActionUpProgressAnimatorSet.isPaused()){
//            mActionUpProgressAnimatorSet.resume();
//        }else if(mActionUpProgressAnimatorSet.isRunning()){
//            mActionUpProgressAnimatorSet.pause();
//        }else if(!mActionUpProgressAnimatorSet.isRunning()){
//            mActionUpProgressAnimatorSet.start();
//        }

    }

}
