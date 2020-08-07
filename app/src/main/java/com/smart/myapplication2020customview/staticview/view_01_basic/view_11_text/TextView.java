package com.smart.myapplication2020customview.staticview.view_01_basic.view_11_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;


public class TextView extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mFontSize;
    private int mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mLeft, mTop, mRight, mBottom;
    private String mText;

    public TextView(Context context) {
        this(context, null);
    }

    public TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mPaint.setStrokeWidth(mStrokeWidth);
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
        mLeft = mCenterX - mWidth/4;
        mTop = mCenterY - mHeight/4;
        mRight = mCenterX + mWidth/4;
        mBottom = mCenterY + mHeight/4;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 单行字
        //drawText(@NonNull String text, float x, float y, @NonNull Paint paint)
        // 此处的 x，y 是文字左下角的坐标

//        canvas.drawText(mText, 0, 0, mPaint);
//        canvas.drawText(mText, 0, mFontSize, mPaint);
//        mPaint.setColor(getResources().getColor(R.color.translucent_blue_700));
//        canvas.drawLine(mLeft, mCenterY, mRight, mCenterY, mPaint);
//        canvas.drawLine(mCenterX, mTop, mCenterX, mBottom, mPaint);
//        mPaint.setColor(getResources().getColor(R.color.blue_400));
//        canvas.drawText(mText, mCenterX - mFontSize * mText.length()/2, mCenterY + mFontSize/2, mPaint);

        //2. 多行字（旋转画布）
//        for (int i = 0; i < 360; i+=15) {
//            if(i == 0){
//                mPaint.setColor(getResources().getColor(R.color.red_400));
//            }else if(i == 15 || i == 345){
//                mPaint.setColor(getResources().getColor(R.color.green_400));
//            }else{
//                mPaint.setColor(getResources().getColor(R.color.blue_400));
//            }
//            canvas.save();
//            canvas.rotate(i, mCenterX, mCenterY);
//            canvas.drawText(mText, mCenterX - mFontSize * mText.length()/2, mCenterY + mFontSize/2, mPaint);
//            canvas.restore();
//        }

//        for (int i = 0; i < 360; i+=15) {
//            if(i == 0){
//                mPaint.setColor(getResources().getColor(R.color.red_400));
//            }else if(i == 15 || i == 345){
//                mPaint.setColor(getResources().getColor(R.color.green_400));
//            }else{
//                mPaint.setColor(getResources().getColor(R.color.blue_400));
//            }
//            canvas.save();
//            canvas.rotate(i, mCenterX, mCenterY);
//            canvas.drawText(mText, mCenterX + mFontSize * 1.5f, mCenterY + mFontSize/2, mPaint);
//            canvas.restore();
//        }

        //3. 多行字（设置文字的大小）
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






































































