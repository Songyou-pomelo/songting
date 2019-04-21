package com.example.greeknews.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.base.Constants;
import com.example.greeknews.ptrsenter.EmptyPersenter;
import com.example.greeknews.view.EmptyView;

import butterknife.BindView;

/**
 * 简单文本
 * A simple {@link Fragment} subclass.
 */
public class GoldTabFragment extends BaseFragment<EmptyView,EmptyPersenter> implements  EmptyView {

    @BindView(R.id.gold_tab_tv)
    TextView gold_tab_tv;
    public static GoldTabFragment newInstence(String text){
        GoldTabFragment goldTabFragment = new GoldTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA,text);
        goldTabFragment.setArguments(bundle);

        return goldTabFragment;
    }

    @Override
    protected EmptyPersenter initPresenter() {
        return new EmptyPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_tab;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        String string = arguments.getString(Constants.DATA);
        gold_tab_tv.setText(string);
    }
}
