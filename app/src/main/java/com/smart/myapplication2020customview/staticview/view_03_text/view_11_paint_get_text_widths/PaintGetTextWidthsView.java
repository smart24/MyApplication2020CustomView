package com.smart.myapplication2020customview.staticview.view_03_text.view_11_paint_get_text_widths;

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
 * FileName: PaintGetTextWidthsView
 *
 * Des: Paint getTextWidths(String text, float[] widths) 获取字符串中每个字符的宽度，并把结果填入参数 widths
 *
 * 这相当于 measureText() 的一个快捷方法，它的计算等价于对字符串中的每个字符分别调用 measureText() ，并把它们
 * 的计算结果分别填入 widths 的不同元素。
 *
 * Time: 2020/6/5 4:38 PM
 */
public class PaintGetTextWidthsView extends android.view.View {

    private Paint mPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mOffsetX, mOffsetY;
    private String mContent;
    private float []mTextWidths;

    public PaintGetTextWidthsView(Context context) {
        this(context, null);
    }

    public PaintGetTextWidthsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintGetTextWidthsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mTextWidths = new float[mContent.length()];
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
        canvas.drawText(mContent, mOffsetX, mOffsetY, mPaint);
        mPaint.getTextWidths(mContent, mTextWidths);
        for (int i = 0; i < mTextWidths.length; i++) {
            Log.e(Constants.TAG, "onDraw()  i== " + i + "  width  " + mTextWidths[i]);
        }
    }
}
