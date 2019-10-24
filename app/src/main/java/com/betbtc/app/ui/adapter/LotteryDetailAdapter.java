package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;

import com.betbtc.app.R;
import com.betbtc.app.model.HistoryItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class LotteryDetailAdapter extends BaseQuickAdapter<HistoryItem,BaseViewHolder> {
    public LotteryDetailAdapter(@Nullable List<HistoryItem> data) {
        super(R.layout.item_lottery_detail,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryItem item) {

    }
}
