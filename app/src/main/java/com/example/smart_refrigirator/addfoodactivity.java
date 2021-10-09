package com.example.smart_refrigirator;


import androidx.appcompat.app.AppCompatActivity;


import java.sql.*;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import com.mysql.cj.jdbc.JdbcConnection;
import android.widget.RadioButton;



import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import java.util.Date;



public class addfoodactivity extends AppCompatActivity {
    int trayno;
EditText food_name;
EditText food_exp;
RadioButton tr1,tr2,tr3,tr4;
Button add;
Date exp;
    String f_name="fdsa";
    CalendarView simpleCalendarView;
    int i=0;
    String username="root";
    String password="#Karunarathna2044";
    String url="jdbc:mysql://findlaptop.cjhrwevkqni3.us-east-2.rds.amazonaws.com:3306/payroll";


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


         simpleCalendarView = (CalendarView) findViewById(R.id.exp_calander);






    }

    public void add_to_database(View view)
    {


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

        f_name = food_name.getText().toString();

        long selectedDate = simpleCalendarView.getDate();
        exp = new Date(selectedDate);

        String add_food_to_table = "UPDATE payroll.tray_info SET food_name='assss' WHERE tray_number=1";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con9= DriverManager.getConnection(url, username, password);
            Statement st9= con9.createStatement();


            st9.executeUpdate(add_food_to_table);





        }catch(Exception e)
        {
            e.printStackTrace();
        }


    }







}