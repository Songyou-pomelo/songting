package com.example.greeknews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greeknews.R;
import com.example.greeknews.adapter.RlvDailyNewsAdapter;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.bean.DailyNewsBean;
import com.example.greeknews.ptrsenter.DailyNewsPersnter;
import com.example.greeknews.ptrsenter.ZhihuDailyNewsPersnter;
import com.example.greeknews.view.DailyNewsView;
import com.example.greeknews.view.ZhihuDailyNewsView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyNewsFragment extends BaseFragment<DailyNewsView,DailyNewsPersnter> implements DailyNewsView {

    private static final String TAG = "DailyNewsFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private RlvDailyNewsAdapter mAdapter;
    private ArrayList<DailyNewsBean.StoriesBean> newsList = new ArrayList<>();
    private ArrayList<DailyNewsBean.TopStoriesBean>  banners = new ArrayList<>();

    @Override
    protected DailyNewsPersnter initPresenter() {
        return new DailyNewsPersnter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    public void initView() {
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));


        mAdapter = new RlvDailyNewsAdapter(getContext(),
                newsList, banners);
        mRlv.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        initPresenter().getData();
    }





    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void setData(DailyNewsBean bean) {
        Log.i("TAG","setdata"+bean.getTop_stories().size());
        mAdapter.setData(bean);
    }

}

