package com.betbtc.app.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SafeCenterActivity extends MvpActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lin_change_login_pwd)
    LinearLayout linChangeLoginPwd;
    @BindView(R.id.lin_change_trade_pwd)
    LinearLayout linChangeTradePwd;
    @BindView(R.id.lin_bind_email)
    LinearLayout linBindEmail;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_safe_center;
    }

    @Override
    public void initViewAndData() {
        tvTitle.setText(R.string.safe_center);

    }
    @OnClick({R.id.iv_back, R.id.lin_change_login_pwd, R.id.lin_change_trade_pwd, R.id.lin_bind_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.lin_change_login_pwd:
                break;
            case R.id.lin_change_trade_pwd:
                break;
            case R.id.lin_bind_email:
                break;
        }
    }
}
