package com.smart.myapplication2020customview.staticview.view_02_paint.view_04_compose_shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: PaintComposeShader2
 *
 * Des: Paint composeShader 解析
 *
 * Time: 2020/5/20 2:32 PM
 */
public class PaintComposeShader2 extends View {

    private Paint mFramePaint, mSrcPaint;
    private float mFrameStrokeWidth;
    private PathEffect mPathEffect;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Path mPath;
    private Bitmap mSrcBitmap, mDstBitmap;
    private Shader mSrcShader, mDstShader, mComposeShader;
    private float mBitmapWidth, mBitmapHeight;

    public PaintComposeShader2(Context context) {
        this(context, null);
    }

    public PaintComposeShader2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintComposeShader2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setColor(getResources().getColor(R.color.blue_400));

        mPath = new Path();

        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_composite_src);
        mSrcShader = new BitmapShader(mSrcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapWidth = mSrcBitmap.getWidth();
        mBitmapHeight = mSrcBitmap.getHeight();
        mPath.addRect(0, 0, mBitmapWidth, mBitmapHeight, Path.Direction.CW);

        mDstBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_composite_dst);
        mDstShader = new BitmapShader(mDstBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //Create a new compose shader, given shaders A, B, and a combining PorterDuff mode.
        //When the mode is applied, it will be given the result from shader A as its
        //"dst", and the result from shader B as its "src"

        //1. 第一类
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.SRC);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.SRC_OVER);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.SRC_IN);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.SRC_ATOP);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.DST);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.DST_OVER);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.DST_IN);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.DST_ATOP);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.CLEAR);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.SRC_OUT);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.DST_OUT);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.XOR);

        //2. 第二类
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.DARKEN);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.LIGHTEN);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.MULTIPLY);
//        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.SCREEN);
        mComposeShader = new ComposeShader(mDstShader, mSrcShader, PorterDuff.Mode.OVERLAY);
        mSrcPaint.setShader(mComposeShader);
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
        canvas.drawRect(0, 0, mBitmapWidth, mBitmapHeight, mSrcPaint);
        canvas.restore();

    }

}


































































































































