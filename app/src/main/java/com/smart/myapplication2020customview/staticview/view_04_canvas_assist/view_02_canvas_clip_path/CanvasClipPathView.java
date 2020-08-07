package com.smart.myapplication2020customview.staticview.view_04_canvas_assist.view_02_canvas_clip_path;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasClipRectView
 *
 * Des: Canvas clipPath()
 *
 * Time: 2020/6/6 6:04 PM
 */
public class CanvasClipPathView extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private Path mPath;
    private float mRadius;
    private RectF mRectF;
    private CornerPathEffect mCornerPathEffect;

    public CanvasClipPathView(Context context) {
        this(context, null);
    }

    public CanvasClipPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.white));
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro);
        mPaint.setStrokeWidth(mStrokeWidth);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();

        mRectF = new RectF();

        mPath = new Path();

        mCornerPathEffect = new CornerPathEffect(getResources().getDimension(R.dimen.padding_micro_xx));
        mPaint.setPathEffect(mCornerPathEffect);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mRadius = (mBitmapWidth < mBitmapHeight ? mBitmapWidth : mBitmapHeight) / 2;

        //1. Circle
//        mPath.addCircle(mWidth/2, mHeight/2, mRadius, Path.Direction.CW);

        //2. Rect
//        mRectF.left = (int)((mWidth - mBitmapWidth)/2);
//        mRectF.top = (int)((mHeight - mBitmapHeight)/2);
//        mRectF.right = (int)((mWidth - mBitmapWidth)/2 + mBitmapWidth);
//        mRectF.bottom = (int)((mHeight - mBitmapHeight)/2 + mBitmapHeight);
//        mPath.addRoundRect(mRectF, mStrokeWidth, mStrokeWidth, Path.Direction.CW);

        //3. Triangle
        mPath.moveTo(mWidth/2, mHeight/2 - mBitmapHeight/2);
        mPath.lineTo(mWidth/2 + mBitmapWidth/2, mHeight/2 + mBitmapHeight/2);
        mPath.lineTo(mWidth/2 - mBitmapWidth/2, mHeight/2 + mBitmapHeight/2);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.grey_700));

        canvas.drawPath(mPath, mPaint);

        canvas.save();
        canvas.clipPath(mPath);
        canvas.drawBitmap(mBitmap, (mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2, mPaint);
        canvas.restore();
    }

}
