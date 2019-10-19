package com.betbtc.app.http.exception;


import android.net.ParseException;

import com.betbtc.app.tools.LogUtil;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;


public class ApiException extends Exception {
    private int statusCode = 0;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, int statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void printStackTrace() {
        LogUtil.e(statusCode + "," + getMessage());
    }


    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int NOT_ALLOW = 405;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int SERVICE_UNAVAILABLE = 503;

    public static ServiceException handleException(Throwable e) {
        if (e != null) {
            e.printStackTrace();
        }
        ServiceException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            String message = null;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    message = "操作未授权";
                    break;
                case FORBIDDEN:
                    message = "请求被拒绝";
                    break;
                case NOT_FOUND:
                    message = "服务器不可用";
                    break;
                case REQUEST_TIMEOUT:
                    message = "服务器执行超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    message = "服务器内部错误";
                    break;
                case SERVICE_UNAVAILABLE:
                    message = "服务器不可用";
                    break;
                case NOT_ALLOW:
                    message = "HTTP 405 not allowed";
                    break;
                default:
                    message = "网络错误";
                    break;
            }
            ex = new ServiceException(httpException.code(), message);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            ex = new ServiceException(ExceptionHandle.ERROR.PARSE_ERROR, "解析错误");
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ServiceException(ExceptionHandle.ERROR.NETWORD_ERROR, "连接失败");
            return ex;
        } else if (e instanceof javax.net.ssl.SSLException) {
            ex = new ServiceException(ExceptionHandle.ERROR.SSL_ERROR, "证书验证失败");
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ServiceException(ExceptionHandle.ERROR.TIMEOUT_ERROR, "连接超时");
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ServiceException(ExceptionHandle.ERROR.TIMEOUT_ERROR, "连接超时");
            return ex;
        } else if (e instanceof java.net.UnknownHostException) {
            ex = new ServiceException(ExceptionHandle.ERROR.TIMEOUT_ERROR, "网络异常、请检查网络！");
            return ex;
        } else {
            ex = new ServiceException(ExceptionHandle.ERROR.UNKNOWN, "未知错误");
        }
        return ex;
    }
    public class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;
    }
}

