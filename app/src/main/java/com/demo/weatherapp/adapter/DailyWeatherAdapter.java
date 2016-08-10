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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder> {

    private final List<WeatherBean.DailyForecastBean> mValues;


    public void updateSource(List<WeatherBean.DailyForecastBean> date) {
        mValues.clear();
        mValues.addAll(date);
        notifyDataSetChanged();
    }

    public DailyWeatherAdapter(List<WeatherBean.DailyForecastBean> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_forecast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        WeatherBean.DailyForecastBean mItem = mValues.get(position);
        holder.img.setImageResource(Constants.ICON_MAP.get(mItem.cond.txtD));
        String dayAndTmp = String.format("%s  %s ℃ ~ %s ℃", Utils.dayForWeek(mItem.date),
                mItem.tmp.min, mItem.tmp.max);
        holder.dayTmp.setText(dayAndTmp);
        String info = String.format("湿度 %s%%, 降雨概率 %s%%，%s，%s", mItem.hum, mItem.pop
                , mItem.wind.sc, mItem.wind.dir);
        holder.info.setText(info);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.day_tmp)
        TextView dayTmp;
        @BindView(R.id.info)
        TextView info;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
