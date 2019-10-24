package com.betbtc.app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.model.HistoryItem;
import com.betbtc.app.tools.ColorUtils;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.ui.adapter.LotteryTypeDetailAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LotteryTypeDetailActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bar_chart)
    BarChart barChart;
    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;

    List<Integer> dataArr=new ArrayList<>();
    List<Integer> colorArr=new ArrayList<>();

    List<HistoryItem> detailList=new ArrayList<>();
    LotteryTypeDetailAdapter lotteryTypeDetailAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_lottery_type_detail;
    }

    @Override
    public void initViewAndData() {
      tvTitle.setText("最高价详情");
        for (int i = 0; i <24 ; i++) {
            int num=(int)(Math.random()*100);
            dataArr.add(num);
            colorArr.add(Math.random()*2>1? ColorUtils.getColor(R.color.rise):ColorUtils.getColor(R.color.fall));
        }
        initChartView();
        initChartData();
        rvDetail.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        for (int i = 0; i < 10; i++) {
            detailList.add(new HistoryItem());
        }
        lotteryTypeDetailAdapter=new LotteryTypeDetailAdapter(detailList);
        rvDetail.setAdapter(lotteryTypeDetailAdapter);
    }
    public void initChartView(){
        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(10f);
        leftAxis.setAxisMinimum(0f);


        barChart.getAxisLeft().setDrawGridLines(false);

        // add a nice and smooth animation
        barChart.animateY(1000);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setAutoScaleMinMaxEnabled(true);

    }
    public void initChartData(){
        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < dataArr.size(); i++) {
            values.add(new BarEntry(i, Math.abs(dataArr.get(i))));
        }

        BarDataSet set1;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Data Set");
            set1.setColors(colorArr);
            set1.setDrawValues(true);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
//            data.setBarWidth(10);
            barChart.setData(data);
            barChart.setFitBars(true);
        }

        barChart.invalidate();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
