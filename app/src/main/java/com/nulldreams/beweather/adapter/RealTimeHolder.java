package com.nulldreams.beweather.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nulldreams.adapter.AbsViewHolder;
import com.nulldreams.adapter.DelegateAdapter;
import com.nulldreams.beweather.R;
import com.nulldreams.beweather.module.RealTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by gaoyunfei on 2017/1/18.
 */

public class RealTimeHolder extends AbsViewHolder<RealTimeDelegate> {

    private View mTopView;
    private ImageView mSkyconIv;
    private TextView mTemperatureTv, mSkyconTv,
            mLocationTv, mWindTv, mHumidityTv,
            mPm25Tv;

    public RealTimeHolder(View itemView) {
        super(itemView);
        mTopView = findViewById(R.id.real_time_top);
        mSkyconIv = (ImageView)findViewById(R.id.real_time_icon);
        mTemperatureTv = (TextView)findViewById(R.id.real_time_temperature);
        mSkyconTv = (TextView)findViewById(R.id.real_time_skycon);
        mLocationTv = (TextView)findViewById(R.id.real_time_location);
        mWindTv = (TextView)findViewById(R.id.real_time_wind);
        mHumidityTv = (TextView)findViewById(R.id.real_time_humidity);
        mPm25Tv = (TextView)findViewById(R.id.real_time_pm25);
    }

    @Override
    public void onBindView(final Context context, RealTimeDelegate realTimeDelegate, int position, DelegateAdapter adapter) {
        RealTime realTime = realTimeDelegate.getSource();
        mSkyconIv.setImageResource(realTime.skycon.getIconRes());
        mTopView.setBackgroundResource(realTime.skycon.getBgRes());
        mTemperatureTv.setText(context.getString(R.string.n_centigrade, (int)realTime.temperature + ""));
        mSkyconTv.setText(realTime.skycon.getTextRes());
        mLocationTv.setText(realTimeDelegate.getCity() + " " + realTimeDelegate.getCountry());
        mWindTv.setText(realTime.wind.getDirection() + "  " + context.getString(R.string.n_kmph, realTime.wind.speed + ""));
        mHumidityTv.setText(realTime.getHumidityText());
        mPm25Tv.setText(realTime.pm25 + "");

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setDrawingCacheEnabled(true);
                Bitmap bmp = v.getDrawingCache();
                try {
                    FileOutputStream outputStream = new FileOutputStream(new File(context.getExternalCacheDir(), "a.png"));
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    v.setDrawingCacheEnabled(false);
                }
            }
        });
    }
}
