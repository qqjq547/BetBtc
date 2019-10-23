package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;

import com.betbtc.app.R;
import com.betbtc.app.model.HistoryItem;
import com.betbtc.app.mvp.model.BetItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class LotteryDetailItemAdapter extends BaseQuickAdapter<HistoryItem,BaseViewHolder> {
    String[] type=new String[]{"收盘价","最高价","最低价","差价","成交价"};
    public LotteryDetailItemAdapter(@Nullable List<HistoryItem> data) {
        super(R.layout.item_lottery_detail_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryItem item) {
        helper.setText(R.id.tv_type,type[helper.getAdapterPosition()]);
    }
}
