package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;

import com.betbtc.app.R;
import com.betbtc.app.mvp.model.BetItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class BetRecordItemAdapter extends BaseQuickAdapter<BetItem,BaseViewHolder> {
    public BetRecordItemAdapter(@Nullable List<BetItem> data) {
        super(R.layout.item_bet_record_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BetItem item) {

    }
}
