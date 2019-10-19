package com.betbtc.app.http.retrofit;

import android.net.ParseException;

import com.betbtc.app.R;
import com.betbtc.app.http.exception.ApiException;
import com.betbtc.app.tools.AppManager;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public abstract class ApiCallback<T> implements Observer<T> {

    public abstract void onSuccess(T response);

    public abstract void onError(ApiException exception);

    public abstract void onFinish();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ApiException) {//返回的过滤封装好的ApiException
            ApiException apiException = (ApiException) e;
            onError(apiException);
        } else {//返回原始的Throwable
            String errMsg = "";
            if (e instanceof ConnectException) {
                errMsg = AppManager.getsApplication().getString(R.string.network_error_and_late);
            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException) {
                errMsg =AppManager.getsApplication().getString(R.string.parse_error_and_late);
            } else if (e instanceof HttpException) {
                errMsg = AppManager.getsApplication().getString(R.string.http_error_and_late);
            } else if (e instanceof SocketTimeoutException) {
                errMsg = AppManager.getsApplication().getString(R.string.timeout_and_late);
            } else {
                errMsg = AppManager.getsApplication().getString(R.string.timeout_and_late);
            }
            onError(new ApiException(
                    errMsg));
        }
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
