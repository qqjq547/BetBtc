package com.betbtc.app.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.tools.ColorUtils;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.tools.Constant;
import com.betbtc.app.tools.glide.CircleTransformation;
import com.betbtc.app.tools.glide.GlideApp;
import com.betbtc.app.ui.dialog.SheetDialog;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.hjq.toast.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonInfoActivity extends MvpActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lin_person_info)
    LinearLayout linPersonInfo;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.lin_nick_name)
    LinearLayout linNickName;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.lin_gender)
    LinearLayout linGender;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.lin_birthday)
    LinearLayout linBirthday;

    String path;
    String avatarPath;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_person_info;
    }

    @Override
    public void initViewAndData() {
       tvTitle.setText(R.string.person_info);
        GlideApp.with(this)
                .load(R.drawable.ic_default_head)
                .transform(new CircleTransformation(2, ColorUtils.getColor(R.color.bg_white)))
                .into(ivAvatar);
    }


    @OnClick({R.id.iv_back, R.id.iv_avatar, R.id.lin_nick_name, R.id.lin_gender, R.id.lin_birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_avatar:
                new RxPermissions(this)
                        .request(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) {
                                ImageSelector.builder()
                                        .useCamera(true) // 设置是否使用拍照
                                        .setSingle(true)  //设置是否单选
                                        .setCrop(true)
                                        .canPreview(true) //是否可以预览图片，默认为true
                                        .start(PersonInfoActivity.this, Constant.REQUEST_PICK_PICTURE); // 打开相册
                            } else {
                                ToastUtils.show(R.string.get_pic_permission);
                            }
                        });

                break;
            case R.id.lin_nick_name:
                startActivity(new Intent(this,SetNickNameActivity.class));
                break;
            case R.id.lin_gender:
               String[] genders=getResources().getStringArray(R.array.gender_array);
                new SheetDialog(Arrays.asList(genders), new SheetDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        tvGender.setText(genders[position]);
                    }
                }).show(getSupportFragmentManager());
                break;
            case R.id.lin_birthday:
                new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tvBirthday.setText(CommonUtil.dateToString(date,Constant.FORMAT_DATE));
                    }
                }).build().show();
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_PICK_PICTURE:
                    ArrayList<String> images = intent.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
                    path = images.get(0);
                    GlideApp.with(this)
                            .load(path)
                            .transform(new CircleTransformation(2, ColorUtils.getColor(R.color.bg_white)))
                            .into(ivAvatar);
//                    showLoadingDialog(null);
//                    mvpPresenter.fileUpload(Constant.BUSSINESSTYPE_RED_AVATAR, new File(path));
                    break;
//                case Constant.REQUEST_SET_NICKNAME:
//                    String nickName = intent.getStringExtra(Constant.NICKNAME);
//                    tvNickname.setText(nickName);
//                    break;
//                case Constant.REQUEST_SET_INVITER:
//                    String inviter = intent.getStringExtra(Constant.INVITER);
//                    tvInviter.setText(inviter);
//                    tvInviter.setVisibility(View.VISIBLE);
//                    ivInviterArrow.setVisibility(View.GONE);
//                    break;
            }
        }
    }
}
