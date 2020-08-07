package com.smart.myapplication2020customview.staticview.view_04_canvas_assist.view_06_unit_04_pratice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasMintRulerView
 *
 * Des: 薄荷健康尺子效果
 *      - 问题拆解
 *          - 背景；
 *          - 标记线；
 *          - 刻度；
 *          - 动画；
 *          - 手势；
 * 
 * Version: V1.0
 * 
 * State: 
 * 
 * Optimize: 
 * 
 * Time: 2020/6/8 4:45 PM
 */
public class CanvasMintRulerView extends android.view.View {

    private Paint mValuePaint, mValueUnitPaint, mBackgroundPaint, mMarkerLinePaint, mScalePaint, mScaleValuePaint, mValueIndicatorPaint;
    private float mValueTextSize, mValueUnitTextSize;
    private float mStartValue, mCurrentValue, mEndValue;
    private String mValueString;
    private Rect mValueRect;
    private float mValuePositionX, mValuePositionY;
    private String mValueUnitString;
    private Rect mValueUnitRect;
    private float mValueUnitPositionX, mValueUnitPositionY;
    private float mRulerTopHeight, mRulerBottomHeight, mRulerHeight;
    private float mMarkerLineWidth;
    private float mScaleMiniUnitDistance;
    private float mScaleLongLineWidth, mScaleLongLineHeight, mScaleShortLineWidth, mScaleShortLineHeight;
    private float mScaleValueTextSize;
    private String mScaleValueString;
    private Rect mScaleValueRect;
    private float mScaleValuePositionX, mScaleValuePositionY;
    private float mValueIndicatorWidth;
    private float mValueIndicatorStartX, mValueIndicatorStartY, mValueIndicatorEndX, mValueIndicatorEndY;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;

    public CanvasMintRulerView(Context context) {
        this(context, null);
    }

    public CanvasMintRulerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasMintRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                           Paint 相关                                   //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
        mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mValuePaint.setStyle(Paint.Style.FILL);
        mValuePaint.setColor(getResources().getColor(R.color.green_500));
        mValueTextSize = getResources().getDimension(R.dimen.font_forty_eight);
        mValuePaint.setTextSize(mValueTextSize);
        mValuePaint.setTextAlign(Paint.Align.CENTER);

        mValueUnitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mValueUnitPaint.setStyle(Paint.Style.FILL);
        mValueUnitPaint.setColor(getResources().getColor(R.color.green_500));
        mValueUnitTextSize = getResources().getDimension(R.dimen.font_medium);
        mValueUnitPaint.setTextSize(mValueUnitTextSize);

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(getResources().getColor(R.color.green_100));

        mMarkerLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMarkerLinePaint.setStyle(Paint.Style.STROKE);
        mMarkerLinePaint.setColor(getResources().getColor(R.color.grey_400));
        mMarkerLineWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mMarkerLinePaint.setStrokeWidth(mMarkerLineWidth);

        mScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScalePaint.setStyle(Paint.Style.STROKE);
        mScalePaint.setColor(getResources().getColor(R.color.grey_400));

        mScaleValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScaleValuePaint.setStyle(Paint.Style.FILL);
        mScaleValuePaint.setColor(getResources().getColor(R.color.grey_900));
        mScaleValueTextSize = getResources().getDimension(R.dimen.font_twenty_four);
        mScaleValuePaint.setTextSize(mScaleValueTextSize);
        mScaleValuePaint.setTextAlign(Paint.Align.CENTER);

        mValueIndicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mValueIndicatorPaint.setStyle(Paint.Style.STROKE);
        mValueIndicatorPaint.setColor(getResources().getColor(R.color.green_500));
        mValueIndicatorWidth = getResources().getDimension(R.dimen.padding_micro);
        mValueIndicatorPaint.setStrokeWidth(mValueIndicatorWidth);


        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                           起始值相关                                    //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
        mStartValue = getResources().getInteger(R.integer.two_hundred);
        mCurrentValue = mStartValue;
        mEndValue = getResources().getInteger(R.integer.two_thousand);
        mValueRect = new Rect();

        mValueUnitString = getResources().getString(R.string.kg);
        mValueUnitRect = new Rect();

