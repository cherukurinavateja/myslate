package com.example.myslate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    import Myvanvas code
    private MyCanvas MyCanvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyCanvas = (MyCanvas) findViewById(R.id.canvas);
    }

    public void clearCanvas(View v){
//        clear path on pressing clear button
        MyCanvas.clearCanvas();
    }

    public void save(View v){

//        opening new screen after capturing coordinates

        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);

    }


}
