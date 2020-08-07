package com.smart.myapplication2020customview.staticview.view_03_text.view_05_paint_set_typeface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

public class PaintSetTypefaceView extends android.view.View {

    private Paint mPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private String mContent;

    public PaintSetTypefaceView(Context context) {
        this(context, null);
    }

    public PaintSetTypefaceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintSetTypefaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.deep_orange_400));
        mTextSize = getResources().getDimension(R.dimen.font_thirty_two);
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
//        mPaint.setTypeface(Typeface.DEFAULT);
//        canvas.drawText(mContent, mCenterX, mCenterY, mPaint);
//
//        mPaint.setTypeface(Typeface.SERIF);
//        canvas.drawText(mContent, mCenterX, mCenterY + mTextSize * 2, mPaint);
//
//        mPaint.setTypeface(Typeface.MONOSPACE);
//        canvas.drawText(mContent, mCenterX, mCenterY + mTextSize * 4f, mPaint);
//
//        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Chilling-Sabrina.ttf"));
//        canvas.drawText(mContent, mCenterX, mCenterY + mTextSize * 6f, mPaint);


        mPaint.setFakeBoldText(true);
        mPaint.setTypeface(Typeface.DEFAULT);
        canvas.drawText(mContent, mCenterX/2, mCenterY/2, mPaint);

        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Point lines.ttf"));
        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 2, mPaint);

        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Blacklettersh.ttf"));
        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 4f, mPaint);

        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "汉仪天宇风行体.ttf"));
        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 6f, mPaint);

        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "east-liberty-signature-3.ttf"));
        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 8f, mPaint);
    }
}
