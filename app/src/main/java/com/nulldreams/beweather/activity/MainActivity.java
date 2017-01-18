package com.nulldreams.beweather.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.nulldreams.beweather.R;
import com.nulldreams.beweather.module.RealTime;
import com.nulldreams.beweather.retrofit.NetManager;
import com.nulldreams.beweather.retrofit.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTemperatureTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTemperatureTv = (TextView)findViewById(R.id.temperature);

        NetManager.getInstance(this).getA(0, 0, new Callback<Response<RealTime>>() {
            @Override
            public void onResponse(Call<Response<RealTime>> call, retrofit2.Response<Response<RealTime>> response) {
                Log.v(TAG, "onResponse " + response.message());
                Response<RealTime> realTimeResponse = response.body();
                RealTime realTime = realTimeResponse.result;
                mTemperatureTv.setText(realTime.temperature + "");
            }

            @Override
            public void onFailure(Call<Response<RealTime>> call, Throwable t) {
                Log.v(TAG, "onFailure " + t.getLocalizedMessage());
            }
        });
    }
}
