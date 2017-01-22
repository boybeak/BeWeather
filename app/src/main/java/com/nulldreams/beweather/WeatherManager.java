package com.nulldreams.beweather;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.nulldreams.beweather.module.Forecast;
import com.nulldreams.beweather.module.RealTime;
import com.nulldreams.beweather.retrofit.NetService;
import com.nulldreams.beweather.retrofit.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaoyunfei on 2017/1/18.
 */

public class WeatherManager {

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

    private RealTime mLastRealTime;

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

    public RealTime getLastRealTime () {
        return mLastRealTime;
    }
}
