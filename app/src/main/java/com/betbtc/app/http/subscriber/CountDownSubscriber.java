package com.betbtc.app.http.subscriber;

import io.reactivex.Observer;

/**
 * Created by codeest on 2017/2/23.
 */

public abstract class CountDownSubscriber<T> implements Observer<T> {


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {
        if (t instanceof Integer) {
            int sec = (Integer) t;
        }
    }
}
