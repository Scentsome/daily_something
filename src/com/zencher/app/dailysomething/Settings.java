package com.zencher.app.dailysomething;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;



import android.widget.Button;

import com.zencher.app.dailysomething.activity.Sample1Activity;
import com.zencher.app.dailysomething.util.LogUtil;

/**
 * Created by kevin on 2015/7/13.
 */
public class Settings extends Fragment implements TimePickerDialog.OnTimeSetListener{
    private View v;
    private Button bt,bt2, lovekevin;

    private TextView tv;
    Switch sw;
    int savedHour,savedMin;
    SharedPreferences settingsActivity,settingsActivity2,settingsActivity3;
    int notificationId;
    private PendingIntent alarmIntent;
    boolean swex;
    public static final long DAY = 1000 * 60 * 60 * 24;

     void check(TextView tv, int hour, int min){
        if (min<10){
            tv.setVisibility(View.VISIBLE);
            tv.setText(Integer.toString(hour) + ":" + "0" + Integer.toString(min));
            SharedPreferences.Editor editor = settingsActivity.edit();
            editor.putString("mystring", Integer.toString(hour) + ":" + "0" + Integer.toString(min));
            //最後要提交commit
            editor.commit();
        }else{
            tv.setVisibility(View.VISIBLE);
            tv.setText(Integer.toString(hour) + ":" + Integer.toString(min));
            SharedPreferences.Editor editor = settingsActivity.edit();
            editor.putString("mystring", Integer.toString(hour) + ":" + Integer.toString(min));
            //最後要提交commit
            editor.commit();
        }
    }



    private static final String KEY_PASSWORD = "key_password";

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, Settings.class);
        return intent;
    }

    public static Intent createIntent(Context context, int password) {
        Intent intent = new Intent(context, Settings.class);
        intent.putExtra(KEY_PASSWORD, password);
        return intent;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);



        LogUtil.d("onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        settingsActivity = this.getActivity().getSharedPreferences("pref", Context.MODE_APPEND);
        settingsActivity2 = this.getActivity().getSharedPreferences("pref2",Context.MODE_APPEND);
        settingsActivity3 = this.getActivity().getSharedPreferences("pref3", Context.MODE_APPEND);
        boolean getState = settingsActivity2.getBoolean("swSt",false);
        String mystring = settingsActivity.getString("mystring", "");
        int timeSaver = settingsActivity3.getInt("timeS", 0);
        int timeSaver2 = settingsActivity3.getInt("timeS2",0);
        v = inflater.inflate(R.layout.setting, container, false);
        bt = (Button) v.findViewById(R.id.button7);
        bt2 = (Button)v.findViewById(R.id.button13);

        lovekevin = (Button)v.findViewById(R.id.buttonlovekevin);

        tv = (TextView)v.findViewById(R.id.textView5);
        sw = (Switch)v.findViewById(R.id.switch1);
        sw.setChecked(getState);
        swex = getState;
        tv.setText(mystring);
        savedHour = timeSaver;
        savedMin = timeSaver2;
        if (tv.getText() != null){
            tv.setVisibility(View.VISIBLE);
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), Settings.this, 0, 0, true).show();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = settingsActivity.edit();
                editor.clear();
                editor.commit();
                tv.setVisibility(View.GONE);
                tv.setText(null);
                if(sw.isChecked()){
                    sw.toggle();
                }
                SharedPreferences.Editor editor2 = settingsActivity2.edit();
                editor2.putBoolean("swSt", sw.isChecked());
                //最後要提交commit
                editor2.commit();
                AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                am.cancel(alarmIntent);
            }
        });



        lovekevin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Sample1Activity.class);
                startActivity(intent);
            }
        });



        getActivity().setTitle("Setting");

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor2 = settingsActivity2.edit();
                editor2.putBoolean("swSt", isChecked);
                //最後要提交commit
                editor2.commit();
                if (tv.getVisibility() == View.VISIBLE) {
                    if (isChecked) {
                        Intent bootIntent = new Intent(getActivity(), AlarmBroadcastReceiver.class);
                        bootIntent.putExtra("notificationId", notificationId);
                        alarmIntent = PendingIntent.getBroadcast(getActivity(), 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager alarm = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

                        long firstTime = SystemClock.elapsedRealtime();    // 開機到現在的時間(包括睡眠)
                        long systemTime = System.currentTimeMillis();

                        Calendar startTime = Calendar.getInstance();
                        startTime.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                        startTime.set(Calendar.HOUR_OF_DAY, savedHour);
                        startTime.set(Calendar.MINUTE, savedMin);
                        startTime.set(Calendar.SECOND, 0);
                        long alarmStartTime = startTime.getTimeInMillis();

                        if (systemTime > alarmStartTime) {
                            Toast.makeText(getActivity(), "Alarm set! (Next Day)", Toast.LENGTH_SHORT).show();
                            startTime.add(Calendar.DAY_OF_MONTH, 1);
                            alarmStartTime = startTime.getTimeInMillis();
                        } else {
                            Toast.makeText(getActivity(), "Alarm Set!", Toast.LENGTH_SHORT).show();
                        }
                        long time = alarmStartTime - systemTime;
                        firstTime += time;
                        alarm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, DAY, alarmIntent);
                        notificationId++;
                    } else {
                        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                        am.cancel(alarmIntent);
                        Toast.makeText(getActivity(), "Alarm Canceled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (sw.isChecked() != true){
            sw.toggle();
            SharedPreferences.Editor editor2 = settingsActivity2.edit();
            editor2.putBoolean("swSt", sw.isChecked());
            //最後要提交commit
            editor2.commit();
        }
        check(tv, hourOfDay, minute);
        Intent bootIntent = new Intent(getActivity(), AlarmBroadcastReceiver.class);
        bootIntent.putExtra("notificationId", notificationId);
        alarmIntent = PendingIntent.getBroadcast(getActivity(), 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);

        long firstTime = SystemClock.elapsedRealtime();	// 開機到現在的時間(包括睡眠)
        long systemTime = System.currentTimeMillis();

        Calendar startTime = Calendar.getInstance();
        startTime.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        startTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        startTime.set(Calendar.MINUTE, minute);
        startTime.set(Calendar.SECOND, 0);
        savedHour = hourOfDay;
        savedMin = minute;

        SharedPreferences.Editor editor3 = settingsActivity3.edit();
        editor3.putInt("timeSaver", hourOfDay);
        editor3.putInt("timeSaver2",minute);
        //最後要提交commit
        editor3.commit();

        long alarmStartTime = startTime.getTimeInMillis();

        if (systemTime > alarmStartTime) {
            Toast.makeText(getActivity(), "Alarm set! (Next Day)", Toast.LENGTH_SHORT).show();
            startTime.add(Calendar.DAY_OF_MONTH, 1);
            alarmStartTime = startTime.getTimeInMillis();
        }else{
            Toast.makeText(getActivity(), "Alarm Set!", Toast.LENGTH_SHORT).show();
        }

        long time = alarmStartTime - systemTime;
        firstTime += time;
        alarm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, DAY, alarmIntent);
        notificationId++;


    }
}