package com.nulldreams.beweather.module;

import com.nulldreams.beweather.retrofit.Result;

import java.util.Date;
import java.util.List;

/**
 * Created by gaoyunfei on 2017/1/19.
 */

public class Hourly extends Result {

    public List<Value> pm25;
    public List<Skycon> skycon;
    public List<Value> cloudrate;
    public List<Value> aqi;
    public List<Value> humidity;
    public List<Value> precipitation;
    public List<Value> temperature;
    public Minutely minutely;

    public String description;

    public class Value {
        public float value;
        public Date datetime;
    }
    public class Skycon {
        public com.nulldreams.beweather.module.Skycon value;
        public Date datetime;
    }

}
