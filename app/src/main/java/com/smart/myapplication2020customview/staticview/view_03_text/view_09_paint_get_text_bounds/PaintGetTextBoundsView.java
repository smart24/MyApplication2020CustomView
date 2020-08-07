package com.smart.myapplication2020customview.staticview.view_03_text.view_09_paint_get_text_bounds;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

/**
 * FileName: PaintGetTextBoundsView
 *
 * Des: Paint getTextBounds(String text, int start, int end, Rect bounds) 获取文字的显示范围
 *
 * Time: 2020/6/5 4:38 PM
 */
public class PaintGetTextBoundsView extends android.view.View {

    private Paint mPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mOffsetX, mOffsetY;
    private String mContent;
    private Rect mRect;

    public PaintGetTextBoundsView(Context context) {
        this(context, null);
    }

    public PaintGetTextBoundsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintGetTextBoundsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        mRect = new Rect();
        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);
        Log.e(Constants.TAG, "init()" + "  left  " + mRect.left + "  top  " + mRect.top + "  right  " + mRect.right + "  bottom  " + mRect.bottom);
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
        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);
        Log.e(Constants.TAG, "onDraw()  DEFAULT" + "  left  " + mRect.left + "  top  " + mRect.top + "  right  " + mRect.right + "  bottom  " + mRect.bottom);
        mPaint.setStyle(Paint.Style.STROKE);
        mRect.left += mOffsetX;
        mRect.top += mOffsetY;
        mRect.right += mOffsetX;
        mRect.bottom += mOffsetY;
        canvas.drawRect(mRect, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Point lines.ttf"));
        mOffsetY += mTextSize * 2f;
        canvas.drawText(mContent, mOffsetX, mOffsetY, mPaint);
        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);
        Log.e(Constants.TAG, "onDraw()  Point" + "  left  " + mRect.left + "  top  " + mRect.top + "  right  " + mRect.right + "  bottom  " + mRect.bottom);
        mPaint.setStyle(Paint.Style.STROKE);
        mRect.left += mOffsetX;
        mRect.top += mOffsetY;
        mRect.right += mOffsetX;
        mRect.bottom += mOffsetY;
        canvas.drawRect(mRect, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Blacklettersh.ttf"));
        mOffsetY += mTextSize * 2f;
        canvas.drawText(mContent, mOffsetX, mOffsetY, mPaint);
        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);
        Log.e(Constants.TAG, "onDraw()  Blacklettersh" + "  left  " + mRect.left + "  top  " + mRect.top + "  right  " + mRect.right + "  bottom  " + mRect.bottom);
        mPaint.setStyle(Paint.Style.STROKE);
        mRect.left += mOffsetX;
        mRect.top += mOffsetY;
        mRect.right += mOffsetX;
        mRect.bottom += mOffsetY;
        canvas.drawRect(mRect, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "汉仪天宇风行体.ttf"));
        mOffsetY += mTextSize * 2f;
        canvas.drawText(mContent, mOffsetX, mOffsetY, mPaint);
        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);
        Log.e(Constants.TAG, "onDraw()  汉仪天宇风行体" + "  left  " + mRect.left + "  top  " + mRect.top + "  right  " + mRect.right + "  bottom  " + mRect.bottom);
        mPaint.setStyle(Paint.Style.STROKE);
        mRect.left += mOffsetX;
        mRect.top += mOffsetY;
        mRect.right += mOffsetX;
        mRect.bottom += mOffsetY;
        canvas.drawRect(mRect, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "east-liberty-signature-3.ttf"));
        mOffsetY += mTextSize * 2f;
        canvas.drawText(mContent, mOffsetX, mOffsetY, mPaint);
        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);
        Log.e(Constants.TAG, "onDraw()  East" + "  left  " + mRect.left + "  top  " + mRect.top + "  right  " + mRect.right + "  bottom  " + mRect.bottom);
        mPaint.setStyle(Paint.Style.STROKE);
        mRect.left += mOffsetX;
        mRect.top += mOffsetY;
        mRect.right += mOffsetX;
        mRect.bottom += mOffsetY;
        canvas.drawRect(mRect, mPaint);

    }
}
