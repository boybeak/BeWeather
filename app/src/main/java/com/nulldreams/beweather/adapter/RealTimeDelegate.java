package com.nulldreams.beweather.adapter;

import android.text.TextUtils;

import com.nulldreams.adapter.annotation.AnnotationDelegate;
import com.nulldreams.adapter.annotation.DelegateInfo;
import com.nulldreams.beweather.R;
import com.nulldreams.beweather.module.RealTime;
import com.nulldreams.beweather.module.Wind;

/**
 * Created by gaoyunfei on 2017/1/18.
 */
@DelegateInfo(layoutID = R.layout.layout_real_time, holderClass = RealTimeHolder.class)
public class RealTimeDelegate extends AnnotationDelegate<RealTime> {

    private String country, city;

    private String direction;

    public RealTimeDelegate(RealTime realTime) {
        super(realTime);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
