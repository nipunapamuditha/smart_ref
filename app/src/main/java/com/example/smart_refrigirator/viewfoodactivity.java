package com.example.smart_refrigirator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Handler;


public class viewfoodactivity extends AppCompatActivity {

    Button tray1;
    Button tray2;
    Button tray3;
    Button tray4;
    int trayid;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfoodactivity);

        tray1 = findViewById(R.id.tray1);
        tray2 = findViewById(R.id.tray2);
        tray3 = findViewById(R.id.tray3);
        tray4 = findViewById(R.id.tray4);



        tray1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trayid =1;

            }
        });

        tray2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trayid =2;
            }
        });

        tray3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trayid =3;
            }
        });

        tray4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trayid =4;
            }
        });



    }
    public void clickbuttionviewdetails(View view)   // just sending user to the add window
    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

            }
        }, 500);
        Intent intent = new Intent(this,viewdetails.class);
        intent.putExtra("message",trayid);
        startActivity(intent);


    }
}