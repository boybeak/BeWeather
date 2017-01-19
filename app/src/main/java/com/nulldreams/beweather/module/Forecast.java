package com.nulldreams.beweather.module;

import com.nulldreams.beweather.retrofit.Result;

import java.util.Date;
import java.util.List;

/**
 * Created by gaoyunfei on 2017/1/19.
 */

public class Forecast extends Result {

    public Hourly hourly;
    public List<Alert> alert;

    public class Alert extends Result{
        public String code, description, location;
        public Date pubdate;
        public float[] bound_coord;
    }
}
