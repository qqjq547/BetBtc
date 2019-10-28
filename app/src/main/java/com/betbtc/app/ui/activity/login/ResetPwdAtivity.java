package com.betbtc.app.ui.activity.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.tools.Constant;
import com.betbtc.app.view.PasswordEditText;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ResetPwdAtivity extends MvpActivity {
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_title)
    TextView tvTitle;
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

    String mobile;
    int type;

    String password;
    String passwordAgain;


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
        mobile = getIntent().getStringExtra(Constant.MOBILE);
        type = getIntent().getIntExtra(Constant.TYPE, Constant.TYPE_REGISTER);
        if (type == Constant.TYPE_REGISTER) {
            tvTitle.setText(R.string.set_pwd);
        } else {
            tvTitle.setText(R.string.reset_pwd);
        }
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
                password = etPwd.getText().toString().trim();
                passwordAgain = etPwdAgain.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.show("密码不能为空");
                } else if (TextUtils.isEmpty(passwordAgain)) {
                    ToastUtils.show("确认密码不能为空");
                } else if (!CommonUtil.isPassword(password)) {
                    ToastUtils.show("密码格式不正确");
                } else if (!TextUtils.equals(password, passwordAgain)) {
                    ToastUtils.show("密码不一致");
                } else {
                    switch (type) {
                        case Constant.TYPE_REGISTER:
                            startActivity(new Intent(this, LoginActivity.class));
                            break;
                        case Constant.TYPE_FORGET_PWD:
                            startActivity(new Intent(this, LoginActivity.class));
                            break;
                        case Constant.TYPE_CHANGE_PWD:
                            finish();
                            break;
                    }
                }
                break;
        }
    }
}
