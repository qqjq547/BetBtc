package com.betbtc.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechargeActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_coin_type)
    TextView tvCoinType;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.tv_coin_address)
    TextView tvCoinAddress;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.tv_summary)
    TextView tvSummary;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initViewAndData() {
         tvTitle.setText(R.string.recharge);
    }

    @OnClick({R.id.iv_back, R.id.tv_coin_type, R.id.tv_share, R.id.tv_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_coin_type:
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_copy:
                break;
        }
    }
}
