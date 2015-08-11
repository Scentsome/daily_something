package com.zencher.app.dailysomething;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.*;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private TabHost mTabHost;
    private TabManager mTabManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        setTitle("Main");
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);
        mTabHost.setCurrentTab(0);
        mTabManager.addTab(mTabHost.newTabSpec("DiaryList").setIndicator("DiaryList", this.getResources().getDrawable(android.R.drawable.ic_dialog_alert)),
                ListFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("Setting").setIndicator("Setting",this.getResources().getDrawable(android.R.drawable.ic_dialog_alert)),
                Settings.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("Calendar").setIndicator("Calendar",this.getResources().getDrawable(android.R.drawable.ic_dialog_alert)),
                CalendarFragment.class, null);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm); //先取得螢幕解析度
        int screenWidth = dm.widthPixels;   //取得螢幕的寬
        TabWidget tabWidget = mTabHost.getTabWidget();   //取得tab的物件
        int count = tabWidget.getChildCount();   //取得tab的分頁有幾個
        if (count > 3) {
            for (int i = 0; i < count; i++) {
                tabWidget.getChildTabViewAt(i)
                        .setMinimumWidth((screenWidth)/3);//設定每一個分頁最小的寬度
            }
        }
    }
    public void doPositiveClick(){
        Intent intent = new Intent();
        intent.setClass(this, New_Diary.class);
        startActivity(intent);
    }
    public void doNegativeClick() {
    }
}
