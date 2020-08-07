package com.smart.myapplication2020customview.staticview.view_04_canvas_assist.view_06_unit_04_pratice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasFlipboardTurnPageView
 *
 * Des: Canvas Flipboard TurnPage View 红板报翻页效果
 *
 * Time: 2020/6/6 6:04 PM
 */
public class CanvasFlipboardTurnPageView extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private Rect mLeftRect, mRightRect;
    private Camera mCamera;

    public CanvasFlipboardTurnPageView(Context context) {
        this(context, null);
    }

    public CanvasFlipboardTurnPageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasFlipboardTurnPageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        mLeftRect = new Rect();
        mRightRect = new Rect();

        mCamera = new Camera();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth/2;
        mCenterY = mHeight/2;

        mLeftRect.left = (int)(mCenterX - mBitmapWidth/2);
        mLeftRect.top = (int)(mCenterY - mBitmapHeight/2);
        mLeftRect.right = (int)(mCenterX);
        mLeftRect.bottom = (int)(mCenterY + mBitmapHeight/2);

        mRightRect.left = (int)(mCenterX);
        mRightRect.top = (int)(mCenterY - mBitmapHeight/2);
        mRightRect.right = (int)(mCenterX + mBitmapWidth/2);
        mRightRect.bottom = (int)(mCenterY + mBitmapHeight/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.grey_700));

        //实现策略：先正确旋转，再裁剪
        //1. 正确旋转
//        canvas.save();
//        mCamera.save();
//        canvas.translate(mCenterX, mCenterY);
//        mCamera.rotateY(-15);
//        mCamera.applyToCanvas(canvas);
//        canvas.translate(-mCenterX, -mCenterY);
//        mCamera.restore();
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();

        //2. 只正确旋转裁剪的那部分内容
//        canvas.save();
//        mCamera.save();
//        canvas.translate(mCenterX, mCenterY);
//        mCamera.rotateY(-15);
//        mCamera.applyToCanvas(canvas);
//        canvas.translate(-mCenterX, -mCenterY);
//        mCamera.restore();
//        canvas.clipRect(mRightRect);
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();

        //3. 正确旋转裁剪的那部分内容，将另外一部分内容补齐
        canvas.save();
        mCamera.save();
        canvas.translate(mCenterX, mCenterY);
        mCamera.rotateY(-15);
        mCamera.applyToCanvas(canvas);
        canvas.translate(-mCenterX, -mCenterY);
        mCamera.restore();
        canvas.clipRect(mRightRect);
        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
        canvas.restore();

        canvas.save();
        canvas.clipRect(mLeftRect);
        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
        canvas.restore();
    }

}
