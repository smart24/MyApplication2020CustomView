package com.smart.myapplication2020customview.staticview.view_03_text.view_02_canvas_draw_text_on_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

public class CanvasDrawTextOnPathView extends android.view.View {

    private Paint mPaint;
    private float mFontSize;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private String mText;
    private Rect mRect;
    private Path mPath;

    public CanvasDrawTextOnPathView(Context context) {
        this(context, null);
    }

    public CanvasDrawTextOnPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawTextOnPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mFontSize = getResources().getDimension(R.dimen.font_thirty_two);
        mPaint.setTextSize(mFontSize);
        mPaint.setColor(getResources().getColor(R.color.grey_700));

        mText = getResources().getString(R.string.calorie_content);

        mRect = new Rect();

        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mPaint.getTextBounds(mText, 0, mText.length(), mRect);

        mPath.addCircle(mCenterX, mCenterY, (mCenterX < mCenterY ? mCenterX : mCenterY)/2, Path.Direction.CW);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mCenterX, mCenterY, mCenterX/2, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawTextOnPath(mText, mPath, getResources().getDimension(R.dimen.padding_zero), getResources().getDimension(R.dimen.padding_zero), mPaint);
//        canvas.drawTextOnPath(mText, mPath, getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_zero), mPaint);
        canvas.drawTextOnPath(mText, mPath, getResources().getDimension(R.dimen.padding_zero), getResources().getDimension(R.dimen.font_thirty_two), mPaint);
    }

}
















































































































































































































































































































































































































