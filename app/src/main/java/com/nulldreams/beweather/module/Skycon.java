package com.nulldreams.beweather.module;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.nulldreams.beweather.R;

/**
 * Created by gaoyunfei on 2017/1/18.
 */

public enum Skycon {
    CLEAR_DAY(R.string.text_clear_day, R.drawable.ic_weather_sunny, R.drawable.bg_sunny),
    CLEAR_NIGHT(R.string.text_clear_night, R.drawable.ic_weather_night, R.drawable.bg_cloudy),
    PARTLY_CLOUDY_DAY(R.string.text_partly_cloudy_day, R.drawable.ic_weather_partlycloudy, R.drawable.bg_sunny),
    PARTLY_CLOUDY_NIGHT(R.string.text_partly_cloudy_night, R.drawable.ic_weather_partlycloudy, R.drawable.bg_sunny),
    CLOUDY(R.string.text_cloudy, R.drawable.ic_weather_cloudy, R.drawable.bg_cloudy),
    RAIN(R.string.text_rain, R.drawable.ic_weather_pouring, R.drawable.bg_rain),
    SNOW(R.string.text_snow, R.drawable.ic_weather_snowy, R.drawable.bg_sunny),
    WIND(R.string.text_wind, R.drawable.ic_weather_windy, R.drawable.bg_sunny),
    FOG(R.string.text_fog, R.drawable.ic_weather_fog, R.drawable.bg_sunny),
    HAZE(R.string.text_haze, R.drawable.ic_weather_fog, R.drawable.bg_sunny),
    SLEET(R.string.text_sleet, R.drawable.ic_weather_snowy_rainy, R.drawable.bg_sunny);

    private int textRes, iconRes, bgRes;

    Skycon (@StringRes int textRes, @DrawableRes int iconRes, @DrawableRes int bgRes) {
        this.textRes = textRes;
        this.iconRes = iconRes;
        this.bgRes = bgRes;
    }

    public int getTextRes() {
        return textRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public int getBgRes() {
        return bgRes;
    }
}
