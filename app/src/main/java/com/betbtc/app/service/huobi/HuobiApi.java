package com.betbtc.app.service.huobi;

import com.betbtc.app.http.response.HuobiResponse;
import com.betbtc.app.service.huobi.model.KLine;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HuobiApi {

    @GET("market/history/kline")
    Observable<HuobiResponse<List<KLine>>> getHistoryKline(
            @Query("symbol") String symbol,
            @Query("period") String period,
            @Query("size") int size
    );



}
