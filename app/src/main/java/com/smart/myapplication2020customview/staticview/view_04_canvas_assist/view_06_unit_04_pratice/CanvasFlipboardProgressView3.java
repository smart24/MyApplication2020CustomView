package com.smart.myapplication2020customview.staticview.view_04_canvas_assist.view_06_unit_04_pratice;

import android.animation.AnimatorSet;
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
 * Version: 3.0
 *
 * State: 完成
 *
 * Optimize: Y
 *
 * Time: 2020/6/6 6:04 PM
 */
public class CanvasFlipboardProgressView3 extends View implements View.OnClickListener {

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
    private AnimatorSet mAnimatorSet;

    public CanvasFlipboardProgressView3(Context context) {
        this(context, null);
    }

    public CanvasFlipboardProgressView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasFlipboardProgressView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_game);

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

        //4. 将裁切的对称部分折起来，运动完之后，将裁剪部分也折起来
        //裁切（Left）
        canvas.save();
        canvas.rotate( - mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas Clip

        mCamera.save();
        canvas.translate(mCenterX, mCenterY);
        mCamera.rotateY(mLeftCurrentAngle);
        mCamera.applyToCanvas(canvas);
        canvas.translate(-mCenterX, -mCenterY);
        mCamera.restore();

        canvas.clipRect(0, 0, mCenterX, mHeight);
        canvas.rotate(mRotateCurrentAngle, mCenterX, mCenterY); //旋转 Canvas
        canvas.drawBitmap(mBitmap, mLeftRect.left, mLeftRect.top, mPaint);
        canvas.restore();


        //裁切对称部分折起来（Right）
        canvas.save();
        canvas.rotate( -mRotateCurrentAngle, mCenterX, mCenterY);

        mCamera.save();
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
        }

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
        }

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
        }

        if(mAnimatorSet == null){
            mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playSequentially(mRightAnimator, mRotateAnimator, mLeftAnimator);
        }

        mAnimatorSet.start();
    }

}
