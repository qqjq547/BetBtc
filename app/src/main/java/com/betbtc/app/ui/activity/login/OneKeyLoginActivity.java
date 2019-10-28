package com.betbtc.app.ui.activity.login;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.tools.LogUtil;
import com.hjq.toast.ToastUtils;
import com.mob.secverify.OperationCallback;
import com.mob.secverify.SecVerify;
import com.mob.secverify.VerifyCallback;
import com.mob.secverify.datatype.VerifyResult;
import com.mob.secverify.exception.VerifyException;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

public class OneKeyLoginActivity extends MvpActivity {
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_errmsg)
    TextView tvErrmsg;
    @BindView(R.id.btb_login)
    Button btbLogin;
    @BindView(R.id.tv_other_login)
    TextView tvOtherLogin;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_onekey_login;
    }

    @Override
    public void initViewAndData() {
        new RxPermissions(this)
                .request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) {
                        SecVerify.preVerify(new OperationCallback<Void>() {
                            @Override
                            public void onComplete(Void data) {
                                //TODO处理成功的结果
                                tvErrmsg.setText("");
                            }

                            @Override
                            public void onFailure(VerifyException e) {
                                //TODO处理失败的结果
                                tvErrmsg.setText(e.getMessage());
                            }
                        });
                    } else {
                        ToastUtils.show(R.string.get_pic_permission);
                    }
                });
    }

    @OnClick({R.id.btb_login, R.id.tv_other_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btb_login:
                SecVerify.verify(new VerifyCallback() {
                    @Override
                    public void onOtherLogin() {
                        // 用户点击“其他登录方式”，处理自己的逻辑
                    }

                    @Override
                    public void onUserCanceled() {
                        // 用户点击“关闭按钮”或“物理返回键”取消登录，处理自己的逻辑
                    }

                    @Override
                    public void onComplete(VerifyResult data) {
                        // 获取授权码成功，将token信息传给应用服务端，再由应用服务端进行登录验证，此功能需由开发者自行实现
                        LogUtil.d("data=" + data.getToken());
                    }

                    @Override
                    public void onFailure(VerifyException e) {
                        //TODO处理失败的结果
                    }
                });
                break;
            case R.id.tv_other_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

}
