package com.betbtc.app.http.adapter;

import java.util.Map;

import okhttp3.OkHttpClient;

public interface IServiceAdapter {

    OkHttpClient getOkHttpClient();

    void refreshHeaders(Map<String, String> paramMap);

}
