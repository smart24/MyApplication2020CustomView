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
 * FileName: CanvasSkewView
 *
 * Des: Canvas skew(float sx, float sy) 错切
 *
 * 参数里的 sx 和 sy 是 x 方向和 y 方向的错切系数
 *
 * Time: 2020/6/7 9:19 AM
 */
public class CanvasSkewView extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;

    public CanvasSkewView(Context context) {
        this(context, null);
    }

    public CanvasSkewView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasSkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        //⚠️注意事项：由于 Canvas 本身自带的几何变换方法是倒序的
        canvas.save();
        canvas.translate((mWidth - mBitmapWidth)/2 * 3/2, 0);
        canvas.skew(-getResources().getFraction(R.fraction.percentage_fifty, 1, 1), getResources().getFraction(R.fraction.percentage_ten, 0, 1));
        canvas.translate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);
        canvas.drawRect(0, 0, mBitmapWidth, mBitmapHeight, mPaint);
        canvas.restore();

    }

}