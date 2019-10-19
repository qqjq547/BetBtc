package com.betbtc.app.view;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.OrientationHelper;

import com.betbtc.app.R;


public class VerticalDecoration extends DividerItemDecoration {

    public VerticalDecoration(Context context) {
        super(context, OrientationHelper.VERTICAL);
        setDrawable(ContextCompat.getDrawable(context, R.drawable.bg_decoration));
    }
    public VerticalDecoration(Context context, int drawableId) {
        super(context, OrientationHelper.VERTICAL);
        setDrawable(ContextCompat.getDrawable(context,drawableId));
    }


}
