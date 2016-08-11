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

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder> {

    private final List<WeatherBean.SuggestionBean> mValues;

    public void updateSource(List<WeatherBean.SuggestionBean> date) {
        mValues.clear();
        mValues.addAll(date);
        notifyDataSetChanged();
    }

    public SuggestionAdapter(List<WeatherBean.SuggestionBean> items) {
        mValues = items;
    }
    public SuggestionAdapter() {
        mValues = new ArrayList<>(0);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tip_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        WeatherBean.SuggestionBean mItem = mValues.get(position);

        holder.img.setImageResource(R.drawable.icon_cloth);
        holder.brf.setText(mItem.comf.brf);
        holder.detail.setText(mItem.comf.txt);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.brf)
        TextView brf;
        @BindView(R.id.detail)
        TextView detail;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
