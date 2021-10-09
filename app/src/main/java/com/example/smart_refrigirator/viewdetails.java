package com.example.smart_refrigirator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class viewdetails extends AppCompatActivity {

    TextView namef;
    TextView expf;
    TextView weightf;
    String datez;
    String pattern = "MM/dd/yyyy HH:mm:ss";
    DateFormat df= new SimpleDateFormat(pattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdetails);

        namef=findViewById(R.id.fname);
        expf=findViewById(R.id.fexp);
        weightf=findViewById(R.id.fweight);

        Intent intent =getIntent();
        int intValue = intent.getIntExtra("message", 0);

        int i=0;
        String username="root";
        String password="#Karunarathna2044";
        String url="jdbc:mysql://findlaptop.cjhrwevkqni3.us-east-2.rds.amazonaws.com:3306/payroll";

        int t1_weighttreshhold;
        String t1_name = null;
        Date t1_exp=new Date();

        /*commands for get how many items are in the refrigirator which one is the closest to expire
        which one is the closest to running out

        get all the details and  to the calculation on the app
        */
        String sql_initial_details_t1 = "select*from payroll.tray_info where tray_number = '1'";
        String sql_initial_details_t2 = "select*from payroll.tray_info where tray_number = '2'";
        String sql_initial_details_t3 = "select*from payroll.tray_info where tray_number = '3'";
        String sql_initial_details_t4 = "select*from payroll.tray_info where tray_number = '4'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con9= DriverManager.getConnection(url, username, password);
            Statement st9= con9.createStatement();

if(intValue==1) {
    ResultSet tray1 = st9.executeQuery(sql_initial_details_t1);

    if (tray1.next()) {
        t1_name = tray1.getString(2);
        t1_exp = tray1.getDate(4);
        t1_weighttreshhold = tray1.getInt(3);

    }
}       if(intValue==2) {
                ResultSet tray2 = st9.executeQuery(sql_initial_details_t2);
                if (tray2.next()) {
                    t1_name = tray2.getString(2);
                    t1_exp = tray2.getDate(4);
                    t1_weighttreshhold = tray2.getInt(3);

                }
            }
            if(intValue==3) {
                ResultSet tray3 = st9.executeQuery(sql_initial_details_t3);
                if (tray3.next()) {
                    t1_name = tray3.getString(2);
                    t1_exp = tray3.getDate(4);
                    t1_weighttreshhold = tray3.getInt(3);

                }
            }
            if(intValue==4) {
                ResultSet tray4 = st9.executeQuery(sql_initial_details_t4);
                if (tray4.next()) {
                    t1_name = tray4.getString(2);
                    t1_exp = tray4.getDate(4);
                    t1_weighttreshhold = tray4.getInt(3);

                }
            }


        }catch(Exception e)
        {
            e.printStackTrace();
        }

        datez = df.format(t1_exp);
            namef.setText(t1_name);
            expf.setText(datez);
                // have to add value from firebase
    }
}