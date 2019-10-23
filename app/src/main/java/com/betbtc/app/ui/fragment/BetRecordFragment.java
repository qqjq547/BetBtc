package com.betbtc.app.ui.fragment;

import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpFragment;
import com.betbtc.app.mvp.model.BetItem;
import com.betbtc.app.mvp.model.BetRecord;
import com.betbtc.app.ui.adapter.BetRecordAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BetRecordFragment extends MvpFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_bet_record)
    RecyclerView rvBetRecord;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;

    List<BetRecord> betRecords=new ArrayList<>();
    BetRecordAdapter betRecordAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_bet_record;
    }

    @Override
    public void initViewAndData() { tvTitle.setText("注单");
        rvBetRecord.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rvBetRecord.setItemAnimator(new DefaultItemAnimator());
       BetRecord betRecord1=new BetRecord();
       List<BetItem> item1=new ArrayList<>();
        item1.add(new BetItem());
        item1.add(new BetItem());
        betRecord1.setList(item1);
        betRecords.add(betRecord1);

        BetRecord betRecord2=new BetRecord();
        List<BetItem> item2=new ArrayList<>();
        item2.add(new BetItem());
        item2.add(new BetItem());
        item2.add(new BetItem());
        betRecord2.setList(item2);
        betRecords.add(betRecord2);

        BetRecord betRecord3=new BetRecord();
        List<BetItem> item3=new ArrayList<>();
        item3.add(new BetItem());
        item3.add(new BetItem());
        item3.add(new BetItem());
        item3.add(new BetItem());
        betRecord3.setList(item3);
        betRecords.add(betRecord3);

        betRecordAdapter=new BetRecordAdapter(betRecords);
        rvBetRecord.setAdapter(betRecordAdapter);

    }
}
