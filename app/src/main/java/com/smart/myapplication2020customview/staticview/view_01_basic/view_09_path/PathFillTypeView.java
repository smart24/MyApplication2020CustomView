package com.smart.myapplication2020customview.staticview.view_01_basic.view_09_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.smart.myapplication2020customview.R;


public class PathFillTypeView extends View {

    private Paint mSrcPaint, mDstPaint;
    private int mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mLeft, mTop, mRight, mBottom;
    private Path mSrcPath, mDstPath;
    private float mRadius;

    public PathFillTypeView(Context context) {
        this(context, null);
    }

    public PathFillTypeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathFillTypeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setStyle(Paint.Style.FILL);
        mSrcPaint.setStrokeCap(Paint.Cap.ROUND);
        mSrcPaint.setStrokeWidth(getResources().getDimension(R.dimen.padding_micro_x));
        mSrcPaint.setColor(getResources().getColor(R.color.blue_400));

        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setStyle(Paint.Style.FILL);
        mDstPaint.setStrokeCap(Paint.Cap.ROUND);
        mDstPaint.setStrokeWidth(getResources().getDimension(R.dimen.padding_micro_x));
        mDstPaint.setColor(getResources().getColor(R.color.blue_400));

        mSrcPath = new Path();
        mDstPath = new Path();

        mRadius = getResources().getDimension(R.dimen.padding_seventy_two);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
        //1. 椭圆
//        mLeft = mCenterX - mWidth/4;
//        mTop = mCenterY - mHeight/4;
//        mRight = mCenterX + mWidth/4;
//        mBottom = mCenterY + mHeight/4;

        //2. 圆
        mLeft = mCenterX - mWidth/3;
        mTop = mCenterY - mWidth/3;
        mRight = mCenterX + mWidth/3;
        mBottom = mCenterY + mWidth/3;

        mRadius = mWidth/5;
        mSrcPaint.setStyle(Paint.Style.STROKE);
        mSrcPath.addCircle(mCenterX - mRadius/2, mCenterY  - mRadius - mRadius/4, mRadius, Path.Direction.CW);
        mSrcPath.addCircle(mCenterX + mRadius/2, mCenterY - mRadius - mRadius/4, mRadius, Path.Direction.CW);

        //1. WINDING：non-zero winding rule （非零环绕数原则）
        //首先，它需要你图形中的所有线条都是有绘制方向的。然后，同样是从平面中的点向任意方向射出一条射线，
        //但计算规则不一样：以 0 为初始值，对于射线和图形的所有交点，遇到每个顺时针的交点（图形从射线的左边向右穿过）
        //把结果加 1，遇到每个逆时针的交点（图形从射线的右边向左穿过）把结果减 1，最终把所有的交点都算上，
        //得到的结果如果不是 0，则认为这个点在图形内部，是要被涂色的区域；如果是 0，则认为这个点在图形外部，
        //是不被涂色的区域。
        //
        //射线的方向无所谓，同一个点射向任何方向的射线，结果都是一样的，不信你可以试试。
        //1.1 两圆同向
//        mDstPaint.setStyle(Paint.Style.FILL);
//        mDstPath.setFillType(Path.FillType.WINDING);
//        mDstPath.addCircle(mCenterX - mRadius/2, mCenterY + mRadius + mRadius/4, mRadius, Path.Direction.CW);
//        mDstPath.addCircle(mCenterX + mRadius/2, mCenterY + mRadius + mRadius/4, mRadius, Path.Direction.CW);

        //1.2 两圆不同向
//        mDstPaint.setStyle(Paint.Style.FILL);
//        mDstPath.setFillType(Path.FillType.WINDING);
//        mDstPath.addCircle(mCenterX - mRadius/2, mCenterY + mRadius + mRadius/4, mRadius, Path.Direction.CCW);
//        mDstPath.addCircle(mCenterX + mRadius/2, mCenterY + mRadius + mRadius/4, mRadius, Path.Direction.CW);

        //2. EVEN_ODD：even-odd rule （奇偶原则）
        //对于平面中的任意一点，向任意方向射出一条射线，这条射线和图形相交的次数（相交才算，相切不算哦）
        //如果是奇数，则这个点被认为在图形内部，是要被涂色的区域；如果是偶数，则这个点被认为在图形外部，
        //是不被涂色的区域。
        //
        //射线的方向无所谓，同一个点射向任何方向的射线，结果都是一样的，不信你可以试试。
//        mDstPaint.setStyle(Paint.Style.FILL);
//        mDstPath.setFillType(Path.FillType.EVEN_ODD);
//        mDstPath.addCircle(mCenterX - mRadius/2, mCenterY + mRadius + mRadius/4, mRadius, Path.Direction.CW);
//        mDstPath.addCircle(mCenterX + mRadius/2, mCenterY + mRadius + mRadius/4, mRadius, Path.Direction.CW);

        //对于 EVEN_ODD 来说，图形的绘制方向对最终的结果没有任何影响
        mDstPaint.setStyle(Paint.Style.FILL);
        mDstPath.setFillType(Path.FillType.EVEN_ODD);
        mDstPath.addCircle(mCenterX - mRadius/2, mCenterY + mRadius + mRadius/4, mRadius, Path.Direction.CCW);
        mDstPath.addCircle(mCenterX + mRadius/2, mCenterY + mRadius + mRadius/4, mRadius, Path.Direction.CW);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mSrcPath, mSrcPaint);
        canvas.drawPath(mDstPath, mDstPaint);
    }

}






































































