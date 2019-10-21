package com.betbtc.app.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lin_person_info)
    LinearLayout linPersonInfo;
    @BindView(R.id.lin_msg_notify)
    LinearLayout linMsgNotify;
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    @BindView(R.id.lin_real_name)
    LinearLayout linRealName;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.lin_language)
    LinearLayout linLanguage;
    @BindView(R.id.tv_color)
    TextView tvColor;
    @BindView(R.id.lin_color)
    LinearLayout linColor;
    @BindView(R.id.lin_logout)
    LinearLayout linLogout;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initViewAndData() {
         tvTitle.setText(R.string.setting);
    }

    @OnClick({R.id.iv_back, R.id.lin_person_info, R.id.lin_msg_notify, R.id.lin_real_name, R.id.lin_language, R.id.lin_color, R.id.lin_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.lin_person_info:
                startActivity(new Intent(this,PersonInfoActivity.class));
                break;
            case R.id.lin_msg_notify:
                startActivity(new Intent(this,MsgNotifyActivity.class));
                break;
            case R.id.lin_real_name:
                break;
            case R.id.lin_language:
                break;
            case R.id.lin_color:
                break;
            case R.id.lin_logout:
                break;
        }
    }
}
