package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_09_unit_05_practice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
 * Theory: Submit Button 共有两个状态：ACTION_DOWN，ACTION_UP
 *
 *         ACTION_DOWN
 *
 *         当 ACTION_DOWN 时，可分为两个阶段。在第一阶段，圆角矩形内部填充色由白色变为绿色，圆角矩形内部文字字
 *         体颜色由绿色变为白色，此二者同时进行。在第二阶段，即圆角矩形内部文字颜色由绿色变为白色之后，文字尺寸由
 *         大变小再变大。至此，ACTION_DOWN 要做的所有事到此结束。
 *
 *              ACTION_DOWN
 *                  - 第一阶段
 *                      - 圆角矩形内部填充色由白色变为绿色，圆角矩形内部文字字体颜色由绿色变为白色；
 *                  - 第二阶段
 *                      - 文字尺寸由大变小再变大；
 *
 *
 *         ACTION_UP
 *
 *         当 ACTION_UP 时，可分为三个阶段。在第一阶段，圆角矩形边框颜色由绿色变为灰色、宽度缩小至与高度相等，
 *         圆角矩形内部填充色由绿色变为白色、宽度缩小至与高度相等，圆角矩形内部文字字体颜色由白色变为透明，此三者
 *         同时进行。在第二阶段，即圆角矩形内部文字字体颜色由白色变为透明之后，与圆角矩形缩小宽度之后形成的圆相同
 *         的圆开始由 -90° 向 360° 绘制。与圆角矩形缩小宽度之后形成的圆相同的圆绘制完之后，颜色立即绿色变为透明，
 *         与此同时，圆角矩形边框颜色由灰色变为绿色。在第三阶段，即圆角矩形边框颜色由灰色变为绿色之后，圆角矩形边
 *         框宽度恢复至初始状态，圆角矩形内部填充色由白色变为绿色、宽度恢复至初始状态，对号从中间的拐点开始向两端
 *         绘制，此三者同时进行。至此，ACTION_UP 要做的所有事到此结束，整个 Submit Button 要处理的动画也到此
 *         结束。
 *
 *              ACTION_UP
 *                  - 第一阶段
 *                      - 圆角矩形边框颜色由绿色变为灰色、宽度缩小至与高度相等，圆角矩形内部填充色由绿色变为白色、
 *                        宽度缩小与高度相等，圆角矩形内部文字字体颜色由白色变为透明；
 *                  - 第二阶段
 *                      - 与圆角矩形缩小宽度之后形成的圆相同的圆开始由 -90° 向 360° 绘制。与圆角矩形缩小
 *                        宽度之后形成的圆相同的圆绘制完之后，颜色立即绿色变为透明，与此同时，圆角矩形边框颜色
 *                        由灰色变为绿色；
 *                  - 第三阶段
 *                      - 圆角矩形边框宽度恢复至初始状态，圆角矩形内部填充色由白色变为绿色、宽度恢复至初始状态，
 *                        对号从中间的拐点开始向两端绘制；
 *
 * Version: V 7.0
 *
 * State: FINISHED
 *
 * Optimize: N
 *
 * Time: 2020/6/10 7:14 AM
 */
public class CanvasDynamicRoundRectView7 extends View {

    //各类 Paint：圆角矩形，圆角矩形边框，圆角矩形中间文字，进度条，对号
    private Paint mRectPaint, mRectFramePaint, mRectTextPaint, mCircleProgressPaint, mRightSignalPaint;

    //View 尺寸，中心
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;

    /***********************************************************************************************
     *                                                                                             *
     *                                                                                             *
     *                                                                                             *
     *                                          ACTION_DOWN                                        *
     *                                                                                             *
     *                                                                                             *
     *                                                                                             *
     *=============================================================================================*/
    //圆角矩形背景
    private float mRectWidth, mRectHeight, mRectLeft, mRectTop, mRectRight, mRectBottom, mRectRadius;
    private int mRectStartColor, mRectCurrentColor, mRectEndColor;
    //圆角矩形背景填充色处理动画（由白变绿——正向）
    private ValueAnimator mRectColorAnimator;

    //圆角矩形中间文字
    private String mRectText;
    private Rect mRectTextRect;
    private int mRectTextStartColor, mRectTextCurrentColor, mRectTextEndColor;
    //文字字体颜色处理动画（由绿变白——正向）
    private ValueAnimator mRectTextColorAnimator;
    private float mRectTextStartTextSize, mRectTextCurrentTextSize, mRectTextEndTextSize;
    //文字尺寸处理动画
    private ValueAnimator mRectTextSizeAnimator;

    //mActionDownFirstPhaseAnimatorSet：ACTION_DOWN 第一阶段处理动画
    //mActionDownSecondPhaseAnimatorSet：ACTION_DOWN 第一阶段和第二阶段总处理动画
    private AnimatorSet mActionDownFirstPhaseAnimatorSet, mActionDownSecondPhaseAnimatorSet;

