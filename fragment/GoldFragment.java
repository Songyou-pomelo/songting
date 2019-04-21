package com.example.greeknews.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.greeknews.R;
import com.example.greeknews.activity.GoldShowActivity;
import com.example.greeknews.adapter.VpGoldTabAdapter;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.base.Constants;
import com.example.greeknews.bean.GoldShowBean;
import com.example.greeknews.ptrsenter.GoldPersenter;
import com.example.greeknews.view.GoldView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends BaseFragment<GoldView, GoldPersenter> implements GoldView {


    @BindView(R.id.goldtab)
    TabLayout goldtab;
    @BindView(R.id.goldimg)
    ImageView goldimg;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private ArrayList<GoldShowBean> showBeans;
    private ArrayList<BaseFragment> listfrag;

    @Override
    protected GoldPersenter initPresenter() {
        return new GoldPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        iniTitle();
        setFragments();

    }

    private void setFragments() {
        initFragments();
        VpGoldTabAdapter adapter = new VpGoldTabAdapter(getChildFragmentManager(), listfrag, showBeans);
        vp.setAdapter(adapter);
        goldtab.setupWithViewPager(vp);
    }

    private void initFragments() {
        listfrag = new ArrayList<>();
        for (int i = 0; i < showBeans.size(); i++) {
            GoldShowBean goldShowBean = showBeans.get(i);
            if (goldShowBean.isChecked){
                listfrag.add(GoldTabFragment.newInstence(goldShowBean.title));

            }
        }
    }

    private void iniTitle() {
        showBeans = new ArrayList<>();
        showBeans.add(new GoldShowBean("Android",true));
        showBeans.add(new GoldShowBean("ios",true));
        showBeans.add(new GoldShowBean("前端",true));
        showBeans.add(new GoldShowBean("后端",true));
        showBeans.add(new GoldShowBean("设计",true));
        showBeans.add(new GoldShowBean("产品",true));
        showBeans.add(new GoldShowBean("阅读",true));
        showBeans.add(new GoldShowBean("工具资源",true));
    }

    @OnClick(R.id.goldimg)
    public void click(View v){

        switch (v.getId()) {
            case R.id.goldimg:
                go2ShowActivity();
                break;
        }
    }

    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), GoldShowActivity.class);
        intent.putExtra(Constants.DATA,showBeans);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null){
            if (requestCode == 100 && resultCode == Activity.RESULT_OK){

                showBeans = (ArrayList<GoldShowBean>) data.getSerializableExtra(Constants.DATA);
                //刷新界面
                setFragments();
            }
        }

    }
}
