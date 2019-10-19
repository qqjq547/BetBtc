package com.betbtc.app.http.exception;

import android.text.TextUtils;

public class ServiceException extends Exception {
    private int code;
    private String message;

    private ServiceException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        if (TextUtils.isEmpty(message)) {
            return "未知错误";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
