package com.betbtc.app.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.view.PasswordEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPwdAtivity extends MvpActivity {
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.et_pwd)
    PasswordEditText etPwd;
    @BindView(R.id.lin_pwd)
    LinearLayout linPwd;
    @BindView(R.id.v_line1)
    View vLine1;
    @BindView(R.id.et_pwd_again)
    PasswordEditText etPwdAgain;
    @BindView(R.id.lin_pwd_again)
    LinearLayout linPwdAgain;
    @BindView(R.id.v_line2)
    View vLine2;
    @BindView(R.id.btn_ensure)
    Button btnEnsure;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_reset_pwd;
    }

    @Override
    public void initViewAndData() {
      etPwd.setOnFocusChangeListener(focusChangeListener);
      etPwdAgain.setOnFocusChangeListener(focusChangeListener);
    }
    View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()) {
                case R.id.et_pwd:
                    vLine1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    vLine2.setBackgroundColor(getResources().getColor(R.color.bg_white));
                    break;
                case R.id.et_pwd_again:
                    vLine1.setBackgroundColor(getResources().getColor(R.color.bg_white));
                    vLine2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    };

    @OnClick({R.id.tv_cancel, R.id.btn_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                onBackPressed();
                break;
            case R.id.btn_ensure:
                break;
        }
    }
}
