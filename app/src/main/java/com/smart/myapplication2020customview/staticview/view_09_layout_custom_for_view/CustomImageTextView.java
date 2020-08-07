package com.smart.myapplication2020customview.staticview.view_09_layout_custom_for_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.BitmapUtils;

/**
 * FileName: CustomImageTextView
 *
 * Des: 
 * 
 * Version: 
 * 
 * State: 
 * 
 * Optimize: 
 * 
 * Time: 2020/6/27 7:53 PM
 */
public class CustomImageTextView extends View {

    private Paint mImagePaint, mTextPaint;
    private int mImageWidth, mImageHeight;
    private Bitmap mImageBitmap;
    private int mImageMarginBottom;
    private int mTextSize;
    private String mTextContent;
    private Rect mTextRect;
    private int mWidth, mHeight;

    public CustomImageTextView(Context context) {
        this(context, null);
    }

    public CustomImageTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mImagePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mImageWidth = (int) getResources().getDimension(R.dimen.padding_one_hundred_ninety_six);
        mImageHeight = (int) getResources().getDimension(R.dimen.padding_one_hundred_ninety_six);
        mImageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);
        mImageBitmap = BitmapUtils.scaleBitmap(mImageBitmap, mImageWidth, mImageHeight);
        mImageMarginBottom = (int)getResources().getDimension(R.dimen.padding_large);

        mTextSize = (int)getResources().getDimension(R.dimen.font_medium);
        mTextPaint.setTextSize(mTextSize);
        mTextContent = getResources().getString(R.string.kobe_bryant);
        mTextRect = new Rect();
        mTextPaint.getTextBounds(mTextContent, 0, mTextContent.length(), mTextRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = mImageWidth >= mTextRect.width() ? mImageWidth : mTextRect.width();
        mHeight = mImageHeight + mImageMarginBottom + mTextRect.height();

        mWidth = resolveSize(mWidth, widthMeasureSpec);
        mHeight = resolveSize(mHeight, heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);
    }

    public static int resolveSize(int size, int measureSpec) {
        return resolveSizeAndState(size, measureSpec, 0) & MEASURED_SIZE_MASK;
    }

    public static int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
        final int specMode = MeasureSpec.getMode(measureSpec);
        final int specSize = MeasureSpec.getSize(measureSpec);
        final int result;
        switch (specMode) {
            case MeasureSpec.AT_MOST:
                if (specSize < size) {
                    result = specSize | MEASURED_STATE_TOO_SMALL;
                } else {
                    result = size;
                }
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                result = size;
        }
        return result | (childMeasuredState & MEASURED_STATE_MASK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mImageBitmap, 0, 0, mImagePaint);

        canvas.drawText(mTextContent, mImageWidth/2, (mImageHeight + mImageMarginBottom), mTextPaint);
    }

}

