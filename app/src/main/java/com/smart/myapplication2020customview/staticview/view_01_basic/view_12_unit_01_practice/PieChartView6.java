package com.smart.myapplication2020customview.staticview.view_01_basic.view_12_unit_01_practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

import java.util.ArrayList;

/**
 * FileName: PieChartView6
 *
 * Des: 仿支付宝账单详情饼状图
 *
 * Time: 2020/5/20 12:28 PM
 */
public class PieChartView6 extends View {

    private Paint mPaint, mCoverCirclePaint, mCoverPathPaint, mIndicatorPointPaint, mIndicatorLinePaint, mTextPaint, mValuePaint;
    private float mCoverStrokeWidth, mIndicatorPointRadius, mIndicatorStrokeWidth, mTextFontSize, mValueFontSize;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private int mRadius;
    private int mPadding;
    private int mLeft, mTop, mRight, mBottom;
    private ArrayList<Arc6> mArcs;
    private String[] mArcNames;
    private int[] mArcValues;
    private int[] mArcColors;
    private int mArcTotalValue;
    private float mArcStrokeWidth;
    private float mArcPadding;
    private Path mCoverPath;
    private float mIndicatorPadding;
    private float mIndicatorLength;
    private float mTextVerticalPadding;
    private float mLimitAngleValue;

    public PieChartView6(Context context) {
        this(context, null);
    }

