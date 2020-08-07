package com.smart.myapplication2020customview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.smart.myapplication2020customview.animateview.AnimateViewActivity;
import com.smart.myapplication2020customview.staticview.StaticViewActivity;
import com.smart.myapplication2020customview.touchableview.TouchableViewActivity;
import com.smart.myapplication2020customview.utils.ActivityUtils;

/**
 * FileName: MainActivity
 *
 * Des:
 *
 * Time: 2020/5/6 11:55 AM
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStaticView, mAnimateView, mTouchableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mStaticView = findViewById(R.id.static_view);
        mAnimateView = findViewById(R.id.animate_view);
        mTouchableView = findViewById(R.id.touchable_view);
        mStaticView.setOnClickListener(this);
        mAnimateView.setOnClickListener(this);
        mTouchableView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.static_view:
                ActivityUtils.startActivity(this, StaticViewActivity.class);
                break;
            case R.id.animate_view:
                ActivityUtils.startActivity(this, AnimateViewActivity.class);
                break;
            case R.id.touchable_view:
                ActivityUtils.startActivity(this, TouchableViewActivity.class);
                break;
        }
    }

}
