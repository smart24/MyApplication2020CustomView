package com.smart.myapplication2020customview.staticview.view_04_canvas_assist.view_04_canvas_geometric_transformation_matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasMatrixView
 *
 * Des: Canvas Custom Matrix
 *
 * Time: 2020/6/7 1:54 PM
 */
public class CanvasCustomMatrixView extends android.view.View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private Matrix mMatrix;

    public CanvasCustomMatrixView(Context context) {
        this(context, null);
    }

    public CanvasCustomMatrixView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasCustomMatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        mMatrix = new Matrix();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        float []pointsSrc = {0, 0, mBitmapWidth, 0, 0, mBitmapHeight, mBitmapWidth, mBitmapHeight};
        float []pointsDst = {0 - 10, 0 + 50, mBitmapWidth + 120, 0 - 90, 0 + 20, mBitmapHeight + 30, mBitmapWidth + 20, mBitmapHeight + 60};

        mMatrix.setPolyToPoly(pointsSrc, 0, pointsDst, 0, 4);

        mMatrix.postTranslate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.grey_700));

        canvas.drawRect(0, 0, mBitmapWidth, mBitmapHeight, mPaint);

        canvas.save();
        canvas.concat(mMatrix);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.restore();

        canvas.save();
        canvas.concat(mMatrix);
        canvas.drawRect(0, 0, mBitmapWidth, mBitmapHeight, mPaint);
        canvas.restore();

    }

}
