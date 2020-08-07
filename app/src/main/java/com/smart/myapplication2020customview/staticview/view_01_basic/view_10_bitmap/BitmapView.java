package com.smart.myapplication2020customview.staticview.view_01_basic.view_10_bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;


public class BitmapView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mLeft, mTop, mRight, mBottom;
    private Bitmap mBitmap;
    private int mBitmapWidth, mBitmapHeight;
    private int mBitmapLeft, mBitmapTop;

    public BitmapView(Context context) {
        this(context, null);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.padding_micro_x));
        mPaint.setColor(getResources().getColor(R.color.blue_400));

        mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_aojiao);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
        mLeft = mCenterX - mWidth/8;
        mRight = mCenterX + mWidth/8;
        mTop = mCenterY - mWidth/8;
        mBottom = mCenterY + mWidth/8;

        mBitmapLeft = (mWidth - mBitmapWidth) /2;
        mBitmapTop = (mHeight - mBitmapHeight)/2;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 单 Bitmap
        //canvas.drawBitmap(@NonNull Bitmap bitmap, float left, float top, @android.annotation.Nullable Paint paint)
        //canvas.drawBitmap(@NonNull Bitmap bitmap, @Nullable Rect src, @NonNull RectF dst, @Nullable Paint paint)
//        canvas.drawBitmap(mBitmap, mBitmapLeft, mBitmapTop, mPaint);
        canvas.drawBitmap(mBitmap, null, new RectF(mLeft, mTop, mRight, mBottom), mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.translucent_blue_700));
//        canvas.drawRect(new RectF(mLeft, mTop, mRight, mBottom), mPaint);
        canvas.drawRoundRect(new RectF(mLeft, mTop, mRight, mBottom), getResources().getDimension(R.dimen.padding_micro), getResources().getDimension(R.dimen.padding_micro), mPaint);

        //2. 多 Bitmap（旋转画布）
//        for (int i = 0; i < 360; i+=15) {
//            canvas.save();
//            canvas.rotate(i, mCenterX, mCenterY);
//            canvas.drawBitmap(mBitmap, mBitmapLeft, mBitmapTop, mPaint);
//            canvas.restore();
//        }

    }

}





































































