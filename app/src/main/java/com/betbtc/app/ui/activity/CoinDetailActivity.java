package com.betbtc.app.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.model.CoinDetail;
import com.betbtc.app.ui.adapter.CoinDetailAdapter;
import com.betbtc.app.view.VerticalDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoinDetailActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;

    List<CoinDetail> coinDetails=new ArrayList<>();
    CoinDetailAdapter coinDetailAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_coin_detail;
    }

    @Override
    public void initViewAndData() {
      tvTitle.setText(R.string.balance_detail);
      tvText.setText(R.string.all);
        if (coinDetailAdapter != null) {
            return;
        }
        coinDetails.add(new CoinDetail());
        coinDetails.add(new CoinDetail());
        coinDetails.add(new CoinDetail());
        coinDetails.add(new CoinDetail());
        coinDetails.add(new CoinDetail());
        coinDetails.add(new CoinDetail());
        coinDetailAdapter = new CoinDetailAdapter(coinDetails);
        coinDetailAdapter.setEmptyView(getLayoutInflater().inflate(R.layout.layout_empty, null));
        coinDetailAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        coinDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        coinDetailAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        View emptyView = getLayoutInflater().inflate(R.layout.layout_empty, null);
        coinDetailAdapter.setEmptyView(emptyView);
        rvDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvDetail.addItemDecoration(new VerticalDecoration(this,R.drawable.bg_decoration_space));
        rvDetail.setItemAnimator(new DefaultItemAnimator());
        rvDetail.setAdapter(coinDetailAdapter);
        srlRefresh.setEnableRefresh(true);
        srlRefresh.setEnableLoadMore(true);
        srlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
          @Override
          public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

          }

          @Override
          public void onRefresh(@NonNull RefreshLayout refreshLayout) {

          }
      });
    }

    @OnClick({R.id.iv_back, R.id.tv_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_text:
                break;
        }
    }
}
