package com.example.nishan.countdowntimer;

import android.os.CountDownTimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView txtView;

    CountDownTimer mCountDownTimer;
    long startTime, milliseconds, diff ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= (Button) findViewById(R.id.button);
        txtView= (TextView) findViewById(R.id.textview1);

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        formatter.setLenient(false);


        String endTime = "07.11.2017, 23:59:00";

        Date endDate;
        try {
            endDate = formatter.parse(endTime);
            milliseconds = endDate.getTime();

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        startTime = System.currentTimeMillis();

        diff = milliseconds - startTime;


        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                mCountDownTimer = new CountDownTimer(milliseconds, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        startTime=startTime-1;
                        Long serverUptimeSeconds =
                                (millisUntilFinished - startTime) / 1000;

                        String daysLeft = String.format("%d", serverUptimeSeconds / 86400);
                        //txtViewDays.setText(daysLeft);
                        Log.d("daysLeft",daysLeft);

                        String hoursLeft = String.format("%d", (serverUptimeSeconds % 86400) / 3600);
                        //txtViewHours.setText(hoursLeft);
                        Log.d("hoursLeft",hoursLeft);

                        String minutesLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) / 60);
                        //txtViewMinutes.setText(minutesLeft);
                        Log.d("minutesLeft",minutesLeft);

                        String secondsLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) % 60);
                        //txtViewSecond.setText(secondsLeft);
                        Log.d("secondsLeft",secondsLeft);

                        txtView.setText("Time Left: "
                                                    + daysLeft+" days "
                                                    + hoursLeft+" hrs "
                                                    + minutesLeft+" min "
                                                    + secondsLeft+" sec ");

                    }

                    @Override
                    public void onFinish() {

                        txtView.setText("Times Up!");

                    }
                }.start();



            }

    });

    }


}