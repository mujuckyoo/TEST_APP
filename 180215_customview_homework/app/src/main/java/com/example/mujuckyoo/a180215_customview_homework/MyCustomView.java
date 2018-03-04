package com.example.mujuckyoo.a180215_customview_homework;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mujuckyoo on 2018. 3. 2..
 */

public class MyCustomView extends View {

    Context myContext;
    double degree = 0;
    int progress;

    public MyCustomView(Context context) {
        super(context);

    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Context context, AttributeSet attrs) {

        myContext = context;

        TypedArray typedArray = myContext.obtainStyledAttributes(attrs, R.styleable.MyCustomView);

/*        circle = new Rect(100,10,410,310);
        progress = new Rect(10,100,210,210);*/

    }

    @Override
    protected void onDraw(Canvas canvas) {


        Paint paint = new Paint();

        RectF rect = new RectF();
        rect.set(350, 150, 650, 450);


        출처: http://itpangpang.xyz/321 [ITPangPang]

        paint.setColor(Color.CYAN);
        canvas.drawCircle(500,300,150,paint);


        paint.setColor(Color.RED);
        canvas.drawArc(rect, -90, (float)degree, true,paint);


        paint.setColor(Color.WHITE);
        canvas.drawCircle(500,300,130,paint);
    }

    public void drawArc(int degree) {


    }


}
