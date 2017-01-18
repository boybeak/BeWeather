package com.nulldreams.beweather;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by gaoyunfei on 2017/1/18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
