package com.betbtc.app.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.model.HistoryItem;
import com.betbtc.app.ui.adapter.PeriodDetailAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PeriodDetailActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    PeriodDetailAdapter periodDetailAdapter;
    List<HistoryItem> historyItemList = new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_period_detail;
    }

    @Override
    public void initViewAndData() {
        tvTitle.setText(R.string.lottery_detail);
        rvList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        historyItemList.add(new HistoryItem());
        historyItemList.add(new HistoryItem());
        historyItemList.add(new HistoryItem());
        historyItemList.add(new HistoryItem());
        historyItemList.add(new HistoryItem());
        periodDetailAdapter = new PeriodDetailAdapter(historyItemList);
        periodDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(PeriodDetailActivity.this, LotteryTypeDetailActivity.class));
            }
        });
        rvList.setAdapter(periodDetailAdapter);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
