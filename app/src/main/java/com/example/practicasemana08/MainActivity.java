package com.example.practicasemana08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private Button up,right,left,down,shoot;
    private TCPSingleton tcp;
    private String accionesboton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        up = findViewById(R.id.up);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        down = findViewById(R.id.down);
        shoot = findViewById(R.id.shoot);

        tcp = TCPSingleton.getInstance();

        shoot.setOnClickListener(
                v -> {

                    tcp.accion("SHOOT");

                }

        );
        up.setOnTouchListener(this);
        left.setOnTouchListener(this);
        right.setOnTouchListener(this);
        down.setOnTouchListener(this);



    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.up:
                accionesboton="UP";
                Log.e("-->", "entro");
                break;


            case R.id.down:
                accionesboton="DOWN";
                break;



            case R.id.left:
                accionesboton="LEFT";
                break;



            case R.id.right:
                accionesboton="RIGHT";
                break;


        }

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                accionesboton=accionesboton+"START";
                tcp.accion(accionesboton);
                break;

            case MotionEvent.ACTION_UP:
                accionesboton=accionesboton+"STOP";
                tcp.accion(accionesboton);

                break;

        }
        return true;
    }
}