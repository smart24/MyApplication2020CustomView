package com.smart.myapplication2020customview.staticview.view_02_paint.view_14_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintSetTextSizeView
 *
 * Des: Paint setTextSize()
 *
 * Time: 2020/6/3 11:06 AM
 */
public class PaintSetTextSizeView extends View {

    private Paint mPaint;
    private float mFontSize;
    private int mWidth, mHeight;
    private float mCenterX, mCenterY;
    private String mText;

    public PaintSetTextSizeView(Context context) {
        this(context, null);
    }

    public PaintSetTextSizeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintSetTextSizeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.blue_400));
        mFontSize = getResources().getDimension(R.dimen.font_medium);
        mPaint.setTextSize(mFontSize);

        mText = getResources().getString(R.string.tian_wang_gai_di_hu);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setTextSize(getResources().getDimension(R.dimen.font_micro));
        canvas.drawText(mText, mCenterX - mFontSize * mText.length()/2, mCenterY + mFontSize/2 - mFontSize - mFontSize, mPaint);
        mPaint.setTextSize(getResources().getDimension(R.dimen.font_medium));
        canvas.drawText(mText, mCenterX - mFontSize * mText.length()/2, mCenterY + mFontSize/2, mPaint);
        mPaint.setTextSize(getResources().getDimension(R.dimen.font_thirty_two));
        canvas.drawText(mText, mCenterX - mFontSize * mText.length()/2, mCenterY + mFontSize/2 + mFontSize + getResources().getDimension(R.dimen.font_thirty_two), mPaint);
        mPaint.setTextSize(getResources().getDimension(R.dimen.font_forty_eight));
        canvas.drawText(mText, mCenterX - mFontSize * mText.length()/2, mCenterY + mFontSize/2 + mFontSize + getResources().getDimension(R.dimen.font_thirty_two) + mFontSize + getResources().getDimension(R.dimen.font_forty_eight), mPaint);
    }

}






































































