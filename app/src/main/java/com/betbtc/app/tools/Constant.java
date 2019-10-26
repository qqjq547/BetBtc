package com.betbtc.app.tools;

import com.betbtc.app.BuildConfig;

/**
 * Created by Administrator on 2017/10/26.
 */

public class Constant {
    /**
     * 是否调试模式
     */
    public static final boolean isDebug = BuildConfig.LOG_DEBUG;

    public static final String COUNTRY_CODE="86";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String ADDRESS="address";
    public static final String MOBILE = "mobile";
    public static final String TYPE = "type";



    public static final String[] DIAL_DATA=new String[]{
            "1","2","3","+10",
            "4","5","6","+50",
            "7","8","9","+200",
            "clear","0","del","max",
    };

    public static final int REQUEST_PICK_PICTURE = 0x11 ;
    public static final String NICKNAME = "nickname";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
}