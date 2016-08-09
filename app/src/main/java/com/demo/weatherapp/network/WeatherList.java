package com.demo.weatherapp.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherList {

    @SerializedName("HeWeather data service 3.0") @Expose
    public List<Weather> mHeWeatherList
            = new ArrayList<>();
}
