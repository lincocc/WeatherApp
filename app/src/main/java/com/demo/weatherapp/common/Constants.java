package com.demo.weatherapp.common;

import com.demo.weatherapp.R;

import java.util.HashMap;

public class Constants {
    public static final String HE_WEATHER_KEY = "3c67d53fce3b40838d30f7b002280a59";

    public static final HashMap<String, Integer> ICON_MAP = new HashMap<>();
    static {
        ICON_MAP.put("未知", R.mipmap.none);
        ICON_MAP.put("晴", R.mipmap.type_one_sunny);
        ICON_MAP.put("阴", R.mipmap.type_one_cloudy);
        ICON_MAP.put("多云", R.mipmap.type_one_cloudy);
        ICON_MAP.put("少云", R.mipmap.type_one_cloudy);
        ICON_MAP.put("晴间多云", R.mipmap.type_one_cloudytosunny);
        ICON_MAP.put("小雨", R.mipmap.type_one_light_rain);
        ICON_MAP.put("中雨", R.mipmap.type_one_light_rain);
        ICON_MAP.put("大雨", R.mipmap.type_one_heavy_rain);
        ICON_MAP.put("暴雨", R.mipmap.type_one_heavy_rain);
        ICON_MAP.put("阵雨", R.mipmap.type_one_thunderstorm);
        ICON_MAP.put("雷阵雨", R.mipmap.type_one_thunder_rain);
        ICON_MAP.put("霾", R.mipmap.type_one_fog);
        ICON_MAP.put("雾", R.mipmap.type_one_fog);
    }

    public static final HashMap<String, Integer> ICON_MAP_2 = new HashMap<>();
    static {
        ICON_MAP_2.put("未知", R.mipmap.none);
        ICON_MAP_2.put("晴", R.mipmap.type_two_sunny);
        ICON_MAP_2.put("阴", R.mipmap.type_two_cloudy);
        ICON_MAP_2.put("多云", R.mipmap.type_two_cloudy);
        ICON_MAP_2.put("少云", R.mipmap.type_two_cloudy);
        ICON_MAP_2.put("晴间多云", R.mipmap.type_two_cloudytosunny);
        ICON_MAP_2.put("小雨", R.mipmap.type_two_light_rain);
        ICON_MAP_2.put("中雨", R.mipmap.type_two_rain);
        ICON_MAP_2.put("大雨", R.mipmap.type_two_rain);
        ICON_MAP_2.put("暴雨", R.mipmap.type_two_rain);
        ICON_MAP_2.put("阵雨", R.mipmap.type_two_rain);
        ICON_MAP_2.put("雷阵雨", R.mipmap.type_two_thunderstorm);
        ICON_MAP_2.put("霾", R.mipmap.type_two_haze);
        ICON_MAP_2.put("雾", R.mipmap.type_two_fog);
        ICON_MAP_2.put("雨夹雪", R.mipmap.type_two_snowrain);
    }

    public static final HashMap<String, Integer> SUGGESTION_ICON_MAP = new HashMap<>();
    static {
        SUGGESTION_ICON_MAP.put("comf", R.drawable.icon_cloth);
        SUGGESTION_ICON_MAP.put("cw", R.drawable.icon_cloth);
        SUGGESTION_ICON_MAP.put("drsg", R.drawable.icon_cloth);
        SUGGESTION_ICON_MAP.put("flu", R.drawable.icon_flu);
        SUGGESTION_ICON_MAP.put("sport", R.drawable.icon_sport);
        SUGGESTION_ICON_MAP.put("trav", R.drawable.icon_travel);
    }
}
