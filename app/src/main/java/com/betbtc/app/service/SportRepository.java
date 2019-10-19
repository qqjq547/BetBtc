package com.betbtc.app.service;

import com.betbtc.app.http.adapter.InfiniteServiceAdapter;
import com.betbtc.app.http.response.HttpResponse;
import com.betbtc.app.http.retrofit.RetrofitClient;
import com.betbtc.app.tools.RxUtil;
import com.betbtc.app.tools.UrlManage;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;

/**
 * @author Levi
 * @date 2019-05-25
 * @desc
 */
public class SportRepository {
    private static SportRepository instance;

    public static SportRepository getInstance() {
        if (instance == null) {
            instance = new SportRepository();
        }
        return instance;
    }

    /**
     * 聊天室 用户发送消息的token
     *
     * @return
     */
    public Observable<HttpResponse> getUserSendMessageToken() {
        return RetrofitClient.getInstance().getApiService(UrlManage.getRootUrl(), SportApi.class, getOkHttpClient())
                .getUserSendMessageToken()
                .compose(RxUtil.exceptionIoTransformer());
    }



    private OkHttpClient getOkHttpClient() {
        return InfiniteServiceAdapter.getInstance().getOkHttpClient();
    }

}
