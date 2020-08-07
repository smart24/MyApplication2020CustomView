package com.smart.myapplication2020customview.staticview.view_01_basic.view_05_oval;

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

/**
 * FileName: OlympicView
 *
 * Des: 椭圆
 *
 * To Do:
 *
 *  V 1.0: 椭圆  👌
 *
 *  V 2.0: 菊花  👌
 *
 *  V 3.0: 奥运  👈
 *
 * Time: 2020/5/7 2:41 PM
 */
public class OlympicView extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private float mLeft, mTop, mRight, mBottom, mRadius;
    private ArrayList<Circle> mCircles;
    private Circle mCircle;

    public OlympicView(Context context) {
        this(context, null);
    }

    public OlympicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OlympicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(getResources().getColor(R.color.blue_400));

        mCircles = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
        mLeft = mCenterX - mWidth/6;
        mTop = mCenterY - mWidth/6;
        mRight = mCenterX + mWidth/6;
        mBottom = mCenterY + mWidth/6;

        //1. 竖版
//        mRadius = (mRight - mLeft)/2;
//        mCircles.add(new Circle(getResources().getColor(R.color.blue_400), (mCenterX + mRadius/2), (mCenterY - mRadius * 2 - mStrokeWidth * 2), mRadius));
//        mCircles.add(new Circle(getResources().getColor(R.color.grey_700), (mCenterX + mRadius/2), mCenterY, mRadius));
//        mCircles.add(new Circle(getResources().getColor(R.color.red_400), (mCenterX + mRadius/2), (mCenterY + mRadius * 2 + mStrokeWidth * 2), mRadius));
//        mCircles.add(new Circle(getResources().getColor(R.color.yellow_400), (mCenterX - mRadius/2), (mCenterY - mRadius - mStrokeWidth), mRadius));
//        mCircles.add(new Circle(getResources().getColor(R.color.green_400), (mCenterX - mRadius/2), (mCenterY + mRadius + mStrokeWidth), mRadius));

        //2. 横版
        mRadius = (mRight - mLeft)/4;
        mCircles.add(new Circle(getResources().getColor(R.color.blue_400), (mCenterX - mRadius * 2 - mStrokeWidth * 2), (mCenterY - mRadius/2), mRadius));
        mCircles.add(new Circle(getResources().getColor(R.color.grey_700), mCenterX, (mCenterY - mRadius/2), mRadius));
        mCircles.add(new Circle(getResources().getColor(R.color.red_400), (mCenterX + mRadius * 2 + mStrokeWidth * 2), (mCenterY - mRadius/2), mRadius));
        mCircles.add(new Circle(getResources().getColor(R.color.yellow_400), (mCenterX - mRadius - mStrokeWidth), (mCenterY + mRadius/2), mRadius));
        mCircles.add(new Circle(getResources().getColor(R.color.green_400), (mCenterX + mRadius + mStrokeWidth), (mCenterY + mRadius/2), mRadius));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 单个圆
//        canvas.drawOval(mLeft, mTop, mRight, mBottom, mPaint);

        //2. 奥运五环
        for (int i = 0; i < mCircles.size(); i++) {
            mCircle = mCircles.get(i);
            mPaint.setColor(mCircle.getColor());
            canvas.drawCircle(mCircle.getX(), mCircle.getY(), mCircle.getRadius(), mPaint);
        }
    }

}

class Circle{

    private int color;
    private float x, y;
    private float radius;

    public Circle(){}

    public Circle(int color, float x, float y, float radius){
        this.setColor(color);
        this.setX(x);
        this.setY(y);
        this.setRadius(radius);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

}



































































