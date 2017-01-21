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

    public String getDirection () {
        if (TextUtils.isEmpty(direction)) {
            float dir = getSource().wind.direction;
            if (dir >= 337.5 || dir <= 22.5) direction = "N";
            if (dir > 22.5 &&  dir < 67.5) direction = "NE";
            if (dir >= 67.5 && dir <= 112.5) direction = "E";
            if (dir > 112.5 && dir < 157.5) direction = "SE";
            if (dir >= 157.5 && dir <= 202.5) direction = "S";
            if (dir > 202.5 && dir < 247.5) direction = "SW";
            if (dir >= 247.5 && dir <= 292.5) direction = "W";
            if (dir > 292.5 && dir < 337.5) direction = "NW";
        }
        return direction;
    }
}
