package com.zencher.app.dailysomething;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

/**
 * Created by kevin on 2015/7/15.
 */
public class CalendarFragment extends Fragment {
    private View v;
    private CalendarView cv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        v = inflater.inflate(R.layout.calendar, container, false);
        cv = (CalendarView)v.findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Dialog_ex dialogFragment = Dialog_ex.newInstance("New Diary?");
                dialogFragment.show(getFragmentManager(), "dialog");
            }
        });

        getActivity().setTitle("Calendar");
        return v;
    }
}
