package com.example.myslate;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class MyCanvas extends View {

    Paint paint;
    Path path;
//  storing Array of Xcordinates and Ycoordinates
    public static ArrayList<ArrayList<Integer> > Xcord = new ArrayList<ArrayList<Integer> >();
    public static ArrayList<ArrayList<Integer> > Ycord = new ArrayList<ArrayList<Integer> >();

//    Coordinates for every touch start and touch end
    public static  List<Integer> xpos = new ArrayList<Integer>();
    public static List<Integer> ypos = new ArrayList<Integer>();

    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

//        desinging paint style
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
//        paint.setPathEffect(new DashPathEffect(new float[] {50,50}, 1));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30f);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//      Drawing on screen with path
        canvas.drawPath(path,paint);

    }

    public void clearCanvas(){

//      clear co-ordinates and style on press clear button
        path.reset();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30f);
        Xcord.clear();
        Ycord.clear();

        invalidate();
    }

//    public void changeLineStyle(){
//
//        paint.setPathEffect(new DashPathEffect(new float[] {100,100}, 0));
//
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        float xPos = event.getX();
        float yPos = event.getY();

        switch (event.getAction()){

//            Action_down for capturing touch start

            case MotionEvent.ACTION_DOWN:
//                add co-ordinates to array list
                path.moveTo(xPos,yPos);
                xpos.add((int) xPos);
                ypos.add((int) yPos);
                return true;

//            Action_move for capturing moving points
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos,yPos);
                xpos.add((int) xPos);
                ypos.add((int) yPos);

                break;

//                Action_up is where touch ends

            case MotionEvent.ACTION_UP:

//              saving Xcordinates and Ycordinates on every new touch start
                ArrayList<Integer> Xdest= new ArrayList<Integer>();
                ArrayList<Integer> Ydest= new ArrayList<Integer>();
                Xdest.addAll(xpos);
                Ydest.addAll(ypos);
                Xcord.add((ArrayList<Integer>) Xdest);
                Ycord.add((ArrayList<Integer>) Ydest);
                xpos.clear();
                ypos.clear();
                System.out.println(Xcord);
                break;

            default:
                return false;

        }

        invalidate();
        return true;
    }
}
