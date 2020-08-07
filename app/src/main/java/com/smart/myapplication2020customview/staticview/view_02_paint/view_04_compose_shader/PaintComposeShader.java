package com.smart.myapplication2020customview.staticview.view_02_paint.view_04_compose_shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;


public class PaintComposeShader extends View {

    private Paint mBackgroundPaint, mSrcPaint, mDstPaint;
    private float mBackgroundStrokeWidth;
    private PathEffect mPathEffect;
    private float mWidth, mHeight;
    private float mPadding;
    private float mMinimumEdge;
    private float mCenterX, mCenterY;
    private float mRadius;
    private Path mPath;

    public PaintComposeShader(Context context) {
        this(context, null);
    }

    public PaintComposeShader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintComposeShader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(getResources().getColor(R.color.grey_400));
        mBackgroundPaint.setStyle(Paint.Style.STROKE);
        mBackgroundStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mBackgroundPaint.setStrokeWidth(mBackgroundStrokeWidth);
        mPathEffect = new DashPathEffect(new float[]{getResources().getDimension(R.dimen.padding_micro), getResources().getDimension(R.dimen.padding_micro)}, getResources().getDimension(R.dimen.padding_micro));
        mBackgroundPaint.setPathEffect(mPathEffect);

        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setColor(getResources().getColor(R.color.blue_400));

        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setColor(getResources().getColor(R.color.red_400 ));

        mPadding = getResources().getDimension(R.dimen.padding_medium);

        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mMinimumEdge = (mWidth < mHeight ? mWidth : mHeight) - (mPadding * 2);

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mRadius = mMinimumEdge * 2 / 3 / 2;

        mPath.addRect(mCenterX - mMinimumEdge/2, mCenterY - mMinimumEdge/2, mCenterX + mMinimumEdge/2, mCenterY + mMinimumEdge/2, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath, mBackgroundPaint);
        canvas.drawRect(mCenterX - mMinimumEdge/2 + mPadding, mCenterY + mMinimumEdge/2  - mPadding - mMinimumEdge * 2/3, mCenterX - mMinimumEdge/2 + mPadding + mMinimumEdge * 2/3, mCenterY + mMinimumEdge/2  - mPadding, mSrcPaint);
        canvas.drawCircle(mCenterX + mMinimumEdge/2 - mPadding - mRadius, mCenterY - mMinimumEdge/2 + mPadding + mRadius, mRadius, mDstPaint);

    }

}


































































































































