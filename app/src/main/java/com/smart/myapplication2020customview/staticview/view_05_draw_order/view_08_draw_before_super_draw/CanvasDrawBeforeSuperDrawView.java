package com.smart.myapplication2020customview.staticview.view_05_draw_order.view_08_draw_before_super_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.smart.myapplication2020customview.R;

/**
 * FileName: CanvasDrawAfterSuperDrawView
 *
 * Des: Canvas draw before super.draw()
 *
 *      由于 draw() 是总调度方法，所以如果把绘制代码写在 super.draw() 的上面，那么这段代码会在其他所有绘制之前
 *      被执行，所以这部分绘制内容会被其他所有的内容盖住，包括背景。是的，背景也会盖住它。
 *
 *      是不是觉得没用？觉得怎么可能会有谁想要在背景的下面绘制内容？别这么想，有的时候它还真的有用。
 *
 *      例如我有一个 EditText，我想给这个 EditText 加一个绿色的底，我不能使用给它设置绿色背景色的方式，因为这就
 *      相当于是把它的背景替换掉，从而会导致下面的那条横线消失，在这种时候，你就可以重写它的 draw() 方法，然后在
 *      super.draw() 的上方插入代码，以此来在所有内容的底部涂上一片绿色。
 *
 * Version:
 *
 * State:
 *
 * Optimize:
 *
 * Time: 2020/6/9 4:17 PM
 */
public class CanvasDrawBeforeSuperDrawView extends androidx.appcompat.widget.AppCompatEditText {

    public CanvasDrawBeforeSuperDrawView(Context context) {
        this(context, null);
    }

    public CanvasDrawBeforeSuperDrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawBeforeSuperDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(getResources().getColor(R.color.deep_orange_400));
        super.draw(canvas);
    }

}