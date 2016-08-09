package com.demo.weatherapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.demo.weatherapp.MainFragment;


public class MainViewPagerAdapter extends FragmentPagerAdapter{

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(1);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
