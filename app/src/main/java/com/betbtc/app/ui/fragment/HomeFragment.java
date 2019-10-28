package com.betbtc.app.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpFragment;
import com.betbtc.app.model.HomeMyBet;
import com.betbtc.app.mvp.model.HomeBet;
import com.betbtc.app.ui.activity.LotteryTypeDetailActivity;
import com.betbtc.app.ui.activity.PeriodDetailActivity;
import com.betbtc.app.ui.adapter.HomeBetAdapter;
import com.betbtc.app.ui.dialog.BetDialog;
import com.betbtc.app.ui.dialog.CombineBetDialog;
import com.betbtc.app.view.VerticalDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends MvpFragment {

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_value)
    TextView tvValue;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_minute)
    TextView tvMinute;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.collasping_toolbar)
    CollapsingToolbarLayout collaspingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_deadline)
    TextView tvDeadline;
    @BindView(R.id.tv_my_betrecord)
    TextView tvMyBetrecord;
    @BindView(R.id.lin_my_betrecord)
    LinearLayout linMyBetrecord;
    @BindView(R.id.tv_bet_times)
    TextView tvBetTimes;
    @BindView(R.id.lin_bet_times)
    LinearLayout linBetTimes;
    @BindView(R.id.tv_total)
    LinearLayout tvTotal;
    @BindView(R.id.rv_bet)
    RecyclerView rvBet;
    HomeBetAdapter homeBetAdapter;
    List<HomeBet> homeBets = new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViewAndData() {
        rvBet.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvBet.addItemDecoration(new VerticalDecoration(getContext()));
        rvBet.setItemAnimator(new DefaultItemAnimator());
        String[] titles= getResources().getStringArray(R.array.bet_type);
        for (int i = 0; i < titles.length; i++) {
            homeBets.add(new HomeBet(titles[i]));
        }
        homeBetAdapter = new HomeBetAdapter(homeBets);
        homeBetAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), LotteryTypeDetailActivity.class));
            }
        });
        homeBetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_rise_add:
                        new BetDialog(new HomeMyBet(), 234.94).show(getFragmentManager());
                        break;
                    case R.id.iv_fall_add:
                        new BetDialog(new HomeMyBet(), 234.13).show(getFragmentManager());
                        break;
                }

            }
        });
        rvBet.setAdapter(homeBetAdapter);
    }
    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    @OnClick({R.id.lin_my_betrecord, R.id.tv_period_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_my_betrecord:
                new CombineBetDialog().show(getFragmentManager());
                break;
            case R.id.tv_period_detail:
                startActivity(new Intent(getActivity(), PeriodDetailActivity.class));
                break;
        }

    }
}
