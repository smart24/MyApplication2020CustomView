package com.smart.myapplication2020customview.staticview.view_03_text.view_01_canvas_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasDrawTextView
 *
 * Des: Canvas.drawText()
 *
 * Time: 2020/6/3 5:10 PM
 */
public class CanvasDrawTextView extends android.view.View {

    private Paint mTextPaint;
    private float mFontSize;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private String mText;
    private Rect mRect;

    public CanvasDrawTextView(Context context) {
        this(context, null);
    }

    public CanvasDrawTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mFontSize = getResources().getDimension(R.dimen.font_thirty_two);
        mTextPaint.setTextSize(mFontSize);
        mTextPaint.setColor(getResources().getColor(R.color.grey_700));


        mText = getResources().getString(R.string.calorie_content);

        mRect = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mTextPaint.getTextBounds(mText, 0, mText.length(), mRect);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mTextPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mCenterX, mCenterY, mCenterX/2, mTextPaint);

        mTextPaint.setStyle(Paint.Style.FILL);
//        canvas.drawText(mText, (mCenterX - mRect.width()/2), (mCenterY + mRect.height()/2), mTextPaint);
        canvas.drawText(mText, 0, 4, (mCenterX - mRect.width()/2), (mCenterY + mRect.height()/2), mTextPaint);

    }

}
















































































































































































































































































































































































