    public PieChartView6(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView6(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_HARDWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mCoverCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCoverCirclePaint.setStyle(Paint.Style.STROKE);
        mCoverCirclePaint.setColor(getResources().getColor(R.color.blue_400));
        mCoverStrokeWidth = getResources().getDimension(R.dimen.padding_small);
        mCoverCirclePaint.setStrokeWidth(mCoverStrokeWidth);

        mCoverPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCoverPathPaint.setStyle(Paint.Style.FILL);
        mCoverPathPaint.setColor(getResources().getColor(R.color.white));
        mCoverPathPaint.setStrokeWidth(mCoverStrokeWidth);

        mIndicatorPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIndicatorPointPaint.setStyle(Paint.Style.STROKE);
        mIndicatorPointPaint.setStrokeCap(Paint.Cap.ROUND);
        mIndicatorPointRadius = getResources().getDimension(R.dimen.padding_micro);
        mIndicatorPointPaint.setStrokeWidth(mIndicatorPointRadius);

        mIndicatorLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIndicatorLinePaint.setStyle(Paint.Style.STROKE);
        mIndicatorLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mIndicatorStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mIndicatorLinePaint.setStrokeWidth(mIndicatorStrokeWidth);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(getResources().getColor(R.color.grey_700));
        mTextFontSize = getResources().getDimension(R.dimen.font_micro_extra);
        mTextPaint.setTextSize(mTextFontSize);

        mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mValuePaint.setStyle(Paint.Style.FILL);
        mValuePaint.setFakeBoldText(true);
        mValuePaint.setColor(getResources().getColor(R.color.grey_800));
        mValueFontSize = getResources().getDimension(R.dimen.font_micro_extra);
        mValuePaint.setTextSize(mValueFontSize);

        mArcs = new ArrayList<>();

        mArcNames = getResources().getStringArray(R.array.living_expenses_names);
        mArcValues = getResources().getIntArray(R.array.living_expenses_int_values);
        mArcColors = getResources().getIntArray(R.array.android_version_colors);

        for (int arcValue : mArcValues) {
            mArcTotalValue += arcValue;
        }

        mArcStrokeWidth = getResources().getDimension(R.dimen.padding_large_x_x);
        mArcPadding = getResources().getDimension(R.dimen.padding_micro_xx);

        mCoverPath = new Path();
        mCoverPath.setFillType(Path.FillType.EVEN_ODD);

        mIndicatorPadding = getResources().getDimension(R.dimen.padding_small);
        mIndicatorLength = getResources().getDimension(R.dimen.padding_medium);
        mTextVerticalPadding = getResources().getDimension(R.dimen.padding_micro);

//        mLimitAngleValue = 360 * 0.1f;
        mLimitAngleValue = 360 * 0.0f;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mRadius = (mWidth <= mHeight ? mWidth : mHeight) / 4;

        mPadding = mRadius / 4;

        mLeft = mWidth / 2 - mRadius;
        mTop = mHeight / 2 - mRadius;
        mRight = mWidth / 2 + mRadius;
        mBottom = mHeight / 2 + mRadius;

//        float startAngle = 0;
        float startAngle = -90;
        float currentAngle = 0;
        float currentRadius = 0;
        float currentSinValue = 0;
        float currentCosValue = 0;
        float deltaArcPadding = 0;
        float indicatorStartX = 0;
        float indicatorStartY = 0;
        float indicatorStopX = 0;
        float indicatorStopY = 0;
        float indicatorExtendedLineStartX = 0;
        float indicatorExtendedLineStartY = 0;
        float indicatorExtendedLineStopX = 0;
        float indicatorExtendedLineStopY = 0;
        float textX, textY;
        float valueX, valueY;
        Rect textRect = null;
        Rect valueRect = null;
        String currentName = null;
        String currentValue = null;
        Arc6 previousArc = null;
        Arc6 currentArc = null;
        float twoIndicatorExtendedLineVerticalStandardDistance = 0;
        float twoIndicatorExtendedLineVerticalActualDistance = 0;
        for (int i = 0; i < mArcNames.length; i++) {
            currentAngle = 360 * (mArcValues[i] / (float) mArcTotalValue);
            currentRadius = (float) Math.toRadians(startAngle + currentAngle / 2);
            currentSinValue = (float) Math.sin(currentRadius);
            currentCosValue = (float) Math.cos(currentRadius);
            deltaArcPadding = (float) (Math.abs(mArcPadding / (currentAngle % 180 == 0 ? 1 : Math.sin(Math.toRadians((currentAngle > 180 ? (360 - currentAngle) : currentAngle) / 2)))));
            indicatorStartX = (mRadius - mArcPadding + mIndicatorPadding) * currentCosValue;
            indicatorStartY = (mRadius - mArcPadding + mIndicatorPadding) * currentSinValue;
            indicatorStopX = (mRadius - mArcPadding + mIndicatorPadding + mIndicatorLength) * currentCosValue;
            indicatorStopY = (mRadius - mArcPadding + mIndicatorPadding + mIndicatorLength) * currentSinValue;
            indicatorExtendedLineStartX = (mRadius - mArcPadding + mIndicatorPadding + mIndicatorLength) * currentCosValue;
            indicatorExtendedLineStartY = (mRadius - mArcPadding + mIndicatorPadding + mIndicatorLength) * currentSinValue;
            indicatorExtendedLineStopX = (float) ((currentCosValue >= 0 ? 1 : -1) * ((mWidth <= mHeight ? mWidth : mHeight) / 2 - mPadding));
            indicatorExtendedLineStopY = (mRadius - mArcPadding + mIndicatorPadding + mIndicatorLength) * currentSinValue;
            textRect = new Rect();
            valueRect = new Rect();
            currentName = mArcNames[i];
            mTextPaint.getTextBounds(currentName, 0, currentName.length(), textRect);
            currentValue = Constants.MONEY_SYMBOL_RMB + Constants.PLACE_HOLDER_BLANK + mArcValues[i];
            mValuePaint.getTextBounds(currentValue, 0, currentValue.length(), valueRect);
            textX = (float) ((currentCosValue >= 0 ? 1 : -1) * ((mWidth <= mHeight ? mWidth : mHeight) / 2 - mPadding) - (currentCosValue <= 0 ? 0 : textRect.width()));
            textY = (mRadius - mArcPadding + mIndicatorPadding + mIndicatorLength) * currentSinValue + textRect.height() + mTextVerticalPadding/2;
            valueX = (float) ((currentCosValue >= 0 ? 1 : -1) * ((mWidth <= mHeight ? mWidth : mHeight) / 2 - mPadding) - (currentCosValue <= 0 ? 0 : valueRect.width()));
            valueY = (mRadius - mArcPadding + mIndicatorPadding + mIndicatorLength) * currentSinValue - mTextVerticalPadding;
            currentArc = new Arc6(
                    currentName,
                    currentValue,
                    currentSinValue,
                    currentCosValue,
                    mLeft + (deltaArcPadding * currentCosValue),
                    mTop + (deltaArcPadding * currentSinValue),
                    mRight + (deltaArcPadding * currentCosValue),
                    mBottom + (deltaArcPadding * currentSinValue),
                    startAngle,
                    currentAngle,
                    true,
                    mArcColors[i],
                    indicatorStartX,
                    indicatorStartY,
                    indicatorStopX,
                    indicatorStopY,
                    indicatorExtendedLineStartX,
                    indicatorExtendedLineStartY,
                    indicatorExtendedLineStopX,
                    indicatorExtendedLineStopY,
                    textX,
                    textY,
                    valueX,
                    valueY
            );

            //是否是第一个
            //  - 1. 如果是第一个点；
            //  - 2. 如果不是第一点
            //      - 是否同方向
            //          - 2.1 如果同方向
            //              - 如果相邻两 Arc 指示器延长线的垂直距离大于限定值
            //              - 如果相邻两 Arc 指示器延长线的垂直距离不大于限定值（需要重新计算文字的位置）  👈
            //          - 2.2 如果不同方向

            if(mArcs != null && mArcs.size() > 0){
                //如果不是第一个点
                previousArc = mArcs.get(i - 1);
                if(previousArc.getCosValue() >= 0 && currentCosValue >= 0 || previousArc.getCosValue() < 0 && currentCosValue < 0){
                    //如果不是第一个点 & 如果同方向 → 判断相邻两指示器延长线的垂直距离是否大于限定值（mTextVerticalPadding/2 + textRect.height() + valueRect.height() + valueRect.height() + mTextVerticalPadding）
                    twoIndicatorExtendedLineVerticalActualDistance = Math.abs(previousArc.getIndicatorExtendedLineStartY() - currentArc.getIndicatorExtendedLineStartY());
                    twoIndicatorExtendedLineVerticalStandardDistance = (mTextVerticalPadding/2 + textRect.height() + textRect.height() + valueRect.height() + mTextVerticalPadding);
                        if(previousArc.getCosValue() >= 0 && currentCosValue >= 0){
                            if((twoIndicatorExtendedLineVerticalActualDistance < twoIndicatorExtendedLineVerticalStandardDistance) || (currentArc.getIndicatorExtendedLineStartY() < previousArc.getIndicatorExtendedLineStartY())) {
                                //如果同时在 Y 轴右侧
                                indicatorStopY = previousArc.getIndicatorExtendedLineStartY() + twoIndicatorExtendedLineVerticalStandardDistance;
                                indicatorExtendedLineStartY = previousArc.getIndicatorExtendedLineStartY() + twoIndicatorExtendedLineVerticalStandardDistance;
                                indicatorExtendedLineStopY = previousArc.getIndicatorExtendedLineStartY() + twoIndicatorExtendedLineVerticalStandardDistance;
                                textY = previousArc.getIndicatorExtendedLineStartY() + twoIndicatorExtendedLineVerticalStandardDistance + textRect.height() + mTextVerticalPadding / 2;
                                valueY = previousArc.getIndicatorExtendedLineStartY() + twoIndicatorExtendedLineVerticalStandardDistance - mTextVerticalPadding;
                            }
                        }else if(previousArc.getCosValue() < 0 && currentCosValue < 0){
                            if((twoIndicatorExtendedLineVerticalActualDistance < twoIndicatorExtendedLineVerticalStandardDistance) || (currentArc.getIndicatorExtendedLineStartY() > previousArc.getIndicatorExtendedLineStartY())) {
                                //如果同时在 Y 轴左侧
                                indicatorStopY = previousArc.getIndicatorExtendedLineStartY() - twoIndicatorExtendedLineVerticalStandardDistance;
                                indicatorExtendedLineStartY = previousArc.getIndicatorExtendedLineStartY() - twoIndicatorExtendedLineVerticalStandardDistance;
                                indicatorExtendedLineStopY = previousArc.getIndicatorExtendedLineStartY() - twoIndicatorExtendedLineVerticalStandardDistance;
                                textY = previousArc.getIndicatorExtendedLineStartY() - twoIndicatorExtendedLineVerticalStandardDistance + textRect.height() + mTextVerticalPadding / 2;
                                valueY = previousArc.getIndicatorExtendedLineStartY() - twoIndicatorExtendedLineVerticalStandardDistance - mTextVerticalPadding;
                            }
                        }
                        currentArc.setIndicatorStopY(indicatorStopY);
                        currentArc.setIndicatorExtendedLineStartY(indicatorExtendedLineStartY);
                        currentArc.setIndicatorExtendedLineStopY(indicatorExtendedLineStopY);
                        currentArc.setTextY(textY);
                        currentArc.setValueY(valueY);
                }
            }
            mArcs.add(currentArc);
            startAngle += currentAngle;
        }

        mCoverPath.addRect(0, 0, mWidth, mHeight, Path.Direction.CW);
        mCoverPath.addCircle(mCenterX, mCenterY, mRadius - mArcPadding, Path.Direction.CW);
        mCoverPath.addCircle(mCenterX, mCenterY, mRadius - mArcPadding - mArcStrokeWidth, Path.Direction.CW);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.e(Constants.TAG, "onDraw");

        for (int i = 0; i < mArcs.size(); i++) {
            Arc6 arc = mArcs.get(i);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(arc.getColor());
            canvas.drawArc(arc.getLeft(), arc.getTop(), arc.getRight(), arc.getBottom(), arc.getStartAngle(), arc.getSweepAngle(), arc.isUseCenter(), mPaint);
        }

        canvas.drawPath(mCoverPath, mCoverPathPaint);

        for (int i = 0; i < mArcs.size(); i++) {
            Arc6 arc = mArcs.get(i);
            if(arc.getSweepAngle() >= mLimitAngleValue ){
                mIndicatorPointPaint.setColor(arc.getColor());
                mIndicatorLinePaint.setColor(arc.getColor());
                canvas.save();
                canvas.translate(mWidth / 2, mHeight / 2);
                canvas.drawPoint(arc.getIndicatorStartX(), arc.getIndicatorStartY(), mIndicatorPointPaint);
                canvas.drawLine(arc.getIndicatorStartX(), arc.getIndicatorStartY(), arc.getIndicatorStopX(), arc.getIndicatorStopY(), mIndicatorLinePaint);
                canvas.drawLine(arc.getIndicatorExtendedLineStartX(), arc.getIndicatorExtendedLineStartY(), arc.getIndicatorExtendedLineStopX(), arc.getIndicatorExtendedLineStopY(), mIndicatorLinePaint);
                canvas.drawText(arc.getText(), arc.getTextX(), arc.getTextY(), mTextPaint);
                canvas.drawText(arc.getValue(), arc.getValueX(), arc.getValueY(), mValuePaint);
                canvas.restore();
            }
        }
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(getResources().getColor(R.color.grey_800));
//        canvas.drawCircle(mCenterX, mCenterY, mRadius - mArcPadding, mPaint);
//        mPaint.setColor(getResources().getColor(R.color.grey_800));
//        canvas.drawCircle(mCenterX, mCenterY, mRadius - mArcPadding - mArcStrokeWidth, mPaint);
    }
}

class Arc6 {

