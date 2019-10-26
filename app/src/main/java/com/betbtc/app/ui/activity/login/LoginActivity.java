package com.betbtc.app.ui.activity.login;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.http.subscriber.CountDownSubscriber;
import com.betbtc.app.tools.ColorUtils;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.tools.Constant;
import com.betbtc.app.tools.LogUtil;
import com.betbtc.app.tools.PrefUtil;
import com.betbtc.app.tools.ProgressDialog;
import com.betbtc.app.tools.RxUtil;
import com.betbtc.app.ui.activity.MainActivity;
import com.betbtc.app.view.ClearEditText;
import com.betbtc.app.view.PasswordEditText;
import com.hjq.toast.ToastUtils;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import javax.annotation.Resource;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import rx.functions.Action0;

public class LoginActivity extends MvpActivity {
    @BindView(R.id.rbtn_code)
    RadioButton rbtnCode;
    @BindView(R.id.rbtn_pwd)
    RadioButton rbtnPwd;
    @BindView(R.id.rgroup)
    RadioGroup rgroup;
    @BindView(R.id.et_mobile)
    ClearEditText etMobile;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.lin_code)
    LinearLayout linCode;
    @BindView(R.id.et_pwd)
    PasswordEditText etPwd;
    @BindView(R.id.lin_pwd)
    LinearLayout linPwd;
    @BindView(R.id.btb_login)
    Button btbLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.v_line1)
    View vLine1;
    @BindView(R.id.v_line2)
    View vLine2;

    String mobile="";
    String code="";
    String password="";

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initViewAndData() {
        etMobile.setOnFocusChangeListener(focusChangeListener);
        etCode.setOnFocusChangeListener(focusChangeListener);
        etPwd.setOnFocusChangeListener(focusChangeListener);
        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_code:
                        checkTab(checkedId);
                        linCode.setVisibility(View.VISIBLE);
                        linPwd.setVisibility(View.GONE);
                        break;
                    case R.id.rbtn_pwd:
                        checkTab(checkedId);
                        linCode.setVisibility(View.GONE);
                        linPwd.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        rbtnCode.setChecked(true);
        initSms();
    }

    @OnClick({R.id.lin_country,R.id.tv_getcode, R.id.btb_login, R.id.tv_register, R.id.tv_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_country:
                SMSSDK.getSupportedCountries();
                break;
            case R.id.tv_getcode:
                mobile=etMobile.getText().toString().trim();
                if (TextUtils.isEmpty(mobile)){
                    ToastUtils.show("手机号不能为空");
                }else if(!CommonUtil.isMobileNO(mobile)){
                    ToastUtils.show("手机号格式不正确");
                }else {
                    ProgressDialog.start();
                    SMSSDK.getVerificationCode(Constant.COUNTRY_CODE,mobile);
                }
                break;
            case R.id.btb_login:
                mobile=etMobile.getText().toString().trim();
                code=etCode.getText().toString().trim();
                password=etPwd.getText().toString().trim();
                if (TextUtils.isEmpty(mobile)){
                    ToastUtils.show("手机号不能为空");
                }else if(!CommonUtil.isMobileNO(mobile)){
                    ToastUtils.show("手机号格式不正确");
                }
                if (rgroup.getCheckedRadioButtonId()==R.id.rbtn_code){
                    if(TextUtils.isEmpty(code)){
                        ToastUtils.show("验证码");
                    }else {
                        ProgressDialog.start();
                        SMSSDK.submitVerificationCode(Constant.COUNTRY_CODE,mobile,code);
                    }
                }else {
                    if(TextUtils.isEmpty(password)){
                        ToastUtils.show("密码不能为空");
                    }else if(!CommonUtil.isPassword(password)){
                        ToastUtils.show("密码格式不正确");
                    }else {
                        ProgressDialog.start();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ProgressDialog.stop();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                PrefUtil.getInstance().setString(PrefUtil.USER_TOKEN,mobile);
                            }
                        },2000);
                    }
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this,ForgetPwdActivity.class));
                break;
        }
    }

    View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()) {
                case R.id.et_mobile:
                    vLine1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    vLine2.setBackgroundColor(getResources().getColor(R.color.bg_white));
                    break;
                case R.id.et_code:
                case R.id.et_pwd:
                    vLine1.setBackgroundColor(getResources().getColor(R.color.bg_white));
                    vLine2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    };
  public void checkTab(int viewId){
      for (int i=0;i<rgroup.getChildCount();i++){
          RadioButton rbtn=(RadioButton) rgroup.getChildAt(i);
          if (rbtn.getId()==viewId){
              rbtn.setTextColor(getResources().getColor(R.color.text_white));
              rbtn.setTextSize(16);
              rbtn.getPaint().setFakeBoldText(true);
          }else {
              rbtn.setTextColor(getResources().getColor(R.color.text_white_light));
              rbtn.setTextSize(14);
              rbtn.getPaint().setFakeBoldText(false);
          }

      }

  }
  public void initSms(){
      EventHandler eh=new EventHandler(){

          @Override
          public void afterEvent(int event, int result, Object data) {
              LogUtil.d("event="+event+",data="+data);
              if (result == SMSSDK.RESULT_COMPLETE) {
                  //回调完成
                  if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                      //提交验证码成功
                      runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              ProgressDialog.stop();
                              startActivity(new Intent(LoginActivity.this, MainActivity.class));
                          }
                      });
                  }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                      //获取验证码成功
                      runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              ProgressDialog.stop();
                              sendCode();
                          }
                      });
                  }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                      //返回支持发送验证码的国家列表
                  }
              }else{
                  ((Throwable)data).printStackTrace();
                  runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          ProgressDialog.stop();
                          ToastUtils.show(((Throwable)data).getMessage());
                      }
                  });
              }
          }
      };
      SMSSDK.registerEventHandler(eh); //注册
  }
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


}
