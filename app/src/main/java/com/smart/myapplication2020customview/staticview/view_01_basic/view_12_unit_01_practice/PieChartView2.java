package com.smart.myapplication2020customview.staticview.view_01_basic.view_12_unit_01_practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

import java.util.ArrayList;


public class PieChartView2 extends View {

    private Paint mPaint;
    private float mStrokeWidth, mFontSize;
    private int mWidth, mHeight;
    private int mRadius;
    private int mLeft, mTop, mRight, mBottom;
    private ArrayList<Arc> mArcs;
    private float [] mArcPercentages;
    private String [] mArcNames;
    private int [] mArcColors;
    private float mArcSpace;
    private ArrayList<Point> mPoints;
    private float mPointPadding;

    public PieChartView2(Context context) {
        this(context, null);
    }

    public PieChartView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro);
        mPaint.setStrokeWidth(mStrokeWidth);
        mFontSize = getResources().getDimension(R.dimen.font_micro_extra);
        mPaint.setTextSize(mFontSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(getResources().getColor(R.color.blue_400));

        mArcs = new ArrayList<>();

        mArcPercentages = new float[]{0.261f, 0.141f, 0.101f, 0.181f, 0.201f, 0.097f};
        mArcNames = getResources().getStringArray(R.array.android_version_names);
        mArcColors = getResources().getIntArray(R.array.android_version_colors);

        mPoints = new ArrayList<>();
        mPointPadding = getResources().getDimension(R.dimen.padding_small);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mRadius = mWidth/3;

        mLeft = mWidth/2 - mRadius;
        mTop = mHeight/2 - mRadius;
        mRight = mWidth/2 + mRadius;
        mBottom = mHeight/2 + mRadius;

        mArcSpace = 0.003f;

        float startAngle = 0;
        for (int i = 0; i < mArcNames.length; i++) {
            mArcs.add(new Arc(mArcNames[i], mLeft, mTop, mRight, mBottom, startAngle, 360 * mArcPercentages[i], true, mArcColors[i]));
//            mPoints.add(new Point((int)((mRadius + mPointPadding) * Math.cos((startAngle + 360 * mArcPercentages[i]/2) * 3.14 /180)), (int)((mRadius + mPointPadding) * Math.sin((startAngle + 360 * mArcPercentages[i]/2)  * 3.14 /180))));
//            Log.e("TAG", " MANUAL  " + (startAngle + 360 * mArcPercentages[i]/2) * 3.14 /180);
//            Log.e("TAG", " JAVA  " + Math.toRadians(startAngle + 360 * mArcPercentages[i]/2));
            mPoints.add(new Point((int)((mRadius + mPointPadding) * Math.cos(Math.toRadians(startAngle + 360 * mArcPercentages[i]/2))), (int)((mRadius + mPointPadding) * Math.sin((Math.toRadians(startAngle + 360 * mArcPercentages[i]/2))))));
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
            canvas.save();
            canvas.translate(mWidth/2, mHeight/2);
            canvas.drawPoint(mPoints.get(i).x, mPoints.get(i).y, mPaint);
            canvas.restore();
        }
    }

}



































































