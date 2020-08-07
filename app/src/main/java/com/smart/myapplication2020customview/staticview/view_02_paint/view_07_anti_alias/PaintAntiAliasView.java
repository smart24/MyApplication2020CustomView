package com.smart.myapplication2020customview.staticview.view_02_paint.view_07_anti_alias;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;


public class PaintAntiAliasView extends View {

    private Paint mPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mRadius;
    private String mBlack, mWhite;

    public PaintAntiAliasView(Context context) {
        this(context, null);
    }

    public PaintAntiAliasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintAntiAliasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setTextAlign(Paint.Align.CENTER);
        mTextSize = getResources().getDimension(R.dimen.font_forty_eight);
        mPaint.setTextSize(mTextSize);

        mBlack = "黑";
        mWhite = "白";
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mRadius = (mWidth < mHeight ? mWidth : mHeight)/4;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(false);
        mPaint.setColor(getResources().getColor(R.color.white));
        canvas.drawRect(0, 0, mWidth, mHeight/2, mPaint);
        mPaint.setColor(getResources().getColor(R.color.grey_800));
        canvas.drawCircle(mCenterX, mCenterY/2, mCenterY/2 * 3/4, mPaint);
        mPaint.setColor(getResources().getColor(R.color.white));
        canvas.drawText(mWhite, mCenterX, mCenterY/2 + mTextSize * 1/3, mPaint);

        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.grey_800));
        canvas.drawRect(0, mHeight/2, mWidth, mHeight, mPaint);
        mPaint.setColor(getResources().getColor(R.color.white));
        canvas.drawCircle(mCenterX, mHeight/2 + mCenterY/2, mCenterY/2 * 3/4, mPaint);
        mPaint.setColor(getResources().getColor(R.color.grey_800));
        canvas.drawText(mBlack, mCenterX, mHeight/2 + mCenterY/2 + mTextSize * 1/3, mPaint);
    }

}


































































































































