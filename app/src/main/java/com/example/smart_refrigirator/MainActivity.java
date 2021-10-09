package com.example.smart_refrigirator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


// dynmodb




import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import java.time.LocalDate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;




@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    static int maximum(int a, int b, int c, int d) {

        int max = a;

        if (b > max)
            max = b;
        if (c > max)
            max = c;
        if (d > max)
            max = d;

        return max;
    }

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    Button addfood;
    Button deletefood;
    Button changefood;
    Button fooddetails;
    TextView initialdetails;
    String soro=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialdetails= findViewById(R.id.initialdetails);

        int i=0;
        String username="root";
        String password="#Karunarathna2044";
        String url="jdbc:mysql://findlaptop.cjhrwevkqni3.us-east-2.rds.amazonaws.com:3306/payroll";

        int t1_weighttreshhold,t2_weighttreshhold,t3_weighttreshhold,t4_weighttreshhold;
        String t1_name = null,t2_name = null,t3_name = null,t4_name = null;
        Date t1_exp=new Date();
        Date t2_exp=new Date();
        Date t3_exp=new Date();
        Date t4_exp=new Date();;
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


            ResultSet tray1 =  st9.executeQuery(sql_initial_details_t1);

            if(tray1.next())
            {
                t1_name=tray1.getString(2);
                t1_exp=tray1.getDate(4);
                t1_weighttreshhold=tray1.getInt(3);

            }

            ResultSet tray2 =  st9.executeQuery(sql_initial_details_t2);
            if(tray2.next())
            {
                t2_name=tray2.getString(2);
                t2_exp=tray2.getDate(4);
                t2_weighttreshhold=tray2.getInt(3);

            }

            ResultSet tray3 =  st9.executeQuery(sql_initial_details_t3);
            if(tray3.next())
            {
                t3_name=tray3.getString(2);
                t3_exp=tray3.getDate(4);
                t3_weighttreshhold=tray3.getInt(3);

            }

            ResultSet tray4 =  st9.executeQuery(sql_initial_details_t4);
            if(tray4.next())
            {
                t4_name=tray4.getString(2);
                t4_exp=tray4.getDate(4);
                t4_weighttreshhold=tray4.getInt(3);

            }



        }catch(Exception e)
        {
            e.printStackTrace();
        }

        // calculation and storing them in the string


        //getting closest expire date
        //getting today date  date
           Date today = new Date( );
        Date today2 = new Date( );
        Date today3 = new Date( );
        Date today4 = new Date( );

        long fkd=System.currentTimeMillis();
        today=new java.sql.Date(fkd);// got date
        today2=today;
        today3=today;
        today4=today;

        int tray_1_how_long_left = 0;
        int tray_2_how_long_left = 0;
        int tray_3_how_long_left = 0;
        int tray_4_how_long_left = 0;

        while(t1_exp.compareTo(today) != 0)
        {
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.DATE, 1);
            today = c.getTime();

            tray_1_how_long_left ++;

            if(tray_1_how_long_left>300)
            {
                break;
            }
        }

        while(t2_exp.compareTo(today2) != 0)
        {
            Calendar c = Calendar.getInstance();
            c.setTime(today2);
            c.add(Calendar.DATE, 1);
            today2 = c.getTime();

            tray_2_how_long_left ++;

            if(tray_2_how_long_left>300)
            {
                break;
            }
        }

        while(t3_exp.compareTo(today3) != 0)
        {
            Calendar c = Calendar.getInstance();
            c.setTime(today3);
            c.add(Calendar.DATE, 1);
            today3 = c.getTime();

            tray_3_how_long_left ++;

            if(tray_3_how_long_left>300)
            {
                break;
            }
        }

        while(t4_exp.compareTo(today4) != 0)
        {
            Calendar c = Calendar.getInstance();
            c.setTime(today4);
            c.add(Calendar.DATE, 1);
            today4 = c.getTime();

            tray_4_how_long_left ++;

            if(tray_4_how_long_left>300)
            {
                break;
            }
        }
        // all the deference count created btween dates
        int deference_dates_count = maximum(tray_1_how_long_left,tray_2_how_long_left,tray_3_how_long_left,tray_4_how_long_left);
        String closer_expirefood_name;
        if(deference_dates_count==tray_1_how_long_left)
        {
            closer_expirefood_name = t1_name;
        }
        else if(deference_dates_count==tray_2_how_long_left)
        {
            closer_expirefood_name = t2_name;
        }
        else if(deference_dates_count==tray_3_how_long_left)
        {
            closer_expirefood_name = t3_name;
        }
        else if(deference_dates_count==tray_4_how_long_left)
        {
            closer_expirefood_name = t4_name;
        }
        //got the name of the food which is about to expire

        // now getting the weights of tray which is closest to   run out

        // display the details on view






    }
    public void clickbuttionadd(View view)   // just sending user to the add window
    {
    Intent intent = new Intent(this,addfoodactivity.class);
    startActivity(intent);


    }
    public void clickbuttionview(View view)   // just sending user to the add window
    {
        Intent intent = new Intent(this,viewfoodactivity.class);
        startActivity(intent);


    }
}
