package com.example.greeknews.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseActivity;
import com.example.greeknews.fragment.AboutFragment;
import com.example.greeknews.fragment.CollectFragment;
import com.example.greeknews.fragment.GankFragment;
import com.example.greeknews.fragment.GoldFragment;
import com.example.greeknews.fragment.SettingFragment;
import com.example.greeknews.fragment.V2exFragment;
import com.example.greeknews.fragment.WeChatFragment;
import com.example.greeknews.fragment.ZhihuDailyNewsFragment;
import com.example.greeknews.ptrsenter.MainPersenter;
import com.example.greeknews.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPersenter> implements MainView {

    @BindView(R.id.fragment_container)
    FrameLayout fragment_container;
    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.tool)
    Toolbar tool;
    FragmentManager manager;
    private ArrayList<Fragment> list;
    private ArrayList<Integer> title;

    private final int TYPE_ZHIHU = 0;
    private final int TYPE_WECHAT = 1;
    private final int TYPE_GANK = 2;
    private final int TYPE_GOLD = 3;
    private final int TYPE_V2EX = 4;
    private final int TYPE_COLLECT = 5;
    private final int TYPE_SETTINGS = 6;
    private final int TYPE_ABOUT = 7;
    private int mLastFragmentPosition;
    private ZhihuDailyNewsFragment zhihuDailyNewsFragment;
    private WeChatFragment weChatFragment;
    private FragmentTransaction transaction;

    @Override
    protected MainPersenter initPersenter() {
        return new MainPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        manager = getSupportFragmentManager();
        tool.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(tool);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tool, R.string.app_name, R.string.app_name);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        dl.addDrawerListener(toggle);
        toggle.syncState();
        initFragments();
        initTitles();
        addZhuhuDailyNewsFragment();
    }

    private void addZhuhuDailyNewsFragment() {

        FragmentTransaction beginTransaction = manager.beginTransaction();
        beginTransaction.add(R.id.fragment_container,list.get(0));
        beginTransaction.commit();
        tool.setTitle(title.get(0));

    }

    private void initTitles() {
        title = new ArrayList<>();
        title.add(R.id.zhihu);
        title.add(R.id.gank);
        title.add(R.id.about);
        title.add(R.id.wechat);
        title.add(R.id.gold);
        title.add(R.id.collect);
        title.add(R.id.settings);
        title.add(R.id.v2ex);

    }

    private void initFragments() {

        /*zhihuDailyNewsFragment = new ZhihuDailyNewsFragment();
        weChatFragment = new WeChatFragment();*/
       /* gankFragment = new GankFragment();
        goldFragment = new GoldFragment();
        v2exFragment = new V2exFragment();
        collectFragment = new CollectFragment();
        settingFragment = new SettingFragment();*/
        //初始化fragment
        list = new ArrayList<>();
        list.add(new ZhihuDailyNewsFragment());
        list.add(new WeChatFragment());
        list.add(new GankFragment());
        list.add(new GoldFragment());
        list.add(new V2exFragment());
        list.add(new CollectFragment());
        list.add(new SettingFragment());
        list.add(new AboutFragment());
    }

    @Override
    public void initLister() {

        nav.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId != R.id.info_title && itemId !=R.id.option_title){
                    menuItem.setCheckable(true);
                switch (itemId) {
                    case R.id.zhihu:
                        tool.setTitle(R.string.zhihu);
                        switchFragment(TYPE_ZHIHU);
                        break;
                    case R.id.wechat:
                        tool.setTitle(R.string.wechat);
                        switchFragment(TYPE_WECHAT);
                        break;
                    case R.id.gank:
                        tool.setTitle(R.string.gank);
                        Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                        switchFragment(TYPE_GANK);
                        break;
                    case R.id.gold:
                        tool.setTitle(R.string.gold);
                        Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
                        switchFragment(TYPE_GOLD);
                        break;
                    case R.id.v2ex:
                        tool.setTitle(R.string.v2ex);
                        Toast.makeText(MainActivity.this, "5", Toast.LENGTH_SHORT).show();
                        switchFragment(TYPE_V2EX);
                        break;
                    case R.id.collect:
                        tool.setTitle(R.string.collect);
                        Toast.makeText(MainActivity.this, "6", Toast.LENGTH_SHORT).show();
                        switchFragment(TYPE_COLLECT);
                        break;
                    case R.id.settings:
                        tool.setTitle(R.string.settings);
                        Toast.makeText(MainActivity.this, "7", Toast.LENGTH_SHORT).show();
                        switchFragment(TYPE_SETTINGS);
                        break;
                    case R.id.about:
                        tool.setTitle(R.string.about);
                        Toast.makeText(MainActivity.this, "8", Toast.LENGTH_SHORT).show();
                        switchFragment(TYPE_ABOUT);
                        break;
                }
                dl.closeDrawer(Gravity.LEFT);
                }else {
                    menuItem.setCheckable(false);
                }
                return false;
            }
        });
    }

    private void switchFragment(int type) {
        //显示一个fragmnet,隐藏一个Fragment
        //显示
        Fragment fragment = list.get(type);
        //需要隐藏
        Fragment hideFragment = list.get(mLastFragmentPosition);
        transaction = manager.beginTransaction();
        if (!fragment.isAdded()){
            transaction.add(R.id.fragment_container,fragment);
        }

        transaction.hide(hideFragment);
        transaction.show(fragment);
        transaction.commit();

        mLastFragmentPosition = type;
    }
}
