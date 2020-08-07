package com.smart.myapplication2020customview.staticview.view_01_basic.view_01_color;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;


public class ColorView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private Bitmap mBitmap;
    private int mBitmapWidth, mBitmapHeight;
    private int mLeft, mTop;

    public ColorView(Context context) {
        super(context);
        init();
    }

    public ColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.translucent_green_700));
        mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_aojiao);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mLeft = (mWidth - mBitmapWidth) /2;
        mTop = (mHeight - mBitmapHeight)/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.blue_400));

        //
        canvas.drawBitmap(mBitmap, mLeft, mTop, mPaint);

        //
        canvas.drawColor(getResources().getColor(R.color.transparent));
//        canvas.drawColor(getResources().getColor(R.color.translucent));
//        canvas.drawColor(getResources().getColor(R.color.translucent_green_700));
//        canvas.drawARGB(24, 128, 128, 128);
    }

}
