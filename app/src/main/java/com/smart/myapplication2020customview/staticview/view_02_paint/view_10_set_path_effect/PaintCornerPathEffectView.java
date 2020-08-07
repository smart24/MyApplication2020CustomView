package com.smart.myapplication2020customview.staticview.view_02_paint.view_10_set_path_effect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintCornerPathEffectView
 *
 * Des: CornerPathEffect  圆角
 *
 * Time: 2020/5/29 3:44 PM
 */
public class PaintCornerPathEffectView extends View {

    private Paint mSrcPaint, mDstPaint;
    private float mStrokeWidth;
    private CornerPathEffect mCornerPathEffect;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mDeltaX, mDeltaY;
    private Path mPath;

    public PaintCornerPathEffectView(Context context) {
        this(context, null);
    }

    public PaintCornerPathEffectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintCornerPathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setStyle(Paint.Style.STROKE);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mSrcPaint.setStrokeWidth(mStrokeWidth);


        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setStyle(Paint.Style.STROKE);
        mDstPaint.setStrokeWidth(mStrokeWidth);

        mCornerPathEffect = new CornerPathEffect(getResources().getDimension(R.dimen.avatar_size));
        mDstPaint.setPathEffect(mCornerPathEffect);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mDeltaX = (mWidth / 2) / 3;
        mDeltaY = (mHeight / 2) / 3;

        mPath = new Path();
        //1. 矩形
//        mPath.moveTo((mCenterX - mDeltaX), (mCenterY - mDeltaY));
//        mPath.lineTo((mCenterX + mDeltaX), (mCenterY - mDeltaY));
//        mPath.lineTo((mCenterX + mDeltaX), (mCenterY + mDeltaY));
//        mPath.lineTo((mCenterX - mDeltaX), (mCenterY + mDeltaY));
//        mPath.lineTo((mCenterX - mDeltaX), (mCenterY - mDeltaY));

        //2. W
        mPath.moveTo((mCenterX - mDeltaX * 3/2), mCenterY);
        mPath.lineTo((mCenterX - mDeltaX * 3/4), (mCenterY - mDeltaY));
        mPath.lineTo(mCenterX, mCenterY);
        mPath.lineTo((mCenterX + mDeltaX * 3/4), (mCenterY + mDeltaY));
        mPath.lineTo((mCenterX + mDeltaX * 3/2), mCenterY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 通过 Canvas drawLine 绘制直线时不起作用
        canvas.drawLine((mCenterX - mDeltaX), (mCenterY - mDeltaY), (mCenterX + mDeltaX), (mCenterY - mDeltaY), mDstPaint);
        canvas.drawLine((mCenterX + mDeltaX), (mCenterY - mDeltaY), (mCenterX + mDeltaX), (mCenterY + mDeltaY), mDstPaint);

        //2. 通过 Canvas drawLine 绘制多条直线时不起作用
//        canvas.drawLines(new float[]{(mCenterX - mDeltaX), (mCenterY - mDeltaY), (mCenterX + mDeltaX), (mCenterY - mDeltaY),(mCenterX + mDeltaX), (mCenterY - mDeltaY), (mCenterX + mDeltaX), (mCenterY + mDeltaY)}, mDstPaint);

        //3. 通过 Canvas drawPath 绘制直线时起作用
        //原图
//        canvas.save();
//        canvas.translate(0, - mDeltaY);
//        canvas.drawPath(mPath, mSrcPaint);
//        canvas.restore();

        //加 CornerPathEffect 之后
//        canvas.save();
//        canvas.translate(0,  mDeltaY);
//        canvas.drawPath(mPath, mDstPaint);
//        canvas.restore();

        //4. 通过 Canvas drawCircle 绘制圆时不起作用
//        canvas.drawCircle(mCenterX, mCenterY, (mWidth < mHeight ? mWidth : mHeight) / 3, mDstPaint);
    }

}


































































































































