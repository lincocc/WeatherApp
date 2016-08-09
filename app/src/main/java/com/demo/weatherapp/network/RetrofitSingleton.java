package com.demo.weatherapp.network;

import com.demo.weatherapp.BuildConfig;
import com.demo.weatherapp.common.Constance;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zk on 2015/12/16.
 * update by hugo thanks for brucezz
 */
public class RetrofitSingleton {

    private static WeatherApi apiService = null;
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    private void init() {
        initOkHttp();
        initRetrofit();
        apiService = retrofit.create(WeatherApi.class);
    }

    private RetrofitSingleton() {
        init();
    }

    public static RetrofitSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitSingleton INSTANCE = new RetrofitSingleton();
    }

    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        // 缓存 http://www.jianshu.com/p/93153b34310e
//        File cacheFile = new File(BaseApplication.cacheDir,"/NetCache");
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
//        Interceptor cacheInterceptor = chain -> {
//            Request request = chain.request();
//            if (!Util.isNetworkConnected(BaseApplication.getmAppContext())) {
//                request = request.newBuilder()
//                    .cacheControl(CacheControl.FORCE_CACHE)
//                    .build();
//            }
//            Response response = chain.proceed(request);
//            if (Util.isNetworkConnected(BaseApplication.getmAppContext())) {
//                int maxAge = 0;
//                // 有网络时 设置缓存超时时间0个小时
//                response.newBuilder()
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .build();
//            } else {
//                // 无网络时，设置超时为4周
//                int maxStale = 60 * 60 * 24 * 28;
//                response.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                    .build();
//            }
//            return response;
//        };
//        builder.cache(cache).addInterceptor(cacheInterceptor);
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    private static void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApi.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

//    public static void disposeFailureInfo(Throwable t, Context context, View view) {
//        if (t.toString().contains("GaiException") || t.toString().contains("SocketTimeoutException") ||
//            t.toString().contains("UnknownHostException")) {
//            Snackbar.make(view, "网络不好,~( ´•︵•` )~", Snackbar.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
//        }
//        PLog.w(t.toString());
//    }

    public Observable<Weather> fetchWeather(String city) {
        return apiService.weatherList(city, Constance.HE_WEATHER_KEY)
                .flatMap(new Func1<WeatherList, Observable<WeatherList>>() {
                    @Override
                    public Observable<WeatherList> call(WeatherList weatherList) {
                        if (weatherList.mHeWeatherList.get(0).status.equals("no more requests")) {
                            return Observable.error(new RuntimeException("API免费次数已用完"));
                        }
                        return Observable.just(weatherList);
                    }
                })
                .map(new Func1<WeatherList, Weather>() {
                    @Override
                    public Weather call(WeatherList weatherList) {
                        return weatherList.mHeWeatherList.get(0);
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    public Observable<VersionAPI> fetchVersion() {
//        return apiService.mVersionAPI(C.API_TOKEN).compose(RxUtils.rxSchedulerHelper());
//    }
}
