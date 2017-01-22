package com.nulldreams.beweather.provider;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

import com.amap.api.location.AMapLocation;
import com.nulldreams.beweather.LocationManager;
import com.nulldreams.beweather.R;
import com.nulldreams.beweather.module.RealTime;
import com.nulldreams.beweather.WeatherManager;
import com.nulldreams.beweather.module.Skycon;

/**
 * Created by gaoyunfei on 2017/1/22.
 */

public class WeatherProvider extends AppWidgetProvider{

    private static final String TAG = WeatherProvider.class.getSimpleName();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //final int N = appWidgetIds.length;
        final RealTime realTime = WeatherManager.getInstance(context).getLastRealTime();
        final AMapLocation location = LocationManager.getInstance(context).getLastLocation();
        Log.v(TAG, "onUpdate realTime=" + realTime);
        if (realTime != null && location != null) {
            final Skycon skycon = realTime.skycon;
            /*for (int i = 0; i < N; i++) {
                final int id = appWidgetIds[i];

                *//*Intent it = new Intent(context, MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(context, 0, it, 0);*//*

                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_real_time_item);

                views.setImageViewResource();

                appWidgetManager.updateAppWidget(id, views);
            }*/
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_real_time_item);
            views.setImageViewResource(R.id.real_time_icon, skycon.getIconRes());
            views.setInt(R.id.real_time_top, "setBackgroundResource", skycon.getBgRes());
            views.setTextViewText(R.id.real_time_skycon, context.getString(skycon.getTextRes()));
            views.setTextViewText(R.id.real_time_temperature, context.getString(R.string.n_centigrade, realTime.temperature + ""));
            views.setTextViewText(R.id.real_time_location, location.getCity() + " " + location.getCountry());

            views.setTextColor(R.id.real_time_skycon, context.getResources().getColor(R.color.weather_icon_color));
            views.setTextColor(R.id.real_time_temperature, context.getResources().getColor(R.color.weather_icon_color));
            views.setTextColor(R.id.real_time_location, context.getResources().getColor(R.color.weather_icon_color));

            views.setTextViewText(R.id.real_time_wind, realTime.wind.getDirection() + "  " + context.getString(R.string.n_kmph, realTime.wind.speed + ""));
            views.setTextViewText(R.id.real_time_humidity, realTime.getHumidityText());
            views.setTextViewText(R.id.real_time_pm25, realTime.pm25 + "");

            appWidgetManager.updateAppWidget(appWidgetIds, views);
        }
    }
}
