package com.smart.myapplication2020customview.staticview.view_04_canvas_assist.view_01_canvas_clip_rect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasClipRectView
 *
 * Des: Canvas clipRect()
 *
 * Time: 2020/6/6 6:04 PM
 */
public class CanvasClipRectView extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private Rect mRect;

    public CanvasClipRectView(Context context) {
        this(context, null);
    }

    public CanvasClipRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.white));
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mPaint.setStrokeWidth(mStrokeWidth);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();

        mRect = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mRect.left = (int)((mWidth - mBitmapWidth)/2);
        mRect.top = (int)((mHeight - mBitmapHeight)/2);
        mRect.right = (int)((mWidth - mBitmapWidth)/2 + mBitmapWidth);
        mRect.bottom = (int)((mHeight - mBitmapHeight)/2 + mBitmapHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.grey_700));

        canvas.drawRect(mRect, mPaint);

        //1. 裁剪和绘制的区域不一致
//        canvas.save();
//        canvas.clipRect(mRect);
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        canvas.restore();

        //2. 裁剪和绘制的区域一致
//        canvas.save();
//        canvas.clipRect(mRect);
//        canvas.translate(mRect.left, mRect.top);
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        canvas.restore();

//        canvas.save();
//        canvas.translate(mRect.left, mRect.top);
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        canvas.clipRect(mRect);
//        canvas.restore();

//        canvas.save();
//        canvas.translate(mRect.left, mRect.top);
//        canvas.clipRect(mRect);
//        canvas.translate(mRect.left, mRect.top);
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        canvas.restore();

        //3. 最简单的方法
        canvas.save();
        canvas.clipRect(mRect);
        canvas.drawBitmap(mBitmap, mRect.left, mRect.top, mPaint);
        canvas.restore();
    }

}
