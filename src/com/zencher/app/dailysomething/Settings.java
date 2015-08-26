package com.zencher.app.dailysomething;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



import android.content.Context;
import android.app.Activity;
import android.view.KeyEvent;

import com.zencher.app.dailysomething.AppConfig;
import com.zencher.app.dailysomething.InitPassCodeActivity;
import com.zencher.app.dailysomething.R;
import com.zencher.app.dailysomething.activity.Sample1Activity;
import com.zencher.app.dailysomething.util.LogUtil;
import com.zencher.app.dailysomething.util.PrefUtil;
import com.zencher.app.dailysomething.util.ShowToast;



/**
 * Created by kevin on 2015/7/13.
 */
public class Settings extends Fragment{

    private View v;
    private Button bt, bt2;



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

        v = inflater.inflate(R.layout.setting, container, false);
        bt = (Button) v.findViewById(R.id.button7);
        bt2 = (Button) v.findViewById(R.id.button8);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Alarm.class);
                startActivity(intent);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Sample1Activity.class);
                startActivity(intent);
            }
        });

        getActivity().setTitle("Setting");
        return v;
    }



}