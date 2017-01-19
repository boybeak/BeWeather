package com.nulldreams.beweather.module;

import com.nulldreams.beweather.retrofit.Result;

import java.util.Date;
import java.util.List;

/**
 * Created by gaoyunfei on 2017/1/19.
 */

public class Daily extends Result {

    public List<Astro> astro;
    public List<Value> temperature, pm25, cloudrate,
            aqi, precipitation, humidity;
    public List<Hourly.Value> skycon;
    public List<Wind> wind;

    public class Astro {
        public Date date;
        public SunTime sunset, sunrise;
    }

    public class SunTime {
        public String time;
    }

    public class Value {
        public Date date;
        public float max, avg, min;
    }
    public class Wind {
        public Date date;
        public com.nulldreams.beweather.module.Wind max, avg, min;
    }
}
