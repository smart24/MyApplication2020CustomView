package com.smart.myapplication2020customview.framework;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(setContentRes(), container, false);
        initView(view);
        initView(view,savedInstanceState);
        initData();
        return view;
    }

    protected abstract int setContentRes();

    protected void initView(View view) {}

    protected void initView(View view, Bundle savedInstanceState) {}

    protected void initData(){}
}
