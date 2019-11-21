package com.example.mylittlegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private BallPlayer player;
    private Point playerPoint;
    private Point limit;

    public GamePanel(Context context, Point limit) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);

        //TODO question 1: récupérer la taille de l'écran et l'enregister dans la variable limit

        //TODO question 2: créer un joueur
    }

    public GamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GamePanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GamePanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (true){
            try{
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) {e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP: // quand on lache l'écran
                //TODO question 6: taper la balle
                break;
            case MotionEvent.ACTION_DOWN: // quand on appuie sur l'écran
                //TODO question 6: taper la balle
                break;
        }
        return true;
    }

    public void update(){
        //TODO question 4: update la balle
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);

        //TODO question 3: dessiner la balle
    }

    public int collision(){
        //TODO question 5: gérer les collisions avec les murs. return 0, 1, 2, 3 ou 4 selon le mur
        return 0;
    }
}
