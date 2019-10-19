package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;

import com.betbtc.app.R;
import com.betbtc.app.model.CoinDetail;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CoinDetailAdapter extends BaseQuickAdapter<CoinDetail,BaseViewHolder> {
    public CoinDetailAdapter(@Nullable List<CoinDetail> data) {
        super(R.layout.item_coin_detail,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinDetail item) {

    }
}
