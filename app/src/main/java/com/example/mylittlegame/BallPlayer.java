package com.example.mylittlegame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.WindowManager;

public class BallPlayer implements GameObject{

    private Rect rectangle;
    private Point center;
    private int speedY;
    private int speedX;
    private int radius;
    private int color;
    private int touchCompt;

    public BallPlayer(Point point, int radius, int color){
        this.center = point;
        this.radius = radius;
        this.color = color;
        this.speedY = 0;
        this.speedX = 0;
        touchCompt = 0;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(center.x, center.y, radius, paint);
    }

    @Override
    public void update() {

    }

    public void update(int cas, Point point){
        switch (cas){
            case 1: // gauche
                speedX = -speedX;
                break;
            case 2: // haut
                speedY = -speedY;
                break;
            case 3: // droite
                speedX = -speedX;
                break;
            case 4: // bas
                speedY = -speedY;
                break;
        }

        if(isInCircle(point) && touchCompt == 0){
            speedX += -speedX + 15 * (center.x - point.x)/radius;
            speedY = -speedY + 5 * (center.y - point.y)/radius;
            touchCompt = 3;
        }

        speedY = 1 + speedY;
        center.y = speedY + center.y;
        System.out.println(speedX);
        center.x = speedX + center.x;

        if(touchCompt > 0)
            touchCompt --;

    }

    public Point getUpLeft(){
        return new Point(center.x - radius, center.y - radius);
    }

    public Point getDownRight(){
        return new Point(center.x + radius, center.y + radius);
    }

    public int getDirX(){
        if (speedX>0)
            return 1;
        else if(speedX==0)
            return 0;
        else
            return -1;
    }

    public int getDirY(){
        if (speedY>0)
            return 1;
        else if(speedY==0)
            return 0;
        else
            return -1;
    }

    public boolean isInCircle(Point point){
        if(Math.sqrt(Math.pow((center.x - point.x), 2)+Math.pow((center.y - point.y),2))<radius)
            return true;
        else
            return false;
    }
}