    /***********************************************************************************************
     *                                                                                             *
     *                                                                                             *
     *                                                                                             *
     *                                            ACTION_UP                                        *
     *                                                                                             *
     *                                                                                             *
     *                                                                                             *
     *=============================================================================================*/
    //圆角矩形背景填充色处理动画（由绿变白——反向）
    private int mRectReverseStartColor, mRectReverseEndColor;
    private ValueAnimator mRectColorReverseAnimator;

    //圆角矩形边框
    private float mRectFrameStrokeWidth;
    private int mRectFrameStartColor, mRectFrameCurrentColor, mRectFrameEndColor;
    //圆角矩形边框颜色处理动画
    private ValueAnimator mRectFrameColorAnimator;

    //文字字体颜色处理动画（由白变透明——反向）
    private int mRectTextReverseStartColor, mRectTextReverseEndColor;
    private ValueAnimator mRectTextColorReverseAnimator;

    //圆角矩形本身和边框宽度缩小处理动画（缩小）
    private float mRectWidthDecreaseStartDeltaX, mRectWidthCurrentDeltaX, mRectWidthDecreaseEndDeltaX;
    private ValueAnimator mRectWidthDecreaseAnimator;

    //ACTION_UP 第一阶段 处理动画
    private AnimatorSet mActionUpFirstPhaseAnimatorSet;

    //与圆角矩形缩小宽度之后形成的圆相同的圆绘制处理动画；
    //ACTION_UP 第二阶段 处理动画，因为 ACTION_UP 第二阶段只有绘制圆弧进度的动画，所以没有所谓的
    //「ACTION_UP Second Phrase Animator」
    private int mCircleProgressDecreaseColor, mCircleProgressCurrentColor, mCircleProgressIncreaseColor;
    private RectF mCircleProgressRect;
    private float mCircleProgressStartValue, mCircleProgressCurrentValue, mCircleProgressEndValue;
    private ValueAnimator mCircleProgressAnimator;

    //圆角矩形本身和边框宽度恢复至初始状态处理动画（放大）
    private float mRectWidthIncreaseStartDeltaX, mRectWidthIncreaseEndDeltaX;
    private ValueAnimator mRectWidthIncreaseAnimator;

    //绘制对号处理动画
    private float mRightSignalStrokeWidth;
    private Path mRightSignalLeftPath, mRightSignalRightPath;
    private float mRightSignalLeftPathStartWidth, mRightSignalLeftPathCurrentWidth, mRightSignalLeftPathEndWidth;
    private float mRightSignalRightPathStartWidth, mRightSignalRightPathCurrentWidth, mRightSignalRightPathEndWidth;
    private int mRightSignalPathStartColor, mRightSignalPathCurrentColor, mRightSignalPathEndColor;
    private float mRightSignalVerticalDeltaY;
    //mRightSignalLeftPathAnimator：对号左半边处理动画
    //mRightSignalRightPathAnimator：对号右半边处理动画
    //mRightSignalPathColorAnimator：对号颜色处理动画
    private ValueAnimator mRightSignalLeftPathAnimator, mRightSignalRightPathAnimator, mRightSignalPathColorAnimator;

    //ACTION_UP 第三阶段 处理动画，仅仅是 ACTION_UP 第三阶段
    private AnimatorSet mActionUpThirdPhraseAnimatorSet;

    //ACTION_UP 第一阶段、第二阶段和第三阶段总处理动画
    private AnimatorSet mActionUpFinalAnimatorSet;

    //优化用户体验：根据按下的时间长短，执行不同的动画逻辑
    //      - 如果按下、抬起的时间差大于 400 ms，执行现在的逻辑；
    //      - 如果按下、抬起的时间差小于 400 ms，执行另外的逻辑；
    private long mActionDownTime, mActionUpTime, mActionDownUpIntervalTime, mActionDownUpStandardIntervalTime;
    private boolean mIsNeedAutoPlayAnimation;

    //性能优化：
    //      - 只有 ACTION_DOWN 的时候，才绘制圆角矩形中间文字；
    //      - 只有 ACTION_UP 的时候，才绘制进度条，对号；
    private boolean mIsActionDown, mIsActionUp;

    public CanvasDynamicRoundRectView7(Context context) {
        this(context, null);
    }

