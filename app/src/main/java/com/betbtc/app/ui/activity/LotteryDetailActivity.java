package com.betbtc.app.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.model.History;
import com.betbtc.app.model.HistoryItem;
import com.betbtc.app.ui.adapter.LotteryDetailItemAdapter;
import com.betbtc.app.view.VerticalDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LotteryDetailActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    LotteryDetailItemAdapter lotteryDetailItemAdapter;
    List<HistoryItem> historyItemList=new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_lottery_detail;
    }

    @Override
    public void initViewAndData() {
      tvTitle.setText(R.string.lottery_detail);
      rvList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
      historyItemList.add(new HistoryItem());
      historyItemList.add(new HistoryItem());
      historyItemList.add(new HistoryItem());
      historyItemList.add(new HistoryItem());
      historyItemList.add(new HistoryItem());
      lotteryDetailItemAdapter = new LotteryDetailItemAdapter(historyItemList);
      rvList.setAdapter(lotteryDetailItemAdapter);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
