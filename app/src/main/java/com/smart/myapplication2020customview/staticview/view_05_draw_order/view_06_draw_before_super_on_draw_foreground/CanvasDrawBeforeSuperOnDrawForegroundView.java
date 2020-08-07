package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_06_draw_before_super_on_draw_foreground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasDrawAfterSuperOnDrawForegroundView
 *
 * Des: Canvas draw before super.onDrawForeground()
 *
 *      如果你把绘制代码写在了 super.onDrawForeground() 的上面，绘制内容就会在 dispatchDraw() 和
 *      super.onDrawForeground() 之间执行，那么绘制内容会盖住子 View，但被滑动边缘渐变、滑动条以及前景盖住。
 * 
 * Version: 
 * 
 * State: 
 * 
 * Optimize: 
 * 
 * Time: 2020/6/9 3:21 PM
 */
public class CanvasDrawBeforeSuperOnDrawForegroundView extends androidx.appcompat.widget.AppCompatImageView {

    private Paint mTextBackgroundPaint, mTextPaint;
    private float mTextSize;
    private Rect mRect;
    private String mContent;
    private float mContentPadding;

    public CanvasDrawBeforeSuperOnDrawForegroundView(Context context) {
        this(context, null);
    }

    public CanvasDrawBeforeSuperOnDrawForegroundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawBeforeSuperOnDrawForegroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mTextBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextBackgroundPaint.setStyle(Paint.Style.FILL);
        mTextBackgroundPaint.setColor(getResources().getColor(R.color.deep_orange_400));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(getResources().getColor(R.color.white));
        mTextSize = getResources().getDimension(R.dimen.font_medium);
        mTextPaint.setTextSize(mTextSize);

        mRect = new Rect();

        mContent = getResources().getString(R.string.new_signal);
        mTextPaint.getTextBounds(mContent, 0, mContent.length(), mRect);

        mContentPadding = getResources().getDimension(R.dimen.padding_small);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        canvas.drawRect(0, (mRect.height() * 3/2), (mContentPadding + mRect.width() + mContentPadding), (mRect.height() * 3 + mRect.height() * 1/2), mTextBackgroundPaint);
        canvas.drawText(mContent, mContentPadding, mRect.height() * 3, mTextPaint);
        super.onDrawForeground(canvas);
    }

}
