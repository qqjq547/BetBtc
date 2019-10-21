package com.betbtc.app.ui.adapter;


import com.betbtc.app.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class CoinDetailsTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public CoinDetailsTypeAdapter(@Nullable List<String> data) {
        super(R.layout.item_coin_details_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_type, item);
    }
}
