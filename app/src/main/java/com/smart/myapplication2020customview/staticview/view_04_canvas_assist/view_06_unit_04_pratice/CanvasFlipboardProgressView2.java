package com.smart.myapplication2020customview.staticview.view_04_canvas_assist.view_06_unit_04_pratice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import com.smart.myapplication2020customview.utils.BitmapUtils;

/**
 * FileName: CanvasFlipboardTurnPageView
 *
 * Des: Canvas Flipboard Progress View 红板报进度条效果
 *
 * Version: 2.0
 *
 * State: 完成
 *
 * Time: 2020/6/6 6:04 PM
 */
public class CanvasFlipboardProgressView2 extends View implements View.OnClickListener {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private Rect mLeftRect, mRightRect;
    private Camera mCamera;
    private ValueAnimator mLeftAnimator, mRotateAnimator, mRightAnimator;
    private float mLeftStartAngle, mLeftCurrentAngle, mLeftEndAngle;
    private float mRotateStartAngle, mRotateCurrentAngle, mRotateEndAngle;
    private float mRightStartAngle, mRightCurrentAngle, mRightEndAngle;

    public CanvasFlipboardProgressView2(Context context) {
        this(context, null);
    }

    public CanvasFlipboardProgressView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasFlipboardProgressView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.white));
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mPaint.setStrokeWidth(mStrokeWidth);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_bird_woodpecker);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar_three);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_tiger);

        mLeftRect = new Rect();
        mRightRect = new Rect();

        mCamera = new Camera();

        //糊脸修正，给camera 做z轴距离适配，避免绘制糊脸
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        mCamera.setLocation(0, 0, newZ);

        mLeftStartAngle = 0;
        mLeftCurrentAngle = 0;
        mLeftEndAngle = 30;

        mRotateStartAngle = 0;
        mRotateCurrentAngle = 0;
        mRotateEndAngle = 270;

        mRightStartAngle = 0;
        mRightCurrentAngle = 0;
        mRightEndAngle = -30;

        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mBitmap = BitmapUtils.scaleBitmap(mBitmap, mWidth/2, mHeight/2);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();

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
//        canvas.rotate(- mRotateCurrentAngle);
//        canvas.clipRect(- mCenterX, - mCenterY, 0, mCenterY);
//        canvas.rotate(mRotateCurrentAngle);
//        canvas.translate(- mCenterX, - mCenterY);
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();

        //1.2 先旋转 Canvas，再旋转 Canvas Clip 内容（正解）
//        canvas.save();
//        canvas.rotate( - mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip
//        canvas.clipRect(0, 0, mCenterX, mHeight);
//        canvas.rotate(mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();


        //2. 旋转裁切，绘制裁切的对称部分
        //2.1 绘制裁切的对称部分
//        canvas.save();
//        canvas.rotate( - mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip
//        canvas.clipRect(mCenterX, 0, mWidth, mHeight);
//        canvas.rotate(mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();

        //2.2 旋转裁切，绘制裁切的对称部分
//        //裁切
//        canvas.save();
//        canvas.rotate( - mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip
//        canvas.clipRect(0, 0, mCenterX, mHeight);
//        canvas.rotate(mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();
//
//        //裁切对称部分
//        canvas.save();
//        canvas.rotate( - mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip
//        canvas.clipRect(mCenterX, 0, mWidth, mHeight);
//        canvas.rotate(mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();


        //3. 将裁切的对称部分折起来
//        //裁切
//        canvas.save();
//        canvas.rotate( - mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip
//        canvas.clipRect(0, 0, mCenterX, mHeight);
//        canvas.rotate(mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();
//
//        //裁切对称部分折起来
//        canvas.save();
//        mCamera.save();
//        canvas.rotate( - mRotateCurrentAngle, mCenterX, mCenterY);
//
//        canvas.translate(mCenterX, mCenterY);
//        mCamera.rotateY(-45);
//        mCamera.applyToCanvas(canvas);
//        canvas.translate(-mCenterX, -mCenterY);
//        mCamera.restore();
//
//        canvas.clipRect(mCenterX, 0, mWidth, mHeight);
//        canvas.rotate(mRotateCurrentAngle, mCenterX, mCenterY);
//        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
//        canvas.restore();


        //4. 将裁切的对称部分折起来，运动完之后，将裁剪部分也折起来
        //裁切（Left）
        canvas.save();
        mCamera.save();
        canvas.rotate( - mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip

        canvas.translate(mCenterX, mCenterY);
        mCamera.rotateY(mLeftCurrentAngle);
        mCamera.applyToCanvas(canvas);
        canvas.translate(-mCenterX, -mCenterY);
        mCamera.restore();

        canvas.clipRect(0, 0, mCenterX, mHeight);
        canvas.rotate(mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
        canvas.restore();

        //裁切对称部分折起来
        canvas.save();
        mCamera.save();
        canvas.rotate( - mRotateCurrentAngle, mCenterX, mCenterY);

        canvas.translate(mCenterX, mCenterY);
        mCamera.rotateY(mRightCurrentAngle);
        mCamera.applyToCanvas(canvas);
        canvas.translate(-mCenterX, -mCenterY);
        mCamera.restore();

        canvas.clipRect(mCenterX, 0, mWidth, mHeight);
        canvas.rotate(mRotateCurrentAngle, mCenterX, mCenterY);
        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
        canvas.restore();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        initRightAnimator();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initRightAnimator(){
        if(mRightAnimator == null){
            mRightAnimator = ValueAnimator.ofFloat(mRightStartAngle, mRightEndAngle);
            mRightAnimator.setDuration(getResources().getInteger(R.integer.four_hundred));
            mRightAnimator.setInterpolator(new LinearInterpolator());
            mRightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRightCurrentAngle = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
            mRightAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    initRotateAnimator();
                }
            });
            mRightAnimator.start();
        }else if(mRightAnimator.isPaused()){
            mRightAnimator.resume();
        }else if(!mRightAnimator.isRunning()){
            mRightAnimator.start();
            mLeftCurrentAngle = 0;
            mRotateCurrentAngle = 0;
            mRightCurrentAngle = 0;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initRotateAnimator(){
        if(mRotateAnimator == null){
            mRotateAnimator = ValueAnimator.ofFloat(mRotateStartAngle, mRotateEndAngle);
            mRotateAnimator.setDuration(getResources().getInteger(R.integer.eight_hundred));
            mRotateAnimator.setInterpolator(new LinearInterpolator());
            mRotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRotateCurrentAngle = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
            mRotateAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    initLeftAnimator();
                }
            });
            mRotateAnimator.start();
        }else if(mRotateAnimator.isPaused()){
            mRotateAnimator.resume();
        }else if(!mRotateAnimator.isRunning()){
            mRotateAnimator.start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initLeftAnimator(){
        if(mLeftAnimator == null){
            mLeftAnimator = ValueAnimator.ofFloat(mLeftStartAngle, mLeftEndAngle);
            mLeftAnimator.setDuration(getResources().getInteger(R.integer.four_hundred));
            mLeftAnimator.setInterpolator(new LinearInterpolator());
            mLeftAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mLeftCurrentAngle = (float)animation.getAnimatedValue();
                    invalidate();
                }
            });
            mLeftAnimator.start();
        }else if(mLeftAnimator.isPaused()){
            mLeftAnimator.resume();
        }else if(!mLeftAnimator.isRunning()){
            mLeftAnimator.start();
        }
    }

}
