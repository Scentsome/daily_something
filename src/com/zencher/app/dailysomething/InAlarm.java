package com.zencher.app.dailysomething;

import android.app.*;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by kevin on 2015/7/24.
 */
public class InAlarm extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), (Alarm)getActivity(),  hour, minute,true);
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hour, int minute) {
    }
}
