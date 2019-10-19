package com.betbtc.app.base;


import android.os.Bundle;

import androidx.annotation.Nullable;


public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P mvpPresenter;
    protected abstract P createPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPresenter = createPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
