package com.betbtc.app.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.tools.Constant;
import com.betbtc.app.ui.activity.common.MyCaptureActivity;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.hjq.toast.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.security.Permission;

import butterknife.BindView;
import butterknife.OnClick;

public class WithDrawActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_scan)
    ImageView tvScan;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_available)
    TextView tvAvailable;
    @BindView(R.id.tv_left_times)
    TextView tvLeftTimes;
    @BindView(R.id.tv_arrive)
    TextView tvArrive;
    @BindView(R.id.btn_withdraw)
    Button btnWithdraw;
    @BindView(R.id.tv_summary)
    TextView tvSummary;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void initViewAndData() {
        tvTitle.setText(R.string.widthdraw);

    }
    @OnClick({R.id.iv_back, R.id.tv_scan, R.id.tv_all, R.id.btn_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_scan:
                new RxPermissions(this)
                        .request(Manifest.permission.CAMERA)
                        .subscribe(granted -> {
                            if (granted) {
                                startActivity(new Intent(this, MyCaptureActivity.class));
                            } else {
                                ToastUtils.show(R.string.get_pic_permission);
                            }
                        });
                break;
            case R.id.tv_all:
                break;
            case R.id.btn_withdraw:
                break;
        }
    }
}
