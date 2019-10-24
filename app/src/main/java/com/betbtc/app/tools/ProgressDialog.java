package com.betbtc.app.tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.betbtc.app.R;


public class ProgressDialog extends Dialog {

    private static ProgressDialog dialog;

    public ProgressDialog(Context context) {
        super(context);
    }

    public ProgressDialog(Context context, int theme) {
        super(context, theme);
    }
    public static void start() {
        if (dialog!=null) {
            if (dialog.isShowing()){
                dialog.cancel();
            }
            dialog=null;
        }
        dialog = new ProgressDialog(AppManager.currentActivity(), R.style.dialog_progress);
        dialog.setTitle("");
        dialog.setContentView(R.layout.dialog_progress);
        // 按返回键是否取消
        dialog.setCancelable(false);
        // 监听返回键处理
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.show();
    }
    public static void stop(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

}