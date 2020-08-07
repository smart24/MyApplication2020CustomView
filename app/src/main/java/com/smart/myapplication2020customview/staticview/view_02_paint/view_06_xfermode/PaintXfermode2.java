package com.smart.myapplication2020customview.staticview.view_02_paint.view_06_xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintXfermode2
 *
 * Des: Paint xfermode 解析
 *
 * Time: 2020/5/20 2:32 PM
 */
public class PaintXfermode2 extends View {

    private Paint mFramePaint, mGraphPaint;
    private float mFrameStrokeWidth;
    private PathEffect mPathEffect;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Path mPath;
    private Bitmap mSrcBitmap, mDstBitmap;
    private Shader mSrcShader, mDstShader, mComposeShader;
    private float mBitmapWidth, mBitmapHeight;
    private Xfermode mXfermode;

    public PaintXfermode2(Context context) {
        this(context, null);
    }

    public PaintXfermode2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintXfermode2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mFramePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFramePaint.setColor(getResources().getColor(R.color.grey_400));
        mFramePaint.setStyle(Paint.Style.STROKE);
        mFrameStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mFramePaint.setStrokeWidth(mFrameStrokeWidth);
        mPathEffect = new DashPathEffect(new float[]{getResources().getDimension(R.dimen.padding_micro), getResources().getDimension(R.dimen.padding_micro)}, getResources().getDimension(R.dimen.padding_micro));
        mFramePaint.setPathEffect(mPathEffect);

        mGraphPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGraphPaint.setColor(getResources().getColor(R.color.blue_400));

        mPath = new Path();

        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_composite_src);
        mSrcShader = new BitmapShader(mSrcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapWidth = mSrcBitmap.getWidth();
        mBitmapHeight = mSrcBitmap.getHeight();
        mPath.addRect(0, 0, mBitmapWidth, mBitmapHeight, Path.Direction.CW);

        mDstBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_composite_dst);
        mDstShader = new BitmapShader(mDstBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //1. Alpha 合成 (Alpha Compositing)
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);

        //2. 混合 (Blending)
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.OVERLAY);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.save();
        canvas.translate(mCenterX - mBitmapWidth/2, mCenterY - mBitmapHeight/2);
        canvas.drawPath(mPath, mFramePaint);

        // DST
        int count = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mDstBitmap, 0, 0, mGraphPaint);
        mGraphPaint.setXfermode(mXfermode);

        // SRC
        canvas.drawBitmap(mSrcBitmap, 0, 0, mGraphPaint);
        mGraphPaint.setXfermode(null); // 用完及时清除 Xfermode

        canvas.restoreToCount(count);

        canvas.restore();

    }

}


































































































































