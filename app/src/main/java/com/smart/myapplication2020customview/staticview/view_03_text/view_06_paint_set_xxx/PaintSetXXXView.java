package com.smart.myapplication2020customview.staticview.view_03_text.view_06_paint_set_xxx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;

import java.util.Locale;

/**
 * FileName: PaintSetXXXView
 *
 * Des: Paint setXXX 设置文字的显示效果
 *
 * Time: 2020/6/5 1:59 PM
 */
public class PaintSetXXXView extends android.view.View {

    private Paint mPaint;
    private float mTextSize;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private String mContent;
    private PathEffect mPathEffect;

    public PaintSetXXXView(Context context) {
        this(context, null);
    }

    public PaintSetXXXView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintSetXXXView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.deep_orange_400));
        mTextSize = getResources().getDimension(R.dimen.font_thirty_two);
        mPaint.setTextSize(mTextSize);

//        mContent = getResources().getString(R.string.calorie_content);
//        mContent = getResources().getString(R.string.china_no_1);
        mContent = getResources().getString(R.string.tian_wang_gai_di_hu);
//        mContent = getResources().getString(R.string.love);

        mPathEffect = new DashPathEffect(new float[]{getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_micro)}, getResources().getDimension(R.dimen.padding_zero));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1. setTextSize(float textSize) //设置文字大小
//        mPaint.setTextSize(mTextSize);
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2, mPaint);

        //2. setTypeface(Typeface typeface) //设置字体
//        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "east-liberty-signature-3.ttf"));
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2, mPaint);

        //3. setFakeBoldText(boolean fakeBoldText) //是否使用伪粗体
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2, mPaint);
//        mPaint.setFakeBoldText(true);
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 2, mPaint);

        //4. setStrikeThruText(boolean strikeThruText) //是否加删除线
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2, mPaint);
//        mPaint.setStrikeThruText(true);
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 2, mPaint);

        //5. setUnderlineText(boolean underlineText) //是否加下划线
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2, mPaint);
//        mPaint.setUnderlineText(true);
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 2, mPaint);

        //6. setTextSkewX(float skewX) //设置文字横向错切角度。其实就是文字倾斜度的啦
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2, mPaint);
//        mPaint.setTextSkewX(getResources().getFraction(R.fraction.percentage_fifty, 1, 1));
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 2, mPaint);
//        mPaint.setTextSkewX(-getResources().getFraction(R.fraction.percentage_fifty, 1, 1));
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 4, mPaint);

        //7. setTextScaleX(float scaleX) //设置文字横向放缩。也就是文字变胖变瘦
//        mPaint.setTextScaleX(getResources().getFraction(R.fraction.percentage_one_hundred, 1, 1));
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2, mPaint);
//        mPaint.setTextScaleX(getResources().getFraction(R.fraction.percentage_fifty, 1, 1));
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 2, mPaint);
//        mPaint.setTextScaleX(getResources().getFraction(R.fraction.percentage_fifty, 3, 1));
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 4, mPaint);

        //8. setLetterSpacing(float letterSpacing) //设置字符间距。默认值是 0
//        mPaint.setLetterSpacing(getResources().getFraction(R.fraction.percentage_fifty, 0, 1));
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2, mPaint);
//        mPaint.setLetterSpacing(getResources().getFraction(R.fraction.percentage_fifty, 1, 1));
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 2, mPaint);
//        mPaint.setLetterSpacing(getResources().getFraction(R.fraction.percentage_fifty, 2, 1));
//        canvas.drawText(mContent, mCenterX/2, mCenterY/2 + mTextSize * 4, mPaint);

        //9. setFontFeatureSettings(String settings) //用 CSS 的 font-feature-settings 的方式来设置文字
//        mPaint.setFontFeatureSettings(null);
//        canvas.drawText(mContent, mCenterX/12, mCenterY/2, mPaint);
//        mPaint.setFontFeatureSettings("smcp");
//        canvas.drawText(mContent, mCenterX/12, mCenterY/2 + mTextSize * 2, mPaint);
//        mPaint.setFontFeatureSettings("c2sc");
//        canvas.drawText(mContent, mCenterX/12, mCenterY/2 + mTextSize * 4, mPaint);

        //10. setTextAlign(Paint.Align align) //设置文字的对齐方式。一共有三个值：LEFT CETNER 和 RIGHT。默认值为 LEFT
//        mPaint.setTextAlign(Paint.Align.LEFT);
//        canvas.drawText(mContent, mCenterX, mCenterY/2, mPaint);
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText(mContent, mCenterX, mCenterY/2 + mTextSize * 2, mPaint);
//        mPaint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText(mContent, mCenterX, mCenterY/2 + mTextSize * 4, mPaint);
//        mPaint.setPathEffect(mPathEffect);
//        canvas.drawLine(mCenterX, mCenterY/2 - mTextSize, mCenterX, mCenterY/2 + mTextSize * 4.5f, mPaint);

        //11. setTextLocale(Locale locale) / setTextLocales(LocaleList locales) //设置绘制所使用的 Locale
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextLocale(Locale.CHINA);
        canvas.drawText(mContent, mCenterX, mCenterY/2, mPaint);
        mPaint.setTextLocale(Locale.TAIWAN);
        canvas.drawText(mContent, mCenterX, mCenterY/2 + mTextSize * 2, mPaint);
        mPaint.setTextLocale(Locale.JAPAN);
        canvas.drawText(mContent, mCenterX, mCenterY/2 + mTextSize * 4, mPaint);
    }
}
