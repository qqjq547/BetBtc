package com.betbtc.app.ui.adapter;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.model.History;
import com.betbtc.app.mvp.model.BetRecord;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.ui.activity.LotteryDetailActivity;
import com.betbtc.app.view.GridItemDecoration;
import com.betbtc.app.view.VerticalDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HistoryAdapter extends BaseQuickAdapter<History, BaseViewHolder> {
    public HistoryAdapter(@Nullable List<History> data) {
        super(R.layout.item_history,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, History item) {
        helper.addOnClickListener(R.id.lin_main);
        RecyclerView rvItem=helper.getView(R.id.rv_item);
        rvItem.setLayoutManager(new GridLayoutManager(mContext,5));
        rvItem.addItemDecoration(new GridItemDecoration(5, CommonUtil.dp2px(mContext,10),false));
        HistoryItemAdapter adapter=new HistoryItemAdapter(item.getList());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mContext.startActivity(new Intent(mContext, LotteryDetailActivity.class));
            }
        });
        rvItem.setAdapter(adapter);
    }
}
