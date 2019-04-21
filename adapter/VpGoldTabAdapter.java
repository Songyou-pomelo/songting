package com.example.greeknews.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.bean.GoldShowBean;

import java.util.ArrayList;

public class VpGoldTabAdapter extends FragmentStatePagerAdapter {

    ArrayList<BaseFragment> list;
    ArrayList<GoldShowBean> goldShowBeans;
    ArrayList<String> newTitle = new ArrayList<>();

    public VpGoldTabAdapter(FragmentManager fm, ArrayList<BaseFragment> list, ArrayList<GoldShowBean> goldShowBeans) {
        super(fm);
        this.list = list;
        this.goldShowBeans = goldShowBeans;

        for (int i = 0; i < goldShowBeans.size(); i++) {
            GoldShowBean bean = goldShowBeans.get(i);
            if (bean.isChecked){
                newTitle.add(bean.title);
            }
        }
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
        return newTitle.get(position);
    }


}
