package com.smart.myapplication2020customview.staticview.view_02_paint.view_09_color_optimization;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintFilterBitmapView
 *
 * Des: 效果没有出来，不知道为什么
 *
 * Time: 2020/5/25 5:39 PM
 */
public class PaintFilterBitmapView extends View {

    private Paint mPaint;
    private float mWidth, mHeight;
    private float mSpecifiedWidth, mSpecifiedHeight;
    private Bitmap mBitmap;
    private float mDeltaX, mDeltaY;

    public PaintFilterBitmapView(Context context) {
        this(context, null);
    }

    public PaintFilterBitmapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintFilterBitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mSpecifiedWidth = (mWidth < mHeight ? mWidth : mHeight) * 2;
        mSpecifiedHeight = (mWidth < mHeight ? mWidth : mHeight) * 2;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar, options);

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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(mDeltaX, mDeltaY);
        mPaint.setFilterBitmap(true);
        canvas.drawBitmap(mBitmap, 0,0, mPaint);
        canvas.restore();
    }

}


































































































































