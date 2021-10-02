package com.example.smart_refrigirator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;


public class addfoodactivity extends AppCompatActivity {

    private Button capture_image,detact_image;
    private ImageView imageView;
    private TextView textView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfoodactivity);



        capture_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textView.setText("");
            }

        });


    }








}