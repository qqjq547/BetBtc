package com.betbtc.app.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.tools.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends MvpActivity {
    @BindView(R.id.lottie_view)
    LottieAnimationView lottieView;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.tv_welcome)
    TextView tvWelcome;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    public void initViewAndData() {
        lottieView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tvWelcome.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.VISIBLE);
                startButtonAnim(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        startButtonAnim(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        },1000);

    }

    public void startButtonAnim(boolean show) {
        AnimatorSet animatorSet = new AnimatorSet();
        if (show) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(btnStart, "translationY", CommonUtil.dp2px(this, 90), 0f);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(btnStart, "alpha", 0.5f, 1.0f);
            ObjectAnimator animator3 = ObjectAnimator.ofFloat(tvWelcome, "translationY", -CommonUtil.dp2px(this, 90), 0f);
            ObjectAnimator animator4 = ObjectAnimator.ofFloat(tvWelcome, "alpha", 0.5f, 1.0f);
            animatorSet.play(animator1).with(animator2).with(animator3).with(animator4);
        } else {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(btnStart, "translationY", 0f, CommonUtil.dp2px(this, 90));
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(btnStart, "alpha", 1.0f, 0.5f);
            ObjectAnimator animator3 = ObjectAnimator.ofFloat(tvWelcome, "translationY", 0f, -CommonUtil.dp2px(this, 90));
            ObjectAnimator animator4 = ObjectAnimator.ofFloat(tvWelcome, "alpha", 1.0f, 0.5f);
            animatorSet.play(animator1).with(animator2).with(animator3).with(animator4);
        }
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
