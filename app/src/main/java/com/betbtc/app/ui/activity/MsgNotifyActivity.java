package com.betbtc.app.ui.activity;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MsgNotifyActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cb_open_result)
    CheckBox cbOpenResult;
    @BindView(R.id.cb_bet_result)
    CheckBox cbBetResult;
    @BindView(R.id.cb_reward_arrive)
    CheckBox cbRewardArrive;
    @BindView(R.id.cb_recharge_result)
    CheckBox cbRechargeResult;
    @BindView(R.id.cb_withdraw_result)
    CheckBox cbWithdrawResult;
    @BindView(R.id.cb_invite_result)
    CheckBox cbInviteResult;
    @BindView(R.id.cb_system_notify)
    CheckBox cbSystemNotify;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_msg_notify;
    }

    @Override
    public void initViewAndData() {
        tvTitle.setText(R.string.msg_notify);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
