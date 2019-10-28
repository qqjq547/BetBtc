package com.betbtc.app.ui.activity.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.tools.Constant;
import com.betbtc.app.tools.LogUtil;
import com.betbtc.app.tools.ProgressDialog;
import com.betbtc.app.tools.RxUtil;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InputCodeActivity extends MvpActivity {
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.lin_code)
    LinearLayout linCode;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.btn_next)
    Button btnNext;

    String mobile;
    String code;
    int type;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_input_code;
    }

    @Override
    public void initViewAndData() {
        mobile=getIntent().getStringExtra(Constant.MOBILE);
        type = getIntent().getIntExtra(Constant.TYPE, Constant.TYPE_REGISTER);
        tvMobile.setText(CommonUtil.encryptMobile(mobile));
        etCode.setOnFocusChangeListener(focusChangeListener);
        SMSSDK.unregisterAllEventHandler();
        SMSSDK.registerEventHandler(eh);
        ProgressDialog.start();
        tvGetcode.callOnClick();

    }

    @OnClick({R.id.tv_cancel, R.id.tv_getcode, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                onBackPressed();
                break;
            case R.id.tv_getcode:
                ProgressDialog.start();
                SMSSDK.getVerificationCode(Constant.COUNTRY_CODE, mobile);
                break;
            case R.id.btn_next:
                code=etCode.getText().toString().trim();
                if(TextUtils.isEmpty(code)){
                    ToastUtils.show("验证码不能为空");
                }else {
                    ProgressDialog.start();
                    SMSSDK.submitVerificationCode(Constant.COUNTRY_CODE,mobile,code);
                }
                break;
        }
    }
    View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()) {
                case R.id.et_code:
                    if (hasFocus) {
                        vLine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    } else {
                        vLine.setBackgroundColor(getResources().getColor(R.color.bg_white));
                    }
                    break;
            }
        }
    };
    EventHandler eh = new EventHandler() {

        @Override
        public void afterEvent(int event, int result, Object data) {
            LogUtil.d("event=" + event + ",data=" + data);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressDialog.stop();
                            switch (type) {
                                case Constant.TYPE_REGISTER:
                                    startActivity(new Intent(InputCodeActivity.this, ResetPwdAtivity.class)
                                            .putExtra(Constant.TYPE, Constant.TYPE_REGISTER)
                                            .putExtra(Constant.MOBILE, mobile));
                                    break;
                                case Constant.TYPE_FORGET_PWD:
                                    startActivity(new Intent(InputCodeActivity.this, ResetPwdAtivity.class)
                                            .putExtra(Constant.TYPE, Constant.TYPE_FORGET_PWD)
                                            .putExtra(Constant.MOBILE, mobile));
                                    break;
                                case Constant.TYPE_CHANGE_PWD:
                                    finish();
                                    break;
                            }
//
                        }
                    });
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressDialog.stop();
                            sendCode();
                        }
                    });
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ProgressDialog.stop();
                        ToastUtils.show(((Throwable) data).getMessage());
                    }
                });
            }
        }
    };

    private void sendCode() {
        RxUtil.countdown(60)
                .doOnSubscribe(disposable -> {
                    tvGetcode.setEnabled(false);
                    tvGetcode.setTextColor(getResources().getColor(R.color.text_white));
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        tvGetcode.setText(String.valueOf(aLong)+"s");
                    }

                    @Override
                    public void onError(Throwable e) {
                        tvGetcode.setText(R.string.get_code);
                        tvGetcode.setEnabled(true);
                    }

                    @Override
                    public void onComplete() {
                        tvGetcode.setText(R.string.reget_code);
                        tvGetcode.setEnabled(true);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}
