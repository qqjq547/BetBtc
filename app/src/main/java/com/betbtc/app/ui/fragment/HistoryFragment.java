package com.betbtc.app.ui.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpFragment;
import com.betbtc.app.model.History;
import com.betbtc.app.model.HistoryItem;
import com.betbtc.app.ui.activity.LotteryDetailActivity;
import com.betbtc.app.ui.adapter.HistoryAdapter;
import com.betbtc.app.view.VerticalDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HistoryFragment extends MvpFragment {

    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;

    List<History> historyList=new ArrayList<>();
    HistoryAdapter historyAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_history;
    }

    @Override
    public void initViewAndData() {
        rvHistory.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rvHistory.addItemDecoration(new VerticalDecoration(getContext()));
        rvHistory.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < 4; i++) {
            History history=new History();
            List<HistoryItem> list=new ArrayList<>();
            list.add(new HistoryItem());
            list.add(new HistoryItem());
            list.add(new HistoryItem());
            list.add(new HistoryItem());
            list.add(new HistoryItem());
            history.setList(list);
            historyList.add(history);
        }
        historyAdapter=new HistoryAdapter(historyList);
        historyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), LotteryDetailActivity.class));
            }
        });
        rvHistory.setAdapter(historyAdapter);

    }
}
