package com.smart.myapplication2020customview.staticview.view_04_canvas_assist.view_06_unit_04_pratice;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasFlipboardTurnPageView
 *
 * Des: Canvas Flipboard Progress View 红板报进度条效果
 *
 * Time: 2020/6/6 6:04 PM
 */
public class CanvasFlipboardProgressView extends View implements View.OnClickListener, ValueAnimator.AnimatorUpdateListener {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private Rect mLeftRect, mRightRect;
    private Camera mCamera;
    private ValueAnimator mValueAnimator;
    private float mStartAngle, mCurrentAngle, mEndAngle;

    public CanvasFlipboardProgressView(Context context) {
        this(context, null);
    }

    public CanvasFlipboardProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasFlipboardProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_wallpaper_plane);

        mLeftRect = new Rect();
        mRightRect = new Rect();

        mCamera = new Camera();

        //糊脸修正，给camera 做z轴距离适配，避免绘制糊脸
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        mCamera.setLocation(0, 0, newZ);

        mStartAngle = 0;
        mEndAngle = 270;

        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();

//        mBitmap = BitmapUtils.scaleBitmap(mBitmap, mWidth, mHeight);
//        mBitmapWidth = mBitmap.getWidth();
//        mBitmapHeight = mBitmap.getHeight();

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

        //实现策略：
        //1. 旋转裁切；
        //2. 绘制裁切的对称部分；
        //3. 将裁切的对称部分折起来；

        //1. 旋转裁切
        //1.1 Write by 吃掉你了喔
//        canvas.save();
//        canvas.translate(mCenterX, mCenterY);
//        canvas.rotate(- mCurrentAngle);
//        canvas.clipRect(- mCenterX, - mCenterY, 0, mCenterY);
//        canvas.rotate(mCurrentAngle);
//        canvas.translate(- mCenterX, - mCenterY);
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();

        //1.2 先旋转 Canvas，再旋转 Canvas Clip 内容（正解）
//        canvas.save();
//        canvas.rotate( - mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip
//        canvas.clipRect(0, 0, mCenterX, mHeight);
//        canvas.rotate(mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();


        //2. 旋转裁切，绘制裁切的对称部分
        //2.1 绘制裁切的对称部分
//        canvas.save();
//        canvas.rotate( - mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip
//        canvas.clipRect(mCenterX, 0, mWidth, mHeight);
//        canvas.rotate(mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();

        //2.2 旋转裁切，绘制裁切的对称部分
//        //裁切
//        canvas.save();
//        canvas.rotate( - mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip
//        canvas.clipRect(0, 0, mCenterX, mHeight);
//        canvas.rotate(mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();
//
//        //裁切对称部分
//        canvas.save();
//        canvas.rotate( - mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip
//        canvas.clipRect(mCenterX, 0, mWidth, mHeight);
//        canvas.rotate(mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();


        //3. 将裁切的对称部分折起来
//        //裁切
//        canvas.save();
//        canvas.rotate( - mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip
//        canvas.clipRect(0, 0, mCenterX, mHeight);
//        canvas.rotate(mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();
//
//        //裁切对称部分折起来
//        canvas.save();
//        mCamera.save();
//        canvas.rotate( - mCurrentAngle, mCenterX, mCenterY);
//
//        canvas.translate(mCenterX, mCenterY);
//        mCamera.rotateY(-45);
//        mCamera.applyToCanvas(canvas);
//        canvas.translate(-mCenterX, -mCenterY);
//        mCamera.restore();
//
//        canvas.clipRect(mCenterX, 0, mWidth, mHeight);
//        canvas.rotate(mCurrentAngle, mCenterX, mCenterY);
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();


        //4. 将裁切的对称部分折起来，运动完之后，将裁剪部分也折起来
        //裁切
        canvas.save();
        mCamera.save();
        canvas.rotate( - mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip

        if(mCurrentAngle == mEndAngle){
            canvas.translate(mCenterX, mCenterY);
            mCamera.rotateY(45);
            mCamera.applyToCanvas(canvas);
            canvas.translate(-mCenterX, -mCenterY);
            mCamera.restore();
        }

        canvas.clipRect(0, 0, mCenterX, mHeight);
        canvas.rotate(mCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
        canvas.restore();

        //裁切对称部分折起来
        canvas.save();
        mCamera.save();
        canvas.rotate( - mCurrentAngle, mCenterX, mCenterY);

        canvas.translate(mCenterX, mCenterY);
        mCamera.rotateY(-45);
        mCamera.applyToCanvas(canvas);
        canvas.translate(-mCenterX, -mCenterY);
        mCamera.restore();

        canvas.clipRect(mCenterX, 0, mWidth, mHeight);
        canvas.rotate(mCurrentAngle, mCenterX, mCenterY);
        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
        canvas.restore();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
//        if(mValueAnimator == null){
//            mValueAnimator = ValueAnimator.ofFloat(mStartAngle, mEndAngle);
//            mValueAnimator.setDuration(getResources().getInteger(R.integer.two_thousand));
//            mValueAnimator.setInterpolator(new LinearInterpolator());
//            mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
//            mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
//            mValueAnimator.addUpdateListener(this);
//            mValueAnimator.start();
//        }else if(mValueAnimator.isPaused()){
//            mValueAnimator.resume();
//        }else if(mValueAnimator.isRunning()){
//            mValueAnimator.pause();
//        }

        if(mValueAnimator == null){
            mValueAnimator = ValueAnimator.ofFloat(mStartAngle, mEndAngle);
            mValueAnimator.setDuration(getResources().getInteger(R.integer.two_thousand));
            mValueAnimator.setInterpolator(new LinearInterpolator());
//            mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
//            mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
            mValueAnimator.addUpdateListener(this);
            mValueAnimator.start();
        }else if(mValueAnimator.isPaused()){
            mValueAnimator.resume();
        }else if(!mValueAnimator.isRunning()){
            mValueAnimator.start();
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mCurrentAngle = (float)animation.getAnimatedValue();
        invalidate();
    }
}
