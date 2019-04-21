package com.example.greeknews.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.greeknews.base.BaseFragment;

import java.util.ArrayList;

public class VpZhihuAdapter extends FragmentPagerAdapter {

    Context context;
    ArrayList<BaseFragment> list;
    ArrayList<Integer> title;

    public VpZhihuAdapter(FragmentManager fm, Context context, ArrayList<BaseFragment> list, ArrayList<Integer> title) {
        super(fm);
        this.context = context;
        this.list = list;
        this.title = title;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(title.get(position));
    }
}
