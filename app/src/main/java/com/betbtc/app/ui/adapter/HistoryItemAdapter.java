package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.model.History;
import com.betbtc.app.model.HistoryItem;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.view.GridItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HistoryItemAdapter extends BaseQuickAdapter<HistoryItem, BaseViewHolder> {
    String[] type=new String[]{"收盘价","最高价","最低价","差价","成交价"};
    public HistoryItemAdapter(@Nullable List<HistoryItem> data) {
        super(R.layout.item_history_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryItem item) {
        helper.setText(R.id.tv_type,type[helper.getAdapterPosition()]);
    }

}
