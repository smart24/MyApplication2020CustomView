package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_07_draw_after_super_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasDrawAfterSuperDrawView
 *
 * Des: Canvas draw after super.draw()
 *
 *      由于 draw() 是总调度方法，所以如果把绘制代码写在 super.draw() 的下面，那么这段代码会在其他所有绘制完成
 *      之后再执行，也就是说，它的绘制内容会盖住其他的所有绘制内容。
 *
 *      它的效果和重写 onDrawForeground()，并把绘制代码写在 super.onDrawForeground() 下面时的效果是一样的：
 *      都会盖住其他的所有内容。
 *
 *      当然了，虽说它们效果一样，但如果你既重写 draw() 又重写 onDrawForeground() ，那么 draw() 里的内容还是
 *      会盖住 onDrawForeground() 里的内容的。所以严格来讲，它们的效果还是有一点点不一样的。
 *
 *      但这属于抬杠……
 *
 * Version:
 *
 * State:
 *
 * Optimize:
 *
 * Time: 2020/6/9 4:17 PM
 */
public class CanvasDrawAfterSuperDrawView extends androidx.appcompat.widget.AppCompatImageView {

    private Paint mTextBackgroundPaint, mTextPaint;
    private float mTextSize;
    private Rect mRect;
    private String mContent;
    private float mContentPadding;

    public CanvasDrawAfterSuperDrawView(Context context) {
        this(context, null);
    }

    public CanvasDrawAfterSuperDrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawAfterSuperDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
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
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawRect(0, (mRect.height() * 3 / 2), (mContentPadding + mRect.width() + mContentPadding), (mRect.height() * 3 + mRect.height() * 1 / 2), mTextBackgroundPaint);
        canvas.drawText(mContent, mContentPadding, mRect.height() * 3, mTextPaint);
    }

}