package com.zencher.app.dailysomething.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zencher.app.dailysomething.AppConfig;

import com.zencher.app.dailysomething.InitPassCodeActivity;
import com.zencher.app.dailysomething.R;
import com.zencher.app.dailysomething.util.LogUtil;
import com.zencher.app.dailysomething.util.PrefUtil;
import com.zencher.app.dailysomething.util.ShowToast;


public class Sample1Activity extends Activity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, Sample1Activity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate");
        setContentView(R.layout.activity_sample1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d("onStop");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LogUtil.d("onUserLeaveHint");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i("onDestroy");
    }



    public void onLock(View view) {
        // To the input screen
        startActivity(InitPassCodeActivity.createIntent(getApplicationContext()));
    }

    public void onUnlock(View view) {
        PrefUtil.setBool(getApplicationContext(), AppConfig.PREF_KEY_IS_LOCKED, false);
        PrefUtil.setInt(getApplicationContext(), AppConfig.PREF_KEY_PASSWORD, 0);
        ShowToast.show("Passcode removed¡I", this);
    }

}
