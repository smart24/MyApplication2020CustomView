package com.smart.myapplication2020customview.staticview.view_01_basic.view_12_unit_01_practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

import java.util.ArrayList;

/**
 * FileName: AliPayBillView
 *
 * Des: 仿写支付宝账单界面
 *
 * To Do:
 *
 *      1. 实现基础界面布局  👌
 *
 *      2. 实现基础界面布局 + Item 之间分割均匀  👈
 *
 * Time: 2020/5/13 9:09 PM
 */
public class AliPayBillView2 extends View {

    private Paint mArcPaint, mIndicatorPointPaint, mIndicatorLinePaint, mTextPaint, mValuePaint, mItemDividerPaint;
    private float mArcStrokeWidth, mIndicatorPointRadius, mIndicatorStrokeWidth, mTextFontSize, mValueFontSize, mItemDividerStrokeWidth;
    private int mWidth, mHeight;
    private int mRadius;
    private int mPadding;
    private int mLeft, mTop, mRight, mBottom;
    private ArrayList<Item2> mItems;
    private float[] mItemPercentages;
    private String[] mItemNames;
    private String[] mItemValues;
    private int[] mItemColors;
    private float mItemSpace;
    private float mIndicatorPadding;
    private float mIndicatorLength;
    private float mTextVerticalPadding;

    public AliPayBillView2(Context context) {
        this(context, null);
    }

    public AliPayBillView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AliPayBillView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeCap(Paint.Cap.BUTT);
        mArcStrokeWidth = getResources().getDimension(R.dimen.padding_large_x);
        mArcPaint.setStrokeWidth(mArcStrokeWidth);

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

        mItemDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mItemDividerPaint.setStyle(Paint.Style.STROKE);
        mItemDividerPaint.setStrokeCap(Paint.Cap.SQUARE);
        mItemDividerPaint.setColor(getResources().getColor(R.color.white));
        mItemDividerStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mItemDividerPaint.setStrokeWidth(mItemDividerStrokeWidth);

        mItems = new ArrayList<>();

//        mItemPercentages = new float[]{0.261f, 0.141f, 0.101f, 0.181f, 0.201f, 0.097f};
//        mItemPercentages = new float[]{0.264f, 0.144f, 0.104f, 0.184f, 0.204f, 0.099f};
//        mItemPercentages = new float[]{0.164f, 0.164f, 0.164f, 0.164f, 0.164f, 0.162f};

        mItemPercentages = new float[]{0.167f, 0.167f, 0.167f, 0.167f, 0.167f, 0.082f, 0.082f};
        mItemNames = getResources().getStringArray(R.array.living_expenses_names);
        mItemValues = getResources().getStringArray(R.array.living_expenses_values);
        mItemColors = getResources().getIntArray(R.array.android_version_colors);

        mIndicatorPadding = getResources().getDimension(R.dimen.padding_micro_xx);
        mIndicatorLength = getResources().getDimension(R.dimen.padding_medium_x);
        mTextVerticalPadding = getResources().getDimension(R.dimen.padding_micro);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mRadius = (mWidth <= mHeight ? mWidth : mHeight) / 5;

        mPadding = mRadius / 4;

        mLeft = mWidth / 2 - mRadius;
        mTop = mHeight / 2 - mRadius;
        mRight = mWidth / 2 + mRadius;
        mBottom = mHeight / 2 + mRadius;

//        mItemSpace = 0.003f;
//        mItemSpace = 0.000f;

