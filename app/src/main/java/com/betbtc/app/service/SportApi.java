package com.betbtc.app.service;

import com.betbtc.app.http.response.HttpResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SportApi {

    @GET("/api/gl/chat/token")
    Observable<HttpResponse> getUserSendMessageToken();



}
