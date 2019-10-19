package com.betbtc.app.ui.adapter;

import com.betbtc.app.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2017/12/11.
 */

public class SheetAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SheetAdapter(@Nullable List<String> data) {
        super(R.layout.item_sheet_menu,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
    }
}
