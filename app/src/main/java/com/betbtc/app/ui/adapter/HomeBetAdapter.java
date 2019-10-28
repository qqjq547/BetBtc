package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;

import com.betbtc.app.R;
import com.betbtc.app.mvp.model.HomeBet;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HomeBetAdapter extends BaseQuickAdapter<HomeBet,BaseViewHolder> {
    public HomeBetAdapter(@Nullable List<HomeBet> data) {
        super(R.layout.item_home_bet,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBet item) {
        helper.addOnClickListener(R.id.iv_rise_add);
        helper.addOnClickListener(R.id.iv_fall_add);
        helper.setText(R.id.tv_title,item.getTitle());
    }
}
