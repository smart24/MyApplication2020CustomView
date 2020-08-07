package com.smart.myapplication2020customview.staticview.view_08_layout_baisc;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.smart.myapplication2020customview.R;

/**
 * FileName: LayoutBasicActivity
 *
 * Des: 
 * 
 * Version: 
 * 
 * State: 
 * 
 * Optimize: 
 * 
 * Time: 2020/6/22 11:21 AM
 */
public class LayoutBasicActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private FrameLayout mParentFrame;
    private AppCompatImageView mTarget;
    private SeekBar mWidthSeekBar, mHeightSeekBar;
    private int mWidth, mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE|Window.FEATURE_ACTION_BAR);// 隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 隐藏状态栏
        setContentView(R.layout.activity_layout_basic);
        init();
    }


//    第一版：通过父 View 修改 View 的尺寸
//    private void init(){
//        mParentFrame = findViewById(R.id.parent_frame);
//        mTarget = findViewById(R.id.target);
//        mWidthSeekBar = findViewById(R.id.width_seek_bar);
//        mHeightSeekBar = findViewById(R.id.height_seek_bar);
//        mWidthSeekBar.setOnSeekBarChangeListener(this);
//        mHeightSeekBar.setOnSeekBarChangeListener(this);
//
//        mWidth = mParentFrame.getWidth();
//        mHeight = mParentFrame.getHeight();
//
//        Log.e(Constants.TAG, "onCreate  mParentFrame.getWidth()  " + mWidth);
//    }
//
//    第一版：通过父 View 修改 View 的尺寸
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        mWidth = mParentFrame.getWidth();
//        mHeight = mParentFrame.getHeight();
//
//        Log.e(Constants.TAG, "onWindowFocusChanged  mParentFrame.getWidth()  " + mWidth);
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        int id = seekBar.getId();
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mParentFrame.getLayoutParams();
//        switch (id){
//            case R.id.width_seek_bar:
//                layoutParams.width = mWidth + mWidth * seekBar.getProgress() / 100;
//                break;
//            case R.id.height_seek_bar:
//                layoutParams.height = mHeight + mHeight * seekBar.getProgress() / 100;
//                break;
//        }
//        mParentFrame.setLayoutParams(layoutParams);
//    }


    //第二版：直接修改 View 的尺寸
    private void init(){
        mTarget = findViewById(R.id.target);
        mWidthSeekBar = findViewById(R.id.width_seek_bar);
        mHeightSeekBar = findViewById(R.id.height_seek_bar);
        mWidthSeekBar.setOnSeekBarChangeListener(this);
        mHeightSeekBar.setOnSeekBarChangeListener(this);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mWidth = mTarget.getWidth();
        mHeight = mTarget.getHeight();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mTarget.getLayoutParams();
        switch (id){
            case R.id.width_seek_bar:
                layoutParams.width = mWidth + mWidth * seekBar.getProgress() / 100;
                break;
            case R.id.height_seek_bar:
                layoutParams.height = mHeight + mHeight * seekBar.getProgress() / 100;
                break;
        }
        mTarget.setLayoutParams(layoutParams);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}