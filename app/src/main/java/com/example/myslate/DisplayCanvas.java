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
import static com.example.myslate.MyCanvas.Xcord;
import static com.example.myslate.MyCanvas.Ycord;


public class DisplayCanvas extends View {
    Paint paint;
    Path path;

    int totalTouchStarts = Xcord.size();

    public DisplayCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setPathEffect(new DashPathEffect(new float[] {50,50}, 1));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30f);

//        Drawing path from the given X,Y coordinates

        for(int etouch=0; etouch<totalTouchStarts; etouch++){

            int length = Xcord.get(etouch).size();

            for(int cord=0; cord<length;cord++){

                if(cord == 0){

                    path.moveTo(Xcord.get(etouch).get(cord),Ycord.get(etouch).get(cord));

                }else{
                    path.lineTo(Xcord.get(etouch).get(cord),Ycord.get(etouch).get(cord));
                }
            }
        }

    }

    public void clearCanvas(){
        path.reset();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30f);

        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float xPos = event.getX();
        float yPos = event.getY();
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                path.moveTo(xPos,yPos);
                return true;
            case MotionEvent.ACTION_MOVE:

                path.lineTo(xPos,yPos);
                break;
            case MotionEvent.ACTION_UP:
                break;

            default:
                return false;

        }


        invalidate();
        return true;

    }
}
