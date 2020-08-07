package com.smart.myapplication2020customview.staticview.view_02_paint.view_06_xfermode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;


public class PaintXfermode extends View {

    private Paint mBackgroundPaint, mGraphPaint;
    private float mBackgroundStrokeWidth;
    private PathEffect mPathEffect;
    private float mWidth, mHeight;
    private float mPadding;
    private float mMinimumEdge;
    private float mCenterX, mCenterY;
    private float mRadius;
    private Path mPath;
    private Xfermode mXfermode;

    public PaintXfermode(Context context) {
        this(context, null);
    }

    public PaintXfermode(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintXfermode(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        mGraphPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPadding = getResources().getDimension(R.dimen.padding_medium);

        mPath = new Path();

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mMinimumEdge = (mWidth < mHeight ? mWidth : mHeight) * 3/4;

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mRadius = mMinimumEdge * 3 / 5 / 2;

        mPath.addRect(mCenterX - mMinimumEdge/2, mCenterY - mMinimumEdge/2, mCenterX + mMinimumEdge/2, mCenterY + mMinimumEdge/2, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath, mBackgroundPaint);

        int layerId = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);

        // DST
        mGraphPaint.setColor(getResources().getColor(R.color.red_400));
        canvas.drawCircle(mCenterX + mMinimumEdge/2 - mPadding - mRadius, mCenterY - mMinimumEdge/2 + mPadding + mRadius, mRadius, mGraphPaint);

        //不知道为什么不起作用
//        mGraphPaint.setXfermode(mXfermode);

        //只有这样设置的时候才起作用
//        mGraphPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        mGraphPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        mGraphPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST));
//        mGraphPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        // SRC
        mGraphPaint.setColor(getResources().getColor(R.color.blue_400));
        canvas.drawRect(mCenterX - mMinimumEdge/2 + mPadding, mCenterY + mMinimumEdge/2  - mPadding - mMinimumEdge * 3 / 5, mCenterX - mMinimumEdge/2 + mPadding + mMinimumEdge * 3 / 5, mCenterY + mMinimumEdge/2  - mPadding, mGraphPaint);
        mGraphPaint.setXfermode(null); // 用完及时清除 Xfermode

        canvas.restoreToCount(layerId);

    }

}


































































































































