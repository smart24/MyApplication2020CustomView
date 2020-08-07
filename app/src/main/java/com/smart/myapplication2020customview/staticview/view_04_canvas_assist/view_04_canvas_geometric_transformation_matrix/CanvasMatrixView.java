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
 * Des: Canvas Matrix
 *
 * postXXX 向后执行 XXX 操作
 *
 * preXXX 向前执行 XXX 操作
 *
 * Time: 2020/6/7 1:54 PM
 */
public class CanvasMatrixView extends android.view.View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private Matrix mMatrix;

    public CanvasMatrixView(Context context) {
        this(context, null);
    }

    public CanvasMatrixView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasMatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        //1. 平移
        //1.1 向后平移
//        mMatrix.postTranslate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);
        //1.2 向前平移
//        mMatrix.preTranslate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);

        //2. 旋转
        //2.1 向后旋转
//        mMatrix.postRotate(45, mWidth/2, mHeight/2);
        //2.2 向前旋转
//        mMatrix.preRotate(45, mWidth/2, mHeight/2);
        //2.3 先平移，再旋转
//        mMatrix.postTranslate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);
//        mMatrix.postRotate(45, mWidth/2, mHeight/2);
        //2.4 先旋转，再平移
//        mMatrix.postTranslate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);
//        mMatrix.preRotate(45, mWidth/2, mHeight/2);
        //上面等价于👇
//        mMatrix.postRotate(45, mWidth/2, mHeight/2);
//        mMatrix.postTranslate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);

        //3. 缩放
        //3.1 向后缩放
//        mMatrix.postScale(getResources().getFraction(R.fraction.percentage_fifty, 1, 1), getResources().getFraction(R.fraction.percentage_fifty, 1, 1), mWidth/2, mHeight/2);
        //3.2 向前缩放
//        mMatrix.preScale(getResources().getFraction(R.fraction.percentage_fifty, 3, 1), getResources().getFraction(R.fraction.percentage_fifty, 3, 1), mWidth/2, mHeight/2);
        //3.3 先平移，再缩放
//        mMatrix.postTranslate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);
//        mMatrix.postScale(getResources().getFraction(R.fraction.percentage_fifty, 3, 1), getResources().getFraction(R.fraction.percentage_fifty, 3, 1), mWidth/2, mHeight/2);

        //3. 错切
        //3.1 向后错切
//        mMatrix.postSkew(-getResources().getFraction(R.fraction.percentage_fifty, 1, 1), getResources().getFraction(R.fraction.percentage_ten, 0, 1));
        //3.2 向前错切
//        mMatrix.preSkew(-getResources().getFraction(R.fraction.percentage_fifty, 1, 1), getResources().getFraction(R.fraction.percentage_ten, 0, 1));
        //3.3 先平移，再旋转
        mMatrix.postTranslate((mWidth - mBitmapWidth)/2, (mHeight - mBitmapHeight)/2);
        mMatrix.postSkew(-getResources().getFraction(R.fraction.percentage_fifty, 1, 1), getResources().getFraction(R.fraction.percentage_ten, 0, 1));
        mMatrix.postTranslate((mWidth - mBitmapWidth)/2 * 3/2, 0);
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
