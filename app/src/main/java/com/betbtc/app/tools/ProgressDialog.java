package com.betbtc.app.tools;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.betbtc.app.R;


public class ProgressDialog extends Dialog {

    private static ProgressDialog dialog;

    public ProgressDialog(Context context) {
        super(context);
    }

    public ProgressDialog(Context context, int theme) {
        super(context, theme);
    }


    /**
     * 当窗口焦点改变时调用
     */
    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = findViewById(R.id.spinnerImageView);
        // 获取ImageView上的动画背景
        AnimationDrawable spinner = (AnimationDrawable) imageView.getDrawable();
        // 开始动画
        spinner.start();

    }

    /**
     * 给Dialog设置提示信息
     *
     * @param message
     */
    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
    }

    /**
     * 弹出自定义ProgressDialog
     *
     * @param message        提示
     * @param cancelable     是否按返回键取消
     * @param cancelListener 按下返回键监听
     * @return
     */
    public static void start(CharSequence message, boolean cancelable, OnCancelListener cancelListener) {
        if (dialog!=null) {
            if (dialog.isShowing()){
                dialog.cancel();
            }
            dialog=null;
        }
        dialog = new ProgressDialog(AppManager.currentActivity(), R.style.dialog_progress);
        dialog.setTitle("");
        dialog.setContentView(R.layout.dialog_progress);
        if (TextUtils.isEmpty(message)) {
            dialog.findViewById(R.id.message).setVisibility(View.GONE);
        } else {
            TextView txt = dialog.findViewById(R.id.message);
            txt.setText(message);
        }
        // 按返回键是否取消
        dialog.setCancelable(cancelable);
        // 监听返回键处理
        dialog.setOnCancelListener(cancelListener);
        // 设置居中
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.show();
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
        dialog.findViewById(R.id.message).setVisibility(View.GONE);
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