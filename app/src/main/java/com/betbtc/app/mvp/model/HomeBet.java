package com.betbtc.app.mvp.model;

public class HomeBet {
    int id;
    String title;
    double riseRate;
    int riseTime;
    int riseCoinCount;
    double downRate;
    int downime;
    int downCoinCount;

    public HomeBet(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRiseRate() {
        return riseRate;
    }

    public void setRiseRate(double riseRate) {
        this.riseRate = riseRate;
    }

    public int getRiseTime() {
        return riseTime;
    }

    public void setRiseTime(int riseTime) {
        this.riseTime = riseTime;
    }

    public int getRiseCoinCount() {
        return riseCoinCount;
    }

    public void setRiseCoinCount(int riseCoinCount) {
        this.riseCoinCount = riseCoinCount;
    }

    public double getDownRate() {
        return downRate;
    }

    public void setDownRate(double downRate) {
        this.downRate = downRate;
    }

    public int getDownime() {
        return downime;
    }

    public void setDownime(int downime) {
        this.downime = downime;
    }

    public int getDownCoinCount() {
        return downCoinCount;
    }

    public void setDownCoinCount(int downCoinCount) {
        this.downCoinCount = downCoinCount;
    }
}
