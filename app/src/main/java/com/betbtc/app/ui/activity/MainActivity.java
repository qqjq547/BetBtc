package com.betbtc.app.ui.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadingDialogListener;
import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.tools.AppManager;
import com.betbtc.app.ui.fragment.DataFragment;
import com.betbtc.app.ui.fragment.HistoryFragment;
import com.betbtc.app.ui.fragment.HomeFragment;
import com.betbtc.app.ui.fragment.UserFragment;

import butterknife.BindView;

public class MainActivity extends MvpActivity {

    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.rbtn_home)
    RadioButton rbtnHome;
    @BindView(R.id.rbtn_data)
    RadioButton rbtnData;
    @BindView(R.id.rbtn_history)
    RadioButton rbtnHistory;
    @BindView(R.id.rbtn_user)
    RadioButton rbtnUser;
    @BindView(R.id.rgroup_main)
    RadioGroup rgroupMain;
    @BindView(R.id.container)
    LinearLayout container;
//    BadgeView badgeView;
    long mExitTime;
    Toast toast;
    int currentId = -1;
    Fragment[] fragments = new Fragment[5];
    MainReceiver mainReceiver;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewAndData() {
//        setStatusbar(R.color.colorPrimary,false);
        fragments[0] = new HomeFragment();
        fragments[1] = new DataFragment();
        fragments[2] = new HistoryFragment();
        fragments[3] = new UserFragment();
        rgroupMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_home:
                        selectTab(0);
                        break;
                    case R.id.rbtn_data:
                        selectTab(1);
                        break;
                    case R.id.rbtn_history:
                        selectTab(2);
                        break;
                    case R.id.rbtn_user:
                        selectTab(3);
                        break;
                }
            }
        });
        rbtnHome.setChecked(true);
        mainReceiver=new MainReceiver();
        IntentFilter filter=new IntentFilter();
        registerReceiver(mainReceiver,filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        setMsgDotView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mainReceiver);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            toast = Toast.makeText(this, R.string.press_again_to_exit, Toast.LENGTH_SHORT);
            toast.show();
            mExitTime = System.currentTimeMillis();
        } else {
            toast.cancel();
            AppManager.finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }
    public void selectTab(int index) {
        if (currentId == index) {
            return;
        }
        if (currentId >= 0) {
            switchContent(fragments[currentId], fragments[index]);
        } else {
            switchContent(null, fragments[index]);
        }
        currentId = index;
    }

    public void switchContent(Fragment from, Fragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (from == null) {
            transaction.add(R.id.content, to).commit();
        } else if (from != to) {
            if (!to.isAdded()) {
                transaction.hide(from).add(R.id.content, to).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }
    private void startUpgrade(String downloadUrl) {
        if(TextUtils.isEmpty(downloadUrl)){
            return;
        }
        if (downloadUrl.endsWith(".apk")){
            AllenVersionChecker
                    .getInstance()
                    .downloadOnly(UIData.create().setDownloadUrl(downloadUrl))
                    .setDirectDownload(true)
                    .setForceRedownload(true)
                    .setCustomDownloadingDialogListener(new CustomDownloadingDialogListener() {
                        @Override
                        public Dialog getCustomDownloadingDialog(Context context, int progress, UIData versionBundle) {
//                            UpgradeDialog downloadDialog=new UpgradeDialog(context);
                            return null;
                        }
                        @Override
                        public void updateUI(Dialog dialog, int progress, UIData versionBundle) {
//                            ProgressBar progressBar = dialog.findViewById(R.id.pb_upgrade);
//                            progressBar.setProgress(progress);
                        }
                    })
                    .executeMission(this);
        }else if(downloadUrl.contains(".html")){
//            IntentUtils.startOutWebActivity(this,downloadUrl);
        }
    }
//    public void setMsgDotView(){
//        if (badgeView==null)
//            badgeView= new BadgeView(this,vFakeUser);
//        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
//        badgeView.setTextColor(getResources().getColor(R.color.text_white));
//        badgeView.setBadgeBackgroundColor(Color.RED);
//        badgeView.setTextSize(6);
//        int width=CommonUtil.dip2px(this,8);
//        badgeView.setWidth(width);
//        badgeView.setHeight(width);
//        badgeView.setBadgeMargin(CommonUtil.dip2px(this,20), CommonUtil.dip2px(this,5)); //各边间隔
//        if (getPref().getBoolean(PrefUtil.MSGISNEW,false)) {
//            badgeView.show();
//        }else {
//            badgeView.hide();
//        }
//    }
    class MainReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, final Intent intent) {
            switch (intent.getAction()){
            }
        }
    }
}
