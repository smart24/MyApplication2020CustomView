package com.smart.myapplication2020customview.staticview.view_01_basic.view_12_unit_01_practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
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


public class CalorieView2 extends View {

    private Paint mBackgroundPaint, mCalorieArcPaint, mCalorieTitlePaint, mCalorieContentPaint;
    private float mStrokeWidth, mCalorieTitleFontSize, mCalorieContentFontSize;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private int mRadius;
    private int mLeft, mTop, mRight, mBottom;
    private ArrayList<CalorieArc2> mCalorieArcs;
    private float[] mCalorieArcPercentages;
    private int[] mCalorieArcColors;
    private String mCalorieTitleCalorie, mCalorieTitleBurned, mCalorieContent;
    private Rect mCalorieTitleCalorieRect, mCalorieTitleBurnedRect, mCalorieContentRect;

    public CalorieView2(Context context) {
        this(context, null);
    }

    public CalorieView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalorieView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        mCalorieTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCalorieTitlePaint.setStyle(Paint.Style.FILL);
        mCalorieTitleFontSize = getResources().getDimension(R.dimen.font_medium);
        mCalorieTitlePaint.setTextSize(mCalorieTitleFontSize);
        mCalorieTitlePaint.setTypeface(Typeface.SANS_SERIF);

        mCalorieContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCalorieContentPaint.setStyle(Paint.Style.FILL);
        mCalorieContentFontSize = getResources().getDimension(R.dimen.font_micro_extra);
        mCalorieContentPaint.setTextSize(mCalorieContentFontSize);
        mCalorieContentPaint.setColor(getResources().getColor(R.color.grey_600));

        mCalorieArcs = new ArrayList<>();

        mCalorieArcPercentages = new float[]{0.1f, 0.26f, 0.34f, 0.48f, 0.6f, 0.7f};
        mCalorieArcColors = getResources().getIntArray(R.array.android_version_colors);

        mCalorieTitleCalorie = getResources().getString(R.string.calorie_title_calorie);
        mCalorieTitleBurned = getResources().getString(R.string.calorie_title_burned);
        mCalorieContent = getResources().getString(R.string.calorie_content);

        mCalorieTitleCalorieRect = new Rect();
        mCalorieTitleBurnedRect = new Rect();
        mCalorieContentRect = new Rect();

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
        CalorieArc2 currentCalorieArc = null;
        for (int i = 0; i < mCalorieArcPercentages.length; i++) {
            currentAngle = 360 * mCalorieArcPercentages[i] ;
            currentCalorieArc = new CalorieArc2(
                    String.valueOf(currentAngle),
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

        mCalorieTitlePaint.getTextBounds(mCalorieTitleCalorie, 0, mCalorieTitleCalorie.length(), mCalorieTitleCalorieRect);
        mCalorieTitlePaint.getTextBounds(mCalorieTitleBurned, 0, mCalorieTitleBurned.length(), mCalorieTitleBurnedRect);
        mCalorieContentPaint.getTextBounds(mCalorieContent, 0, mCalorieContent.length(), mCalorieContentRect);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mCenterX, mCenterY, mRadius, mBackgroundPaint);

        if(mCalorieArcs != null && mCalorieArcs.size() > 0) {
            for (int i = 0; i < mCalorieArcs.size(); i++) {
                CalorieArc2 calorieArc = mCalorieArcs.get(i);

                Log.e(Constants.TAG, "  " + i + "  " + calorieArc.getSweepAngle());

                mCalorieArcPaint.setColor(mCalorieArcColors[i]);
                canvas.drawArc(calorieArc.getLeft(), calorieArc.getTop(), calorieArc.getRight(), calorieArc.getBottom(), calorieArc.getStartAngle(), calorieArc.getSweepAngle(), calorieArc.isUseCenter(), mCalorieArcPaint);
            }
        }

        canvas.drawText(mCalorieTitleCalorie, (mCenterX - mCalorieTitleCalorieRect.width()/2), (mCenterY - mCalorieTitleCalorieRect.height()/2), mCalorieTitlePaint);
        canvas.drawText(mCalorieTitleBurned, (mCenterX - mCalorieTitleBurnedRect.width()/2), (mCenterY + mCalorieTitleBurnedRect.height()), mCalorieTitlePaint);
        canvas.drawText(mCalorieContent, (mCenterX - mCalorieContentRect.width()/2), (mCenterY + mCalorieTitleBurnedRect.height() + mCalorieTitleBurnedRect.height() + mCalorieContentRect.height()), mCalorieContentPaint);

//        canvas.drawText(mCalorieTitleCalorie, (mCenterX - mCalorieTitleCalorieRect.width()/2), (mCenterY - mCalorieTitleCalorieRect.height()/2), mCalorieTitlePaint);
//        canvas.drawText(mCalorieTitleBurned, (mCenterX - mCalorieTitleBurnedRect.width()/2), (mCenterY - mCalorieTitleCalorieRect.height()/2 + mCalorieTitlePaint.getFontSpacing()), mCalorieTitlePaint);
//        canvas.drawText(mCalorieContent, (mCenterX - mCalorieContentRect.width()/2), (mCenterY - mCalorieTitleCalorieRect.height()/2 + mCalorieTitlePaint.getFontSpacing() + mCalorieContentPaint.getFontSpacing()), mCalorieContentPaint);

//        canvas.drawText(mCalorieTitleCalorie, (mCenterX - mCalorieTitleCalorieRect.width()/2), (mCenterY - mCalorieTitleCalorieRect.height()/2), mCalorieTitlePaint);
//        canvas.drawText(mCalorieTitleBurned, (mCenterX - mCalorieTitleBurnedRect.width()/2), (mCenterY - mCalorieTitleCalorieRect.height()/2 + mCalorieTitlePaint.getFontSpacing()), mCalorieTitlePaint);
//        canvas.drawText(mCalorieContent, (mCenterX - mCalorieContentRect.width()/2), (mCenterY - mCalorieTitleCalorieRect.height()/2 + mCalorieTitlePaint.getFontSpacing() + mCalorieTitlePaint.getFontSpacing() + mCalorieContentPaint.getFontSpacing()/2), mCalorieContentPaint);

    }

}

class CalorieArc2 {

    private String value;
    private float left, top, right, bottom;
    private float startAngle, sweepAngle;
    private boolean useCenter;
    private int color;

    public CalorieArc2() {}

    public CalorieArc2(String value, float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, int color) {
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








































































































































































