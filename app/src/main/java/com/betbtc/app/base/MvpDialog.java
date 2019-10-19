package com.betbtc.app.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class MvpDialog<P extends BasePresenter> extends BaseDialog {
    protected P mvpPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPresenter = createPresenter();
    }
    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mvpPresenter!=null){
            mvpPresenter.detachView();
        }
    }
}
