package com.smart.myapplication2020customview.staticview.view_01_basic.view_12_unit_01_practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;


public class CalorieView extends View {

    private Paint mBackgroundPaint, mCalorieArcPaint;
    private float mStrokeWidth;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private int mRadius;
    private int mLeft, mTop, mRight, mBottom;
    private ArrayList<CalorieArc> mCalorieArcs;
    private int[] mCalorieArcValues;
    private int[] mCalorieArcColors;
    private int mCalorieArcTotalValue;

    public CalorieView(Context context) {
        this(context, null);
    }

    public CalorieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalorieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_HARDWARE, null);

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.STROKE);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_medium);
        mBackgroundPaint.setStrokeWidth(mStrokeWidth);
        mBackgroundPaint.setColor(getResources().getColor(R.color.grey_300));

        mCalorieArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCalorieArcPaint.setStyle(Paint.Style.STROKE);
        mCalorieArcPaint.setStrokeWidth(mStrokeWidth);
        mCalorieArcPaint.setStrokeCap(Paint.Cap.ROUND);

        mCalorieArcs = new ArrayList<>();

        mCalorieArcValues = getResources().getIntArray(R.array.living_expenses_int_values);
        mCalorieArcColors = getResources().getIntArray(R.array.android_version_colors);

        for (int arcValue : mCalorieArcValues) {
            mCalorieArcTotalValue += arcValue;
        }

//        mCalorieArcTotalValue = getResources().getInteger(R.integer.twenty_thousand);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mRadius = (mWidth <= mHeight ? mWidth : mHeight) / 4;

        mLeft = mWidth / 2 - mRadius;
        mTop = mHeight / 2 - mRadius;
        mRight = mWidth / 2 + mRadius;
        mBottom = mHeight / 2 + mRadius;

//        float startAngle = 0;
        float startAngle = -90;
        float currentAngle = 0;
        CalorieArc currentCalorieArc = null;
        CalorieArc tempCalorieArc = null;
        for (int i = 0; i < mCalorieArcValues.length; i++) {
            currentAngle = 360 * (mCalorieArcValues[i] / (float) mCalorieArcTotalValue);
            currentCalorieArc = new CalorieArc(
                    String.valueOf(mCalorieArcValues[i]),
                    mLeft,
                    mTop,
                    mRight,
                    mBottom,
                    startAngle,
                    currentAngle,
                    false,
                    mCalorieArcColors[i]
            );
            mCalorieArcs.add(currentCalorieArc);
        }
        Collections.reverse(mCalorieArcs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mCenterX, mCenterY, mRadius, mBackgroundPaint);

        if(mCalorieArcs != null && mCalorieArcs.size() > 0) {
            for (int i = 0; i < mCalorieArcs.size(); i++) {
                CalorieArc calorieArc = mCalorieArcs.get(i);
                Log.e(Constants.TAG, "  " + i + "  " + calorieArc.getSweepAngle());
                mCalorieArcPaint.setColor(mCalorieArcColors[i]);
                canvas.drawArc(calorieArc.getLeft(), calorieArc.getTop(), calorieArc.getRight(), calorieArc.getBottom(), calorieArc.getStartAngle(), calorieArc.getSweepAngle(), calorieArc.isUseCenter(), mCalorieArcPaint);
            }
        }

    }

}

class CalorieArc {

    private String value;
    private float left, top, right, bottom;
    private float startAngle, sweepAngle;
    private boolean useCenter;
    private int color;

    public CalorieArc() {}

    public CalorieArc(String value, float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, int color) {
        this.value = value;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
        this.useCenter = useCenter;
        this.color = color;
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
}








































































































































































