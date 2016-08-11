package com.demo.weatherapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.weatherapp.adapter.DailyWeatherAdapter;
import com.demo.weatherapp.adapter.HourlyWeatherAdapter;
import com.demo.weatherapp.adapter.SuggestionAdapter;
import com.demo.weatherapp.common.Constants;
import com.demo.weatherapp.dummy.DummyContent.DummyItem;
import com.demo.weatherapp.network.RetrofitSingleton;
import com.demo.weatherapp.network.WeatherList.WeatherBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class MainFragment extends Fragment {


    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.now_tmp)
    TextView nowTmp;
    @BindView(R.id.max_tmp)
    TextView maxTmp;
    @BindView(R.id.min_tmp)
    TextView minTmp;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.hourly_card)
    CardView hourlyCard;
    @BindView(R.id.daily_card)
    CardView dailyCard;
    @BindView(R.id.suggestion_card)
    CardView suggestionCard;
//    @BindView(R.id.hourly_list)
    RecyclerView hourlyList;
//    @BindView(R.id.daily_list)
    RecyclerView dailyList;
//    @BindView(R.id.suggestion_list)
    RecyclerView suggestionList;

    private OnListFragmentInteractionListener mListener;

    private DailyWeatherAdapter mAdapter;

    public MainFragment() {
    }

    public static MainFragment newInstance(int columnCount) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_main, container, false);

        DailyWeatherAdapter dailyWeatherAdapter = new DailyWeatherAdapter();
        dailyList = (RecyclerView) view.findViewById(R.id.daily_list);
        dailyList.setAdapter(dailyWeatherAdapter);
        HourlyWeatherAdapter hourlyWeatherAdapter = new HourlyWeatherAdapter();
        hourlyList = (RecyclerView) view.findViewById(R.id.hourly_list);
        hourlyList.setAdapter(hourlyWeatherAdapter);
        SuggestionAdapter suggestionAdapter = new SuggestionAdapter();
        suggestionList = (RecyclerView) view.findViewById(R.id.suggestion_list);
        suggestionList.setAdapter(suggestionAdapter);
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            mAdapter = new DailyWeatherAdapter(new ArrayList<WeatherBean.DailyForecastBean>());
//            recyclerView.setAdapter(mAdapter);
////            recyclerView.setBackgroundColor(Utils.getCurrentHourColor());
//        }
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadWeatherDate("shenzhen");
    }

    private void loadWeatherDate(String city) {
        RetrofitSingleton.getInstance().fetchWeather(city).
                share()
                .subscribe(
                new Subscriber<WeatherBean>() {
                    @Override
                    public void onCompleted() {
                        Log.e("aaa", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("aaa", e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherBean weather) {
                        ((DailyWeatherAdapter)dailyList.getAdapter()).updateSource(weather.dailyForecast);
                        ((HourlyWeatherAdapter)hourlyList.getAdapter()).updateSource(weather.hourlyForecast);
                        List<WeatherBean.SuggestionBean> list = new ArrayList<WeatherBean.SuggestionBean>();
                        list.add(weather.suggestion);
                        ((SuggestionAdapter)suggestionList.getAdapter()).updateSource(list);
                        updateNowWeather(weather);
                    }
                });
    }

    private void updateNowWeather(WeatherBean data) {
        img.setImageResource(Constants.ICON_MAP.get(data.now.cond.txt));
        nowTmp.setText(data.now.tmp);
        maxTmp.setText(data.dailyForecast.get(0).tmp.max);
        minTmp.setText(data.dailyForecast.get(0).tmp.min);
        String infoString = String.format("PM2.5:%s, 空气质量%s", data.aqi.city.pm25, data.aqi.city.qlty);
        info.setText(infoString);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
