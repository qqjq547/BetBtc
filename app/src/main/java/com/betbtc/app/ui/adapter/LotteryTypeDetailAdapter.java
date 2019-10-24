package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;

import com.betbtc.app.R;
import com.betbtc.app.model.HistoryItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class LotteryTypeDetailAdapter extends BaseQuickAdapter<HistoryItem,BaseViewHolder> {
    public LotteryTypeDetailAdapter(@Nullable List<HistoryItem> data) {
        super(R.layout.item_lottery_type_detail,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryItem item) {
    }
}
