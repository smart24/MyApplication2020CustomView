package com.smart.myapplication2020customview.staticview.view_03_text.view_03_static_layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

public class StaticLayoutView extends android.view.View {

    private TextPaint mTextPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private String mContent;
    private StaticLayout mStaticLayout;

    public StaticLayoutView(Context context) {
        this(context, null);
    }

    public StaticLayoutView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StaticLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextSize = getResources().getDimension(R.dimen.font_medium);
        mTextPaint.setTextSize(mTextSize);

        mContent = getResources().getString(R.string.motto_from_movie);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

//        mStaticLayout = new StaticLayout(mContent, mTextPaint, (int)mWidth/2, Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
        mStaticLayout = new StaticLayout(mContent, mTextPaint, (int)mWidth, Layout.Alignment.ALIGN_NORMAL, 1.4f, 0f, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mStaticLayout.draw(canvas);
    }

}
