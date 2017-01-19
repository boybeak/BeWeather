package com.nulldreams.beweather.retrofit;

import com.nulldreams.beweather.module.Forecast;
import com.nulldreams.beweather.module.RealTime;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by boybe on 2017/1/18.
 */

public interface NetService {
    @GET("{longitude},{latitude}/realtime.json")
    Call<Response<RealTime>> getRealTime (@Path("longitude") double longitude, @Path("latitude") double latitude);

    @GET("{longitude},{latitude}/forecast.json")
    Call<Response<Forecast>> getForecast (@Path("longitude") double longitude, @Path("latitude") double latitude);
}
