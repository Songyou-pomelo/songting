package com.example.greeknews.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greeknews.R;
import com.example.greeknews.adapter.VpZhihuAdapter;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.ptrsenter.ZhihuDailyNewsPersnter;
import com.example.greeknews.view.ZhihuDailyNewsView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 宋婷 1808D
 */
public class ZhihuDailyNewsFragment extends BaseFragment<ZhihuDailyNewsView,ZhihuDailyNewsPersnter> implements ZhihuDailyNewsView{

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Integer> title;
    private ArrayList<BaseFragment> fragments;
    private ArrayList<BaseFragment> list;

    @Override
    protected ZhihuDailyNewsPersnter initPresenter() {
        return new ZhihuDailyNewsPersnter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_daily_news;
    }

    public void initView() {

        initTitles();

        initFragments();

        VpZhihuAdapter zhihuAdapter = new VpZhihuAdapter(getChildFragmentManager(), getContext(), list, title);
        vp.setAdapter(zhihuAdapter);
        tab.setupWithViewPager(vp);
    }

    private void initFragments() {

        list = new ArrayList<>();
        list.add(new DailyNewsFragment());//日报
        list.add(new SectionFragment());//专栏
        list.add(new HotFragment()); //最热
    }


    private void initTitles() {
        title = new ArrayList<>();
        title.add(R.string.dailyNews);//日报
        title.add(R.string.sections);//专栏
        title.add(R.string.hot);//最热
    }
}
