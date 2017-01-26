package com.nulldreams.beweather;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.GsonBuilder;
import com.nulldreams.beweather.module.Forecast;
import com.nulldreams.beweather.module.RealTime;
import com.nulldreams.beweather.provider.UpdateReceiver;
import com.nulldreams.beweather.provider.WeatherProvider;
import com.nulldreams.beweather.retrofit.NetService;
import com.nulldreams.beweather.retrofit.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaoyunfei on 2017/1/18.
 */

public class WeatherManager {

    private static final String TAG = WeatherManager.class.getSimpleName();

    private static WeatherManager sManager = null;

    public synchronized static WeatherManager getInstance (Context context) {
        if (sManager == null) {
            sManager = new WeatherManager(context.getApplicationContext());
        }
        return sManager;
    }

    private Context mContext;

    private String mToken;

    private NetService mService;

    private AMapLocation mLastLocation;
    private RealTime mLastRealTime;

    private long mLastAppWidgetUpdateTime = 0;

    private WeatherManager(Context context) {
        mContext = context;
        Properties properties = new Properties();
        try {
            InputStream is = mContext.getAssets().open("property");
            properties.load(is);
            mToken = properties.getProperty("token");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.caiyunapp.com/v2/" + mToken + "/")
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();
        mService = retrofit.create(NetService.class);
    }

    public void getRealTime(double longitude, double latitude, final Callback<Response<RealTime>> callback) {
        Call<Response<RealTime>> responseCall = mService.getRealTime(longitude, latitude);
        responseCall.enqueue(new Callback<Response<RealTime>>() {
            @Override
            public void onResponse(Call<Response<RealTime>> call, retrofit2.Response<Response<RealTime>> response) {
                mLastRealTime = response.body().result;
                if (callback != null) {
                    callback.onResponse(call, response);
                }
            }

            @Override
            public void onFailure(Call<Response<RealTime>> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public void getForecast(double longitude, double latitude, Callback<Response<Forecast>> callback) {
        Call<Response<Forecast>> responseCall = mService.getForecast(longitude, latitude);
        responseCall.enqueue(callback);
    }

    public void getForecastThisLocation (final WeatherCallback callback) {
        LocationManager.getInstance(mContext).startLocation(new AMapLocationListener() {
            @Override
            public void onLocationChanged(final AMapLocation aMapLocation) {
                mLastLocation = aMapLocation;
                getRealTime(aMapLocation.getLongitude(), aMapLocation.getLatitude(), new Callback<Response<RealTime>>() {
                    @Override
                    public void onResponse(Call<Response<RealTime>> call, retrofit2.Response<Response<RealTime>> response) {
                        mLastRealTime = response.body().result;
                        mLastRealTime.setUpdateAt(new Date());
                        if (callback != null) {
                            callback.onResponse(aMapLocation, mLastRealTime);
                        }
                        long now = SystemClock.elapsedRealtime();
                        if (now - mLastAppWidgetUpdateTime > 3600000) {
                            Intent it = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                            int[] ids = AppWidgetManager.getInstance(mContext).getAppWidgetIds(new ComponentName(mContext, WeatherProvider.class));
                            it.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                            mContext.sendBroadcast(it);

                            AlarmManager alarmManager = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
                            Intent timeIt = new Intent(UpdateReceiver.ACTION_WEATHER_UPDATE);
                            PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, timeIt, 0);
                            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 3600000, pi);

                            mLastAppWidgetUpdateTime = now;

                            Log.v(TAG, "getForecastThisLocation");
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<RealTime>> call, Throwable t) {
                        /*if (callback != null) {
                            callback.onFailure(call, t);
                        }*/
                    }
                });
            }
        });
    }

    public RealTime getLastRealTime () {
        return mLastRealTime;
    }

    public AMapLocation getLastLocation () {
        return mLastLocation;
    }

    public interface WeatherCallback {
        public void onResponse (AMapLocation location, RealTime time);
    }
}
