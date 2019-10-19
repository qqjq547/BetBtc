package com.betbtc.app.http.exception;

import com.betbtc.app.http.response.HttpResponse;

import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ApiErrorOperator<T extends HttpResponse> implements ObservableOperator<T, T> {

    public ApiErrorOperator() {

    }

    @Override
    public Observer<? super T> apply(final Observer<? super T> observer) {
        return new Observer<T>() {
            @Override
            public void onComplete() {
                observer.onComplete();
            }

            @Override
            public void onError(final Throwable e) {
                observer.onError(ExceptionHandle.handleException(e));
            }

            @Override
            public void onSubscribe(Disposable d) {
                observer.onSubscribe(d);
            }

            @Override
            public void onNext(T response) {
                // 动画接口（https://pxdd.tozuuh.com/spscores/）返回的数据不是完全符合标准，没有回code
                if (response.getCode() != 1 && response.getCode() != 0) {
                    observer.onError(new ServiceException(response.getCode(), response.getMessage()));
                    return;
                }

                observer.onNext(response);
            }
        };
    }
}