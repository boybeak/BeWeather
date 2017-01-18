package com.nulldreams.beweather.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nulldreams.adapter.DelegateAdapter;
import com.nulldreams.beweather.R;
import com.nulldreams.beweather.adapter.RealTimeDecoration;
import com.nulldreams.beweather.adapter.RealTimeDelegate;
import com.nulldreams.beweather.module.RealTime;
import com.nulldreams.beweather.retrofit.NetManager;
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

        NetManager.getInstance(this).getRealTime(0, 0, new Callback<Response<RealTime>>() {
            @Override
            public void onResponse(Call<Response<RealTime>> call, retrofit2.Response<Response<RealTime>> response) {
                RealTime realTime = response.body().result;
                mAdapter.add(new RealTimeDelegate(realTime));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Response<RealTime>> call, Throwable t) {

            }
        });
    }
}
