package com.betbtc.app.tools;


/**
 * Created by cy on 08/01/17.
 */

public final class UrlManage {

    public final static String WITHDRAW_MANAGER = getRootUrl() + "withdraw-manager/tip";
    public final static String HUOBI = "https://api.huobi.pro/";

    /**
     * 获取infinite域名
     *
     * @return
     */

    public static String getRootUrl() {
        String result = "";
        if (result.endsWith("/")) {
            return result;
        } else {
            return result + "/";
        }
    }

}
