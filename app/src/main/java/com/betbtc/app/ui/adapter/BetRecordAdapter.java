package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.mvp.model.BetRecord;
import com.betbtc.app.view.VerticalDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class BetRecordAdapter extends BaseQuickAdapter<BetRecord, BaseViewHolder> {
    public BetRecordAdapter(@Nullable List<BetRecord> data) {
        super(R.layout.item_bet_record,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BetRecord item) {
        if (helper.getAdapterPosition()==0){
            helper.setBackgroundRes(R.id.lin_top,R.drawable.bg_item_top_red);
        }else {
            helper.setBackgroundRes(R.id.lin_top,R.drawable.bg_item_top_gray);
        }
        RecyclerView rvList=helper.getView(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false));
        rvList.addItemDecoration(new VerticalDecoration(mContext));
        rvList.setAdapter(new BetRecordItemAdapter(item.getList()));

    }
}
