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


public class PathView extends View {

    private Paint mPaint;
    private float mStrokeWidth;
    private int mWidth, mHeight;
    private float mCenterX, mCenterY;
    private float mLeft, mTop, mRight, mBottom;
    private Path mPath;
    private float mCornerRadiusX, mCornerRadiusY;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokeWidth = getResources().getDimension(R.dimen.padding_micro_xx);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(getResources().getColor(R.color.blue_400));

        mPath = new Path();

        mCornerRadiusX = getResources().getDimension(R.dimen.padding_micro);
        mCornerRadiusY = getResources().getDimension(R.dimen.padding_micro);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        //1. Arc
//        mPath.addArc(mLeft, mTop, mRight, mBottom, 0, 90);
//        mPath.addArc(mLeft, mTop, mRight, mBottom, 120, 120);
//        mPath.addArc(mLeft, mTop, mRight, mBottom, 270, 60);

        //2. Circle
//        mPath.addCircle(mCenterX, mCenterY, mWidth/4, Path.Direction.CW);

        //3. Oval
//        mPath.addOval(mLeft, mTop, mRight, mBottom, Path.Direction.CW);

        //4. Path
//        mPath.addPath();

        //5. Rect
//        mPath.addRect(mLeft, mTop, mRight, mBottom, Path.Direction.CW);

        //6. RoundRect
//        mPath.addRoundRect(mLeft, mTop, mRight, mBottom, mCornerRadiusX, mCornerRadiusY, Path.Direction.CW);

        //7. moveTo(float x, float y) / rMoveTo(float x, float y) 移动到目标位置
//        mPath.moveTo(mCenterX, mCenterY);
//        mPath.lineTo(mCenterX, mCenterY - mHeight/4);

        //8. lineTo
//        mPath.lineTo(mCenterX, mCenterY);

        //9. rLineTo
//        mPath.rLineTo(mWidth/4, 0);

        //10. quadTo(float x1, float y1, float x2, float y2) / rQuadTo(float dx1, float dy1, float dx2, float dy2) 画二次贝塞尔曲线
//        mPath.quadTo(0, mWidth/2, mWidth/2, mWidth/2);
//        mPath.quadTo(mWidth/2, 0, 0, 0);
//        mPath.lineTo(mWidth/2, mWidth/2);

        //11. rQuadTo
//        mPath.rQuadTo(0, mWidth/2, mWidth/2, mWidth/2);
//        mPath.rQuadTo(mWidth/2, 0, mWidth/2, mWidth/2);

        //12. cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) / rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3) 画三次贝塞尔曲线
//        mPath.cubicTo(0, mWidth/2, mWidth/2, 0, mWidth/2, mWidth/2);

        //13. rCubicTo
//        mPath.rCubicTo(0, mWidth/2, mWidth/2, 0, mWidth/2, mWidth/2);
//        mPath.rCubicTo(0, mWidth/2, mWidth/2, 0, mWidth/2, mWidth/2);

        //14. arcTo(RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo) /
        // arcTo(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo) /
        // arcTo(RectF oval, float startAngle, float sweepAngle) 画弧形
//        mPath.lineTo(mWidth/2, mWidth/2);
//        mPath.arcTo(mLeft, mTop, mRight, mBottom, -90, 90, true);

        //15. close
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.addCircle(mCenterX, mCenterY, mCornerRadiusX, Path.Direction.CW);
        mPath.moveTo(mCenterX, mCenterY - mWidth/4);
        mPath.lineTo(mCenterX + mWidth/4, mCenterY + mWidth/4);
        mPath.lineTo(mCenterX - mWidth/4, mCenterY + mWidth/4);
        mPath.close();

//        mPaint.setStyle(Paint.Style.FILL);
//        mPath.moveTo(mCenterX, mCenterY - mWidth/4);
//        mPath.lineTo(mCenterX + mWidth/4, mCenterY + mWidth/4);
//        mPath.lineTo(mCenterX - mWidth/4, mCenterY + mWidth/4);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 单 Path
        canvas.drawPath(mPath, mPaint);

        //2. 多 Path（旋转画布）
//        for (int i = 0; i < 360; i+=15) {
//            if(i == 0){
//                mPaint.setColor(getResources().getColor(R.color.red_400));
//            }else if(i == 15 || i == 345){
//                mPaint.setColor(getResources().getColor(R.color.green_400));
//            }else{
//                mPaint.setColor(getResources().getColor(R.color.blue_400));
//            }
//            canvas.save();
//            canvas.rotate(i, mCenterX, mCenterY);
//            canvas.drawPath(mPath, mPaint);
//            canvas.restore();
//        }
    }

}





































































