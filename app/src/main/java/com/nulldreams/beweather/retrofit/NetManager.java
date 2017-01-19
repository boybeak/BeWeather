package com.nulldreams.beweather.retrofit;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.nulldreams.beweather.module.Forecast;
import com.nulldreams.beweather.module.RealTime;

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

public class NetManager {

    private static NetManager sManager = null;

    public synchronized static NetManager getInstance (Context context) {
        if (sManager == null) {
            sManager = new NetManager(context.getApplicationContext());
        }
        return sManager;
    }

    private Context mContext;

    private String mToken;

    private NetService mService;

    private NetManager (Context context) {
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

    public void getRealTime(double longitude, double latitude, Callback<Response<RealTime>> callback) {
        Call<Response<RealTime>> responseCall = mService.getRealTime(longitude, latitude);
        responseCall.enqueue(callback);
    }

    public void getForecast(double longitude, double latitude, Callback<Response<Forecast>> callback) {
        Call<Response<Forecast>> responseCall = mService.getForecast(longitude, latitude);
        responseCall.enqueue(callback);
    }
}
