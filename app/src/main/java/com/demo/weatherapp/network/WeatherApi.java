package com.demo.weatherapp.network;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherApi {
    String HOST = "https://api.heweather.com/x3/";

    //https://api.heweather.com/x3/weather?cityid=城市ID&key=你的认证key
    @GET("weather")
    Observable<WeatherList> weatherList(@Query("city") String city, @Query("key") String key);
}
