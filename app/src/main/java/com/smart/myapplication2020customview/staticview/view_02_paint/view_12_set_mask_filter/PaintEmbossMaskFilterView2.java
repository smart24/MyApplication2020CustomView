package com.smart.myapplication2020customview.staticview.view_02_paint.view_12_set_mask_filter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

/**
 * FileName: PaintEmbossMaskFilterView
 *
 * Des: EmbossMaskFilter 浮雕效果
 *
 * 浮雕效果只有关闭硬件加速的时候，才会出效果
 *
 * Time: 2020/6/2 3:49 PM
 */
class PaintEmbossMaskFilterView2 extends android.view.View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private String mContent;
    private Rect mRect;
    private float x, y, z; //direction 是一个 3 个元素的数组，指定了光源的方向
    private float ambient; //环境光的强度，数值范围是 0 到 1
    private float specular; //炫光的系数
    private float blurRadius; //应用光线的范围
    private MaskFilter mMaskFilter;

    public PaintEmbossMaskFilterView2(Context context) {
        this(context, null);
    }

    public PaintEmbossMaskFilterView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintEmbossMaskFilterView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        mContent = getResources().getString(R.string.calorie_content);

        mRect = new Rect();
        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);

        x = getResources().getInteger(R.integer.number_one);
        y = getResources().getInteger(R.integer.number_one);
        z = getResources().getInteger(R.integer.number_one);
        ambient = getResources().getInteger(R.integer.number_one);
        specular = getResources().getInteger(R.integer.number_one);
        blurRadius = getResources().getInteger(R.integer.number_one);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        setMaskFilter();

        Log.e(Constants.TAG, "onSizeChanged");
    }

    private void setMaskFilter(){
        mMaskFilter = new EmbossMaskFilter(new float[]{x, y, z}, ambient, specular, blurRadius);
        mPaint.setMaskFilter(mMaskFilter);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
        setMaskFilter();
        invalidate();
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
        setMaskFilter();
        invalidate();
    }

    @Override
    public float getZ() {
        return z;
    }

    @Override
    public void setZ(float z) {
        this.z = z;
        setMaskFilter();
        invalidate();
    }

    public float getAmbient() {
        return ambient;
    }

    public void setAmbient(float ambient) {
        this.ambient = ambient;
        setMaskFilter();
        invalidate();
    }

    public float getSpecular() {
        return specular;
    }

    public void setSpecular(float specular) {
        this.specular = specular;
        setMaskFilter();
        invalidate();
    }

    public float getBlurRadius() {
        return blurRadius;
    }

    public void setBlurRadius(float blurRadius) {
        this.blurRadius = blurRadius;
        setMaskFilter();
        invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(Constants.TAG, "onDraw");
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mTextSize);
        canvas.drawText(mContent, mCenterX, mCenterY, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        canvas.drawLine(mCenterX - mRect.width()/2, mCenterY - mRect.height(),mCenterX + mRect.width()/2, mCenterY - mRect.height(), mPaint);
        canvas.drawLine(mCenterX - mRect.width()/2, mCenterY + mRect.height()/2,mCenterX + mRect.width()/2, mCenterY + mRect.height()/2, mPaint);
    }

}



































































































































