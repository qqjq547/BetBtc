package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;

import com.betbtc.app.R;
import com.betbtc.app.mvp.model.DealRecord;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class DealRecordAdapter extends BaseQuickAdapter<DealRecord,BaseViewHolder> {
    public DealRecordAdapter(@Nullable List<DealRecord> data) {
        super(R.layout.item_deal_record,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DealRecord item) {

    }
}
