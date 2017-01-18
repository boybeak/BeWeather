package com.nulldreams.beweather.retrofit;

import android.content.Context;

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
            InputStream is = mContext.getAssets().open("token");
            properties.load(is);
            mToken = properties.getProperty("token");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.caiyunapp.com/v2/" + mToken + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = retrofit.create(NetService.class);
    }

    public void getA (double longitude, double latitude, Callback<Response<RealTime>> callback) {
        Call<Response<RealTime>> responseCall = mService.getRealTime(longitude, latitude);
        responseCall.enqueue(callback);
    }
}
