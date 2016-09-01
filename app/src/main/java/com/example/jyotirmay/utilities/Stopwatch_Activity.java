package com.example.jyotirmay.utilities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Stopwatch_Activity extends Activity {
    Button START,RESET , SPLIT;
    TextView TIME;
    ListView LV_Timings;
    ArrayList listitems ;
    ArrayAdapter<String> adapter;
    long starttime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedtime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 0;
    int milliseconds = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listitems = new ArrayList<String> ();

        setContentView(R.layout.activity_stopwatch);
        START = (Button) findViewById(R.id.start_but);
        RESET = (Button) findViewById(R.id.reset_btn);
        SPLIT = (Button) findViewById(R.id.split_btn);
        TIME = (TextView) findViewById(R.id.tv_count);
        LV_Timings = (ListView) findViewById(R.id.lv_stopwatch);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listitems);
        LV_Timings.setAdapter(adapter);



        final Runnable updateTimer = new Runnable() {

            public void run() {

                timeInMilliseconds = SystemClock.uptimeMillis() - starttime;

                updatedtime = timeSwapBuff + timeInMilliseconds;

                secs = (int) (updatedtime / 1000);
                mins = secs / 60;
                secs = secs % 60;
                milliseconds = (int) (updatedtime % 1000);
                TIME.setText("" + mins + ":" + String.format("%02d", secs) + ":"
                        + String.format("%03d", milliseconds));
                TIME.setTextColor(Color.RED);
                handler.postDelayed(this, 0);
            }


        };

        START.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

                if (t == 1) {

//timer will start
                    START.setText("Pause");
                    starttime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimer, 0);
                    t = 0;
                } else {
//timer will pause
                    START.setText("Start");
                    TIME.setTextColor(Color.BLUE);
                    timeSwapBuff += timeInMilliseconds;
                    handler.removeCallbacks(updateTimer);
                    t = 1;
                }

            }
        });

        RESET.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                starttime = 0L;
                timeInMilliseconds = 0L;
                timeSwapBuff = 0L;
                updatedtime = 0L;
                t = 1;
                secs = 0;
                mins = 0;
                milliseconds = 0;
                START.setText("START");
                handler.removeCallbacks(updateTimer);
                TIME.setText("00:00:00");
                adapter.clear();
                adapter.notifyDataSetChanged();

            }});

        SPLIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(t==0) {
                    listitems.add("" + mins + ":" + String.format("%02d", secs) + ":"
                            + String.format("%03d", milliseconds));

                    adapter.notifyDataSetChanged();
                }

            }
        });





    }


        }



