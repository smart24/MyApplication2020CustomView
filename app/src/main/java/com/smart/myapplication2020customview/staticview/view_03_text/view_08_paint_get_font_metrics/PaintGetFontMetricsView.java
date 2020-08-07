package com.smart.myapplication2020customview.staticview.view_03_text.view_08_paint_get_font_metrics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

/**
 * FileName: PaintGetFontMetricsView
 *
 * Des: Paint getFontMetrics() 获取 Paint 的 FontMetrics
 *
 * Time: 2020/6/5 4:19 PM
 */
public class PaintGetFontMetricsView extends View {

    private Paint mTextPaint, mLinePaint;
    private float mFontSize, mStrokeWidth;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private String mText;
    private Rect mRect;
    private Paint.FontMetrics mFontMetrics;
    private float top, ascent, baseline, descent, bottom;

    public PaintGetFontMetricsView(Context context) {
        this(context, null);
    }

    public PaintGetFontMetricsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintGetFontMetricsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mFontSize = getResources().getDimension(R.dimen.font_ninety_six);
        mTextPaint.setTextSize(mFontSize);
        mTextPaint.setColor(getResources().getColor(R.color.grey_700));

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mLinePaint.setStrokeWidth(mStrokeWidth);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);

//        mText = getResources().getString(R.string.king_of_kings);
        mText = getResources().getString(R.string.love);
//        mText = getResources().getString(R.string.china_no_1);

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

        baseline = mCenterY + mRect.height()/2;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawText(mText, (mCenterX - mRect.width()/2), (baseline), mTextPaint);

        mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(mText, mCenterX, baseline, mTextPaint);

        mFontMetrics = mTextPaint.getFontMetrics();

        top = baseline + mFontMetrics.top;
        ascent = baseline + mFontMetrics.ascent;
        descent = baseline + mFontMetrics.descent;
        bottom = baseline + mFontMetrics.bottom;

        mLinePaint.setColor(getResources().getColor(R.color.blue_400));
        canvas.drawLine(mCenterX - mRect.width() * 1.7f/3, top, mCenterX + mRect.width()* 1.7f/3, top, mLinePaint);
        mLinePaint.setColor(getResources().getColor(R.color.green_400));
        canvas.drawLine(mCenterX - mRect.width() * 1.7f/3, ascent, mCenterX + mRect.width()* 1.7f/3, ascent, mLinePaint);
        mLinePaint.setColor(getResources().getColor(R.color.grey_700));
        canvas.drawLine(mCenterX - mRect.width() * 1.7f/3, baseline, mCenterX + mRect.width()* 1.7f/3, baseline, mLinePaint);
        mLinePaint.setColor(getResources().getColor(R.color.orange_400));
        canvas.drawLine(mCenterX - mRect.width() * 1.7f/3, descent, mCenterX + mRect.width()* 1.7f/3, descent, mLinePaint);
        mLinePaint.setColor(getResources().getColor(R.color.red_400));
        canvas.drawLine(mCenterX - mRect.width() * 1.7f/3, bottom, mCenterX + mRect.width()* 1.7f/3, bottom, mLinePaint);

        mLinePaint.setColor(getResources().getColor(R.color.white));
        canvas.drawCircle(mCenterX, mCenterY, mStrokeWidth * 2, mLinePaint);

        mLinePaint.setColor(getResources().getColor(R.color.grey_400));
        Log.e(Constants.TAG, "Top  " + mFontMetrics.top + "  Bottom  " + mFontMetrics.bottom);
        canvas.drawCircle(mCenterX, top + (mFontMetrics.bottom - mFontMetrics.top)/2, mStrokeWidth, mLinePaint);
        canvas.drawCircle(mCenterX, (top + (mFontMetrics.bottom - mFontMetrics.top)/2), (mFontMetrics.bottom - mFontMetrics.top)/2, mLinePaint);

        mLinePaint.setColor(getResources().getColor(R.color.red_400));
        canvas.drawRect(mCenterX - mRect.width()/2, mCenterY - mRect.height()/2, mCenterX + mRect.width()/2, baseline, mLinePaint);

    }

}
















































































































































































































































































































































































































