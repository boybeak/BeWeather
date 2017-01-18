package com.nulldreams.beweather.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nulldreams.adapter.AbsViewHolder;
import com.nulldreams.adapter.DelegateAdapter;
import com.nulldreams.beweather.Formatters;
import com.nulldreams.beweather.R;
import com.nulldreams.beweather.module.RealTime;

/**
 * Created by gaoyunfei on 2017/1/18.
 */

public class RealTimeHolder extends AbsViewHolder<RealTimeDelegate> {

    private TextView mTemperatureTv, mSkyconTv;

    public RealTimeHolder(View itemView) {
        super(itemView);

        mTemperatureTv = (TextView)findViewById(R.id.real_time_temperature);
        mSkyconTv = (TextView)findViewById(R.id.real_time_skycon);
    }

    @Override
    public void onBindView(Context context, RealTimeDelegate realTimeDelegate, int position, DelegateAdapter adapter) {
        RealTime realTime = realTimeDelegate.getSource();
        mTemperatureTv.setText(context.getString(R.string.n_centigrade, Formatters.TEMPERATURE_FORMATTER.format(realTime.temperature)));
        mSkyconTv.setText(realTime.skycon.name());
    }
}
