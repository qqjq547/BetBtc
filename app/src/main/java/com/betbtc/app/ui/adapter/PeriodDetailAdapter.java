package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;

import com.betbtc.app.R;
import com.betbtc.app.model.HistoryItem;
import com.betbtc.app.tools.ColorUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PeriodDetailAdapter extends BaseQuickAdapter<HistoryItem, BaseViewHolder> {
    public PeriodDetailAdapter(@Nullable List<HistoryItem> data) {
        super(R.layout.item_period_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryItem item) {
        helper.setText(R.id.tv_type, mContext.getResources().getStringArray(R.array.bet_type)[helper.getAdapterPosition() % 5]);
        PieChart chart = helper.getView(R.id.piechart);
        initChart(chart);
        setData(chart);
    }

    public void initChart(PieChart chart) {
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);

        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setDrawHoleEnabled(false);
        chart.setDrawCenterText(false);

        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(false);


        chart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);
        chart.setDrawEntryLabels(false);
        Legend l = chart.getLegend();
        l.setDrawInside(false);
        l.setEnabled(false);
    }


    private void setData(PieChart chart) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        entries.add(new PieEntry(35));
        entries.add(new PieEntry(65));

        PieDataSet dataSet = new PieDataSet(entries, null);
        dataSet.setDrawValues(false);
        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ColorUtils.getColor(R.color.rise));
        colors.add(ColorUtils.getColor(R.color.fall));

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
//        data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

}
