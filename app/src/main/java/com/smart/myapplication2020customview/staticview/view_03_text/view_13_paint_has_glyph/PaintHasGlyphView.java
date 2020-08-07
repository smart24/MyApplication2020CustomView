package com.smart.myapplication2020customview.staticview.view_03_text.view_13_paint_has_glyph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

/**
 * FileName: PaintHasGlyphView
 *
 * Des: Paint hasGlyph(String string) 检查指定的字符串中是否是一个单独的字形 (glyph）。最简单的情况是，
 * string 只有一个字母（比如 a）。
 *
 * Time: 2020/6/5 10:20 PM
 */
public class PaintHasGlyphView extends android.view.View {

    private Paint mPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private String mContent;

    public PaintHasGlyphView(Context context) {
        this(context, null);
    }

    public PaintHasGlyphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintHasGlyphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mTextSize = getResources().getDimension(R.dimen.font_medium);
        mPaint.setTextSize(mTextSize);

        mContent = getResources().getString(R.string.love);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawText(mContent, mWidth/2, mHeight/2, mPaint);

        Log.e(Constants.TAG, "hasGlyph  " + mPaint.hasGlyph(mContent));
    }
}
