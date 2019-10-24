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
import com.betbtc.app.ui.adapter.LotteryDetailAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LotteryDetailActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    LotteryDetailAdapter lotteryDetailAdapter;
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
      lotteryDetailAdapter = new LotteryDetailAdapter(historyItemList);
      lotteryDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
          @Override
          public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
              startActivity(new Intent(LotteryDetailActivity.this, LotteryTypeDetailActivity.class));
          }
      });
      rvList.setAdapter(lotteryDetailAdapter);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