    public CanvasDynamicRoundRectView7(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDynamicRoundRectView7(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mRectFramePaint.setColor(getResources().getColor(R.color.green_500));
        mRectFrameStrokeWidth = getResources().getDimension(R.dimen.padding_micro);
        mRectFramePaint.setStrokeWidth(mRectFrameStrokeWidth);

        mRectTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectTextPaint.setStyle(Paint.Style.FILL);
        mRectTextPaint.setColor(getResources().getColor(R.color.green_500));
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
        mRectEndColor = getResources().getColor(R.color.green_500);

        mRectFrameStartColor = getResources().getColor(R.color.green_500);
        mRectFrameCurrentColor = mRectFrameStartColor;
        mRectFrameEndColor = getResources().getColor(R.color.grey_400);

        mRectText = getResources().getString(R.string.submit);
        mRectTextRect = new Rect();

        mRectTextStartColor = getResources().getColor(R.color.green_500);
        mRectTextCurrentColor = mRectTextStartColor;
        mRectTextEndColor = getResources().getColor(R.color.white);
        mRectTextStartTextSize = getResources().getDimension(R.dimen.font_large);
        mRectTextCurrentTextSize = mRectTextStartTextSize;
        mRectTextEndTextSize = getResources().getDimension(R.dimen.font_small);

        mRectReverseStartColor = mRectEndColor;
        mRectReverseEndColor = mRectStartColor;

        mRectTextReverseStartColor = mRectTextEndColor;
        mRectTextReverseEndColor = getResources().getColor(R.color.transparent);

        mRectWidthDecreaseStartDeltaX = getResources().getDimension(R.dimen.padding_zero);
        mRectWidthCurrentDeltaX = mRectWidthDecreaseStartDeltaX;
        mRectWidthDecreaseEndDeltaX = mRectWidth/2 - mRectRadius;

        mCircleProgressDecreaseColor = getResources().getColor(R.color.green_500);
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

        mActionDownUpStandardIntervalTime = getResources().getInteger(R.integer.four_hundred);
        mIsNeedAutoPlayAnimation = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //View 背景
        canvas.drawColor(getResources().getColor(R.color.white));

        //圆角矩形
        mRectPaint.setColor(mRectCurrentColor);
        canvas.drawRoundRect(mRectLeft + mRectWidthCurrentDeltaX, mRectTop, mRectRight - mRectWidthCurrentDeltaX, mRectBottom, mRectRadius, mRectRadius, mRectPaint);

        //圆角矩形边框
        mRectFramePaint.setColor(mRectFrameCurrentColor);
        canvas.drawRoundRect(mRectLeft + mRectWidthCurrentDeltaX, mRectTop, mRectRight - mRectWidthCurrentDeltaX, mRectBottom, mRectRadius, mRectRadius, mRectFramePaint);

        //性能优化：只有 ACTION_DOWN 的时候，才绘制圆角矩形中间文字
        if(mIsActionDown){
            //圆角矩形中间文字
            mRectTextPaint.setColor(mRectTextCurrentColor);
            mRectTextPaint.setTextSize(mRectTextCurrentTextSize);
            mRectTextPaint.getTextBounds(mRectText, 0, mRectText.length(), mRectTextRect);
            canvas.drawText(mRectText, mCenterX, mCenterY + mRectTextRect.height()/2, mRectTextPaint);
        }

        //性能优化：只有 ACTION_UP 的时候，才绘制进度条，对号
        if (mIsActionUp){
            //进度条
            mCircleProgressPaint.setColor(mCircleProgressCurrentColor);
            canvas.drawArc(mCircleProgressRect, mCircleProgressStartValue - 90, mCircleProgressCurrentValue, false, mCircleProgressPaint);

            //对号
            mRightSignalPaint.setColor(mRightSignalPathCurrentColor);
            mRightSignalLeftPath.moveTo(mCenterX, mCenterY + mRightSignalVerticalDeltaY);
            mRightSignalLeftPath.lineTo(mCenterX - mRightSignalLeftPathCurrentWidth, mCenterY - mRightSignalLeftPathCurrentWidth  + mRightSignalVerticalDeltaY);
            canvas.drawPath(mRightSignalLeftPath, mRightSignalPaint);
            mRightSignalRightPath.moveTo(mCenterX, mCenterY + mRightSignalVerticalDeltaY);
            mRightSignalRightPath.lineTo(mCenterX + mRightSignalRightPathCurrentWidth, (mCenterY - mRightSignalRightPathCurrentWidth * 4/3  + mRightSignalVerticalDeltaY));
            canvas.drawPath(mRightSignalRightPath, mRightSignalPaint);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mActionDownTime = System.currentTimeMillis();
                mIsActionDown = true;
                mIsActionUp = false;
                Log.e(Constants.TAG,"MotionEvent.ACTION_DOWN  mActionDownTime  " + mActionDownTime);
                startActionDownAnimator();
                break;
            case MotionEvent.ACTION_UP:
                mActionUpTime = System.currentTimeMillis();
                mActionDownUpIntervalTime = mActionUpTime - mActionDownTime;
                mIsActionDown = false;
                mIsActionUp = true;
                Log.e(Constants.TAG,"MotionEvent.ACTION_UP  mActionUpTime  " + mActionDownTime + "  mActionUpTime  " + mActionUpTime + "  mActionDownUpIntervalTime  " + mActionDownUpIntervalTime);
                if(mActionDownUpIntervalTime < mActionDownUpStandardIntervalTime){
                    mIsNeedAutoPlayAnimation = true;
                }else{
                    mIsNeedAutoPlayAnimation = false;
                    startActionUpAnimator();
                }
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
                    //性能优化：删除不必要的 invalidate()
                    //去掉原因：mRectColorAnimator 和 mRectTextColorAnimator 同时执行，只用保留一个
                    //invalidate() 即可实现界面刷新，故删除
//                    invalidate();
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

        if(mActionDownFirstPhaseAnimatorSet == null){
            mActionDownFirstPhaseAnimatorSet = new AnimatorSet();
            mActionDownFirstPhaseAnimatorSet.playTogether(mRectColorAnimator, mRectTextColorAnimator);
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

        if(mActionDownSecondPhaseAnimatorSet == null){
            mActionDownSecondPhaseAnimatorSet = new AnimatorSet();
            mActionDownSecondPhaseAnimatorSet.playSequentially(mActionDownFirstPhaseAnimatorSet, mRectTextSizeAnimator);
            mActionDownSecondPhaseAnimatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if(mIsNeedAutoPlayAnimation){
                        startActionUpAnimator();
                    }
                }
            });
            mActionDownSecondPhaseAnimatorSet.start();
        }

//        else if(mActionDownSecondPhaseAnimatorSet.isPaused()){
//            mActionDownSecondPhaseAnimatorSet.resume();
//        }else if(mActionDownSecondPhaseAnimatorSet.isRunning()){
//            mActionDownSecondPhaseAnimatorSet.pause();
//        }else if(!mActionDownSecondPhaseAnimatorSet.isRunning()){
//            mActionDownSecondPhaseAnimatorSet.start();
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
                    //性能优化：删除不必要的 invalidate()
                    //去掉原因：mRectColorReverseAnimator、mRectFrameColorAnimator、
                    //mRectTextColorReverseAnimator 和 mRectWidthDecreaseAnimator 同时执行，只用保留一个
                    //invalidate() 即可实现界面刷新，故删除
//                    invalidate();
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
                    //性能优化：删除不必要的 invalidate()
                    //去掉原因：同上
//                    invalidate();
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
                    //性能优化：删除不必要的 invalidate()
                    //去掉原因：同上
//                    invalidate();
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
                    mRectWidthCurrentDeltaX = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mActionUpFirstPhaseAnimatorSet == null){
            mActionUpFirstPhaseAnimatorSet = new AnimatorSet();
            mActionUpFirstPhaseAnimatorSet.playTogether(mRectColorReverseAnimator, mRectFrameColorAnimator, mRectTextColorReverseAnimator, mRectWidthDecreaseAnimator);
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
                    //性能优化：删除不必要的 invalidate()
                    //去掉原因：mRightSignalLeftPathAnimator、mRightSignalRightPathAnimator、
                    //mRightSignalPathColorAnimator、mRectColorAnimator 和 mRectWidthIncreaseAnimator
                    //同时执行，只用保留一个 invalidate() 即可实现界面刷新，故删除
//                    invalidate();
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
                    //性能优化：删除不必要的 invalidate()
                    //去掉原因：同上
//                    invalidate();
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
                    //性能优化：删除不必要的 invalidate()
                    //去掉原因：同上
//                    invalidate();
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
                    mRectWidthCurrentDeltaX = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
        }

        if(mActionUpThirdPhraseAnimatorSet == null){
            mActionUpThirdPhraseAnimatorSet = new AnimatorSet();
            mActionUpThirdPhraseAnimatorSet.playTogether(mRightSignalLeftPathAnimator, mRightSignalRightPathAnimator, mRightSignalPathColorAnimator, mRectColorAnimator, mRectWidthIncreaseAnimator);
        }

        if(mActionUpFinalAnimatorSet == null){
            mActionUpFinalAnimatorSet = new AnimatorSet();
            mActionUpFinalAnimatorSet.playSequentially(mActionUpFirstPhaseAnimatorSet, mCircleProgressAnimator, mActionUpThirdPhraseAnimatorSet);
            mActionUpFinalAnimatorSet.start();
        }

        //重新播放
//        else if(mActionUpFinalAnimatorSet.isPaused()){
//            mActionUpFinalAnimatorSet.resume();
//        }else if(mActionUpFinalAnimatorSet.isRunning()){
//            mActionUpFinalAnimatorSet.pause();
//        }else if(!mActionUpFinalAnimatorSet.isRunning()){
//            mActionUpFinalAnimatorSet.start();
//        }

    }

}
