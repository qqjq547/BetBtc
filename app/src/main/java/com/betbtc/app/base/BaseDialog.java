package com.betbtc.app.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.betbtc.app.R;

import butterknife.ButterKnife;

public abstract class BaseDialog extends DialogFragment {
    public interface OnDismissListener{
        void onDismiss();
    }
    private OnDismissListener onDismissListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog=new Dialog(getContext(),R.style.dialog_bottom_full);
        dialog.setContentView(getLayout());
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        View view = View.inflate(getContext(), getLayout(), null);
        ButterKnife.bind(this, view);
        window.setContentView(view);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        initViewAndData();
    }
    public BaseDialog setGravity(int gravity) {
        Window window = getDialog().getWindow();
        window.setGravity(gravity);
        return this;
    }

    public BaseDialog setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }
    public BaseDialog setFillWidth(boolean fillWidth){
        if (fillWidth){
            getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }else {
            DisplayMetrics dm = new DisplayMetrics();
            getDialog().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
            getDialog().getWindow().setLayout((int) (dm.widthPixels * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
        }
        return this;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener!=null){
            onDismissListener.onDismiss();
        }
    }

    public void show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
    }

    public abstract int getLayout();

    public abstract void initViewAndData();

}
