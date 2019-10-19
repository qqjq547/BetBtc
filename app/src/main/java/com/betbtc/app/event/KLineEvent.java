package com.betbtc.app.event;

import com.betbtc.app.service.huobi.model.KLine;

import java.io.Serializable;

public class KLineEvent implements Serializable {
    private String ch;
    private long ts;
    private KLine kline;
    private String symbol;

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public KLine getKline() {
        return kline;
    }

    public void setKline(KLine kline) {
        this.kline = kline;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
