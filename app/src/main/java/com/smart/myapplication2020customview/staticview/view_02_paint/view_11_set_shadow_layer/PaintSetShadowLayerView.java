package com.smart.myapplication2020customview.staticview.view_02_paint.view_11_set_shadow_layer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;
/**
 * FileName: PaintSetShadowLayerView
 *
 * Des: Paint SetShadowLayer View
 *
 * Time: 2020/6/2 2:57 PM
 */
public class PaintSetShadowLayerView extends View {

    private Paint mPaint;
    private float mTextSize;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private String mContent;
    private Rect mRect;

    public PaintSetShadowLayerView(Context context) {
        this(context, null);
    }

    public PaintSetShadowLayerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintSetShadowLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mTextSize = getResources().getDimension(R.dimen.font_thirty_two);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setShadowLayer(getResources().getDimension(R.dimen.padding_micro_x), getResources().getDimension(R.dimen.padding_zero), getResources().getDimension(R.dimen.padding_zero), getResources().getColor(R.color.blue_400));

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

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mTextSize);
        canvas.drawText(mContent, mCenterX, mCenterY, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        canvas.drawLine(mCenterX - mRect.width()/2, mCenterY - mRect.height(),mCenterX + mRect.width()/2, mCenterY - mRect.height(), mPaint);
        canvas.drawLine(mCenterX - mRect.width()/2, mCenterY + mRect.height()/2,mCenterX + mRect.width()/2, mCenterY + mRect.height()/2, mPaint);
    }

}



































































































































