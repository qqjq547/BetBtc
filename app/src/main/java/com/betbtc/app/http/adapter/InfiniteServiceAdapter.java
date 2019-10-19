package com.betbtc.app.http.adapter;


import com.betbtc.app.http.retrofit.SslSocketFactory;
import com.betbtc.app.tools.Constant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class InfiniteServiceAdapter implements IServiceAdapter {
    private static final int TIME_OUT = 15;
    private static InfiniteServiceAdapter INSTANCE = null;
    private OkHttpClient infiniteOkHttpClient;


    private InfiniteServiceAdapter() {

    }

    public static InfiniteServiceAdapter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InfiniteServiceAdapter();
        }
        return INSTANCE;
    }


    @Override
    public OkHttpClient getOkHttpClient() {
        if (infiniteOkHttpClient != null) {
            return infiniteOkHttpClient;
        }
        infiniteOkHttpClient = buildInfiniteHttpClient(null);
        return infiniteOkHttpClient;
    }

    @Override
    public void refreshHeaders(Map<String, String> paramMap) {
        infiniteOkHttpClient = buildInfiniteHttpClient(paramMap);
    }

    public OkHttpClient buildInfiniteHttpClient(Map<String, String> paramMap) {
        SslSocketFactory.SSLParams sslParams = SslSocketFactory.getSslSocketFactory();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new AddHeaderInterceptor(getInfiniteHeader(paramMap)))
//                .addInterceptor(new HttpLoggingInterceptor())
                .hostnameVerifier((hostname, session) -> true);
        if (Constant.isDebug) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logInterceptor);
        }
        return builder.build();
    }

    /**
     * Infinite 服务 header参数
     *
     * @param paramMap
     * @return
     */
    public Map<String, String> getInfiniteHeader(Map<String, String> paramMap) {
        Map<String, String> headers = new HashMap<>();

//        TreeMap<String, Object> treeMap = new TreeMap<>();
//        treeMap.put(Constants.DEVICE_ID, DeviceUtils.getAndroidID());
//        treeMap.put(Constants.OS_TYPE, Constants.OS_TYPE_VALUE);
//        treeMap.put("timestamp", System.currentTimeMillis());
//        treeMap.put("version", PackageUtils.getVersion(AppManager.getsApplication()));
//
//
//        Set<String> keys = treeMap.keySet();
//        //构造正确的url
//        String signUrl = "";
//        for (String key : keys) {
//            signUrl += key + "=" + treeMap.get(key) + "&";
//            headers.put(key, treeMap.get(key).toString());
//        }
//
//        String sign = MD5Util.getMd5(signUrl.substring(0, signUrl.length() - 1) + "global");
//
//        headers.put(Constants.APP_TYPE, Constants.APP_TYPE_VALUE);
//        headers.put("sign", sign);
//        //拼一个User-Agent，主要是适配我们自己服务器，让它知道我们是手机客户端。。。
//        headers.put("User-Agent", System.getProperty("http.agent") + " android mobile");
//
//        if (paramMap != null) {
//            Iterator<Map.Entry<String, String>> entries = paramMap.entrySet().iterator();
//            while (entries.hasNext()) {
//                Map.Entry<String, String> entry = entries.next();
//                headers.put(entry.getKey(), entry.getValue());
//            }
//        }
        return headers;
    }

}
