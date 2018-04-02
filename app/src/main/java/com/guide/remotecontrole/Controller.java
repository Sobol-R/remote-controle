package com.guide.remotecontrole;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Controller extends View {
    float x = -1;
    float y = -1;
    public Controller(Context context) {
        super(context);
    }

    public Controller(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Controller(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Controller(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(); // создаём кисть
        paint.setFlags(Paint.ANTI_ALIAS_FLAG); // включаем сглаживание
        paint.setColor(Color.RED); // устанавливаем цвет
        paint.setStyle(Paint.Style.FILL_AND_STROKE); // устанавливаем стил

        int cx = canvas.getWidth() / 2;
        int cy = canvas.getHeight() / 2;
        double maxRadius = Math.sqrt(Math.pow(x - cx, 2) + Math.pow(y - cy, 2));

        if (x == -1 && y == -1) {
            canvas.drawCircle(cx, cy, 50, paint);
        } else if (maxRadius > 200) {
            double a = maxRadius / 200;
            float xxx = (float) ((x - cx) / a);
            float yyy = (float) ((y - cy) / a);
            y /= a;
            canvas.drawCircle(xxx + cx , yyy + cy, 50, paint);
        } else {
            canvas.drawCircle(x, y, 50, paint);
        }

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx, cy, 200, paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // только нажали
            x = event.getX();
            y = event.getY();
            invalidate();
            return true; // возвращаем true, мол, обработали событие
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            x = event.getX();
            y = event.getY();
            invalidate();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            x = -1;
            y = -1;
            invalidate();
            return true;
        } else return super.onTouchEvent(event);
    }
}
