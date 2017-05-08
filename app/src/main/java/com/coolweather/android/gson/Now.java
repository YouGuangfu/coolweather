package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mr.You on 2017/4/28.
 */
public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("wind")
    public Wind wind;

    public class Wind {
        @SerializedName("dir")
        public String direction;

        @SerializedName("sc")
        public String sc;

    }

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }

}
