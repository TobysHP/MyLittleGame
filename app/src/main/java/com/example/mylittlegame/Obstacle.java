package com.example.mylittlegame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Obstacle implements GameObject {

    private Point center;
    private int speedY;
    private int speedX;
    private int cote;
    private int color;

   public  Obstacle (Point center, int cote, int color  )
   {
       this.center = center;
       this.cote = cote;
       this.color = color;

       speedY = 6 ;

   }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(new Rect(center.x-cote/2,center.y-cote/2,center.x+cote/2,center.y+cote/2 ),paint);
    }

    @Override
    public void update() {

        center.y = speedY + center.y;
        center.x = speedX + center.x;

    }




}
