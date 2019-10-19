package com.betbtc.app.service.huobi.model;

public class SocketMsg {

    /**
     * ch : market.btcusdt.kline.60min
     * ts : 1570588077733
     * tick : {"id":1570586400,"open":8161.54,"close":8143.57,"low":8136,"high":8164.97,"amount":376.8080115549196,"vol":3071045.5517766103,"count":2797}
     * symbol : btcusdt
     */

    private String ch;
    private long ts;
    private KLine tick;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public KLine getTick() {
        return tick;
    }

    public void setTick(KLine tick) {
        this.tick = tick;
    }
}
