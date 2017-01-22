package com.nulldreams.beweather.module;

import android.text.TextUtils;

import com.nulldreams.beweather.retrofit.Result;

/**
 * Created by gaoyunfei on 2017/1/18.
 */

public class RealTime extends Result {
    public float temperature, cloudrate, humidity;
    public Skycon skycon;
    public int pm25;
    public Precipitation precipitation;
    public Wind wind;

    public String getHumidityText () {
        return humidity * 100 + "%";
    }

    public class Precipitation {
        public Nearest nearest;
        public Local local;
    }
    public class Nearest extends Result {
        public float distance, intensity;
    }
    public class Local extends Result {
        public float intensity;
        public String datasource;
    }
}
