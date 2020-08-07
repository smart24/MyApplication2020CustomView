package com.smart.myapplication2020customview.staticview.view_04_canvas_assist.view_05_canvas_geometric_transformation_camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasCameraView
 *
 * Des: Camera 相关方法
 *
 * ⚠️注意事项：
 * 1. Camera 的原点默认为 （0,0），X 轴右正左负，Y 轴上正下负，Z 轴内正外负
 *
 * 2. Camera 和 Canvas 一样也需要保存和恢复状态才能正常绘制，不然在界面刷新之后绘制就会出现问题
 *
 * 3. 如果你需要图形左右对称，需要配合上 Canvas.translate()，在三维旋转之前把绘制内容的中心点移动到原点，即旋
 * 转的轴心，然后在三维旋转后再把投影移动回来
 *
 * Time: 2020/6/7 4:19 PM
 */
public class CanvasCameraView extends android.view.View {

    private Paint mPaint;
    private float mStrokeWidth;
    private float mWidth, mHeight;
    private float mCenterX, mCenterY;
    private Bitmap mBitmap;
    private float mBitmapWidth, mBitmapHeight;
    private Camera mCamera;

    public CanvasCameraView(Context context) {
        this(context, null);
    }

    public CanvasCameraView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasCameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.white));
        mStrokeWidth = getResources().getDimension(R.dimen.padding_zero);
        mPaint.setStrokeWidth(mStrokeWidth);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();

        mCamera = new Camera();
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

        canvas.drawColor(getResources().getColor(R.color.grey_700));


        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                    Camera 旋转 X（向外是正）                             //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
        //1. 不对称的效果
//        canvas.save();
//        mCamera.save(); // 保存 Camera 的状态
//        mCamera.rotateX(15); // 旋转 Camera 的三维空间
//        mCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//        mCamera.restore(); // 恢复 Camera 的状态
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        canvas.restore();

        //2. 对称效果
//        canvas.save();
//        mCamera.save(); // 保存 Camera 的状态
//        mCamera.rotateX(15); // 旋转 Camera 的三维空间
//        mCamera.rotateX(30); // 旋转 Camera 的三维空间
//        mCamera.rotateX(60); // 旋转 Camera 的三维空间
//        mCamera.rotateX(90); // 旋转 Camera 的三维空间
//        mCamera.rotateX(120); // 旋转 Camera 的三维空间
//        mCamera.rotateX(150); // 旋转 Camera 的三维空间
//        mCamera.rotateX(180); // 旋转 Camera 的三维空间
//        canvas.translate(mCenterX, mCenterY); // 旋转之后把投影移动回来
//        mCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//        canvas.translate(-mCenterX, -mCenterY); // 旋转之前把绘制内容移动到轴心（原点）
//        mCamera.restore(); // 恢复 Camera 的状态
//        canvas.drawBitmap(mBitmap, (mCenterX - mBitmapWidth/2), (mCenterY - mBitmapHeight/2), mPaint);
//        canvas.restore();


        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                    Camera 旋转 Y（向里是正）                             //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
        //1. 不对称的效果
//        canvas.save();
//        mCamera.save(); // 保存 Camera 的状态
//        mCamera.rotateY(15); // 旋转 Camera 的三维空间
//        mCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//        mCamera.restore(); // 恢复 Camera 的状态
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        canvas.restore();

        //2. 对称效果
//        canvas.save();
//        mCamera.save(); // 保存 Camera 的状态
////        mCamera.rotateY(60); // 旋转 Camera 的三维空间
////        mCamera.rotateY(90); // 旋转 Camera 的三维空间
////        mCamera.rotateY(120); // 旋转 Camera 的三维空间
//        mCamera.rotateY(180); // 旋转 Camera 的三维空间
//        canvas.translate(mCenterX, mCenterY); // 旋转之后把投影移动回来
//        mCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//        canvas.translate(-mCenterX, -mCenterY); // 旋转之前把绘制内容移动到轴心（原点）
//        mCamera.restore(); // 恢复 Camera 的状态
//        canvas.drawBitmap(mBitmap, (mCenterX - mBitmapWidth/2), (mCenterY - mBitmapHeight/2), mPaint);
//        canvas.restore();


        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                  Camera 旋转 Z（逆时针是正）                             //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
        //1. 不对称的效果
//        canvas.save();
//        mCamera.save(); // 保存 Camera 的状态
//        mCamera.rotateZ(15); // 旋转 Camera 的三维空间
//        mCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//        mCamera.restore(); // 恢复 Camera 的状态
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        canvas.restore();

        //2. 对称效果
//        canvas.save();
//        mCamera.save(); // 保存 Camera 的状态
//        mCamera.rotateZ(15); // 旋转 Camera 的三维空间
//        mCamera.rotateZ(30); // 旋转 Camera 的三维空间
//        mCamera.rotateZ(60); // 旋转 Camera 的三维空间
//        mCamera.rotateZ(90); // 旋转 Camera 的三维空间
//        mCamera.rotateZ(120); // 旋转 Camera 的三维空间
//        mCamera.rotateZ(150); // 旋转 Camera 的三维空间
//        mCamera.rotateZ(180); // 旋转 Camera 的三维空间
//        canvas.translate(mCenterX, mCenterY); // 旋转之后把投影移动回来
//        mCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//        canvas.translate(-mCenterX, -mCenterY); // 旋转之前把绘制内容移动到轴心（原点）
//        mCamera.restore(); // 恢复 Camera 的状态
//        canvas.drawBitmap(mBitmap, (mCenterX - mBitmapWidth/2), (mCenterY - mBitmapHeight/2), mPaint);
//        canvas.restore();


        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                        Camera 移动 XYZ                                 //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////

//        canvas.save();
//        mCamera.save(); // 保存 Camera 的状态
//        mCamera.translate(mBitmapWidth/2, 0,0);// 移动 Camera 的三维空间，相当于 Canvas.Translate
//        mCamera.translate(0, -mBitmapHeight/2,0);// 移动 Camera 的三维空间，相当于 Canvas.Translate
//        mCamera.translate(0, 0,-mBitmapHeight/4);// 移动 Camera 的三维空间，相当于 Canvas.Scale
//        mCamera.translate(mBitmapWidth/2, -mBitmapHeight/2,-mBitmapHeight/4);// 移动 Camera 的三维空间
//        mCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//        mCamera.restore(); // 恢复 Camera 的状态
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        canvas.restore();


        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                              Camera setLocation 设置相机的位置                           //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
        canvas.save();
        mCamera.save(); // 保存 Camera 的状态
        mCamera.rotateX(45); // 旋转 Camera 的三维空间
//        mCamera.setLocation(0, 0, -mBitmapHeight);
//        mCamera.setLocation(0, 0, -mBitmapHeight/2);
//        mCamera.setLocation(0, 0, -mBitmapHeight/4);
//        mCamera.setLocation(0, 0, -mBitmapHeight/8);
//        mCamera.setLocation(0, 0, -mBitmapHeight/16);
        mCamera.setLocation(0, 0, -mBitmapHeight/32);
//        mCamera.setLocation(0, 0, mBitmapHeight/32);
//        mCamera.setLocation(0, 0, mBitmapHeight/16);
//        mCamera.setLocation(0, 0, mBitmapHeight/8);
//        mCamera.setLocation(0, 0, mBitmapHeight/4);
//        mCamera.setLocation(0, 0, mBitmapHeight/2);
        mCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        mCamera.restore(); // 恢复 Camera 的状态
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.restore();

    }

}
