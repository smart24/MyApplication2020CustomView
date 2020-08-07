package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_02_draw_before_super_on_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasDrawBeforeSuperOnDrawView
 *
 * Des: Canvas draw after super.onDraw()
 *
 *      如果把绘制代码写在 super.onDraw() 的上面，由于绘制代码会执行在原有内容的绘制之前，所以绘制的内容会被控
 *      件的原内容盖住。
 *
 * Version:
 *
 * State:
 *
 * Optimize:
 *
 * Time: 2020/6/9 3:42 PM
 */
public class CanvasDrawBeforeSuperOnDrawView extends androidx.appcompat.widget.AppCompatTextView {

    private Paint mPaint;
    private float mCornerRadius;
    private float mWidth, mHeight;

    public CanvasDrawBeforeSuperOnDrawView(Context context) {
        this(context, null);
    }

    public CanvasDrawBeforeSuperOnDrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawBeforeSuperOnDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.yellow_400));

        mCornerRadius = getResources().getDimension(R.dimen.padding_micro);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0, 0, mWidth, mHeight, mCornerRadius, mCornerRadius, mPaint);
        super.onDraw(canvas);
    }

}
