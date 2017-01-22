package com.nulldreams.beweather.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.nulldreams.adapter.DelegateAdapter;
import com.nulldreams.beweather.LocationManager;
import com.nulldreams.beweather.R;
import com.nulldreams.beweather.adapter.RealTimeDecoration;
import com.nulldreams.beweather.adapter.RealTimeDelegate;
import com.nulldreams.beweather.module.Forecast;
import com.nulldreams.beweather.module.RealTime;
import com.nulldreams.beweather.WeatherManager;
import com.nulldreams.beweather.retrofit.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private RecyclerView mMainRv;

    private DelegateAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainRv = (RecyclerView)findViewById(R.id.main_rv);
        mMainRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DelegateAdapter(this);
        mMainRv.setAdapter(mAdapter);
        mMainRv.addItemDecoration(new RealTimeDecoration(this));

        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        } else {
            getLocationAndRealTime();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocationAndRealTime();
        }
    }

    private void getLocationAndRealTime () {
        LocationManager.getInstance(this).startLocation(new AMapLocationListener() {
            @Override
            public void onLocationChanged(final AMapLocation aMapLocation) {
                Log.v(TAG, "onLocationChanged " + aMapLocation.getLongitude() + " " + aMapLocation.getLatitude()
                        + " address=" + aMapLocation.getCity() + " address=" + aMapLocation.getAddress());
                WeatherManager.getInstance(MainActivity.this).getRealTime(aMapLocation.getLongitude(), aMapLocation.getLatitude(), new Callback<Response<RealTime>>() {
                    @Override
                    public void onResponse(Call<Response<RealTime>> call, retrofit2.Response<Response<RealTime>> response) {
                        RealTime realTime = response.body().result;
                        RealTimeDelegate delegate = new RealTimeDelegate(realTime);
                        delegate.setCountry(aMapLocation.getCountry());
                        delegate.setCity(aMapLocation.getCity());
                        mAdapter.add(delegate);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Response<RealTime>> call, Throwable t) {

                    }
                });
                WeatherManager.getInstance(MainActivity.this).getForecast(aMapLocation.getLongitude(), aMapLocation.getLatitude(), new Callback<Response<Forecast>>() {
                    @Override
                    public void onResponse(Call<Response<Forecast>> call, retrofit2.Response<Response<Forecast>> response) {
                        Log.v(TAG, "onResponse " + response.message());
                    }

                    @Override
                    public void onFailure(Call<Response<Forecast>> call, Throwable t) {

                    }
                });
            }
        });
    }
}
