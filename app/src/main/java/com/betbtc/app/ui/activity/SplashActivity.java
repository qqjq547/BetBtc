package com.betbtc.app.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.tools.PrefUtil;
import com.betbtc.app.ui.activity.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends MvpActivity {


    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViewAndData() {
        startAnim();
    }
    public void startAnim() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(ivIcon, "scaleX", 0f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(ivIcon, "scaleY", 0f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(tvName, "translationY", CommonUtil.dp2px(this, 50),0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(tvName, "alpha", 0.5f, 1.0f);
        animatorSet.play(animator1).with(animator2).with(animator3).with(animator4);
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (PrefUtil.getInstance().getBoolean(PrefUtil.ISFRIST, true)) {
                            startActivity(new Intent(SplashActivity.this, StartActivity.class));
                            PrefUtil.getInstance().setBoolean(PrefUtil.ISFRIST, false);
                        } else {
                            if (TextUtils.isEmpty(PrefUtil.getInstance().getString(PrefUtil.USER_TOKEN,""))){
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            }else {
                                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            }
                        }
                    }
                }, 1000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

}
