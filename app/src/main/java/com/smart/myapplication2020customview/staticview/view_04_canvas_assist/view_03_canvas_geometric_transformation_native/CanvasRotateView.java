package com.smart.myapplication2020customview.staticview.view_04_canvas_assist.view_03_canvas_geometric_transformation_native;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasRotateView
 *
 * Des: Canvas rotate()
 *
 * Time: 2020/6/6 6:04 PM
 */
public class CanvasRotateView extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;

    public CanvasRotateView(Context context) {
        this(context, null);
    }

    public CanvasRotateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.white));
        mStrokeWidth = getResources().getDimension(R.dimen.padding_zero);
        mPaint.setStrokeWidth(mStrokeWidth);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.grey_700));

        canvas.drawRect(0, 0, mBitmapWidth, mBitmapHeight, mPaint);

        //⚠️注意：由于 Canvas 本身自带的几何变换方法是倒序的，即后写的会先执行
        //因此，如果想要实现「先移动再旋转」，需要这样写：
        //canvas.rotate(float degrees, float px, float py);
        //canvas.translate(float dx, float dy);
        //如果想要实现「先旋转再移动」，需要这样写：
        //canvas.translate(float dx, float dy);
        //canvas.rotate(float degrees, float px, float py);
        //二者的执行效果还真的不一样。

        //1. 先移动再旋转
//        canvas.save();
//        //旋转中心为原点
////        canvas.rotate(45);
//        //旋转中心为 View 的中心
//        canvas.rotate(45, mWidth/2, mHeight/2);
//        canvas.translate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        canvas.restore();

        //2. 先旋转再移动
        mPaint.setAlpha(72);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        canvas.save();
        canvas.rotate(45, mWidth/2, mHeight/2);
        mPaint.setAlpha(172);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);
        canvas.rotate(45, mWidth/2, mHeight/2);
        mPaint.setAlpha(255);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.restore();
    }

}
