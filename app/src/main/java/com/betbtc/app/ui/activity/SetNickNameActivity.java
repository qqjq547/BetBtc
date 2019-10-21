package com.betbtc.app.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.model.UserInfo;
import com.betbtc.app.tools.PrefUtil;
import com.betbtc.app.tools.ProgressDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class SetNickNameActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    UserInfo userInfo;
    String nickName;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_set_nickname;
    }

    @Override
    public void initViewAndData() {
       tvTitle.setText(R.string.nickname);
       userInfo= PrefUtil.getInstance().getUserInfo();
       if (userInfo!=null){
           nickName=userInfo.getNickName();
           etNickname.setText(userInfo.getNickName());
       }
    }

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_confirm:
                nickName=etNickname.getText().toString().trim();
                ProgressDialog.start();
//                mvpPresenter.addUserInfo(nickName);
                break;
        }
    }

//    @Override
//    public void setData(Boolean data) {
//        ProgressDialog.stop();
//        userInfo.setNickName(nickName);
//        PrefUtil.getInstance().put(PrefUtil.USER_INFO,userInfo);
//        Intent intent=new Intent();
//        intent.putExtra(Constant.NICKNAME,nickName);
//        setResult(RESULT_OK,intent);
//        finish();
//    }
//
//    @Override
//    public void setError(String msg) {
//        ProgressDialog.stop();
//        ToastUtils.show(msg);
//    }
}
