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


public class PieChartView3 extends View {

    private Paint mPaint;
    private float mStrokeWidth, mFontSize;
    private int mWidth, mHeight;
    private int mRadius;
    private int mLeft, mTop, mRight, mBottom;
    private ArrayList<Arc3> mArcs;
    private float[] mArcPercentages;
    private String[] mArcNames;
    private int[] mArcColors;
    private float mArcSpace;
    private float mIndicatorPadding;
    private float mIndicatorLength;
    private float mIndicatorExtendedLineLength;

    public PieChartView3(Context context) {
        this(context, null);
    }

    public PieChartView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mPaint.setStrokeWidth(mStrokeWidth);
        mFontSize = getResources().getDimension(R.dimen.font_micro_extra);
        mPaint.setTextSize(mFontSize);
//        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(getResources().getColor(R.color.blue_400));

        mArcs = new ArrayList<>();

//        mArcPercentages = new float[]{0.261f, 0.141f, 0.101f, 0.181f, 0.201f, 0.097f};
        mArcPercentages = new float[]{0.164f, 0.164f, 0.164f, 0.164f, 0.164f, 0.162f};
        mArcNames = getResources().getStringArray(R.array.android_version_names);
        mArcColors = getResources().getIntArray(R.array.android_version_colors);

        mIndicatorPadding = getResources().getDimension(R.dimen.padding_small_x);
        mIndicatorLength = getResources().getDimension(R.dimen.padding_medium_x);
        mIndicatorExtendedLineLength = getResources().getDimension(R.dimen.padding_large);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mRadius = mWidth / 4;

        mLeft = mWidth / 2 - mRadius;
        mTop = mHeight / 2 - mRadius;
        mRight = mWidth / 2 + mRadius;
        mBottom = mHeight / 2 + mRadius;

        mArcSpace = 0.003f;

