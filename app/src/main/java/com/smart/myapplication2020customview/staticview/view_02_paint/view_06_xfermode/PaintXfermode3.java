package com.smart.myapplication2020customview.staticview.view_02_paint.view_06_xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintXfermode3
 *
 * Des: Paint xfermode 解析
 *
 * Time: 2020/5/20 2:32 PM
 */
public class PaintXfermode3 extends View {

    private int mBackgroundColor;
    private Paint mFramePaint, mGraphPaint;
    private float mFrameStrokeWidth;
    private PathEffect mPathEffect;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Path mPath;
    private Bitmap mBackgroundBitmap, mSrcBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private Xfermode mXfermode;
    private Canvas mCanvas;

    public PaintXfermode3(Context context) {
        this(context, null);
    }

    public PaintXfermode3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintXfermode3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

//        mBackgroundColor = getResources().getColor(R.color.grey_700);
        mBackgroundColor = getResources().getColor(R.color.white);

        mFramePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFramePaint.setColor(getResources().getColor(R.color.grey_400));
        mFramePaint.setStyle(Paint.Style.STROKE);
        mFrameStrokeWidth = getResources().getDimension(R.dimen.padding_micro_x);
        mFramePaint.setStrokeWidth(mFrameStrokeWidth);
        mPathEffect = new DashPathEffect(new float[]{getResources().getDimension(R.dimen.padding_micro), getResources().getDimension(R.dimen.padding_micro)}, getResources().getDimension(R.dimen.padding_micro));
        mFramePaint.setPathEffect(mPathEffect);
        mFramePaint.setShadowLayer(getResources().getDimension(R.dimen.padding_small), getResources().getDimension(R.dimen.padding_zero), getResources().getDimension(R.dimen.padding_zero), getResources().getColor(R.color.grey_700));

        mGraphPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGraphPaint.setColor(getResources().getColor(R.color.blue_400));
        mGraphPaint.setShadowLayer(getResources().getDimension(R.dimen.padding_zero), getResources().getDimension(R.dimen.padding_zero), getResources().getDimension(R.dimen.padding_zero), getResources().getColor(R.color.white));

        mPath = new Path();

        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);
//        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar_three);
        mBitmapWidth = mSrcBitmap.getWidth();
        mBitmapHeight = mSrcBitmap.getHeight();

        mPath.addRect(0, 0, mBitmapWidth, mBitmapHeight, Path.Direction.CW);



        //1. Alpha 合成 (Alpha Compositing)
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
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
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.OVERLAY);
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
        canvas.drawColor(mBackgroundColor);

        //1.
        canvas.save();
        canvas.translate(mCenterX - mBitmapWidth/2, mCenterY - mBitmapHeight/2);
        mFramePaint.setPathEffect(mPathEffect);
        mFramePaint.setColor(getResources().getColor(R.color.white));
//        canvas.drawPath(mPath, mFramePaint);

        // DST
        int count = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(mBitmapWidth/2, mBitmapHeight/2, (mBitmapWidth/2 < mBitmapHeight/2 ? mBitmapWidth/2 : mBitmapHeight/2), mGraphPaint);
        mGraphPaint.setXfermode(mXfermode);

        // SRC
        canvas.drawBitmap(mSrcBitmap, 0, 0, mGraphPaint);
        mGraphPaint.setXfermode(null); // 用完及时清除 Xfermode

        mFramePaint.setPathEffect(null);
        mFramePaint.setColor(getResources().getColor(R.color.white));
        canvas.drawCircle(mBitmapWidth/2, mBitmapHeight/2, (mBitmapWidth/2 < mBitmapHeight/2 ? mBitmapWidth/2 : mBitmapHeight/2), mFramePaint);

        canvas.restoreToCount(count);

        canvas.restore();

        //2.
//        mBackgroundBitmap = Bitmap.createBitmap((int)mWidth, (int)mHeight, Bitmap.Config.ARGB_8888);
//        mCanvas = new Canvas(mBackgroundBitmap);//用指定的位图构造一个画布来绘制。
//        mCanvas.drawCircle(mBitmapWidth/2, mBitmapHeight/2, (mBitmapWidth/2 < mBitmapHeight/2 ? mBitmapWidth/2 : mBitmapHeight/2), mGraphPaint);
//        mGraphPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        mCanvas.drawBitmap(mSrcBitmap, 0, 0, mGraphPaint);
//        canvas.drawBitmap(mBackgroundBitmap, 0, 0, null);

        //注意事项：PorterDuffXfermode 只对 Bitmap 起作用

        //PorterDuffXfermode SRC 为 Bitmap 时，或 DST 和 SRC 时，会正常显示

    }

}


































































































