        mScaleMiniUnitDistance = getResources().getDimension(R.dimen.padding_medium_x);
        mScaleLongLineWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mScaleLongLineHeight = getResources().getDimension(R.dimen.item_height);
        mScaleShortLineWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mScaleShortLineHeight = mScaleLongLineHeight/2;

        mRulerTopHeight = mScaleLongLineHeight;
        mRulerBottomHeight = mScaleLongLineHeight * 3/2;
        mRulerHeight = mRulerTopHeight + mRulerBottomHeight;

        mScaleValueRect = new Rect();
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
        //1. Value
        mValueString = String.valueOf(mCurrentValue/10);
        mValuePaint.getTextBounds(mValueString, 0, mValueString.length(), mValueRect);
        mValuePositionX = mCenterX;
        mValuePositionY = mCenterY - mRulerHeight/2 - mValueRect.height();
        canvas.drawText(mValueString, 0, mValueString.length(), mValuePositionX, mValuePositionY, mValuePaint);

        //2. Value Unit
        mValueUnitPaint.getTextBounds(mValueUnitString, 0, mValueUnitString.length(), mValueUnitRect);
        mValueUnitPositionX = mCenterX + mValueRect.width()/2 + mValueUnitRect.width()/2;
        mValueUnitPositionY = mCenterY - mRulerHeight/2 - mValueRect.height() * 2 + mValueUnitRect.height() * 2/3;
        canvas.drawText(mValueUnitString, 0, mValueUnitString.length(), mValueUnitPositionX, mValueUnitPositionY, mValueUnitPaint);

        //3. Background
        canvas.drawRect(0, (mCenterY - mRulerHeight/2), mWidth, (mCenterY + mRulerHeight/2), mBackgroundPaint);

        //4. Maker Line
        canvas.drawLine(0, (mCenterY - mRulerHeight/2), mWidth, (mCenterY - mRulerHeight/2), mMarkerLinePaint);

        //5. Scale
        for (int i = (int)mStartValue; i < mEndValue; i++) {
            if(i % 10 ==0){
                mScalePaint.setStrokeWidth(mScaleLongLineWidth);
                canvas.drawLine(((i - mStartValue) * mScaleMiniUnitDistance + mCenterX), (mCenterY - mRulerHeight/2), ((i - mStartValue) * mScaleMiniUnitDistance + mCenterX), (mCenterY - mRulerHeight/2  + mScaleLongLineHeight), mScalePaint);
                //6. Scale Value
                mScaleValueString = String.valueOf(i/10);
                mScaleValuePaint.getTextBounds(mValueString, 0, mValueString.length(), mScaleValueRect);
                mScaleValuePositionX = ((i - mStartValue) * mScaleMiniUnitDistance + mCenterX);
                mScaleValuePositionY = (mCenterY - mRulerHeight/2  + mScaleLongLineHeight) + (mRulerBottomHeight - mScaleValueRect.height())/2 + mScaleValueRect.height();
                canvas.drawText(mScaleValueString, 0, mScaleValueString.length(), mScaleValuePositionX, mScaleValuePositionY, mScaleValuePaint);
            }else{
                mScalePaint.setStrokeWidth(mScaleShortLineWidth);
                canvas.drawLine(((i - mStartValue) * mScaleMiniUnitDistance + mCenterX), (mCenterY - mRulerHeight/2), ((i - mStartValue) * mScaleMiniUnitDistance + mCenterX), (mCenterY - mRulerHeight/2  + mScaleShortLineHeight), mScalePaint);
            }
        }

        //7. Value Indicator
        mValueIndicatorStartX = (mCurrentValue - mStartValue) * mScaleMiniUnitDistance + mCenterX;
        mValueIndicatorStartY = mCenterY - mRulerHeight/2;
        mValueIndicatorEndX = (mCurrentValue - mStartValue) * mScaleMiniUnitDistance + mCenterX;
        mValueIndicatorEndY = mCenterY - mRulerHeight/2 + mScaleLongLineHeight * 10/9;
        mValueIndicatorPaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(mValueIndicatorStartX, mValueIndicatorStartY, mValueIndicatorEndX, mValueIndicatorEndY, mValueIndicatorPaint);
        mValueIndicatorPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mValueIndicatorEndX, mValueIndicatorEndY, mValueIndicatorWidth/2, mValueIndicatorPaint);
    }

}
