package com.baidu.glide.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/28.
 */

public class MyImageView extends ImageView {

    public Point point;
    private Paint paint;
    public boolean isTrue=false;

    public MyImageView(Context context) {
        super(context);
    }
    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        point = new Point();
        
    }
    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        
    }
    public void setOnClickListener(boolean is) {
          isTrue=is;
        System.out.println("\"自定义\" = " + isTrue);
        invalidate();

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isTrue){
        paint = new Paint();
        paint.setColor(Color.RED);
            paint.setStrokeWidth(10f);
            paint.setStyle(Paint.Style.STROKE);//设置填满
            canvas.drawRect(10, 10, 180, 180,paint);// 正方形
        }
    }
}
