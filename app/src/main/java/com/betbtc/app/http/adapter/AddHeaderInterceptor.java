package com.betbtc.app.http.adapter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddHeaderInterceptor implements Interceptor {
    private Map<String, String> headers;

    public AddHeaderInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
//                LogHelper.d("----headerKey: " + headerKey + "----headerVla: " + headers.get(headerKey));
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
        }
        //请求信息
        return chain.proceed(builder.build());
    }
}