package com.smart.myapplication2020customview.staticview.view_01_basic.view_12_unit_01_practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: RectView
 *
 * Des: 探究如何让文字居中
 *
 * Time: 2020/5/18 10:51 AM
 */
public class RectView extends View {

    private Paint mRectPaint, mTextPaint;
    private float mRectStrokeWidth, mTextFontSize;
    private float mWidth, mHeight;
    private float mLeft, mTop, mRight, mBottom;
    private float mCenterX, mCenterY;
    private String mText;
    private Rect mRect;

    public RectView(Context context) {
        this(context, null);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mRectPaint.setStrokeWidth(mRectStrokeWidth);
        mRectPaint.setColor(getResources().getColor(R.color.green_800));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextFontSize = getResources().getDimension(R.dimen.font_forty_eight);
        mTextPaint.setTextSize(mTextFontSize);
        mTextPaint.setColor(getResources().getColor(R.color.deep_orange_400));

        mText = getResources().getString(R.string.king_of_kings);

        mRect = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mLeft = mCenterX - mWidth/4;
        mTop = mCenterY - mWidth/4;
        mRight = mCenterX + mWidth/4;
        mBottom = mCenterY + mWidth/4;

        mTextPaint.getTextBounds(mText, 0, mText.length(), mRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 第一种处理方式
//        canvas.drawCircle(mCenterX, mCenterY, mRectStrokeWidth, mRectPaint);
//        canvas.save();
//        canvas.translate(mCenterX, mCenterY);
//        canvas.drawRect(mRect.left - mRect.width()/2, mRect.top + mRect.height()/2, mRect.right - mRect.width()/2, mRect.bottom + mRect.height()/2, mRectPaint);
//        canvas.drawText(mText, mRect.left - mRect.width()/2,mRect.bottom + mRect.height()/2, mTextPaint);
//        canvas.restore();

        //2. 第二种处理方式
//        mTextPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawRect(mCenterX - mRect.width()/2, mCenterY - mRect.height()/2, mCenterX + mRect.width()/2, mCenterY + mRect.height()/2, mRectPaint);
//        canvas.drawText(mText, mCenterX,mCenterY + mRect.height()/2, mTextPaint);
//        canvas.drawCircle(mCenterX, mCenterY, mRectStrokeWidth, mRectPaint);

        //3.
//        canvas.drawLine(mLeft, mCenterY, mRight, mCenterY, mRectPaint);
//        canvas.drawLine(mCenterX, mTop, mCenterX, mBottom, mRectPaint);
//        canvas.drawCircle(mCenterX, mCenterY, mRectStrokeWidth, mRectPaint);
//        canvas.drawRect(mCenterX - mRect.width()/2, mCenterY - mRect.height()/2, mCenterX + mRect.width()/2, mCenterY + mRect.height()/2, mRectPaint);
//        canvas.drawText(mText, mCenterX - mRect.width()/2, mCenterY + mRect.height()/2, mTextPaint);

        //4.
        canvas.drawLine(mLeft, mCenterY, mRight, mCenterY, mRectPaint);
        canvas.drawLine(mCenterX, mTop, mCenterX, mBottom, mRectPaint);
        canvas.drawCircle(mCenterX, mCenterY, mRectStrokeWidth, mRectPaint);
        canvas.drawRect(mCenterX - mRect.width()/2, mCenterY - mRect.height()/2, mCenterX + mRect.width()/2, mCenterY + mRect.height()/2, mRectPaint);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(mText, mCenterX,mCenterY + mRect.height()/2, mTextPaint);
    }

}


























































































































































































































































































































































































