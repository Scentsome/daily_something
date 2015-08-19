package com.zencher.app.dailysomething;
import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kevin on 2015/7/20.
 */
public class Alarm extends Activity implements TimePickerDialog.OnTimeSetListener{
    private Button bt;
    Switch sw,sw2,sw3,sw4,sw5;
    Button bt2,bt3,bt4,bt5,bt6;
    boolean sws,sws2,sws3,sws4,sws5;
    boolean a,a2,a3,a4,a5;
    int run;
    int notificationId;
    private PendingIntent alarmIntent;
    private void check(Switch swC, int hour, int min){
        if (min<10){
            swC.setText(hour+":"+"0"+min);
        }else{
            swC.setText(hour + ":" + min);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm);
        setTitle("Alarm Setting");
        bt = (Button) findViewById(R.id.button6);
        bt2 = (Button) findViewById(R.id.button8);
        bt3 = (Button) findViewById(R.id.button9);
        bt4 = (Button) findViewById(R.id.button10);
        bt5 = (Button) findViewById(R.id.button11);
        bt6 = (Button) findViewById(R.id.button12);
        sw = (Switch) findViewById(R.id.switch2);
        sw2 = (Switch) findViewById(R.id.switch3);
        sw3 = (Switch) findViewById(R.id.switch4);
        sw4 = (Switch) findViewById(R.id.switch5);
        sw5 = (Switch) findViewById(R.id.switch6);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run = bt2.getId();
                showTimePickerDialog(v);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run = bt3.getId();
                showTimePickerDialog(v);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run = bt4.getId();
                showTimePickerDialog(v);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run = bt5.getId();
                showTimePickerDialog(v);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run = bt6.getId();
                showTimePickerDialog(v);
            }
        });
        Calendar setTimeCalendar = Calendar.getInstance();
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new InAlarm();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(run == 0) {
            if (sws == false){
                check(sw, hourOfDay, minute);
                bt2.setVisibility(view.VISIBLE);
                sw.setVisibility(view.VISIBLE);
                sw.toggle();
                sws = true;
                a = true;
                Intent bootIntent = new Intent(Alarm.this, AlarmBroadcastReceiver.class);
                bootIntent.putExtra("notificationId", notificationId);
                alarmIntent = PendingIntent.getBroadcast(Alarm.this, 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();
                alarm.set(
                        AlarmManager.RTC_WAKEUP,
                        alarmStartTime,
                        alarmIntent
                );
                Toast.makeText(Alarm.this, "Alarm set!", Toast.LENGTH_SHORT).show();
                notificationId++;
            }
            else if (sws == true && sws2 == false){
                check(sw2,hourOfDay,minute);
                bt3.setVisibility(view.VISIBLE);
                sw2.setVisibility(view.VISIBLE);
                sw2.toggle();
                sws2 = true;
                a2 = true;
            }
            else if (sws2 == true && sws3 == false){
                check(sw3,hourOfDay,minute);
                bt4.setVisibility(view.VISIBLE);
                sw3.setVisibility(view.VISIBLE);
                sw3.toggle();
                sws3 = true;
                a3 = true;
            }
            else if (sws3 == true && sws4 == false){
                check(sw4,hourOfDay,minute);
                bt5.setVisibility(view.VISIBLE);
                sw4.setVisibility(view.VISIBLE);
                sw4.toggle();
                sws4 = true;
                a4 = true;
            }
            else if (sws4 == true && sws5 == false){
                check(sw5,hourOfDay,minute);
                bt6.setVisibility(view.VISIBLE);
                sw5.setVisibility(view.VISIBLE);
                sw5.toggle();
                sws5 = true;
                a5 = true;
                bt.setEnabled(false);
            }
        }else{
            if (run == bt2.getId()){
                check(sw,hourOfDay,minute);
                run = 0;
            }
            else if (run == bt3.getId()){
                check(sw2,hourOfDay,minute);
                run = 0;
            }
            else if (run == bt4.getId()) {
                check(sw3,hourOfDay,minute);
                run = 0;
            }
            else if (run == bt5.getId()){
                check(sw4,hourOfDay,minute);
                run = 0;
            }
            else if (run == bt6.getId()){
                check(sw5,hourOfDay,minute);
                run = 0;
            }
        }
    }
}
