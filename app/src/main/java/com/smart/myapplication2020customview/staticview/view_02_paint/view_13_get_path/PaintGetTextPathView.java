package com.smart.myapplication2020customview.staticview.view_02_paint.view_13_get_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintGetFillPathView
 *
 * Des: getTextPath
 *
 * Time: 2020/6/2 5:34 PM
 */
public class PaintGetTextPathView extends android.view.View {

    private Paint mSrcPaint, mDstPaint;
    private float mSrcTextSize, mDstStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mDeltaX, mDeltaY;
    private String mContent;
    private Rect mContentRect;
    private Path mDstPath;

    public PaintGetTextPathView(Context context) {
        this(context, null);
    }

    public PaintGetTextPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintGetTextPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setStyle(Paint.Style.FILL);
        mSrcTextSize = getResources().getDimension(R.dimen.font_thirty_two);
        mSrcPaint.setTextSize(mSrcTextSize);
        mSrcPaint.setTextAlign(Paint.Align.CENTER);

        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setStyle(Paint.Style.STROKE);
        mDstStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mDstPaint.setStrokeWidth(mDstStrokeWidth);

        mContent = getResources().getString(R.string.calorie_content);
        mContentRect = new Rect();
        mSrcPaint.getTextBounds(mContent, 0, mContent.length(), mContentRect);

        mDstPath = new Path();
        mSrcPaint.getTextPath(mContent, 0, mContent.length(), 0, 0, mDstPath);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mDeltaX = (mWidth / 2) / 2;
        mDeltaY = (mHeight / 2) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Src
        canvas.save();
        canvas.translate(0, - mDeltaY);
        canvas.drawText(mContent, mCenterX, mCenterY, mSrcPaint);
        canvas.restore();

        //Dst
        canvas.save();
        canvas.translate(mCenterX, (mCenterY + mDeltaY));
        canvas.drawPath(mDstPath, mDstPaint);
        canvas.restore();

    }

}


































































































