        float startAngle = 0;
        Rect textRect = null;
        Rect valueRect = null;
        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                            第一版：无突出的 Item，一捅到底                                //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < mItemNames.length; i++) {
            textRect = new Rect();
            valueRect = new Rect();
            mTextPaint.getTextBounds(mItemNames[i], 0, mItemNames[i].length(), textRect);
            mValuePaint.getTextBounds(mItemValues[i], 0, mItemValues[i].length(), valueRect);
            mItems.add(
                    new Item2(
                            mItemNames[i],
                            mItemValues[i],
                            mLeft + (float) (mIndicatorPadding * Math.cos(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2))),
                            mTop + (float) (mIndicatorPadding * Math.sin((Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2)))),
                            mRight + (float) (mIndicatorPadding * Math.cos(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2))),
                            mBottom + (float) (mIndicatorPadding * Math.sin((Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2)))),
                            startAngle,
                            360 * mItemPercentages[i],
                            false,
                            mItemColors[i],
                            (float) ((mRadius + mArcStrokeWidth * 5/6 + mIndicatorPadding) * Math.cos(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2))),
                            (float) ((mRadius + mArcStrokeWidth * 5/6 + mIndicatorPadding) * Math.sin(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2))),
                            (float) ((mRadius + mArcStrokeWidth * 5/6 + mIndicatorPadding + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2))),
                            (float) ((mRadius + mArcStrokeWidth * 5/6 + mIndicatorPadding + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2))),
                            (float) ((mRadius + mArcStrokeWidth * 5/6 + mIndicatorPadding + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2))),
                            (float) ((mRadius + mArcStrokeWidth * 5/6 + mIndicatorPadding + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2))),
                            (float) ((Math.cos(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2)) >= 0 ? 1 : -1) * ((mWidth <= mHeight ? mWidth : mHeight) / 2 - mPadding)),
                            (float) ((mRadius + mArcStrokeWidth * 5/6 + mIndicatorPadding + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2))),
                            (float) ((Math.cos(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2)) >= 0 ? 1 : -1) * ((mWidth <= mHeight ? mWidth : mHeight) / 2 - mPadding) - (Math.cos(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2)) <= 0 ? 0 : textRect.width())),
                            (float) ((mRadius + mArcStrokeWidth * 5/6 + mIndicatorPadding + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2)) + textRect.height() + mTextVerticalPadding),
                            (float) ((Math.cos(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2)) >= 0 ? 1 : -1) * ((mWidth <= mHeight ? mWidth : mHeight) / 2 - mPadding) - (Math.cos(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2)) <= 0 ? 0 : valueRect.width())),
                            (float) ((mRadius + mArcStrokeWidth * 5/6 + mIndicatorPadding + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mItemPercentages[i] / 2)) - mTextVerticalPadding),
                            (float) ((mRadius - mArcStrokeWidth/2) * Math.cos(Math.toRadians(startAngle))),
                            (float) ((mRadius - mArcStrokeWidth/2) * Math.sin(Math.toRadians(startAngle))),
                            (float) ((mRadius + mArcStrokeWidth/2) * Math.cos(Math.toRadians(startAngle))),
                            (float) ((mRadius + mArcStrokeWidth/2) * Math.sin(Math.toRadians(startAngle)))
                    )
            );
            startAngle += 360 * mItemPercentages[i] + 360 * mItemSpace;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mItems.size(); i++) {
            Item2 item = mItems.get(i);
            mArcPaint.setColor(item.getColor());
            mIndicatorPointPaint.setColor(item.getColor());
            mIndicatorLinePaint.setColor(item.getColor());
            canvas.drawArc(item.getLeft(), item.getTop(), item.getRight(), item.getBottom(), item.getStartAngle(), item.getSweepAngle(), item.isUseCenter(), mArcPaint);
            canvas.save();
            canvas.translate(mWidth / 2, mHeight / 2);
            canvas.drawPoint(item.getIndicatorStartX(), item.getIndicatorStartY(), mIndicatorPointPaint);
            canvas.drawLine(item.getIndicatorStartX(), item.getIndicatorStartY(), item.getIndicatorStopX(), item.getIndicatorStopY(), mIndicatorLinePaint);
            canvas.drawLine(item.getIndicatorExtendedLineStartX(), item.getIndicatorExtendedLineStartY(), item.getIndicatorExtendedLineStopX(), item.getIndicatorExtendedLineStopY(), mIndicatorLinePaint);
            canvas.drawText(item.getText(), item.getTextX(), item.getTextY(), mTextPaint);
            canvas.drawText(item.getValue(), item.getValueX(), item.getValueY(), mValuePaint);
            canvas.drawLine(item.getDividerStartX(), item.getDividerStartY(), item.getDividerStopX(), item.getDividerStopY(), mItemDividerPaint);
            canvas.restore();
        }
    }

}

class Item2 {

    private String text;
    private String value;
    private float left, top, right, bottom;
    private float startAngle, sweepAngle;
    private boolean useCenter;
    private int color;
    private float indicatorStartX, indicatorStartY, indicatorStopX, indicatorStopY;
    private float indicatorExtendedLineStartX, indicatorExtendedLineStartY, indicatorExtendedLineStopX, indicatorExtendedLineStopY;
    private float textX, textY;
    private float valueX, valueY;
    private float dividerStartX, dividerStartY, dividerStopX, dividerStopY;

    public Item2() {
    }

    public Item2(
            String text,
            String value,
            float left,
            float top,
            float right,
            float bottom,
            float startAngle,
            float sweepAngle,
            boolean useCenter,
            int color,
            float indicatorStartX,
            float indicatorStartY,
            float indicatorStopX,
            float indicatorStopY,
            float indicatorExtendedLineStartX,
            float indicatorExtendedLineStartY,
            float indicatorExtendedLineStopX,
            float indicatorExtendedLineStopY,
            float textX,
            float textY,
            float valueX,
            float valueY,
            float dividerStartX,
            float dividerStartY,
            float dividerStopX,
            float dividerStopY
    ) {
        this.setText(text);
        this.setValue(value);
        this.setLeft(left);
        this.setTop(top);
        this.setRight(right);
        this.setBottom(bottom);
        this.setStartAngle(startAngle);
        this.setSweepAngle(sweepAngle);
        this.setUseCenter(useCenter);
        this.setColor(color);
        this.setIndicatorStartX(indicatorStartX);
        this.setIndicatorStartY(indicatorStartY);
        this.setIndicatorStopX(indicatorStopX);
        this.setIndicatorStopY(indicatorStopY);
        this.setIndicatorExtendedLineStartX(indicatorExtendedLineStartX);
        this.setIndicatorExtendedLineStartY(indicatorExtendedLineStartY);
        this.setIndicatorExtendedLineStopX(indicatorExtendedLineStopX);
        this.setIndicatorExtendedLineStopY(indicatorExtendedLineStopY);
        this.setTextX(textX);
        this.setTextY(textY);
        this.setValueX(valueX);
        this.setValueY(valueY);
        this.setDividerStartX(dividerStartX);
        this.setDividerStartY(dividerStartY);
        this.setDividerStopX(dividerStopX);
        this.setDividerStopY(dividerStopY);
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

    public float getDividerStartX() {
        return dividerStartX;
    }

    public void setDividerStartX(float dividerStartX) {
        this.dividerStartX = dividerStartX;
    }

    public float getDividerStartY() {
        return dividerStartY;
    }

    public void setDividerStartY(float dividerStartY) {
        this.dividerStartY = dividerStartY;
    }

    public float getDividerStopX() {
        return dividerStopX;
    }

    public void setDividerStopX(float dividerStopX) {
        this.dividerStopX = dividerStopX;
    }

    public float getDividerStopY() {
        return dividerStopY;
    }

    public void setDividerStopY(float dividerStopY) {
        this.dividerStopY = dividerStopY;
    }
}


































