        float startAngle = 0;
        Rect rect = null;
        for (int i = 0; i < mArcNames.length; i++) {
            ////////////////////////////////////////////////////////////////////////////////////////
            //                                                                                    //
            //                                       第一版                                        //
            //                                                                                    //
            ////////////////////////////////////////////////////////////////////////////////////////
//            if (i == 0) {
//                mArcs.add(new Arc(mArcNames[i], mLeft + (float) (mIndicatorPadding * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))), mTop + (float) (mIndicatorPadding * Math.sin((Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)))), mRight + (float) (mIndicatorPadding * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))), mBottom + (float) (mIndicatorPadding * Math.sin((Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)))), startAngle, 360 * mArcPercentages[i], true, mArcColors[i]));
//                mPoints.add(new Point((int) ((mRadius + mIndicatorPadding * 2) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))), (int) ((mRadius + mIndicatorPadding * 2) * Math.sin((Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))))));
//            } else {
//                mArcs.add(new Arc(mArcNames[i], mLeft, mTop, mRight, mBottom, startAngle, 360 * mArcPercentages[i], true, mArcColors[i]));
//                mPoints.add(new Point((int) ((mRadius + mIndicatorPadding) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))), (int) ((mRadius + mIndicatorPadding) * Math.sin((Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))))));
//
//            }
//            startAngle += 360 * mArcPercentages[i] + 360 * mArcSpace;


            ////////////////////////////////////////////////////////////////////////////////////////
            //                                                                                    //
            //                                       第二版                                        //
            //                                                                                    //
            ////////////////////////////////////////////////////////////////////////////////////////
//            rect = new Rect();
//            mPaint.getTextBounds(mArcNames[i], 0, mArcNames[i].length(), rect);
//            if (i == 0) {
//                mArcs.add(
//                        new Arc3(
//                            mArcNames[i],
//                            mLeft + (float) (mIndicatorPadding * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                            mTop + (float) (mIndicatorPadding * Math.sin((Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)))),
//                            mRight + (float) (mIndicatorPadding * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                            mBottom + (float) (mIndicatorPadding * Math.sin((Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)))),
//                            startAngle,
//                            360 * mArcPercentages[i],
//                            true,
//                            mArcColors[i],
//                            (float) ((mRadius + mIndicatorPadding * 2) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                            (float) ((mRadius + mIndicatorPadding * 2) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) + (Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) >= 0 ? 1 : -1) * mIndicatorExtendedLineLength),
//                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) + (Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) >= 0 ? 1 : -1) * (mIndicatorExtendedLineLength + mIndicatorPadding + (Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) >= 0 ? 0 : rect.width()))),
//                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) + rect.height()/3)
//                        )
//                );
//            } else {
//                mArcs.add(
//                        new Arc3(
//                                mArcNames[i],
//                                mLeft,
//                                mTop,
//                                mRight,
//                                mBottom,
//                                startAngle,
//                                360 * mArcPercentages[i],
//                                true,
//                                mArcColors[i],
//                                (float) ((mRadius + mIndicatorPadding) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                                (float) ((mRadius + mIndicatorPadding) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                                (float) ((mRadius + mIndicatorPadding + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                                (float) ((mRadius + mIndicatorPadding + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                                (float) ((mRadius + mIndicatorPadding + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                                (float) ((mRadius + mIndicatorPadding + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                                (float) ((mRadius + mIndicatorPadding + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) + (Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) >= 0 ? 1 : -1) * mIndicatorExtendedLineLength),
//                                (float) ((mRadius + mIndicatorPadding + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
//                                (float) ((mRadius + mIndicatorPadding + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) + (Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) >= 0 ? 1 : -1) * (mIndicatorExtendedLineLength + mIndicatorPadding + (Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) >= 0 ? 0 : rect.width()))),
//                                (float) ((mRadius + mIndicatorPadding + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) + rect.height()/3)
//                        )
//                );
//            }

            ////////////////////////////////////////////////////////////////////////////////////////
            //                                                                                    //
            //                                       第三版                                        //
            //                                                                                    //
            ////////////////////////////////////////////////////////////////////////////////////////
            rect = new Rect();
            mPaint.getTextBounds(mArcNames[i], 0, mArcNames[i].length(), rect);
            mArcs.add(
                    new Arc3(
                            mArcNames[i],
                            mLeft + (float) (mIndicatorPadding * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
                            mTop + (float) (mIndicatorPadding * Math.sin((Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)))),
                            mRight + (float) (mIndicatorPadding * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
                            mBottom + (float) (mIndicatorPadding * Math.sin((Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)))),
                            startAngle,
                            360 * mArcPercentages[i],
                            true,
                            mArcColors[i],
                            (float) ((mRadius + mIndicatorPadding * 2) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
                            (float) ((mRadius + mIndicatorPadding * 2) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) + (Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) >= 0 ? 1 : -1) * mIndicatorExtendedLineLength),
                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2))),
                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) + (Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) >= 0 ? 1 : -1) * (mIndicatorExtendedLineLength + mIndicatorPadding + (Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) >= 0 ? 0 : rect.width()))),
                            (float) ((mRadius + mIndicatorPadding * 2 + mIndicatorLength) * Math.sin(Math.toRadians(startAngle + 360 * mArcPercentages[i] / 2)) + rect.height()/3)
                    )
            );
            startAngle += 360 * mArcPercentages[i] + 360 * mArcSpace;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mArcs.size(); i++) {
            Arc3 arc = mArcs.get(i);
            mPaint.setColor(arc.getColor());
            canvas.drawArc(arc.getLeft(), arc.getTop(), arc.getRight(), arc.getBottom(), arc.getStartAngle(), arc.getSweepAngle(), arc.isUseCenter(), mPaint);
            canvas.save();
            canvas.translate(mWidth / 2, mHeight / 2);
            canvas.drawLine(arc.getIndicatorStartX(), arc.getIndicatorStartY(), arc.getIndicatorStopX(), arc.getIndicatorStopY(), mPaint);
            canvas.drawLine(arc.getIndicatorExtendedLineStartX(), arc.getIndicatorExtendedLineStartY(), arc.getIndicatorExtendedLineStopX(), arc.getIndicatorExtendedLineStopY(), mPaint);
            canvas.drawText(arc.getText(), arc.getTextX(), arc.getTextY(), mPaint);
            canvas.restore();
        }
    }

}

class Arc3 {

    private String text;
    private float left, top, right, bottom;
    private float startAngle, sweepAngle;
    private boolean useCenter;
    private int color;
    private float indicatorStartX, indicatorStartY, indicatorStopX, indicatorStopY;
    private float indicatorExtendedLineStartX, indicatorExtendedLineStartY, indicatorExtendedLineStopX, indicatorExtendedLineStopY;
    private float textX, textY;

    public Arc3() {
    }

    public Arc3(
                String text,
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
                float textY
                ) {
        this.setText(text);
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
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}

































































