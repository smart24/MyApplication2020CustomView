package com.smart.myapplication2020customview.staticview.view_02_paint.view_05_color_filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;


public class PaintPorterDuffColorFilterView extends View {

    private Paint mPaint;
    private float mWidth, mHeight;
    private float mSpecifiedWidth, mSpecifiedHeight;
    private Bitmap mBitmap;
    private float mDeltaX, mDeltaY;

    public PaintPorterDuffColorFilterView(Context context) {
        this(context, null);
    }

    public PaintPorterDuffColorFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintPorterDuffColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //PorterDuffColorFilter(@ColorInt int color, @NonNull PorterDuff.Mode mode)
        //
        //Color Src
        //已有的 View 的 Color Dst
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.SRC);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.SRC_IN);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.SRC_OVER);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.SRC_ATOP);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.DST);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.DST_IN);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.DST_OVER);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.DST_ATOP);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.DARKEN);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.LIGHTEN);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.MULTIPLY);
//        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.SCREEN);
        ColorFilter colorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.green_400), PorterDuff.Mode.ADD);
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mSpecifiedWidth = (mWidth < mHeight ? mWidth : mHeight) / 3;
        mSpecifiedHeight = (mWidth < mHeight ? mWidth : mHeight) / 3;

//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_cold_drink);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_bird_woodpecker);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_blue_flag);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_cute_mouse);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_bishi);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_haipa);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_crypt_load);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_plane);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_run_boy);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_see);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_astronaut);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_hotel);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_colors);
        mBitmap = scaleBitmap(mBitmap, mSpecifiedWidth, mSpecifiedHeight);

        mDeltaX = (mWidth - mBitmap.getWidth()) / 2;
        mDeltaY = (mHeight - mBitmap.getHeight()) / 2;

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
        canvas.drawBitmap(mBitmap, 0,0, mPaint);
        canvas.restore();
    }

}
















































































































































































































































