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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;
import java.util.List;


public class addfoodactivity extends AppCompatActivity {

EditText food_name;
EditText food_exp;
RadioButton tr1,tr2,tr3,tr4;
Button add;
Date exp;
    CalendarView simpleCalendarView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfoodactivity);

        food_name= findViewById(R.id.EnterName);

        tr1= findViewById(R.id.radioButton);
        tr2= findViewById(R.id.radioButton2);
        tr3= findViewById(R.id.radioButton3);
        tr4= findViewById(R.id.radioButton4);
        add= findViewById(R.id.button_add);

        // getting from calendar .
         simpleCalendarView = (CalendarView) findViewById(R.id.exp_calander);






    }

    public void add_to_database()
    {
        int trayno=0;

        if(tr1.isChecked())
        {
            trayno=1;
        }
        if(tr2.isChecked())
        {
            trayno=2;
        }
        if(tr3.isChecked())
        {
            trayno=3;
        }
        if(tr4.isChecked())
        {
            trayno=4;
        }

        String f_name = food_name.getText().toString();

        long selectedDate = simpleCalendarView.getDate();
        exp = new Date(selectedDate);
    }






}