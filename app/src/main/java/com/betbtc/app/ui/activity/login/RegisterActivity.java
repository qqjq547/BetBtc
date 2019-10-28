package com.betbtc.app.ui.activity.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.tools.Constant;
import com.betbtc.app.view.ClearEditText;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends MvpActivity {
    @BindView(R.id.et_mobile)
    ClearEditText etMobile;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_go_login)
    TextView tvGoLogin;

    String mobile;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initViewAndData() {
        etMobile.setOnFocusChangeListener(focusChangeListener);
    }
    View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()) {
                case R.id.et_mobile:
                    if (hasFocus) {
                        vLine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    } else {
                        vLine.setBackgroundColor(getResources().getColor(R.color.bg_white));
                    }
                    break;
            }
        }
    };
    @OnClick({R.id.tv_cancel,R.id.btn_register, R.id.tv_go_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                onBackPressed();
                break;
            case R.id.btn_register:
                mobile = etMobile.getText().toString().trim();
                if (TextUtils.isEmpty(mobile)) {
                    ToastUtils.show("手机号不能为空");
                } else if (!CommonUtil.isMobileNO(mobile)) {
                    ToastUtils.show("手机号格式不正确");
                } else {
                    startActivity(new Intent(this, InputCodeActivity.class)
                            .putExtra(Constant.TYPE, Constant.TYPE_REGISTER)
                            .putExtra(Constant.MOBILE, mobile));
                }
                break;
            case R.id.tv_go_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }
}
