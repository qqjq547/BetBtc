package com.betbtc.app.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.betbtc.app.tools.AppManager;
import com.betbtc.app.tools.CommonUtil;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private Integer orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        AppManager.addActivity(this);
        initViewAndData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        if (orientation != null) {
            setRequestedOrientation(orientation);
        }
        ButterKnife.bind(this);
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.removeActivity(this);
    }

    @Override
    public void onBackPressed() {
        if (CommonUtil.isShowKeyBoard(this)) {
            CommonUtil.hideInput(this, getCurrentFocus());
            return;
        }
        super.onBackPressed();
    }

    public abstract int getLayout();

    public abstract void initViewAndData();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (CommonUtil.isShouldHideInput(v, ev)) {
                if (CommonUtil.hideInputMethod(this, v)) {
//                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setStatusbar(int bgColor, boolean darkFont) {
        if (immersionBar == null) {
            immersionBar = ImmersionBar.with(this);
        }
        immersionBar.statusBarColor(bgColor)
                .statusBarDarkFont(darkFont)
                .fitsSystemWindows(true)
                .init();
    }
}
