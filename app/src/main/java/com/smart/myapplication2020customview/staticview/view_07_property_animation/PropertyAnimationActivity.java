package com.smart.myapplication2020customview.staticview.view_07_property_animation;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.staticview.view_07_property_animation.view_01_color_view.ColorView;
import com.smart.myapplication2020customview.staticview.view_07_property_animation.view_02_point_f_view.PointFView;
import com.smart.myapplication2020customview.staticview.view_07_property_animation.view_50_hsl_evaluator.HslEvaluator;
import com.smart.myapplication2020customview.utils.DisplayUtils;

/**
 * FileName: PropertyAnimationActivity
 *
 * Des:
 *
 * Version:
 *
 * State:
 *
 * Optimize:
 *
 * Time: 2020/6/16 6:56 AM
 */
public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ColorView mColorView;
    private PointFView mPointFView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE | Window.FEATURE_ACTION_BAR);// 隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 隐藏状态栏
        setContentView(R.layout.activity_property_animation2);
        initView();
    }

    private void initView() {
        mColorView = findViewById(R.id.color_view);
        mColorView.setOnClickListener(this);

        mPointFView = findViewById(R.id.point_f_view);
        mPointFView.setOnClickListener(this);

        mImageView = findViewById(R.id.image_view);
        mImageView.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
//        changeColor();
//        changePointF();
//        animateViewByPropertyValuesHolder();
//        animateViewByAnimateSet();
//        animateViewByPropertyValuesHolder2();
        animateViewByValueAnimator();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void changeColor() {
        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                          直接改变颜色                                    //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
//        //1. 为自定义属性添加 setter/getter 方法
//
//        //2. 用 ObjectAnimator.ofXXX() 创建 ObjectAnimator 对象
//        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mColorView, "color", getResources().getColor(R.color.blue_400), getResources().getColor(R.color.orange_400));
//        //3. 用 start() 方法执行动画
//        objectAnimator.start();


        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                         ofArgb 改变颜色                                 //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
//        //1. 为自定义属性添加 setter/getter 方法
//
//        //2. 用 ObjectAnimator.ofXXX() 创建 ObjectAnimator 对象
//        ObjectAnimator objectAnimator = ObjectAnimator.ofArgb(mColorView, "color", getResources().getColor(R.color.blue_400), getResources().getColor(R.color.orange_400));
//        //3. 用 start() 方法执行动画
//        objectAnimator.start();


        ////////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                        //
        //                                         ofArgb 改变颜色                                 //
        //                                                                                        //
        ////////////////////////////////////////////////////////////////////////////////////////////
        //1. 为自定义属性添加 setter/getter 方法

        //2. 用 ObjectAnimator.ofXXX() 创建 ObjectAnimator 对象
//        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mColorView, "color", getResources().getColor(R.color.translucent_blue_700), getResources().getColor(R.color.translucent_red_700));
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mColorView, "color", 0xff42A5F5, 0xffff5722);

        //3. 应用自定义 Evaluator
        objectAnimator.setEvaluator(new HslEvaluator());

        //4. 用 start() 方法执行动画
        objectAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changePointF() {
        //1. 为自定义属性添加 setter/getter 方法

        //2. 用 ObjectAnimator.ofXXX() 创建 ObjectAnimator 对象，应用自定义 Evaluator
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(mPointFView, "position", new PointFEvaluator(), new PointF(DisplayUtils.getScreenWidth() / 2, DisplayUtils.getScreenHeight()));

        //3. 用 start() 方法执行动画
        objectAnimator.start();
    }

    private void animateViewByPropertyValuesHolder() {
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
        PropertyValuesHolder propertyValuesHolder2 = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
        PropertyValuesHolder propertyValuesHolder3 = PropertyValuesHolder.ofFloat("alpha", 0, 1);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mColorView, propertyValuesHolder1, propertyValuesHolder2, propertyValuesHolder3);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mPointFView, propertyValuesHolder1, propertyValuesHolder2, propertyValuesHolder3);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mImageView, propertyValuesHolder1, propertyValuesHolder2, propertyValuesHolder3);
        objectAnimator.start();
    }

    private void animateViewByAnimateSet() {
        ObjectAnimator translateAnimatorX = ObjectAnimator.ofFloat(mColorView, "translationX", 0, DisplayUtils.getScreenWidth() / 2);
        ObjectAnimator translateAnimatorY = ObjectAnimator.ofFloat(mColorView, "translationY", 0, DisplayUtils.getScreenHeight() / 2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(translateAnimatorX, translateAnimatorY);
//        animatorSet.playTogether(translateAnimatorX, translateAnimatorY);
        animatorSet.setDuration(getResources().getInteger(R.integer.eight_hundred));
        animatorSet.start();
    }

    private void animateViewByPropertyValuesHolder2() {
        // 在 0% 处开始
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        // 时间经过 50% 的时候，动画完成度 100%
        Keyframe keyframe2 = Keyframe.ofFloat(0.5f, DisplayUtils.getScreenHeight() / 2);
        // 时间见过 100% 的时候，动画完成度倒退到 80%，即反弹 20%
        Keyframe keyframe3 = Keyframe.ofFloat(1, DisplayUtils.getScreenHeight() / 4);
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("translationY", keyframe1, keyframe2, keyframe3);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mColorView, propertyValuesHolder);
        objectAnimator.setDuration(getResources().getInteger(R.integer.eight_hundred));
        objectAnimator.start();
    }

    private void animateViewByValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, DisplayUtils.getScreenHeight() / 2);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mColorView.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(getResources().getInteger(R.integer.eight_hundred));
        valueAnimator.start();
    }

}