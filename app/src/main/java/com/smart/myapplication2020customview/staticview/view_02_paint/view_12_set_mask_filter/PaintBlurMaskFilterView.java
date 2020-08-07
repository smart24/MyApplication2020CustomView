package com.smart.myapplication2020customview.staticview.view_02_paint.view_12_set_mask_filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

class PaintBlurMaskFilterView extends android.view.View {

    private Paint mPaint, mFramePaint;
    private MaskFilter mMaskFilter;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mSpecifiedWidth, mSpecifiedHeight;
    private Bitmap mBitmap;
    private float mDeltaX, mDeltaY;

    public PaintBlurMaskFilterView(Context context) {
        this(context, null);
    }

    public PaintBlurMaskFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintBlurMaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //setShadowLayer() 设置的是在绘制层下方的附加效果
//        mPaint.setShadowLayer(getResources().getDimension(R.dimen.padding_micro_x), getResources().getDimension(R.dimen.padding_micro), getResources().getDimension(R.dimen.padding_micro), getResources().getColor(R.color.blue_400));
//        mPaint.setShadowLayer(getResources().getDimension(R.dimen.padding_micro_x), getResources().getDimension(R.dimen.padding_zero), getResources().getDimension(R.dimen.padding_zero), getResources().getColor(R.color.blue_400));

        //setMaskFilter() 设置的是在绘制层上方的附加效果
//        mMaskFilter = new BlurMaskFilter(getResources().getDimension(R.dimen.padding_large), BlurMaskFilter.Blur.NORMAL);  //内外都模糊绘制
//        mMaskFilter = new BlurMaskFilter(getResources().getDimension(R.dimen.padding_large), BlurMaskFilter.Blur.INNER);  //内部模糊，外部不绘制
//        mMaskFilter = new BlurMaskFilter(getResources().getDimension(R.dimen.padding_large), BlurMaskFilter.Blur.SOLID);  //内部正常绘制，外部模糊
        mMaskFilter = new BlurMaskFilter(getResources().getDimension(R.dimen.padding_large), BlurMaskFilter.Blur.OUTER);  //内部不绘制，外部模糊
        mPaint.setMaskFilter(mMaskFilter);


        mFramePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFramePaint.setColor(getResources().getColor(R.color.white));
        mFramePaint.setStyle(Paint.Style.STROKE);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mFramePaint.setStrokeWidth(mStrokeWidth);

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
        canvas.drawColor(getResources().getColor(R.color.white));
        canvas.drawBitmap(mBitmap, mDeltaX, mDeltaY, mPaint);
    }

}



































































































































