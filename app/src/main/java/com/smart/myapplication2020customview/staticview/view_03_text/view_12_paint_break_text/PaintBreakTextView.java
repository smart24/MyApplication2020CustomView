package com.smart.myapplication2020customview.staticview.view_03_text.view_12_paint_break_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

/**
 * FileName: PaintBreakTextView
 *
 * Des: Paint breakText(String text, boolean measureForwards, float maxWidth, float[] measuredWidth)
 * 这个方法也是用来测量文字宽度的。但和 measureText() 的区别是， breakText() 是在给出宽度上限的前提下测量文字
 * 的宽度。如果文字的宽度超出了上限，那么在临近超限的位置截断文字。
 *
 * breakText() 的返回值是截取的文字个数（如果宽度没有超限，则是文字的总个数）。参数中， text 是要测量的文字；
 * measureForwards 表示文字的测量方向，true 表示由左往右测量；maxWidth 是给出的宽度上限；measuredWidth 是用
 * 于接受数据，而不是用于提供数据的：方法测量完成后会把截取的文字宽度（如果宽度没有超限，则为文字总宽度）赋值
 * 给 measuredWidth[0]。
 *
 * 这个方法可以用于多行文字的折行计算。
 *
 * Time: 2020/6/5 4:38 PM
 */
public class PaintBreakTextView extends android.view.View {

    private Paint mPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mOffsetX, mOffsetY;
    private String mContent;
    private int mMeasuredCount;
    private float []mMeasuredWidth;

    public PaintBreakTextView(Context context) {
        this(context, null);
    }

    public PaintBreakTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintBreakTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.deep_orange_400));
        mTextSize = getResources().getDimension(R.dimen.font_thirty_two);
        mPaint.setTextSize(mTextSize);

//        mContent = getResources().getString(R.string.tian_wang_gai_di_hu);
//        mContent = getResources().getString(R.string.king_of_kings);
        mContent = getResources().getString(R.string.china_no_1);

        mMeasuredWidth = new float[1];
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

        mOffsetX = mCenterX/2;
        mOffsetY = mCenterY/2;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTypeface(Typeface.DEFAULT);
        mMeasuredCount = mPaint.breakText(mContent, 0, mContent.length(), true, 150, mMeasuredWidth);
        Log.e(Constants.TAG, "onDraw()  1  MeasuredWidth == " + mMeasuredWidth[0] + "  MeasuredCount  " + mMeasuredCount);
        canvas.drawText(mContent, 0, mMeasuredCount, mOffsetX, mOffsetY, mPaint);

        mMeasuredCount = mPaint.breakText(mContent, 0, mContent.length(), true, 180, mMeasuredWidth);
        Log.e(Constants.TAG, "onDraw()  2  MeasuredWidth == " + mMeasuredWidth[0] + "  MeasuredCount  " + mMeasuredCount);
        canvas.drawText(mContent, 0, mMeasuredCount, mOffsetX, mOffsetY + mPaint.getFontSpacing(), mPaint);

        mMeasuredCount = mPaint.breakText(mContent, 0, mContent.length(), true, 250, mMeasuredWidth);
        Log.e(Constants.TAG, "onDraw()  3  MeasuredWidth == " + mMeasuredWidth[0] + "  MeasuredCount  " + mMeasuredCount);
        canvas.drawText(mContent, 0, mMeasuredCount, mOffsetX, mOffsetY + mPaint.getFontSpacing() * 2, mPaint);

        mMeasuredCount = mPaint.breakText(mContent, 0, mContent.length(), true, 300, mMeasuredWidth);
        Log.e(Constants.TAG, "onDraw()  4  MeasuredWidth == " + mMeasuredWidth[0]  + "  MeasuredCount  " + mMeasuredCount);
        canvas.drawText(mContent, 0, mMeasuredCount, mOffsetX, mOffsetY + mPaint.getFontSpacing() * 3, mPaint);

        mMeasuredCount = mPaint.breakText(mContent, 0, mContent.length(), true, 400, mMeasuredWidth);
        Log.e(Constants.TAG, "onDraw()  5  MeasuredWidth == " + mMeasuredWidth[0]  + "  MeasuredCount  " + mMeasuredCount);
        canvas.drawText(mContent, 0, mMeasuredCount, mOffsetX, mOffsetY + mPaint.getFontSpacing() * 4, mPaint);

        mMeasuredCount = mPaint.breakText(mContent, 0, mContent.length(), true, 500, mMeasuredWidth);
        Log.e(Constants.TAG, "onDraw()  6  MeasuredWidth == " + mMeasuredWidth[0]  + "  MeasuredCount  " + mMeasuredCount);
        canvas.drawText(mContent, 0, mMeasuredCount, mOffsetX, mOffsetY + mPaint.getFontSpacing() * 5, mPaint);

        mMeasuredCount = mPaint.breakText(mContent, 0, mContent.length(), true, 600, mMeasuredWidth);
        Log.e(Constants.TAG, "onDraw()  7  MeasuredWidth == " + mMeasuredWidth[0]  + "  MeasuredCount  " + mMeasuredCount);
        canvas.drawText(mContent, 0, mMeasuredCount, mOffsetX, mOffsetY + mPaint.getFontSpacing() * 6, mPaint);

        mMeasuredCount = mPaint.breakText(mContent, 0, mContent.length(), true, 700, mMeasuredWidth);
        Log.e(Constants.TAG, "onDraw()  8  MeasuredWidth == " + mMeasuredWidth[0]  + "  MeasuredCount  " + mMeasuredCount);
        canvas.drawText(mContent, 0, mMeasuredCount, mOffsetX, mOffsetY + mPaint.getFontSpacing() * 7, mPaint);

        mMeasuredCount = mPaint.breakText(mContent, 0, mContent.length(), true, 800, mMeasuredWidth);
        Log.e(Constants.TAG, "onDraw()  9  MeasuredWidth == " + mMeasuredWidth[0]  + "  MeasuredCount  " + mMeasuredCount);
        canvas.drawText(mContent, 0, mMeasuredCount, mOffsetX, mOffsetY + mPaint.getFontSpacing() * 8, mPaint);

        mMeasuredCount = mPaint.breakText(mContent, 0, mContent.length(), true, 900, mMeasuredWidth);
        Log.e(Constants.TAG, "onDraw()  10  MeasuredWidth == " + mMeasuredWidth[0]  + "  MeasuredCount  " + mMeasuredCount);
        canvas.drawText(mContent, 0, mMeasuredCount, mOffsetX, mOffsetY + mPaint.getFontSpacing() * 9, mPaint);

    }
}
