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
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.BitmapUtils;

/**
 * FileName: CanvasFlipboardTurnPageView
 *
 * Des: Canvas Flipboard TurnPage View 红板报翻页效果
 *
 * Version: 3.0
 *
 * Status: 目前只实现了翻转，但是第二页的下半部分内容未正确展示
 *
 * Time: 2020/6/6 6:04 PM
 */
public class CanvasFlipboardTurnPageView3 extends View implements View.OnClickListener, ValueAnimator.AnimatorUpdateListener {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Bitmap mFirstBitmap, mSecondBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private Rect mTopRect, mBottomRect;
    private Camera mCamera;
    private ValueAnimator mValueAnimator;
    private float mStartAngle, mCurrentAngle, mEndAngle;
    private Canvas mCanvas;

    public CanvasFlipboardTurnPageView3(Context context) {
        this(context, null);
    }

    public CanvasFlipboardTurnPageView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasFlipboardTurnPageView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.white));
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mPaint.setStrokeWidth(mStrokeWidth);

        mFirstBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_wallpaper);
        mSecondBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_wallpaper_plane);

        mTopRect = new Rect();
        mBottomRect = new Rect();

        mCamera = new Camera();

        mStartAngle = 0;
        mEndAngle = 180;

        mCanvas = new Canvas();

        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mFirstBitmap = BitmapUtils.scaleBitmap(mFirstBitmap, mWidth, mHeight);
        mSecondBitmap = BitmapUtils.scaleBitmap(mSecondBitmap, mWidth, mHeight);

        mBitmapWidth = mFirstBitmap.getWidth();
        mBitmapHeight = mFirstBitmap.getHeight();

        mCenterX = mWidth/2;
        mCenterY = mHeight/2;

        mTopRect.left = (int)(mCenterX - mBitmapWidth/2);
        mTopRect.top = (int)(mCenterY - mBitmapHeight/2);
        mTopRect.right = (int)(mCenterX + mBitmapWidth/2);
        mTopRect.bottom = (int)(mCenterY);

        mBottomRect.left = (int)(mCenterX - mBitmapWidth/2);
        mBottomRect.top = (int)(mCenterY);
        mBottomRect.right = (int)(mCenterX + mBitmapWidth/2);
        mBottomRect.bottom = (int)(mCenterY + mBitmapHeight/2);
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
//        mCamera.rotateX(-15);
//        mCamera.applyToCanvas(canvas);
//        canvas.translate(-mCenterX, -mCenterY);
//        mCamera.restore();
//        canvas.drawBitmap(mBitmap, mTopRect.left, mTopRect.top, mPaint);
//        canvas.restore();

        //2. 只正确旋转裁剪的那部分内容
//        canvas.save();
//        mCamera.save();
//        canvas.translate(mCenterX, mCenterY);
//        mCamera.rotateX(-15);
//        mCamera.applyToCanvas(canvas);
//        canvas.translate(-mCenterX, -mCenterY);
//        mCamera.restore();
//        canvas.clipRect(mBottomRect);
//        canvas.drawBitmap(mBitmap, mTopRect.left, mTopRect.top, mPaint);
//        canvas.restore();

        //3. 正确旋转裁剪的那部分内容，将另外一部分内容补齐
//        canvas.save();
//        canvas.clipRect(mTopRect);
//        canvas.drawBitmap(mFirstBitmap, mTopRect.left, mTopRect.top, mPaint);
//        canvas.restore();
//
//        canvas.save();
//        mCamera.save();
//        canvas.translate(mCenterX, mCenterY);
//        mCamera.rotateX(mCurrentAngle);
//        mCamera.setLocation(0, 0, - getResources().getDimension(R.dimen.padding_two_hundred_fifty_six));
//        mCamera.applyToCanvas(canvas);
//        canvas.translate(-mCenterX, -mCenterY);
//        mCamera.restore();
//        canvas.clipRect(mBottomRect);
//        canvas.drawBitmap(mFirstBitmap, mTopRect.left, mTopRect.top, mPaint);
//        canvas.drawBitmap(mFirstBitmap, mTopRect.left, mTopRect.top, mPaint);
//        canvas.restore();


        //4. 正确旋转裁剪的那部分内容，将另外一部分内容补齐，更改第二页内容
        canvas.save();
        canvas.clipRect(mTopRect);
        canvas.drawBitmap(mFirstBitmap, mTopRect.left, mTopRect.top, mPaint);
        canvas.restore();

        canvas.save();
        mCamera.save();
        canvas.translate(mCenterX, mCenterY);
        mCamera.rotateX(mCurrentAngle);
        mCamera.setLocation(0, 0, - getResources().getDimension(R.dimen.padding_two_hundred_fifty_six));
        mCamera.applyToCanvas(canvas);
        canvas.translate(-mCenterX, -mCenterY);
        mCamera.restore();
        canvas.clipRect(mBottomRect);
        if(0 <= mCurrentAngle && mCurrentAngle < 90){
            canvas.drawBitmap(mFirstBitmap, mTopRect.left, mTopRect.top, mPaint);
        }else if(90 <= mCurrentAngle){
            mCamera.save();
            canvas.translate(mCenterX, mCenterY);
            mCamera.rotateX(180);
            mCamera.setLocation(0, 0, - getResources().getDimension(R.dimen.padding_two_hundred_fifty_six));
            mCamera.applyToCanvas(canvas);
            canvas.translate(-mCenterX, -mCenterY);
            mCamera.restore();
            canvas.drawBitmap(mSecondBitmap, mTopRect.left, mTopRect.top, mPaint);
        }
        canvas.restore();

        mCanvas.setBitmap(mSecondBitmap);


//        if(0 < mCurrentAngle){
//            setBackground(new BitmapDrawable(mSecondBitmap));
//            canvas.save();
//            canvas.clipRect(mBottomRect);
//            canvas.drawBitmap(mSecondBitmap, mTopRect.left, mTopRect.top, mPaint);
//            canvas.restore();
//        }
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
