package com.nulldreams.beweather.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nulldreams.adapter.AbsViewHolder;
import com.nulldreams.adapter.DelegateAdapter;
import com.nulldreams.beweather.R;
import com.nulldreams.beweather.module.RealTime;

/**
 * Created by gaoyunfei on 2017/1/18.
 */

public class RealTimeHolder extends AbsViewHolder<RealTimeDelegate> {

    private ImageView mSkyconIv;
    private TextView mTemperatureTv, mSkyconTv,
            mLocationTv, mWindTv;

    public RealTimeHolder(View itemView) {
        super(itemView);
        mSkyconIv = (ImageView)findViewById(R.id.real_time_icon);
        mTemperatureTv = (TextView)findViewById(R.id.real_time_temperature);
        mSkyconTv = (TextView)findViewById(R.id.real_time_skycon);
        mLocationTv = (TextView)findViewById(R.id.real_time_location);
        mWindTv = (TextView)findViewById(R.id.real_time_wind);
    }

    @Override
    public void onBindView(Context context, RealTimeDelegate realTimeDelegate, int position, DelegateAdapter adapter) {
        RealTime realTime = realTimeDelegate.getSource();
        mSkyconIv.setImageResource(realTime.skycon.getIconRes());
        mTemperatureTv.setText(context.getString(R.string.n_centigrade, (int)realTime.temperature + ""));
        mSkyconTv.setText(realTime.skycon.getTextRes());
        mLocationTv.setText(realTimeDelegate.getCity() + " " + realTimeDelegate.getCountry());
        mWindTv.setText(realTimeDelegate.getDirection() + "  " + context.getString(R.string.n_kmph, realTime.wind.speed + ""));
    }
}
