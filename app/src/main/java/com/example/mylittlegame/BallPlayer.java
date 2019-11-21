package com.example.mylittlegame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class BallPlayer implements GameObject{

    private Point center;
    private int speedY;
    private int speedX;
    private int radius;
    private int color;

    public BallPlayer(){
        //TODO question 2 : compléter le constructeur
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);

        //TODO question 3 : dessiner un cercle avec la fonction canvas.drawCircle()
    }

    @Override
    public void update() {

    }

    public void update(int cas, Point point){
        //TODO question 4 : donner une physique simple à la balle

        //TODO question 5 : gérer les collisions

        //TODO question 6 : taper la balle
    }

    public Point getUpLeft(){
        //TODO question 5: récupérer le point supérieur gauche du rectangle qui encadre la balle
        return null;
    }

    public Point getDownRight(){
        //TODO question 5: récupérer le point inférieur droit du rectangle qui encadre la balle
        return null;
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
        //TODO dire si le point fait parti du cercle
        return true;
    }
}
