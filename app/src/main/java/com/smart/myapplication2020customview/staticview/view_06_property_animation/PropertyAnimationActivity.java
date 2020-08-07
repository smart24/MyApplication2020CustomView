package com.smart.myapplication2020customview.staticview.view_06_property_animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import com.smart.myapplication2020customview.R;
import com.smart.myapplication2020customview.utils.Constants;
import com.smart.myapplication2020customview.utils.ToastUtils;

/**
 * FileName: PropertyAnimationActivity
 *
 * Des: Property Animation
 * 
 * Version: V1.0
 * 
 * State: 
 * 
 * Optimize: 
 * 
 * Time: 2020/6/12 10:48 AM
 */
public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;
    private ViewPropertyAnimator mViewPropertyAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE|Window.FEATURE_ACTION_BAR);// 隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 隐藏状态栏
        setContentView(R.layout.activity_property_animation);
        initView();
    }

    private void initView(){
        mImageView = findViewById(R.id.target);
        mViewPropertyAnimator = mImageView.animate();
        mImageView.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
//        animateByViewPropertyAnimator();
        animateByObjectAnimator();
    }

    /**
     * Des: Animate view by ViewPropertyAnimator
     *
     * Time: 2020/6/12 3:58 PM
     */
    private void animateByViewPropertyAnimator(){
        //transitionX，transitionY，transitionZ
//        mViewPropertyAnimator.translationX(getResources().getDimension(R.dimen.padding_medium));
//        mViewPropertyAnimator.translationXBy(getResources().getDimension(R.dimen.padding_medium));

//        mViewPropertyAnimator.translationY(getResources().getDimension(R.dimen.padding_medium));
//        mViewPropertyAnimator.translationYBy(getResources().getDimension(R.dimen.padding_medium));

//        mViewPropertyAnimator.translationZ(getResources().getDimension(R.dimen.padding_medium));
//        mViewPropertyAnimator.translationZBy(getResources().getDimension(R.dimen.padding_medium));

        //x，y，z
//        mViewPropertyAnimator.x(getResources().getDimension(R.dimen.padding_medium));
//        mViewPropertyAnimator.xBy(getResources().getDimension(R.dimen.padding_medium));

//        mViewPropertyAnimator.y(getResources().getDimension(R.dimen.padding_medium));
//        mViewPropertyAnimator.yBy(getResources().getDimension(R.dimen.padding_medium));

//        mViewPropertyAnimator.z(getResources().getDimension(R.dimen.padding_medium));
//        mViewPropertyAnimator.zBy(getResources().getDimension(R.dimen.padding_medium));

        //rotation，rotationX，rotationY
//        mViewPropertyAnimator.rotation(getResources().getInteger(R.integer.number_one_hundred_eighty));
//        mViewPropertyAnimator.rotationBy(getResources().getInteger(R.integer.number_one_hundred_eighty));

//        mViewPropertyAnimator.rotationX(getResources().getInteger(R.integer.number_one_hundred_eighty));
//        mViewPropertyAnimator.rotationXBy(getResources().getInteger(R.integer.number_one_hundred_eighty));

//        mViewPropertyAnimator.rotationY(getResources().getInteger(R.integer.number_one_hundred_eighty));
//        mViewPropertyAnimator.rotationYBy(getResources().getInteger(R.integer.number_one_hundred_eighty));


        //scaleX，scaleY
//        mViewPropertyAnimator.scaleX(getResources().getInteger(R.integer.number_two));
//        mViewPropertyAnimator.scaleXBy(getResources().getInteger(R.integer.number_two));

//        mViewPropertyAnimator.scaleY(getResources().getInteger(R.integer.number_two));
//        mViewPropertyAnimator.scaleYBy(getResources().getInteger(R.integer.number_two));

        //alpha
//        mViewPropertyAnimator.alpha(getResources().getFraction(R.fraction.percentage_twenty, 1, 1));
//        mViewPropertyAnimator.alphaBy(getResources().getFraction(R.fraction.percentage_twenty, 1, 1));


        //
        mViewPropertyAnimator.translationXBy(getResources().getDimension(R.dimen.padding_thirty_six))
                             .translationYBy(getResources().getDimension(R.dimen.padding_thirty_six))
                             .setDuration(getResources().getInteger(R.integer.eight_hundred))
                             .setInterpolator(new OvershootInterpolator())
                             .setListener(new AnimatorListenerAdapter() {
                                 @Override
                                 public void onAnimationStart(Animator animation) {
                                     super.onAnimationStart(animation);
                                     Log.e(Constants.TAG, "onAnimationStart");
                                 }

                                 @Override
                                 public void onAnimationEnd(Animator animation) {
                                     super.onAnimationEnd(animation);
                                     Log.e(Constants.TAG, "onAnimationEnd");
                                 }
                             });
        ToastUtils.showToast(this, "onClick", Toast.LENGTH_SHORT);
    }

    /**
     * Des: Animate view by ObjectAnimator
     *
     * Time: 2020/6/13 7:52 AM
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateByObjectAnimator(){
        //创建 ObjectAnimator
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mImageView, "translationX", getResources().getDimension(R.dimen.padding_thirty_six));
//        objectAnimator.start();

        //创建 ObjectAnimator，添加 Duration
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mImageView, "translationX", getResources().getDimension(R.dimen.padding_thirty_six));
//        objectAnimator.setDuration(getResources().getInteger(R.integer.eight_hundred));
//        objectAnimator.start();

        //创建 ObjectAnimator，添加 Duration，设置 插值器
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mImageView, "translationX", getResources().getDimension(R.dimen.padding_ninety_six));
        objectAnimator.setDuration(getResources().getInteger(R.integer.eight_hundred));
        //先加速再减速
//        objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());

        //匀速
//        objectAnimator.setInterpolator(new LinearInterpolator());

        //持续加速
//        objectAnimator.setInterpolator(new AccelerateInterpolator());

        //持续减速直到 0
//        objectAnimator.setInterpolator(new DecelerateInterpolator());

        //先回拉一下再进行正常动画轨迹
//        objectAnimator.setInterpolator(new AnticipateInterpolator());

        //动画会超过目标值一些，然后再弹回来
//        objectAnimator.setInterpolator(new OvershootInterpolator());

        //开始前回拉，最后超过一些然后回弹
//        objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());

        //在目标值处弹跳
//        objectAnimator.setInterpolator(new BounceInterpolator());

        //像正弦曲线一样动
//        objectAnimator.setInterpolator(new CycleInterpolator(getResources().getFraction(R.fraction.percentage_twelve, 1, 1)));
//        objectAnimator.setInterpolator(new CycleInterpolator(getResources().getFraction(R.fraction.percentage_twenty_five, 1, 1)));
//        objectAnimator.setInterpolator(new CycleInterpolator(getResources().getFraction(R.fraction.percentage_fifty, 1, 1)));
//        objectAnimator.setInterpolator(new CycleInterpolator(getResources().getFraction(R.fraction.percentage_one_hundred, 1, 1)));

        //自定义动画完成度 / 时间完成度曲线
//        objectAnimator.setInterpolator(new PathInterpolator(getResources().getFraction(R.fraction.percentage_twelve, 1, 1), getResources().getFraction(R.fraction.percentage_one_hundred, 1, 1)));

        //加速运动
        //这个 Interpolator 的作用你不能看它的名字，一会儿 fast 一会儿 linear 的，完全看不懂。其实它和
        //AccelerateInterpolator 一样，都是一个持续加速的运动路线。只不过 FastOutLinearInInterpolator 的
        //曲线公式是用的贝塞尔曲线，而 AccelerateInterpolator 用的是指数曲线。具体来说，它俩最主要的区别是
        //FastOutLinearInInterpolator 的初始阶段加速度比 AccelerateInterpolator 要快一些。
//        objectAnimator.setInterpolator(new FastOutLinearInInterpolator());

        //先加速再减速
        //同样也是先加速再减速的还有前面说过的 AccelerateDecelerateInterpolator，不过它们的效果是明显不一样
        // 的。FastOutSlowInInterpolator 用的是贝塞尔曲线，AccelerateDecelerateInterpolator 用的是
        // 正弦 / 余弦曲线。具体来讲， FastOutSlowInInterpolator 的前期加速度要快得多。
//        objectAnimator.setInterpolator(new FastOutSlowInInterpolator());

        //持续减速
        //它和 DecelerateInterpolator 比起来，同为减速曲线，主要区别在于 LinearOutSlowInInterpolator 的初
        //始速度更高。对于人眼的实际感觉，区别其实也不大，不过还是能看出来一些的。
        objectAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        objectAnimator.start();
    }

}