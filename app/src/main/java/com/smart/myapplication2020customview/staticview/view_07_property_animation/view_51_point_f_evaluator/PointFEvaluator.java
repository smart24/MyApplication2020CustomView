package com.smart.myapplication2020customview.staticview.view_07_property_animation.view_51_point_f_evaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * FileName: PointFEvaluator
 *
 * Des:
 *
 * Version:
 *
 * State:
 *
 * Optimize:
 *
 * Time: 2020/6/16 7:47 AM
 */
public class PointFEvaluator implements TypeEvaluator<PointF> {

    PointF newPoint = new PointF();

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        float x = startValue.x + (fraction * (endValue.x - startValue.x));
        float y = startValue.y + (fraction * (endValue.y - startValue.y));

        newPoint.set(x, y);

        return newPoint;
    }

}
