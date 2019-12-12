package com.example.mylittlegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private BallPlayer player;
    private Obstacle obstacle;
    private Point playerPoint;
    private Point limit;
    float captX,captY;

    public GamePanel(Context context, Point limit) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        playerPoint = new Point(150, 150);

        player = new BallPlayer(new Point(limit.x/2, limit.y/2), 100, Color.rgb(255,0,0));
        obstacle = new Obstacle(new Point(0,-20),200,Color.rgb(0,0,255));
        this.limit = limit;

        setFocusable(true);
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
            case MotionEvent.ACTION_UP:
                playerPoint.set(0, 0);
                break;
            case MotionEvent.ACTION_DOWN:
                playerPoint.set((int)event.getX(), (int)event.getY());
                break;
        }

        return true;
        //return super.onTouchEvent(event);
    }

    public void update(){
       obstacle.update();
        player.update(collision(player.getUpLeft(), player.getDownRight(), player.getDirY(), player.getDirX()), playerPoint,(int) captX, (int) captY );
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.WHITE);

        obstacle.draw(canvas);

        player.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        canvas.drawText("SCORE:" + player.getScore(), 10, 100, paint);
        canvas.drawText("BEST:" + player.getBestScore(), 10, 200, paint);
    }

    public int collision(Point upLeft, Point downRight, int dirY, int dirX){
        if (upLeft.y <= 0 && dirY == -1)
            return 2;
        else if (downRight.y >= limit.y && dirY == 1)
            return 4;
        else if(upLeft.x <= 0 && dirX == -1)
            return 1;
        else if(downRight.x >= limit.x && dirX == 1)
            return 3;
        else
            return 0;


        // 0: /  || 1: g || 2: h || 3: d || 4: b
    }

    public void capteur (float x , float y)
    {

        captX = x ;
        captY = y ;
    }



}
