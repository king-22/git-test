package com.example.jyotirmay.utilities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {
    TimePicker timePicker;
    Button button_date, button_time , button_setalarm;
    TextView Tv_date, Tv_time;
    EditText ET_message;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    int day, month, year , hour_alarm , min_alarm;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm2);

        button_date = (Button) findViewById(R.id.btn);
        button_time = (Button) findViewById(R.id.btn1);
        button_setalarm = (Button) findViewById(R.id.btn_setalarm);

        Tv_date = (TextView) findViewById(R.id.tv_date);
        Tv_time = (TextView) findViewById(R.id.tv_time);

        ET_message = (EditText) findViewById(R.id.et_message);

        showDatePickerDialog();
        showTimePickerDialog();

        button_setalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alarm = new Intent(AlarmClock.ACTION_SET_ALARM);
                alarm.putExtra(AlarmClock.EXTRA_HOUR,hour_alarm);
                alarm.putExtra(AlarmClock.EXTRA_MINUTES,min_alarm);
                message = ET_message.getText().toString();
                alarm.putExtra(AlarmClock.EXTRA_MESSAGE,message);
                startActivity(alarm);

            }
        });
        }


    public void showDatePickerDialog() {

        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });


        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar c = Calendar.getInstance();
                c.set(i, i1, i2);
                month = i1 + 1;
                Tv_date.setText(i + "-" + month + "-" + i2);
            }
        }

                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));



    }

    public void showTimePickerDialog() {

        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        Calendar calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hour_alarm = i;
                min_alarm = i1;
                Tv_time.setText(i+":"+(i1<10?"0":"")+i1 );
            }

        } , calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);


    }

}

