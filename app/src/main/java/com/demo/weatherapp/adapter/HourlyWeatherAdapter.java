package com.demo.weatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.weatherapp.R;
import com.demo.weatherapp.common.Constants;
import com.demo.weatherapp.common.Utils;
import com.demo.weatherapp.network.WeatherList.WeatherBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder> {

    private final List<WeatherBean.HourlyForecastBean> mValues;

    public void updateSource(List<WeatherBean.HourlyForecastBean> date) {
        mValues.clear();
        mValues.addAll(date);
        notifyDataSetChanged();
    }

    public HourlyWeatherAdapter(List<WeatherBean.HourlyForecastBean> items) {
        mValues = items;
    }

    public HourlyWeatherAdapter() {
        mValues = new ArrayList<>(0);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_forecast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        WeatherBean.HourlyForecastBean mItem = mValues.get(position);
        holder.time.setText(mItem.date.split(" ")[1]);
        String hum = String.format("%s%%", mItem.hum);
        holder.hum.setText(hum);
        String tmp = String.format("%s%%", mItem.tmp);
        holder.tmp.setText(tmp);
        String wind = String.format("%s", mItem.wind.sc);
        holder.wind.setText(wind);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.hum)
        TextView hum;
        @BindView(R.id.tmp)
        TextView tmp;
        @BindView(R.id.wind)
        TextView wind;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
