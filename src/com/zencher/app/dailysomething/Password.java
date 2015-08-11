package com.zencher.app.dailysomething;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * Created by ª¢ÄË on 2015/8/5.
 */
public class Password extends Activity {
    Button p1,p2,p3,p4,p5,p6,p7,p8,p9;
    TextView pt1,pt2,pt3,pt4;
    boolean ps1,ps2,ps3,ps4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_in);
        setTitle("Password");
        p1 = (Button)findViewById(R.id.pw1);
        p2 = (Button)findViewById(R.id.pw2);
        p3 = (Button)findViewById(R.id.pw3);
        p4 = (Button)findViewById(R.id.pw4);
        p5 = (Button)findViewById(R.id.pw5);
        p6 = (Button)findViewById(R.id.pw6);
        p7 = (Button)findViewById(R.id.pw7);
        p8 = (Button)findViewById(R.id.pw8);
        p9 = (Button)findViewById(R.id.pw9);
        pt1 = (TextView)findViewById(R.id.editText6);
        pt2 = (TextView)findViewById(R.id.editText7);
        pt3 = (TextView)findViewById(R.id.editText8);
        pt4 = (TextView)findViewById(R.id.editText9);

    }

}
