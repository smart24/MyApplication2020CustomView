package com.smart.myapplication2020customview.staticview.view_02_paint.view_12_set_mask_filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.BitmapUtils;

/**
 * FileName: PaintEmbossMaskFilterView
 *
 * Des: EmbossMaskFilter 浮雕效果
 *
 * 浮雕效果只有关闭硬件加速的时候，才会出效果
 *
 * Time: 2020/6/2 3:49 PM
 */
class PaintEmbossMaskFilterView extends android.view.View {

    private Paint mPaint;
    private MaskFilter mMaskFilter;
    private float mStrokeWidth;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mSpecifiedWidth, mSpecifiedHeight;
    private Bitmap mBitmap;
    private float mDeltaX, mDeltaY;
    private String mContent;
    private Rect mRect;

    public PaintEmbossMaskFilterView(Context context) {
        this(context, null);
    }

    public PaintEmbossMaskFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintEmbossMaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.deep_orange_500));
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mTextSize = getResources().getDimension(R.dimen.font_thirty_two);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);

        //setShadowLayer() 设置的是在绘制层下方的附加效果
//        mPaint.setShadowLayer(getResources().getDimension(R.dimen.padding_micro_x), getResources().getDimension(R.dimen.padding_micro), getResources().getDimension(R.dimen.padding_micro), getResources().getColor(R.color.blue_400));
//        mPaint.setShadowLayer(getResources().getDimension(R.dimen.padding_micro_x), getResources().getDimension(R.dimen.padding_zero), getResources().getDimension(R.dimen.padding_zero), getResources().getColor(R.color.blue_400));

        //setMaskFilter() 设置的是在绘制层上方的附加效果
        float[] direction = new float[] { 1, 1, 1};  //direction 是一个 3 个元素的数组，指定了光源的方向
        float ambient = 1f;  //环境光的强度，数值范围是 0 到 1
        float specular = 1;  //炫光的系数
        float blurRadius = 5;  //应用光线的范围
        mMaskFilter = new EmbossMaskFilter(direction, ambient, specular, blurRadius);  //浮雕效果
        mPaint.setMaskFilter(mMaskFilter);

        mContent = getResources().getString(R.string.calorie_content);

        mRect = new Rect();
        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mSpecifiedWidth = (mWidth < mHeight ? mWidth : mHeight) / 2;
        mSpecifiedHeight = (mWidth < mHeight ? mWidth : mHeight) / 2;

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);
        mBitmap = BitmapUtils.scaleBitmap(mBitmap, mSpecifiedWidth, mSpecifiedHeight);

        mDeltaX = (mWidth - mBitmap.getWidth()) / 2;
        mDeltaY = (mHeight - mBitmap.getHeight()) / 2;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(getResources().getColor(R.color.white));
//        canvas.drawBitmap(mBitmap, mDeltaX, mDeltaY, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mTextSize);
        canvas.drawText(mContent, mCenterX, mCenterY, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        canvas.drawLine(mCenterX - mRect.width()/2, mCenterY - mRect.height(),mCenterX + mRect.width()/2, mCenterY - mRect.height(), mPaint);
        canvas.drawLine(mCenterX - mRect.width()/2, mCenterY + mRect.height()/2,mCenterX + mRect.width()/2, mCenterY + mRect.height()/2, mPaint);
    }

}



































































































































