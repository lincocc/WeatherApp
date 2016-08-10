package com.demo.weatherapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.weatherapp.adapter.DailyWeatherAdapter;
import com.demo.weatherapp.common.Utils;
import com.demo.weatherapp.dummy.DummyContent.DummyItem;
import com.demo.weatherapp.network.RetrofitSingleton;
import com.demo.weatherapp.network.WeatherList.WeatherBean;

import java.util.ArrayList;

import rx.Subscriber;

public class MainFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mAdapter = new DailyWeatherAdapter(new ArrayList<WeatherBean.DailyForecastBean>());
            recyclerView.setAdapter(mAdapter);
            recyclerView.setBackgroundColor(Utils.getCurrentHourColor());
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadWeatherDate("shenzhen");
    }

    private void loadWeatherDate(String city) {
        RetrofitSingleton.getInstance().fetchWeather(city).subscribe(
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
                mAdapter.updateSource(weather.dailyForecast);
            }
        });
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
