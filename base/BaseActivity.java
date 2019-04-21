package com.example.greeknews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends BaseMvpView, P extends BasePersenter> extends AppCompatActivity implements BaseMvpView{

    protected P mPersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        mPersenter = initPersenter();

        if (mPersenter != null){
            mPersenter.bind((V)this);
        }

        initView();
        initLister();
        initData();
    }

    public void initLister() {

    }

    public void initData() {

    }

    public void initView() {

    }

    protected abstract P initPersenter();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //切断V层和 P层的关系
        mPersenter.onDestory();
        mPersenter = null;
    }
}
