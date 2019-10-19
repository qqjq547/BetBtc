package com.betbtc.app.ui.dialog;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.betbtc.app.R;
import com.betbtc.app.base.BaseDialog;
import com.betbtc.app.tools.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

public class CommonDialog extends BaseDialog {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    Builder builder;

    private CommonDialog(Builder builder) {
        super();
        this.builder = builder;
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_common;
    }

    @Override
    public void initViewAndData() {
        if (TextUtils.isEmpty(builder.title)) {
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(builder.title);
        }
        if (TextUtils.isEmpty(builder.message)) {
            tvMessage.setVisibility(View.GONE);
        }else {
            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText(builder.message);
        }
        if (TextUtils.isEmpty(builder.cancel)){
            tvCancel.setVisibility(View.GONE);
        }else {
            tvCancel.setVisibility(View.VISIBLE);
            tvCancel.setText(builder.cancel);
            tvCancel.setOnClickListener(v -> {
                dismiss();
                if (builder.onCancelListener!=null){
                    builder.onCancelListener.onClick(v);
                }
            });
        }
        if (TextUtils.isEmpty(builder.confirm)){
            tvConfirm.setVisibility(View.GONE);
        }else {
            tvConfirm.setVisibility(View.VISIBLE);
            tvConfirm.setText(builder.confirm);
            tvConfirm.setOnClickListener(v -> {
                dismiss();
                if (builder.onConfirmListener!=null){
                    builder.onConfirmListener.onClick(v);
                }
            });
        }
        if (TextUtils.isEmpty(builder.cancel)||TextUtils.isEmpty(builder.confirm)){
            vLine.setVisibility(View.GONE);
        }else {
            vLine.setVisibility(View.VISIBLE);
        }
        setCancelable(builder.cancelAble);
        setOnDismissListener(builder.onDismissListener);
        setGravity(Gravity.CENTER);
        setFillWidth(false);

        tvMessage.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                tvMessage.getViewTreeObserver().removeOnPreDrawListener(this);
                if(tvMessage.getLineCount()==1){
                    tvMessage.setGravity(Gravity.CENTER);
                }
                return false;
            }
        });

    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    @OnClick({R.id.tv_cancel, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                break;
            case R.id.tv_confirm:
                break;
        }
    }

    public static class Builder {
        private String title;
        private String message;
        private String cancel;
        private String confirm;
        private View.OnClickListener onCancelListener;
        private View.OnClickListener onConfirmListener;
        private boolean cancelAble = true;
        private OnDismissListener onDismissListener;

        public Builder(){
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setTitle(int resId) {
            this.title = AppManager.getsApplication().getString(resId);
            return this;
        }


        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        public Builder setMessage(int resId) {
            this.message = AppManager.getsApplication().getString(resId);
            return this;
        }


        public Builder setCancelBtn(String cancel,View.OnClickListener onCancelListener) {
            this.cancel = cancel;
            this.onCancelListener = onCancelListener;
            return this;
        }
        public Builder setCancelBtn(int resId,View.OnClickListener onCancelListener) {
            this.confirm = AppManager.getsApplication().getString(resId);
            this.onCancelListener = onCancelListener;
            return this;
        }

        public Builder setConfirmBtn(String confirm,View.OnClickListener onConfirmListener) {
            this.confirm=confirm;
            this.onConfirmListener = onConfirmListener;
            return this;
        }
        public Builder setConfirmBtn(int resId,View.OnClickListener onConfirmListener) {
            this.confirm=AppManager.getsApplication().getString(resId);
            this.onConfirmListener = onConfirmListener;
            return this;
        }


        public Builder setCancelAble(boolean cancelAble) {
            this.cancelAble = cancelAble;
            return this;
        }

        public void setOnDismissListener(OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
        }

        public CommonDialog build(){
            return new CommonDialog(this);
        }
    }
}
