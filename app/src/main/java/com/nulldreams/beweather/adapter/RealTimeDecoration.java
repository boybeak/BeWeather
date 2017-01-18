package com.nulldreams.beweather.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nulldreams.beweather.R;

/**
 * Created by gaoyunfei on 2017/1/19.
 */

public class RealTimeDecoration extends RecyclerView.ItemDecoration {

    private int margin;

    public RealTimeDecoration (Context context) {
        margin = context.getResources().getDimensionPixelSize(R.dimen.margin_real_time);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            outRect.set(margin, margin, margin, margin);
        } else {
            outRect.set(margin, 0, margin, margin);
        }
    }
}
