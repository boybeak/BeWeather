package com.nulldreams.beweather.retrofit;

import android.location.Location;

/**
 * Created by gaoyunfei on 2017/1/18.
 */

public class Response<T extends Result> {
    public String status, lang, unit;
    public long server_time, tzshift;
    public double[] location;
    public T result;
}
