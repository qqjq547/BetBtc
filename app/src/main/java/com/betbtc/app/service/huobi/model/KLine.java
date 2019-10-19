package com.betbtc.app.service.huobi.model;


import com.icechao.klinelib.entity.KLineEntity;

public class KLine extends KLineEntity {

    /**
     * id : 1570500000
     * open : 8281.46
     * close : 8303.28
     * low : 8265.3
     * high : 8324.39
     * amount : 1072.4907650772523
     * vol : 8899692.62677731
     * count : 6975
     */

    private long id;
    private double open;
    private double close;
    private double low;
    private double high;
    private double amount;
    private double vol;
    private int count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public Long getDate() {
        return id*1000;
    }

    @Override
    public float getOpenPrice() {
        return (float) open;
    }

    @Override
    public float getHighPrice() {
        return (float)high;
    }

    @Override
    public float getLowPrice() {
        return (float)low;
    }

    @Override
    public float getClosePrice() {
        return (float)close;
    }

    @Override
    public float getVolume() {
        return (float)amount;
    }
}
