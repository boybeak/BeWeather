package com.nulldreams.beweather.provider;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.amap.api.location.AMapLocation;
import com.nulldreams.beweather.Formatters;
import com.nulldreams.beweather.R;
import com.nulldreams.beweather.module.RealTime;
import com.nulldreams.beweather.WeatherManager;
import com.nulldreams.beweather.module.Skycon;

import java.util.Date;

/**
 * Created by gaoyunfei on 2017/1/22.
 */

public class WeatherProvider extends AppWidgetProvider{

    private static final String TAG = WeatherProvider.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.v(TAG, "onReceive");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.v(TAG, "onEnabled ");
        WeatherManager.getInstance(context).getForecastThisLocation(null);
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        //final int N = appWidgetIds.length;
        final RealTime realTime = WeatherManager.getInstance(context).getLastRealTime();
        final AMapLocation location = WeatherManager.getInstance(context).getLastLocation();

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_app_widget);

        if (realTime != null && location != null) {
            final Skycon skycon = realTime.skycon;

            views.setImageViewResource(R.id.app_widget_icon, skycon.getIconRes());
            /*views.setInt(R.id.real_time_top, "setBackgroundResource", skycon.getBgRes());*/
            views.setTextViewText(R.id.app_widget_skycon, context.getString(skycon.getTextRes()));
            views.setTextViewText(R.id.app_widget_temperature, context.getString(R.string.n_centigrade, realTime.temperature + ""));
            views.setTextViewText(R.id.app_widget_location, location.getDistrict() + " " + location.getCity() + " " + location.getCountry());

            /*views.setTextColor(R.id.real_time_skycon, context.getResources().getColor(R.color.weather_icon_color));
            views.setTextColor(R.id.real_time_temperature, context.getResources().getColor(R.color.weather_icon_color));
            views.setTextColor(R.id.real_time_location, context.getResources().getColor(R.color.weather_icon_color));

            views.setTextViewText(R.id.real_time_wind, realTime.wind.getDirection() + "  " + context.getString(R.string.n_kmph, realTime.wind.speed + ""));
            views.setTextViewText(R.id.real_time_humidity, realTime.getHumidityText());
            views.setTextViewText(R.id.real_time_pm25, realTime.pm25 + "");
            views.setTextViewText(R.id.real_time_update_at, Formatters.HH_mm_ss.format(realTime.getUpdateAt()));*/

            Log.v(TAG, "WeatherProvider onUpdate onResponse ");
        }

        appWidgetManager.updateAppWidget(appWidgetIds, views);

        Log.v(TAG, "onUpdate realTime=" + realTime);
    }



}
