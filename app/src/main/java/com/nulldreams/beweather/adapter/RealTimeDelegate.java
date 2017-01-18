package com.nulldreams.beweather.adapter;

import com.nulldreams.adapter.annotation.AnnotationDelegate;
import com.nulldreams.adapter.annotation.DelegateInfo;
import com.nulldreams.beweather.R;
import com.nulldreams.beweather.module.RealTime;

/**
 * Created by gaoyunfei on 2017/1/18.
 */
@DelegateInfo(layoutID = R.layout.layout_real_time, holderClass = RealTimeHolder.class)
public class RealTimeDelegate extends AnnotationDelegate<RealTime> {

    public RealTimeDelegate(RealTime realTime) {
        super(realTime);
    }
}
