package com.smart.myapplication2020customview.framework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.smart.myapplication2020customview.utils.StatusBarUtils;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBeforeSetContentView();
        setContentView(setContentRes());
        initView(savedInstanceState);
        initView();
        initData();
    }

    protected void initDataBeforeSetContentView(){};

    protected abstract int setContentRes();

    protected void initView(Bundle savedInstanceState){
        StatusBarUtils.transparentStatusBar(this);
    }

    protected void initView(){
        StatusBarUtils.transparentStatusBar(this);
    }

    protected void initData(){}

}
