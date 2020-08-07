package com.smart.myapplication2020customview.staticview.view_02_paint.view_16_unit_02_practice;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintXfermode3
 *
 * Des: Paint xfermode 解析
 *
 * Time: 2020/5/20 2:32 PM
 */
public class PaintBitmapXfermodeView extends View implements View.OnClickListener, ValueAnimator.AnimatorUpdateListener {

    private int mBackgroundColor;
    private Paint mPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private RectF mRectF;
    private float mRectCornerRadius;
    private Xfermode mXfermode;
    private ValueAnimator mValueAnimator;
    private float mStartingPoint;

    public PaintBitmapXfermodeView(Context context) {
        this(context, null);
    }

    public PaintBitmapXfermodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintBitmapXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mBackgroundColor = getResources().getColor(R.color.white);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.grey_400));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mTextSize = getResources().getDimension(R.dimen.font_twenty_eight);
        mPaint.setTextSize(mTextSize);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);

        mRectF = new RectF();
        mStartingPoint = getResources().getInteger(R.integer.number_zero);

        mRectCornerRadius = getResources().getDimension(R.dimen.padding_zero);

        //1. Alpha 合成 (Alpha Compositing)
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);

        //2. 混合 (Blending)
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.OVERLAY);

        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(mBackgroundColor);

        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

        //1. DST
        canvas.drawBitmap(mBitmap, (mCenterX - mBitmapWidth/2), (mCenterY - mBitmapHeight/2), mPaint);
        mPaint.setXfermode(mXfermode);//设置 Xfermode

        //2. SRC
        mPaint.setColor(getResources().getColor(R.color.grey_800));
        mRectF.left = mStartingPoint;
        mRectF.top = 0;
        mRectF.right = mWidth;
        mRectF.bottom = mHeight;
        canvas.drawRoundRect(mRectF, mRectCornerRadius, mRectCornerRadius, mPaint);
        mPaint.setXfermode(null);//用完及时清除 Xfermode

        canvas.restoreToCount(saved);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        if(mValueAnimator == null){
            mValueAnimator = ValueAnimator.ofFloat(mStartingPoint, mWidth);
            mValueAnimator.setDuration(getResources().getInteger(R.integer.two_thousand));
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
            mValueAnimator.addUpdateListener(this);
            mValueAnimator.start();
        }else if(mValueAnimator.isPaused()){
            mValueAnimator.resume();
        }else if(mValueAnimator.isRunning()){
            mValueAnimator.pause();
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mStartingPoint = (float)animation.getAnimatedValue();
        invalidate();
    }

}


































































































































