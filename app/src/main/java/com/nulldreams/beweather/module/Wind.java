package com.nulldreams.beweather.module;

import android.text.TextUtils;

import java.util.Date;

public class Wind {
    public float direction, speed;
    public Date datetime;

    private String directionStr;

    public String getDirection () {
        if (TextUtils.isEmpty(directionStr)) {
            if (direction >= 337.5 || direction <= 22.5) directionStr = "N";
            if (direction > 22.5 &&  direction < 67.5) directionStr = "NE";
            if (direction >= 67.5 && direction <= 112.5) directionStr = "E";
            if (direction > 112.5 && direction < 157.5) directionStr = "SE";
            if (direction >= 157.5 && direction <= 202.5) directionStr = "S";
            if (direction > 202.5 && direction < 247.5) directionStr = "SW";
            if (direction >= 247.5 && direction <= 292.5) directionStr = "W";
            if (direction > 292.5 && direction < 337.5) directionStr = "NW";
        }
        return directionStr;
    }
}