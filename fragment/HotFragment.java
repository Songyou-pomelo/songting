package com.example.greeknews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.ptrsenter.ZhihuDailyNewsPersnter;
import com.example.greeknews.view.ZhihuDailyNewsView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment<ZhihuDailyNewsView,ZhihuDailyNewsPersnter> implements ZhihuDailyNewsView {


    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot, container, false);
    }

    @Override
    protected ZhihuDailyNewsPersnter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

}
