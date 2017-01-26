package com.nulldreams.beweather.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.nulldreams.beweather.WeatherManager;

public class UpdateReceiver extends BroadcastReceiver {

    public static final String ACTION_WEATHER_UPDATE = "com.nulldreams.beweather.action.UPDATE_WEATHER";

    public UpdateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        WeatherManager.getInstance(context).getForecastThisLocation(null);
    }
}
