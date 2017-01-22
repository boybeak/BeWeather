package com.nulldreams.beweather;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by gaoyunfei on 2017/1/19.
 */

public class LocationManager {

    private static LocationManager sManager = null;

    public static synchronized LocationManager getInstance (Context context) {
        if (sManager == null) {
            sManager = new LocationManager(context.getApplicationContext());
        }
        return sManager;
    }

    private Context mContext;

    private AMapLocationListener mTempLocationListener = null;

    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (mTempLocationListener != null) {
                mTempLocationListener.onLocationChanged(aMapLocation);
                mTempLocationListener = null;
                mLocationClient.stopLocation();
            }
        }
    };

    private AMapLocationClient mLocationClient = null;

    private AMapLocationClientOption mLocationOption = null;

    private LocationManager (Context context) {
        mContext = context;
        Properties properties = new Properties();
        try {
            InputStream is = mContext.getAssets().open("property");
            properties.load(is);
            String apiKey = properties.getProperty("a_map_api_key");
            AMapLocationClient.setApiKey(apiKey);

            mLocationClient = new AMapLocationClient(mContext.getApplicationContext());
            mLocationClient.setLocationListener(mLocationListener);

            mLocationOption = new AMapLocationClientOption();
            mLocationOption.setLocationCacheEnable(true);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
            mLocationOption.setOnceLocation(true);
            mLocationOption.setOnceLocationLatest(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startLocation (AMapLocationListener listener) {
        mTempLocationListener = listener;
        mLocationClient.startLocation();
    }

    public AMapLocation getLastLocation () {
        return mLocationClient.getLastKnownLocation();
    }

}
