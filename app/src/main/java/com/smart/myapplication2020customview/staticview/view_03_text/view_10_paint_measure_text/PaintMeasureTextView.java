package com.smart.myapplication2020customview.staticview.view_03_text.view_10_paint_measure_text;

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
 * FileName: PaintMeasureTextView
 *
 * Des: Paint measureText(String text) 测量文字的宽度并返回
 *
 * 前面有了 getTextBounds()，这里怎么又有一个 measureText()？
 *
 * 如果你用代码分别使用 getTextBounds() 和 measureText() 来测量文字的宽度，你会发现 measureText() 测出来的
 * 宽度总是比 getTextBounds() 大一点点。这是因为这两个方法其实测量的是两个不一样的东西。
 *
 * getTextBounds: 它测量的是文字的显示范围（关键词：显示）。形象点来说，你这段文字外放置一个可变的矩形，然后把
 * 矩形尽可能地缩小，一直小到这个矩形恰好紧紧包裹住文字，那么这个矩形的范围，就是这段文字的 bounds。
 *
 * measureText(): 它测量的是文字绘制时所占用的宽度（关键词：占用）。前面已经讲过，一个文字在界面中，往往需要占
 * 用比他的实际显示宽度更多一点的宽度，以此来让文字和文字之间保留一些间距，不会显得过于拥挤。上面的这幅图，我并没
 * 有设置 setLetterSpacing() ，这里的 letter spacing 是默认值 0，但你可以看到，图中每两个字母之间都是有空隙
 * 的。另外，下方那条用于表示文字宽度的横线，在左边超出了第一个字母 H 一段距离的，在右边也超出了最后一个字母 r
 * （虽然右边这里用肉眼不太容易分辨），而就是两边的这两个「超出」，导致了 measureText() 比 getTextBounds()
 * 测量出的宽度要大一些。
 *
 * 在实际的开发中，测量宽度要用 measureText() 还是 getTextBounds() ，需要根据情况而定。不过你只要掌握了上面
 * 我所说的它们的本质，在选择的时候就不会为难和疑惑了。
 *
 * Time: 2020/6/5 4:38 PM
 */
public class PaintMeasureTextView extends android.view.View {

    private Paint mPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mOffsetX, mOffsetY;
    private String mContent;
    private Rect mRect;
    private float mTextWidth;

    public PaintMeasureTextView(Context context) {
        this(context, null);
    }

    public PaintMeasureTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintMeasureTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mTextWidth = mPaint.measureText(mContent);
        canvas.drawLine(mOffsetX, mOffsetY, mOffsetX + mTextWidth, mOffsetY, mPaint);

    }
}
