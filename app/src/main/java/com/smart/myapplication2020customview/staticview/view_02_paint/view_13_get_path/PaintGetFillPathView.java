package com.smart.myapplication2020customview.staticview.view_02_paint.view_13_get_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintGetFillPathView
 *
 * Des: GetFillPath
 *
 * Time: 2020/6/2 5:34 PM
 */
public class PaintGetFillPathView extends android.view.View {

    private Paint mSrcPaint, mDstPaint;
    private float mSrcStrokeWidth, mDstStrokeWidth;
    private Path mOriginPath;
    private PathEffect mPathEffect;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mDeltaX, mDeltaY;
    private Path mSrcPath, mDstPath;

    public PaintGetFillPathView(Context context) {
        this(context, null);
    }

    public PaintGetFillPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintGetFillPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setStyle(Paint.Style.STROKE);
        mSrcStrokeWidth = getResources().getDimension(R.dimen.padding_medium);
        mSrcPaint.setStrokeWidth(mSrcStrokeWidth);


        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setStyle(Paint.Style.STROKE);
        mDstStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mDstPaint.setStrokeWidth(mDstStrokeWidth);

        //CornerPathEffect
        mPathEffect = new CornerPathEffect(getResources().getDimension(R.dimen.padding_two_hundred_fifty_six));

        //DiscretePathEffect
//        mPathEffect = new DiscretePathEffect(getResources().getDimension(R.dimen.padding_small_x), getResources().getDimension(R.dimen.padding_micro_x));

        //DashPathEffect
//        mPathEffect = new DashPathEffect(new float[]{getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_micro)}, getResources().getDimension(R.dimen.padding_zero));

        //PathDashPathEffect
//        mOriginPath = new Path();
//        mOriginPath.addCircle(getResources().getDimension(R.dimen.padding_zero), getResources().getDimension(R.dimen.padding_zero), getResources().getDimension(R.dimen.padding_micro_x), Path.Direction.CW);
//        mPathEffect = new PathDashPathEffect(mOriginPath, getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_zero), PathDashPathEffect.Style.TRANSLATE);

        //SumPathEffect
//        PathEffect discreteEffect1 = new DiscretePathEffect(getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_micro_x));
//        PathEffect discreteEffect2 = new DiscretePathEffect(getResources().getDimension(R.dimen.padding_medium_x), getResources().getDimension(R.dimen.padding_micro));
//        mPathEffect = new SumPathEffect(discreteEffect1, discreteEffect2);

        //setPathEffect
//        PathEffect dashPathEffect = new DashPathEffect(new float[]{getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_micro_x)}, getResources().getDimension(R.dimen.padding_zero));
//        PathEffect discretePathEffect = new DiscretePathEffect(getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_micro_x));
//        mPathEffect = new ComposePathEffect(dashPathEffect, discretePathEffect);

        mSrcPaint.setPathEffect(mPathEffect);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mDeltaX = (mWidth / 2) / 3;
        mDeltaY = (mHeight / 2) / 3;

        mSrcPath = new Path();
        mDstPath = new Path();

        //1. 矩形
//        mSrcPath.moveTo((mCenterX - mDeltaX), (mCenterY - mDeltaY));
//        mSrcPath.lineTo((mCenterX + mDeltaX), (mCenterY - mDeltaY));
//        mSrcPath.lineTo((mCenterX + mDeltaX), (mCenterY + mDeltaY));
//        mSrcPath.lineTo((mCenterX - mDeltaX), (mCenterY + mDeltaY));
//        mSrcPath.lineTo((mCenterX - mDeltaX), (mCenterY - mDeltaY));

        //2. W
        mSrcPath.moveTo((mCenterX - mDeltaX * 3/2), mCenterY);
        mSrcPath.lineTo((mCenterX - mDeltaX * 3/4), (mCenterY - mDeltaY));
        mSrcPath.lineTo(mCenterX, mCenterY);
        mSrcPath.lineTo((mCenterX + mDeltaX * 3/4), (mCenterY + mDeltaY));
        mSrcPath.lineTo((mCenterX + mDeltaX * 3/2), mCenterY);

        mSrcPaint.getFillPath(mSrcPath, mDstPath);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //原图
        canvas.save();
        canvas.translate(0, - mDeltaY);
        canvas.drawPath(mSrcPath, mSrcPaint);
        canvas.restore();

        //加 CornerPathEffect 之后
        canvas.save();
        canvas.translate(0,  mDeltaY);
        canvas.drawPath(mDstPath, mDstPaint);
        canvas.restore();

    }

}


































































































































