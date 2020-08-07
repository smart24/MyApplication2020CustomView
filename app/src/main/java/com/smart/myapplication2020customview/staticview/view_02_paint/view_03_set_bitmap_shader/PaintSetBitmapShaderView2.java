package com.smart.myapplication2020customview.staticview.view_02_paint.view_03_set_bitmap_shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;


public class PaintSetBitmapShaderView2 extends View {

    private Paint mPaint, mFramePaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mSpecifiedWidth, mSpecifiedHeight;
    private Shader mShader;
    private Bitmap mBitmap;
    private float mDeltaX, mDeltaY;
    private float mRectCornerRadius;
    private Path mPath;

    public PaintSetBitmapShaderView2(Context context) {
        this(context, null);
    }

    public PaintSetBitmapShaderView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintSetBitmapShaderView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mFramePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFramePaint.setColor(getResources().getColor(R.color.white));
        mFramePaint.setStyle(Paint.Style.STROKE);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mFramePaint.setStrokeWidth(mStrokeWidth);

        mRectCornerRadius = getResources().getDimension(R.dimen.padding_small);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mSpecifiedWidth = (mWidth < mHeight ? mWidth : mHeight) / 2;
        mSpecifiedHeight = (mWidth < mHeight ? mWidth : mHeight) / 2;

//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_cold_drink);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_bird_woodpecker);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_blue_flag);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_cute_mouse);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_bishi);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_haipa);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_crypt_load);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_plane);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_run_boy);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_see);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_astronaut);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_hotel);
        mBitmap = scaleBitmap(mBitmap, mSpecifiedWidth, mSpecifiedHeight);
        mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);

        mDeltaX = (mWidth - mBitmap.getWidth()) / 2;
        mDeltaY = (mHeight - mBitmap.getHeight()) / 2;

        mPath = new Path();
        mPath.moveTo((float)(mBitmap.getWidth()/2 + mBitmap.getWidth()/2 * Math.cos(Math.toRadians(30))), (float)(mBitmap.getHeight()/2 + mBitmap.getHeight()/2 * Math.sin(Math.toRadians(30))));
        mPath.lineTo((float)(mBitmap.getWidth()/2 + mBitmap.getWidth()/2 * Math.cos(Math.toRadians(150))), (float)(mBitmap.getHeight()/2 + mBitmap.getHeight()/2 * Math.sin(Math.toRadians(150))));
        mPath.lineTo((float)(mBitmap.getWidth()/2 + mBitmap.getWidth()/2 * Math.cos(Math.toRadians(270))), (float)(mBitmap.getHeight()/2 + mBitmap.getHeight()/2 * Math.sin(Math.toRadians(270))));
        mPath.close();
    }

    /**
     * 根据给定的宽和高进行拉伸
     *
     * @param src    原图
     * @param newWidth  新图的宽
     * @param newHeight 新图的高
     * @return new Bitmap
     */
    private Bitmap scaleBitmap(Bitmap src, float newWidth, float newHeight) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //先确定横向，还是纵向
        float scaleRatio = 0;
        if(width/height >= 1){
            //横向，填充高
            scaleRatio = newHeight / height;
        }else{
            //纵向，填充宽
            scaleRatio = newWidth/ width;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleRatio, scaleRatio);
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, width, height, matrix, false);
        if (!src.isRecycled()) {
            src.recycle();
        }
        return dst;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.grey_700));
        canvas.save();
        canvas.translate(mDeltaX, mDeltaY);
        //1. 圆角头像
        //具体头像内容
        canvas.drawCircle(mBitmap.getWidth() / 2, mBitmap.getHeight() / 2, (mBitmap.getWidth() < mBitmap.getHeight() ? mBitmap.getWidth() : mBitmap.getHeight()) / 2, mPaint);
        //白色边框
        canvas.drawCircle(mBitmap.getWidth() / 2, mBitmap.getHeight() / 2, (mBitmap.getWidth() < mBitmap.getHeight() ? mBitmap.getWidth() : mBitmap.getHeight()) / 2, mFramePaint);

        //2. 圆角矩形头像
        //具体头像内容
//        canvas.drawRoundRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), mRectCornerRadius, mRectCornerRadius, mPaint);
        //白色边框
//        canvas.drawRoundRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), mRectCornerRadius, mRectCornerRadius, mFramePaint);

        //3. 三角头像
        //具体头像内容
//        canvas.drawPath(mPath, mPaint);
        //白色边框
//        canvas.drawPath(mPath, mFramePaint);
        canvas.restore();
    }

}


































































































































