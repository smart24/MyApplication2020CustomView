package com.smart.myapplication2020customview.staticview.view_03_text.view_07_paint_get_font_spacing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;
/**
 * FileName: PaintGetFontSpacingView
 *
 * Des: Paint getFontSpacing() 获取推荐的行距
 *
 * Time: 2020/6/5 3:44 PM
 */
public class PaintGetFontSpacingView extends android.view.View {

    private Paint mPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private String mContent;

    public PaintGetFontSpacingView(Context context) {
        this(context, null);
    }

    public PaintGetFontSpacingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintGetFontSpacingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mTextSize = getResources().getDimension(R.dimen.font_medium);
        mPaint.setTextSize(mTextSize);

        mContent = getResources().getString(R.string.china_no_1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTextSize = getResources().getDimension(R.dimen.font_micro);
        mPaint.setTextSize(mTextSize);
        canvas.drawText(mContent, mCenterX, mCenterY, mPaint);

        mTextSize = getResources().getDimension(R.dimen.font_medium);
        mPaint.setTextSize(mTextSize);
        canvas.drawText(mContent, mCenterX, mCenterY + mPaint.getFontSpacing(), mPaint);

        mTextSize = getResources().getDimension(R.dimen.font_thirty_two);
        mPaint.setTextSize(mTextSize);
        canvas.drawText(mContent, mCenterX, mCenterY + mPaint.getFontSpacing() * 2, mPaint);

        mTextSize = getResources().getDimension(R.dimen.font_forty_eight);
        mPaint.setTextSize(mTextSize);
        canvas.drawText(mContent, mCenterX, mCenterY + mPaint.getFontSpacing() * 3, mPaint);
    }
}
