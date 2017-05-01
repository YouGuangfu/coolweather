package com.coolweather.android.gson;

/**
 * Created by Mr.You on 2017/4/28.
 */
public class AQI {
    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
