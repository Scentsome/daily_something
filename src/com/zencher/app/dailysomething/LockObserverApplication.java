package com.zencher.app.dailysomething;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.zencher.app.dailysomething.util.LogUtil;
import com.zencher.app.dailysomething.util.PrefUtil;

import java.util.HashSet;

import static com.zencher.app.dailysomething.AppConfig.PREF_KEY_IS_LOCKED;

public class LockObserverApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private HashSet<Integer> activityStack;
    private boolean isLaunchApp;

    @Override
    public void onCreate() {
        super.onCreate();
        activityStack = new HashSet<>();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onTerminate() {
        unregisterActivityLifecycleCallbacks(this);
        super.onTerminate();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtil.w("activityStack.size() = " + activityStack.size());
        if (activityStack.size() == 0) {
            LogUtil.w("Start");
            isLaunchApp = true;
        }
        activityStack.add(activity.hashCode());
        if (isLaunchApp) {
            isLaunchApp = false;
            if (checkIsLocked())
                activity.startActivity(ConfirmPassCodeActivity.createIntent(getApplicationContext()));
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityStack.remove(activity.hashCode());
        LogUtil.w("activityStack.size() = " + activityStack.size());
        if (activityStack.size() == 0) {
            LogUtil.w("End");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    /* Determining whether the password-setting, Pete */
    protected boolean checkIsLocked() {
        return PrefUtil.getBool(getApplicationContext(), PREF_KEY_IS_LOCKED);
    }
}
