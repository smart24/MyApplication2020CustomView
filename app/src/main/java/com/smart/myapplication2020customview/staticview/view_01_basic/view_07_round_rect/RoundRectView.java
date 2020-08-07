package com.smart.myapplication2020customview.staticview.view_01_basic.view_07_round_rect;

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

import java.util.ArrayList;

/**
 * FileName: RoundRectView
 *
 * Des:
 *
 * Time: 2020/5/8 11:49 AM
 */
public class RoundRectView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mLeft, mTop, mRight, mBottom;
    private float mRadiusX, mRadiusY;
    private ArrayList<RoundRect> mRoundRects;
    private RoundRect mRoundRect;

    public RoundRectView(Context context) {
        this(context, null);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.padding_micro_x));
        mPaint.setColor(getResources().getColor(R.color.blue_400));

        mRoundRects = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        mLeft = mCenterX - mWidth / 8;
        mTop = mCenterY - mHeight / 8;
        mRight = mCenterX + mWidth / 8;
        mBottom = mCenterY + mHeight / 8;

        mRadiusX = getResources().getDimension(R.dimen.padding_small);
        mRadiusY = getResources().getDimension(R.dimen.padding_small);

        mRoundRects.add(new RoundRect(mCenterX - mWidth / 8 - mWidth / 16 - mWidth / 4,
                mCenterY - mHeight / 8 - mWidth / 16 - mHeight / 4,
                mCenterX + mWidth / 8 - mWidth / 16 - mWidth / 4,
                mCenterY + mHeight / 8 - mWidth / 16 - mHeight / 4,
                mRadiusX,
                mRadiusY,
                BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_aojiao),
                new RectF(mCenterX - mWidth / 16 - mWidth / 16 - mWidth / 4,
                        mCenterY - mHeight / 16 - mWidth / 16 - mHeight / 4,
                        mCenterX + mWidth / 16 - mWidth / 16 - mWidth / 4,
                        mCenterY + mHeight / 16 - mWidth / 16 - mHeight / 4)));//1

        mRoundRects.add(new RoundRect(mCenterX - mWidth / 8,
                mCenterY - mHeight / 8 - mWidth / 16 - mHeight / 4,
                mCenterX + mWidth / 8,
                mCenterY + mHeight / 8 - mWidth / 16 - mHeight / 4,
                mRadiusX,
                mRadiusY,
                BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_aosang),
                new RectF(mCenterX - mWidth / 16,
                        mCenterY - mHeight / 16 - mWidth / 16 - mHeight / 4,
                        mCenterX + mWidth / 16,
                        mCenterY + mHeight / 16 - mWidth / 16 - mHeight / 4)));//2

        mRoundRects.add(new RoundRect(mCenterX - mWidth / 8 + mWidth / 16 + mWidth / 4,
                mCenterY - mHeight / 8 - mWidth / 16 - mHeight / 4,
                mCenterX + mWidth / 8 + mWidth / 16 + mWidth / 4,
                mCenterY + mHeight / 8 - mWidth / 16 - mHeight / 4,
                mRadiusX,
                mRadiusY,
                BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_baiyan),
                new RectF(mCenterX - mWidth / 16 + mWidth / 16 + mWidth / 4,
                        mCenterY - mHeight / 16 - mWidth / 16 - mHeight / 4,
                        mCenterX + mWidth / 16 + mWidth / 16 + mWidth / 4,
                        mCenterY + mHeight / 16 - mWidth / 16 - mHeight / 4)));//3

        mRoundRects.add(new RoundRect(mCenterX - mWidth / 8 - mWidth / 16 - mWidth / 4,
                mCenterY - mHeight / 8,
                mCenterX + mWidth / 8 - mWidth / 16 - mWidth / 4,
                mCenterY + mHeight / 8,
                mRadiusX,
                mRadiusY,
                BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_bishi),
                new RectF(mCenterX - mWidth / 16 - mWidth / 16 - mWidth / 4,
                        mCenterY - mHeight / 16,
                        mCenterX + mWidth / 16 - mWidth / 16 - mWidth / 4,
                        mCenterY + mHeight / 16)));//4

        mRoundRects.add(new RoundRect(mCenterX - mWidth / 8,
                mCenterY - mHeight / 8,
                mCenterX + mWidth / 8,
                mCenterY + mHeight / 8,
                mRadiusX,
                mRadiusY,
                BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_bunaifan),
                new RectF(mCenterX - mWidth / 16,
                        mCenterY - mHeight / 16,
                        mCenterX + mWidth / 16,
                        mCenterY + mHeight / 16)));//5

        mRoundRects.add(new RoundRect(mCenterX - mWidth / 8 + mWidth / 16 + mWidth / 4,
                mCenterY - mHeight / 8,
                mCenterX + mWidth / 8 + mWidth / 16 + mWidth / 4,
                mCenterY + mHeight / 8,
                mRadiusX,
                mRadiusY,
                BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_chijing),
                new RectF(mCenterX - mWidth / 16 + mWidth / 16 + mWidth / 4,
                        mCenterY - mHeight / 16,
                        mCenterX + mWidth / 16 + mWidth / 16 + mWidth / 4,
                        mCenterY + mHeight / 16)));//6

        mRoundRects.add(new RoundRect(mCenterX - mWidth / 8 - mWidth / 16 - mWidth / 4,
                mCenterY - mHeight / 8 + mWidth / 16 + mHeight / 4,
                mCenterX + mWidth / 8 - mWidth / 16 - mWidth / 4,
                mCenterY + mHeight / 8 + mWidth / 16 + mHeight / 4,
                mRadiusX,
                mRadiusY,
                BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_chixiao),
                new RectF(mCenterX - mWidth / 16 - mWidth / 16 - mWidth / 4,
                        mCenterY - mHeight / 16 + mWidth / 16 + mHeight / 4,
                        mCenterX + mWidth / 16 - mWidth / 16 - mWidth / 4,
                        mCenterY + mHeight / 16 + mWidth / 16 + mHeight / 4)));//7

        mRoundRects.add(new RoundRect(mCenterX - mWidth / 8,
                mCenterY - mHeight / 8 + mWidth / 16 + mHeight / 4,
                mCenterX + mWidth / 8,
                mCenterY + mHeight / 8 + mWidth / 16 + mHeight / 4,
                mRadiusX,
                mRadiusY,
                BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_daxiao),
                new RectF(mCenterX - mWidth / 16,
                        mCenterY - mHeight / 16 + mWidth / 16 + mHeight / 4,
                        mCenterX + mWidth / 16,
                        mCenterY + mHeight / 16 + mWidth / 16 + mHeight / 4)));//8

        mRoundRects.add(new RoundRect(mCenterX - mWidth / 8 + mWidth / 16 + mWidth / 4,
                mCenterY - mHeight / 8 + mWidth / 16 + mHeight / 4,
                mCenterX + mWidth / 8 + mWidth / 16 + mWidth / 4,
                mCenterY + mHeight / 8 + mWidth / 16 + mHeight / 4,
                mRadiusX,
                mRadiusY,
                BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_dengyan),
                new RectF(mCenterX - mWidth / 16 + mWidth / 16 + mWidth / 4,
                        mCenterY - mHeight / 16 + mWidth / 16 + mHeight / 4,
                        mCenterX + mWidth / 16 + mWidth / 16 + mWidth / 4,
                        mCenterY + mHeight / 16 + mWidth / 16 + mHeight / 4)));//9
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 单个圆角矩形
        //canvas.drawRoundRect(@NonNull RectF rect, float rx, float ry, @NonNull Paint paint);
        //canvas.drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, @NonNull Paint paint);
        //canvas.drawRoundRect(mLeft, mTop, mRight, mBottom, mRadiusX, mRadiusY, mPaint);

        //2. 多个圆角矩形（旋转画布）