    private String text;
    private String value;
    private float sinValue, cosValue;
    private float left, top, right, bottom;
    private float startAngle, sweepAngle;
    private boolean useCenter;
    private int color;
    private float indicatorStartX, indicatorStartY, indicatorStopX, indicatorStopY;
    private float indicatorExtendedLineStartX, indicatorExtendedLineStartY, indicatorExtendedLineStopX, indicatorExtendedLineStopY;
    private float textX, textY;
    private float valueX, valueY;

    public Arc6() {}

    public Arc6(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, int color) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
        this.useCenter = useCenter;
        this.color = color;
    }

    public Arc6(String text, String value, float sinValue, float cosValue, float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, int color, float indicatorStartX, float indicatorStartY, float indicatorStopX, float indicatorStopY, float indicatorExtendedLineStartX, float indicatorExtendedLineStartY, float indicatorExtendedLineStopX, float indicatorExtendedLineStopY, float textX, float textY, float valueX, float valueY) {
        this.text = text;
        this.value = value;
        this.sinValue = sinValue;
        this.cosValue = cosValue;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
        this.useCenter = useCenter;
        this.color = color;
        this.indicatorStartX = indicatorStartX;
        this.indicatorStartY = indicatorStartY;
        this.indicatorStopX = indicatorStopX;
        this.indicatorStopY = indicatorStopY;
        this.indicatorExtendedLineStartX = indicatorExtendedLineStartX;
        this.indicatorExtendedLineStartY = indicatorExtendedLineStartY;
        this.indicatorExtendedLineStopX = indicatorExtendedLineStopX;
        this.indicatorExtendedLineStopY = indicatorExtendedLineStopY;
        this.textX = textX;
        this.textY = textY;
        this.valueX = valueX;
        this.valueY = valueY;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public float getSinValue() {
        return sinValue;
    }

    public void setSinValue(float sinValue) {
        this.sinValue = sinValue;
    }

    public float getCosValue() {
        return cosValue;
    }

    public void setCosValue(float cosValue) {
        this.cosValue = cosValue;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    public float getSweepAngle() {
        return sweepAngle;
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    public boolean isUseCenter() {
        return useCenter;
    }

    public void setUseCenter(boolean useCenter) {
        this.useCenter = useCenter;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getIndicatorStartX() {
        return indicatorStartX;
    }

    public void setIndicatorStartX(float indicatorStartX) {
        this.indicatorStartX = indicatorStartX;
    }

    public float getIndicatorStartY() {
        return indicatorStartY;
    }

    public void setIndicatorStartY(float indicatorStartY) {
        this.indicatorStartY = indicatorStartY;
    }

    public float getIndicatorStopX() {
        return indicatorStopX;
    }

    public void setIndicatorStopX(float indicatorStopX) {
        this.indicatorStopX = indicatorStopX;
    }

    public float getIndicatorStopY() {
        return indicatorStopY;
    }

    public void setIndicatorStopY(float indicatorStopY) {
        this.indicatorStopY = indicatorStopY;
    }

    public float getIndicatorExtendedLineStartX() {
        return indicatorExtendedLineStartX;
    }

    public void setIndicatorExtendedLineStartX(float indicatorExtendedLineStartX) {
        this.indicatorExtendedLineStartX = indicatorExtendedLineStartX;
    }

    public float getIndicatorExtendedLineStartY() {
        return indicatorExtendedLineStartY;
    }

    public void setIndicatorExtendedLineStartY(float indicatorExtendedLineStartY) {
        this.indicatorExtendedLineStartY = indicatorExtendedLineStartY;
    }

    public float getIndicatorExtendedLineStopX() {
        return indicatorExtendedLineStopX;
    }

    public void setIndicatorExtendedLineStopX(float indicatorExtendedLineStopX) {
        this.indicatorExtendedLineStopX = indicatorExtendedLineStopX;
    }

    public float getIndicatorExtendedLineStopY() {
        return indicatorExtendedLineStopY;
    }

    public void setIndicatorExtendedLineStopY(float indicatorExtendedLineStopY) {
        this.indicatorExtendedLineStopY = indicatorExtendedLineStopY;
    }

    public float getTextX() {
        return textX;
    }

    public void setTextX(float textX) {
        this.textX = textX;
    }

    public float getTextY() {
        return textY;
    }

    public void setTextY(float textY) {
        this.textY = textY;
    }

    public float getValueX() {
        return valueX;
    }

    public void setValueX(float valueX) {
        this.valueX = valueX;
    }

    public float getValueY() {
        return valueY;
    }

    public void setValueY(float valueY) {
        this.valueY = valueY;
    }

}





































































































































































































































































































































