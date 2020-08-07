package com.smart.myapplication2020customview.staticview.view_01_basic.view_12_unit_01_practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

import java.util.ArrayList;


public class PieChartView extends View {

    private Paint mPaint;
    private float mStrokeWidth, mFontSize;
    private int mWidth, mHeight;
    private int mLeft, mTop, mRight, mBottom;
    private ArrayList<Arc> mArcs;
    private float [] mArcPercentages;
    private String [] mArcNames;
    private int [] mArcColors;
    private float mArcSpace;

    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mPaint.setStrokeWidth(mStrokeWidth);
        mFontSize = getResources().getDimension(R.dimen.font_micro_extra);
        mPaint.setTextSize(mFontSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(getResources().getColor(R.color.blue_400));

        mArcs = new ArrayList<>();

        mArcPercentages = new float[]{0.261f, 0.141f, 0.101f, 0.181f, 0.201f, 0.097f};
        mArcNames = getResources().getStringArray(R.array.android_version_names);
        mArcColors = getResources().getIntArray(R.array.android_version_colors);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mLeft = mWidth/2 - mWidth/3;
        mTop = mHeight/2 - mWidth/3;
        mRight = mWidth/2 + mWidth/3;
        mBottom = mHeight/2 + mWidth/3;

        mArcSpace = 0.003f;

        float startAngle = 0;
        for (int i = 0; i < mArcNames.length; i++) {
            mArcs.add(new Arc(mArcNames[i], mLeft, mTop, mRight, mBottom, startAngle, 360 * mArcPercentages[i], true, mArcColors[i]));
            startAngle += 360 * mArcPercentages[i] + 360 * mArcSpace;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mArcs.size(); i++) {
            Arc arc = mArcs.get(i);
            mPaint.setColor(arc.getColor());
            canvas.drawArc(arc.getLeft(), arc.getTop(), arc.getRight(), arc.getBottom(),arc.getStartAngle(), arc.getSweepAngle(), arc.isUseCenter(), mPaint);
        }
    }

}

class Arc{

    private String text;
    private float left, top, right, bottom;
    private float startAngle, sweepAngle;
    private boolean useCenter;
    private int color;

    public Arc(){}

    public Arc(String text, float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, int color){
        this.setText(text);
        this.setLeft(left);
        this.setTop(top);
        this.setRight(right);
        this.setBottom(bottom);
        this.setStartAngle(startAngle);
        this.setSweepAngle(sweepAngle);
        this.setUseCenter(useCenter);
        this.setColor(color);
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
}



































































