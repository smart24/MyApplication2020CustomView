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
import java.util.Random;


public class HistogramView extends View implements View.OnClickListener {

    private Paint mPaint;
    private float mStrokeWidth, mFontSize;
    private int mWidth, mHeight, mDelta;
    private ArrayList<Axis> mAxes;
    private Axis mXAxis, mYAxis;
    private ArrayList<Column> mColumns;
    private String [] mColumnNames;
    private int [] mColumnColors;
    private float mColumnSpace, mColumnWidth;
    private Random mRandom;

    public HistogramView(Context context) {
        this(context, null);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mPaint.setStrokeWidth(mStrokeWidth);
        mFontSize = getResources().getDimension(R.dimen.font_micro_extra);
        mPaint.setTextSize(mFontSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(getResources().getColor(R.color.blue_400));

        //手绘效果
//        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
//        PathEffect discreteEffect = new DiscretePathEffect(10, 1);
//        PathEffect pathEffect = new SumPathEffect(dashEffect, discreteEffect);
//        mPaint.setPathEffect(discreteEffect);

        mAxes = new ArrayList<>();
        mColumns = new ArrayList<>();

        mColumnNames = getResources().getStringArray(R.array.android_version_names);
        mColumnColors = getResources().getIntArray(R.array.android_version_colors);

        mRandom = new Random();

        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(Constants.DEBUG){
            Log.e(Constants.TAG, "onSizeChanged");
        }

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mDelta = mWidth > mHeight ? mHeight : mWidth;

        mXAxis = new Axis(mWidth/6, mHeight/2 + mDelta/3, mWidth * 5/6, mHeight/2 + mDelta/3); //X 轴
        mYAxis = new Axis(mWidth/6, mHeight/2 + mDelta/3, mWidth/6, mHeight/2 + mDelta/3 - mDelta * 4/6); //Y 轴
        mAxes.add(mXAxis);
        mAxes.add(mYAxis);

        mColumnSpace = ((mXAxis.getStopX() - mXAxis.getStartX())/ mColumnNames.length)/6;
        mColumnWidth = ((mXAxis.getStopX() - mXAxis.getStartX())/ mColumnNames.length) * 4/6;

        for (int i = 0; i < mColumnNames.length; i++) {
            if(Constants.DEBUG){
                Log.e(Constants.TAG, "Random  " + mRandom.nextFloat());
            }
            float textX = (mXAxis.getStartX() + (2 * i + 1) * mColumnSpace + (i * mColumnWidth)) + mColumnWidth/2;
            float textY = mXAxis.getStartY() + mColumnSpace + mFontSize;
            mColumns.add(new Column(mColumnNames[i], textX, textY, (mXAxis.getStartX() + (2 * i + 1) * mColumnSpace + (i * mColumnWidth)), (mYAxis.getStartY() + (mYAxis.getStopY() - mYAxis.getStartY()) * mRandom.nextFloat()), (mXAxis.getStartX() + (2 * i + 1) * mColumnSpace + (i * mColumnWidth) + mColumnWidth), mYAxis.getStartY(), mColumnColors[i]));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(Constants.DEBUG){
            Log.e(Constants.TAG, "onDraw");
        }

        mPaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < mAxes.size(); i++) {
            Axis axis = mAxes.get(i);
            canvas.drawLine(axis.getStartX(), axis.getStartY(), axis.getStopX(), axis.getStopY(), mPaint);
        }

        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < mColumns.size(); i++) {
            Column column = mColumns.get(i);
            mPaint.setColor(column.getColor());
            canvas.drawRect(column.getLeft(), column.getTop(), column.getRight(), column.getBottom(), mPaint);
            canvas.drawText(column.getText(), column.getTextX(), column.getTextY(), mPaint);
        }
    }

    @Override
    public void onClick(View v) {

        if(Constants.DEBUG){
            Log.e(Constants.TAG, "onClick");
        }

        mColumns.clear();

        for (int i = 0; i < mColumnNames.length; i++) {
            float textX = (mXAxis.getStartX() + (2 * i + 1) * mColumnSpace + (i * mColumnWidth)) + mColumnWidth/2;
            float textY = mXAxis.getStartY() + mColumnSpace + mFontSize;
            mColumns.add(new Column(mColumnNames[i], textX, textY, (mXAxis.getStartX() + (2 * i + 1) * mColumnSpace + (i * mColumnWidth)), (mYAxis.getStartY() + (mYAxis.getStopY() - mYAxis.getStartY()) * mRandom.nextFloat()), (mXAxis.getStartX() + (2 * i + 1) * mColumnSpace + (i * mColumnWidth) + mColumnWidth), mYAxis.getStartY(), mColumnColors[i]));
        }

        invalidate();
    }

}

class Axis {

    private float startX;
    private float startY;
    private float stopX;
    private float stopY;

    public Axis(){}

    public Axis(float startX, float startY, float stopX, float stopY){
        this.setStartX(startX);
        this.setStartY(startY);
        this.setStopX(stopX);
        this.setStopY(stopY);
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getStopX() {
        return stopX;
    }

    public void setStopX(float stopX) {
        this.stopX = stopX;
    }

    public float getStopY() {
        return stopY;
    }

    public void setStopY(float stopY) {
        this.stopY = stopY;
    }

}

class Column{

    private String text;
    private float textX, textY;
    private float left, top, right, bottom;
    private int color;

    public Column(){}

    public Column(String text, float textX, float textY, float left, float top, float right, float bottom, int color){
        this.setText(text);
        this.setTextX(textX);
        this.setTextY(textY);
        this.setLeft(left);
        this.setTop(top);
        this.setRight(right);
        this.setBottom(bottom);
        this.setColor(color);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}



































































