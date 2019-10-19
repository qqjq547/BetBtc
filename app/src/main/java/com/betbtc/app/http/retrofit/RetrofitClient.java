package com.betbtc.app.http.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description 网络请求工具类
 * @anthor ryan
 * @Date 2019/3/26
 */
public class RetrofitClient {
    private static RetrofitClient instance = null;

    private static final int CONNECT_TIMEOUT = 60;
    protected OkHttpClient mOkHttpClient;


    public static RetrofitClient getInstance() {
        synchronized (RetrofitClient.class) {
            if (instance == null) {
                instance = new RetrofitClient();
            }
        }
        return instance;
    }

    private RetrofitClient() {
        initOkHttp();
    }

    /**
     * init OkHttpClient   通常根据业务调整
     */
    private void initOkHttp() {
        SslSocketFactory.SSLParams sslParams = SslSocketFactory.getSslSocketFactory();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .hostnameVerifier((hostname, session) -> true)
                .addInterceptor(new HttpLoggingInterceptor());

        mOkHttpClient = builder.build();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * 设置自定义的 OkHttpClient
     *
     * @param okHttpClient
     */
    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    /**
     * @param baseUrl
     * @param clz     接口
     * @return
     */
    public <T> T getApiService(String baseUrl, Class<T> clz) {
        return getApiService(baseUrl, clz, null);
    }

    /**
     * @param baseUrl
     * @param clz
     * @param client
     * @return
     */
    public <T> T getApiService(String baseUrl, Class<T> clz, OkHttpClient client) {
        if (client == null) {
            client = mOkHttpClient;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }


}