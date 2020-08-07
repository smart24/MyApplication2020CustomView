package com.smart.myapplication2020customview.staticview.view_02_paint.view_12_set_mask_filter;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.smart.myapplication2020customview.R;

public class EmbossMaskFilterActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private PaintEmbossMaskFilterView2 mPaintEmbossMaskFilterView;
    private SeekBar mX, mY, mZ, mAmbient, mSpecular, mBlurRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emboss_mask_filter);
        initView();
    }

    private void initView(){
        mPaintEmbossMaskFilterView = findViewById(R.id.paint_emboss_mask_filter_view);
        mX = findViewById(R.id.param_x);
        mY = findViewById(R.id.param_y);
        mZ = findViewById(R.id.param_z);
        mAmbient = findViewById(R.id.param_ambient);
        mSpecular = findViewById(R.id.param_specular);
        mBlurRadius = findViewById(R.id.param_blur_radius);
        mX.setOnSeekBarChangeListener(this);
        mY.setOnSeekBarChangeListener(this);
        mZ.setOnSeekBarChangeListener(this);
        mAmbient.setOnSeekBarChangeListener(this);
        mSpecular.setOnSeekBarChangeListener(this);
        mBlurRadius.setOnSeekBarChangeListener(this);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();
        switch (id){
            case R.id.param_x:
                mPaintEmbossMaskFilterView.setX(progress);
                break;
            case R.id.param_y:
                mPaintEmbossMaskFilterView.setY(progress);
                break;
            case R.id.param_z:
                mPaintEmbossMaskFilterView.setZ(progress);
                break;
            case R.id.param_ambient:
                mPaintEmbossMaskFilterView.setAmbient(progress/100);
                break;
            case R.id.param_specular:
                mPaintEmbossMaskFilterView.setSpecular(progress);
                break;
            case R.id.param_blur_radius:
                mPaintEmbossMaskFilterView.setBlurRadius(progress == 0 ? 1 : progress);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}