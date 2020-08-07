package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_01_draw_after_super_on_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;

/**
 * FileName: CanvasDrawAfterSuperOnDrawView
 *
 * Des: Canvas draw after super.onDraw()
 *
 *      把绘制代码写在 super.onDraw() 的下面，由于绘制代码会在原有内容绘制结束之后才执行，所以绘制内容就会盖住
 *      控件原来的内容。
 *
 * Version:
 *
 * State:
 *
 * Optimize:
 *
 * Time: 2020/6/9 12:00 PM
 */
public class CanvasDrawAfterSuperOnDrawView extends androidx.appcompat.widget.AppCompatImageView {

    private Paint mPaint;
    private float mTextSize;
    private StringBuilder mStringBuilder;
    private String mContent;
    private Rect mRect;

    public CanvasDrawAfterSuperOnDrawView(Context context) {
        this(context, null);
    }

    public CanvasDrawAfterSuperOnDrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawAfterSuperOnDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.white));
        mTextSize = getResources().getDimension(R.dimen.font_small);
        mPaint.setTextSize(mTextSize);

        mStringBuilder = new StringBuilder();

        mRect = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStringBuilder.append(getResources().getString(R.string.size));
        mStringBuilder.append(getMeasuredWidth());
        mStringBuilder.append(getResources().getString(R.string.multiply));
        mStringBuilder.append(getMeasuredHeight());

        mContent = mStringBuilder.toString();

        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(Constants.DEBUG){
            canvas.drawText(mContent, 0, mContent.length(), mRect.height(), mRect.height() * 2, mPaint);
        }
    }

}
