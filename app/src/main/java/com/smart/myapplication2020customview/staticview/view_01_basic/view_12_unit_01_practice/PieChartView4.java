package com.smart.myapplication2020customview.staticview.view_01_basic.view_12_unit_01_practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

import java.util.ArrayList;


public class PieChartView4 extends View {
    private Paint mPaint, mCoverCirclePaint, mCoverPathPaint;
    private float mStrokeWidth, mCoverStrokeWidth, mFontSize;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private int mRadius;
    private int mLeft, mTop, mRight, mBottom;
    private ArrayList<Arc4> mArcs;
    private String[] mArcNames;
    private int[] mArcValues;
    private int[] mArcColors;
    private int mArcTotalValue;
    private float mArcPadding;
    private Path mCoverPath;

    public PieChartView4(Context context) {
        this(context, null);
    }

    public PieChartView4(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStyle(Paint.Style.STROKE);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mPaint.setStrokeWidth(mStrokeWidth);
        mFontSize = getResources().getDimension(R.dimen.font_micro_extra);
        mPaint.setTextSize(mFontSize);

        mCoverCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCoverCirclePaint.setStyle(Paint.Style.STROKE);
        mCoverCirclePaint.setColor(getResources().getColor(R.color.blue_400));
        mCoverStrokeWidth = getResources().getDimension(R.dimen.padding_small);
        mCoverCirclePaint.setStrokeWidth(mCoverStrokeWidth);
        mCoverCirclePaint.setTextSize(mFontSize);

        mCoverPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCoverPathPaint.setStyle(Paint.Style.FILL);
        mCoverPathPaint.setColor(getResources().getColor(R.color.white));
        mCoverPathPaint.setStrokeWidth(mCoverStrokeWidth);
        mCoverPathPaint.setTextSize(mFontSize);

        mArcs = new ArrayList<>();

        mArcNames = getResources().getStringArray(R.array.android_version_names);
        mArcValues = getResources().getIntArray(R.array.android_version_values);
        mArcColors = getResources().getIntArray(R.array.android_version_colors);

        for (int arcValue : mArcValues) {
            mArcTotalValue += arcValue;
        }

        mArcPadding = getResources().getDimension(R.dimen.padding_micro_x);

        mCoverPath = new Path();
        mCoverPath.setFillType(Path.FillType.EVEN_ODD);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mRadius = (mWidth <= mHeight ? mWidth : mHeight) / 3;

        mLeft = mWidth / 2 - mRadius;
        mTop = mHeight / 2 - mRadius;
        mRight = mWidth / 2 + mRadius;
        mBottom = mHeight / 2 + mRadius;

        float startAngle = 0;
        float currentAngle = 0;
        float currentRadius = 0;
        float deltaArcPadding = 0;
        for (int i = 0; i < mArcNames.length; i++) {
            currentAngle = 360 * (mArcValues[i]/(float)mArcTotalValue);
            currentRadius = (float) Math.toRadians(startAngle + currentAngle / 2);
//            deltaArcPadding = (float) (mArcPadding/(Math.sin(Math.toRadians(currentAngle))));
            deltaArcPadding = (float) (mArcPadding/(Math.abs(Math.sin(Math.toRadians(currentAngle)))));
//            deltaArcPadding = (float) (mArcPadding/(currentAngle % 180 == 0 ? 1 : Math.sin(Math.toRadians(currentAngle))));
//            deltaArcPadding = (float) (Math.abs(mArcPadding/(currentAngle % 180 == 0 ? 1 : Math.sin(Math.toRadians(currentAngle)))));
            Log.e(Constants.TAG,

                    "  value  " + (mArcValues[i]) +
                            "  totalValue  " + (mArcTotalValue) +
                            "  percentage  " + (mArcValues[i]/(float)mArcTotalValue * 100) +
                            "  currentAngle  " + (360 * (mArcValues[i]/(float)mArcTotalValue)) +
                            "  arcPadding  " + (mArcPadding) +
                            "  currentAngle  " + (currentAngle) +
                            "  Math.toRadians(currentAngle)  " + (Math.toRadians(currentAngle)) +
                            "  Math.abs(Math.sin(Math.toRadians(currentAngle)))  " + (Math.abs(Math.sin(Math.toRadians(currentAngle)))) +
                            "  deltaArcPadding  " + (deltaArcPadding)
            );
//            if (i == 0) {
//                mArcs.add(
//                        new Arc4(
//                                mLeft + (float) (mArcPadding * Math.cos(currentRadius)),
//                                mTop + (float) (mArcPadding * Math.sin(currentRadius)),
//                                mRight + (float) (mArcPadding * Math.cos(currentRadius)),
//                                mBottom + (float) (mArcPadding * Math.sin(currentRadius)),
//                                startAngle,
//                                currentAngle,
//                                true,
//                                mArcColors[i]
//                        )
//                );
//            }else{
//                mArcs.add(
//                        new Arc4(
//                                mLeft,
//                                mTop,
//                                mRight,
//                                mBottom,
//                                startAngle,
//                                currentAngle,
//                                true,
//                                mArcColors[i]
//                        )
//                );
//            }

//            mArcs.add(
//                    new Arc4(
//                            mLeft + (float) (mArcPadding * Math.cos(currentRadius)),
//                            mTop + (float) (mArcPadding * Math.sin(currentRadius)),
//                            mRight + (float) (mArcPadding * Math.cos(currentRadius)),
//                            mBottom + (float) (mArcPadding * Math.sin(currentRadius)),
//                            startAngle,
//                            currentAngle,
//                            true,
//                            mArcColors[i]
//                    )
//            );

//            if (i == 0) {
//                mArcs.add(
//                        new Arc4(
//                                mLeft,
//                                mTop,
//                                mRight,
//                                mBottom,
//                                startAngle,
//                                currentAngle,
//                                true,
//                                mArcColors[i]
//                        )
//                );
//            }else{
//                mArcs.add(
//                        new Arc4(
//                                mLeft + (float) (mArcPadding * Math.cos(currentRadius)),
//                                mTop + (float) (mArcPadding * Math.sin(currentRadius)),
//                                mRight + (float) (mArcPadding * Math.cos(currentRadius)),
//                                mBottom + (float) (mArcPadding * Math.sin(currentRadius)),
//                                startAngle,
//                                currentAngle,
//                                true,
//                                mArcColors[i]
//                        )
//                );
//            }

//            mArcs.add(
//                    new Arc4(
//                            mLeft,
//                            mTop,
//                            mRight,
//                            mBottom,
//                            startAngle,
//                            currentAngle,
//                            true,
//                            mArcColors[i]
//                    )
//            );


////////////////////////////////////////////////////////////////////////////////////////////////////


//            mArcs.add(
//                    new Arc4(
//                            mLeft + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.cos(currentRadius)),
//                            mTop + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.sin(currentRadius)),
//                            mRight + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.cos(currentRadius)),
//                            mBottom + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.sin(currentRadius)),
//                            startAngle,
//                            currentAngle,
//                            true,
//                            mArcColors[i]
//                    )
//            );

//            mArcs.add(
//                    new Arc4(
//                            mLeft + (float) ((mArcPadding) * Math.cos(currentRadius)),
//                            mTop + (float) ((mArcPadding) * Math.sin(currentRadius)),
//                            mRight + (float) ((mArcPadding) * Math.cos(currentRadius)),
//                            mBottom + (float) ((mArcPadding) * Math.sin(currentRadius)),
//                            startAngle,
//                            currentAngle,
//                            true,
//                            mArcColors[i]
//                    )
//            );

//            mArcs.add(
//                    new Arc4(
//                            mLeft + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.cos(currentRadius)),
//                            mTop + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.sin(currentRadius)),
//                            mRight + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.cos(currentRadius)),
//                            mBottom + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.sin(currentRadius)),
//                            startAngle,
//                            currentAngle,
//                            true,
//                            mArcColors[i]
//                    )
//            );

            //处理方式不对
//            float left = mLeft + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.cos(currentRadius));
//            float top = mTop + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.sin(currentRadius));
//            float right = mRight + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.cos(currentRadius));
//            float bottom = mBottom + (float) ((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.sin(currentRadius));
//            float deltaX = (float) Math.abs(((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.cos(currentRadius)) - (mArcPadding * Math.cos(currentRadius)));
//            float deltaY = (float) Math.abs(((mArcPadding/Math.sin(Math.toRadians(currentAngle))) * Math.sin(currentRadius)) - (mArcPadding * Math.sin(currentRadius)));
//            mArcs.add(
//                    new Arc4(
//                            (Math.cos(currentRadius) >= 0 ? left : left + deltaX),
//                            (Math.sin(currentRadius) >= 0 ? top : top + deltaY),
//                            (Math.cos(currentRadius) >= 0 ? right - deltaX : right),
//                            (Math.sin(currentRadius) >= 0 ? bottom - deltaY : bottom),
//                            startAngle,
//                            currentAngle,
//                            true,
//                            mArcColors[i]
//                    )
//            );

            mArcs.add(
                    new Arc4(
                            mLeft + (float) (deltaArcPadding * Math.cos(currentRadius)),
                            mTop + (float) (deltaArcPadding * Math.sin(currentRadius)),
                            mRight + (float) (deltaArcPadding * Math.cos(currentRadius)),
                            mBottom + (float) (deltaArcPadding * Math.sin(currentRadius)),
                            startAngle,
                            currentAngle,
                            true,
                            mArcColors[i]
                    )
            );
//            Log.e(Constants.TAG,
//                    "onSizeChanged:  startAngle  " + startAngle +
//                    "  currentAngle  " + (startAngle + currentAngle) +
//                    "  offsetAngle  " + (startAngle + currentAngle/2) +
//                    "  currentRadius  " + currentRadius +
//                    "  cos  " + (Math.cos(currentRadius)) +
//                    "  deltaX  " + (mArcPadding * Math.cos(currentRadius)) +
//                    "  sin  " + (Math.sin(currentRadius)) +
//                    "  deltaY  " + (mArcPadding * Math.sin(currentRadius)) +
//                    "  left  " + mLeft +
//                    "  newLeft  " + (mLeft + (float) (mArcPadding * Math.cos(currentRadius))) +
//                    "  top  " + mTop +
//                    "  newTop  " + (mTop + (float) (mArcPadding * Math.sin(currentRadius))) +
//                    "  right  " + mRight +
//                    "  newRight  " + (mRight + (float) (mArcPadding * Math.cos(currentRadius))) +
//                    "  bottom  " + mBottom +
//                    "  newBottom  " + (mBottom + (float) (mArcPadding * Math.sin(currentRadius))) +
//                    "  currentAngle  " + (currentAngle) +
//                    "  currentRadian  " + (Math.toRadians(currentAngle)) +
//                    "  Math.sin(Math.toRadians(currentAngle))  " + (Math.sin(Math.toRadians(currentAngle))) +
//                    "  (mArcPadding/Math.sin(Math.toRadians(currentAngle)))  " + (mArcPadding/Math.sin(Math.toRadians(currentAngle)))
//            );

//            Log.e(Constants.TAG,
//
//                    "  percentage  " + (mArcValues[i]/(float)mArcTotalValue * 100) +
//                    "  currentAngle  " + (currentAngle) +
//                    "  Math.toRadians(currentAngle)  " + (Math.toRadians(currentAngle)) +
//                    "  Math.sin(Math.toRadians(currentAngle))  " + (Math.sin(Math.toRadians(currentAngle))) +
//                    "  deltaArcPadding  " + (deltaArcPadding) +
//                    "  left  " + (mLeft + (float) (deltaArcPadding * Math.cos(currentRadius))) +
//                    "  top  " + (mTop + (float) (deltaArcPadding * Math.sin(currentRadius))) +
//                    "  right  " + (mRight + (float) (deltaArcPadding * Math.cos(currentRadius))) +
//                    "  bottom  " + (mBottom + (float) (deltaArcPadding * Math.sin(currentRadius)))
//            );
            startAngle += currentAngle;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mArcs.size(); i++) {
            Arc4 arc = mArcs.get(i);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(arc.getColor());
            canvas.drawArc(arc.getLeft(), arc.getTop(), arc.getRight(), arc.getBottom(), arc.getStartAngle(), arc.getSweepAngle(), arc.isUseCenter(), mPaint);
//            mPaint.setStyle(Paint.Style.STROKE);
//            if(i == 1){
//                canvas.drawRect(arc.getLeft(), arc.getTop(), arc.getRight(), arc.getBottom(), mPaint);
//                canvas.drawCircle(mCenterX, mCenterY, (mRadius + mArcPadding), mPaint);
//            }
            mPaint.setColor(getResources().getColor(R.color.translucent_blue_700));
            canvas.drawCircle(mCenterX, mCenterY, mArcPadding, mPaint);
//            canvas.drawRect((mLeft - mArcPadding), (mTop - mArcPadding), (mRight + mArcPadding), (mBottom + mArcPadding), mPaint);
//            canvas.drawCircle(mCenterX, mCenterY, mRadius + mArcPadding, mCoverCirclePaint);
//            canvas.save();
//            canvas.translate(mWidth / 2, mHeight / 2);
//            canvas.restore();
        }
//        mCoverPath.addRect(0, 0, mWidth, mHeight, Path.Direction.CW);
//        mCoverPath.addCircle(mCenterX, mCenterY, mRadius - mArcPadding/2, Path.Direction.CW);
//        mCoverPath.addCircle(mCenterX, mCenterY, mRadius * 4/5, Path.Direction.CW);
//        canvas.drawPath(mCoverPath, mCoverPathPaint);
    }
}

class Arc4 {

    private float left, top, right, bottom;
    private float startAngle, sweepAngle;
    private boolean useCenter;
    private int color;

    public Arc4() {}

    public Arc4(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, int color) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
        this.useCenter = useCenter;
        this.color = color;
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





































































































































































































































































































































