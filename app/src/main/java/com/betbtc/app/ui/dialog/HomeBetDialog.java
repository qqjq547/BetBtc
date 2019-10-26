package com.betbtc.app.ui.dialog;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpDialog;
import com.betbtc.app.model.HomeMyBet;
import com.betbtc.app.ui.adapter.HomeMyBetAdapter;
import com.betbtc.app.view.VerticalDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeBetDialog extends MvpDialog {
    @BindView(R.id.rv_my_bet)
    RecyclerView rvMyBet;

    HomeMyBetAdapter homeMyBetAdapter;
    List<HomeMyBet> homeMyBetList=new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_home_bet;
    }

    @Override
    public void initViewAndData() {
        rvMyBet.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rvMyBet.addItemDecoration(new VerticalDecoration(getContext()));
        homeMyBetList.add(new HomeMyBet());
        homeMyBetList.add(new HomeMyBet());
        homeMyBetList.add(new HomeMyBet());
        homeMyBetAdapter=new HomeMyBetAdapter(homeMyBetList);
        homeMyBetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.lin_info:
                        homeMyBetAdapter.expandItem(position);
                        break;
                    case R.id.tv_add_bet:
                        dismiss();
                        break;
                    case R.id.tv_confirm_bet:
                        break;
                    case R.id.iv_close:
                        new CommonDialog.Builder()
                                .setTitle(R.string.dialog_title)
                                .setMessage(R.string.ensure_delete_this_bet)
                                .setConfirmBtn(R.string.ensure, v -> {
                                    homeMyBetList.remove(position);
                                    homeMyBetAdapter.notifyItemRemoved(position);
                                })
                                .build()
                                .show(getFragmentManager());

                        break;
                }
            }
        });
        rvMyBet.setAdapter(homeMyBetAdapter);
    }
}
