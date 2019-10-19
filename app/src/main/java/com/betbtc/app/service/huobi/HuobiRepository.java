package com.betbtc.app.service.huobi;

import com.betbtc.app.http.adapter.InfiniteServiceAdapter;
import com.betbtc.app.http.response.HuobiResponse;
import com.betbtc.app.http.retrofit.RetrofitClient;
import com.betbtc.app.service.huobi.model.KLine;
import com.betbtc.app.tools.RxUtil;
import com.betbtc.app.tools.UrlManage;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;


public class HuobiRepository {
    private static HuobiRepository instance;

    public static HuobiRepository getInstance() {
        if (instance == null) {
            instance = new HuobiRepository();
        }
        return instance;
    }

    /**
     * K线数据
     * @return
     */
    public Observable<HuobiResponse<List<KLine>>> getHistoryKline(String symbol, String period, int size) {
        return RetrofitClient.getInstance().getApiService(UrlManage.HUOBI, HuobiApi.class, getOkHttpClient())
                .getHistoryKline(symbol,period,size)
                .compose(RxUtil.schedulersTransformer());
    }



    private OkHttpClient getOkHttpClient() {
        return InfiniteServiceAdapter.getInstance().getOkHttpClient();
    }

}
