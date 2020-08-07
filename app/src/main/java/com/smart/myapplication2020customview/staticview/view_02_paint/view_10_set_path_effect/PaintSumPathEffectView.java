package com.smart.myapplication2020customview.staticview.view_02_paint.view_10_set_path_effect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintSumPathEffectView
 *
 * Des: SumPathEffect
 *
 * Time: 2020/6/2 2:50 PM
 */
class PaintSumPathEffectView extends View {

    private Paint mSrcPaint, mDstPaint;
    private float mStrokeWidth;
    private PathEffect mPathEffect;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mDeltaX, mDeltaY;
    private Path mPath;

    public PaintSumPathEffectView(Context context) {
        this(context, null);
    }

    public PaintSumPathEffectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintSumPathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setStyle(Paint.Style.STROKE);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mSrcPaint.setStrokeWidth(mStrokeWidth);


        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setStyle(Paint.Style.STROKE);
        mDstPaint.setStrokeWidth(mStrokeWidth);

//        PathEffect dashEffect = new DashPathEffect(new float[]{getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_micro_x)}, getResources().getDimension(R.dimen.padding_zero));
//        PathEffect discreteEffect = new DiscretePathEffect(getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_micro_x));
//        mPathEffect = new SumPathEffect(dashEffect, discreteEffect);
//        mDstPaint.setPathEffect(mPathEffect);

        PathEffect discreteEffect1 = new DiscretePathEffect(getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_micro_x));
        PathEffect discreteEffect2 = new DiscretePathEffect(getResources().getDimension(R.dimen.padding_medium_x), getResources().getDimension(R.dimen.padding_micro));
        mPathEffect = new SumPathEffect(discreteEffect1, discreteEffect2);
        mDstPaint.setPathEffect(mPathEffect);
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

        mPath = new Path();
        //1. 矩形
//        mPath.moveTo((mCenterX - mDeltaX), (mCenterY - mDeltaY));
//        mPath.lineTo((mCenterX + mDeltaX), (mCenterY - mDeltaY));
//        mPath.lineTo((mCenterX + mDeltaX), (mCenterY + mDeltaY));
//        mPath.lineTo((mCenterX - mDeltaX), (mCenterY + mDeltaY));
//        mPath.lineTo((mCenterX - mDeltaX), (mCenterY - mDeltaY));

        //2. W
        mPath.moveTo((mCenterX - mDeltaX * 3/2), mCenterY);
        mPath.lineTo((mCenterX - mDeltaX * 3/4), (mCenterY - mDeltaY));
        mPath.lineTo(mCenterX, mCenterY);
        mPath.lineTo((mCenterX + mDeltaX * 3/4), (mCenterY + mDeltaY));
        mPath.lineTo((mCenterX + mDeltaX * 3/2), mCenterY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //原图
        canvas.save();
        canvas.translate(0, - (mDeltaY + (mHeight - (mCenterY - mDeltaY) * 2)/6));
        canvas.drawPath(mPath, mSrcPaint);
        canvas.restore();

        //加 CornerPathEffect 之后
        canvas.save();
        canvas.translate(0,  (mDeltaY + (mHeight - (mCenterY - mDeltaY) * 2)/6));
        canvas.drawPath(mPath, mDstPaint);
        canvas.restore();
    }

}





































































































