//        for (int i = 0; i < 360; i+=15) {
//            if(i == 0){
//                mPaint.setColor(getResources().getColor(R.color.red_400));
//            }else if(i == 15 || i == 345){
//                mPaint.setColor(getResources().getColor(R.color.green_400));
//            }else{
//                mPaint.setColor(getResources().getColor(R.color.blue_400));
//            }
//            canvas.save();
//            canvas.rotate(i, mCenterX, mCenterY);
//            canvas.drawRoundRect(mLeft, mTop, mRight, mBottom, mRadiusX, mRadiusY, mPaint);
//            canvas.restore();
//        }

        //3. 多个圆角矩形
        for (int i = 0; i < mRoundRects.size(); i++) {
            mRoundRect = mRoundRects.get(i);
            canvas.drawRoundRect(mRoundRect.getLeft(), mRoundRect.getTop(), mRoundRect.getRight(), mRoundRect.getBottom(), mRoundRect.getRadiusX(), mRoundRect.getRadiusY(), mPaint);
            canvas.drawBitmap(mRoundRect.getBitmap(), null, mRoundRect.getBitmapRectF(), mPaint);
        }
    }

}

class RoundRect {

    private float left;
    private float top;
    private float right;
    private float bottom;
    private float radiusX;
    private float radiusY;
    private Bitmap bitmap;
    private RectF bitmapRectF;

    public RoundRect() {
    }

    public RoundRect(float left, float top, float right, float bottom, float radiusX, float radiusY, Bitmap bitmap, RectF bitmapRectF) {
        this.setLeft(left);
        this.setTop(top);
        this.setRight(right);
        this.setBottom(bottom);
        this.setRadiusX(radiusX);
        this.setRadiusY(radiusY);
        this.setBitmap(bitmap);
        this.setBitmapRectF(bitmapRectF);
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(float radiusX) {
        this.radiusX = radiusX;
    }

    public float getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(float radiusY) {
        this.radiusY = radiusY;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public RectF getBitmapRectF() {
        return bitmapRectF;
    }

    public void setBitmapRectF(RectF bitmapRectF) {
        this.bitmapRectF = bitmapRectF;
    }
}




































































