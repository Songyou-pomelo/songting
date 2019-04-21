package com.example.greeknews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseMvpView,P extends BasePersenter> extends Fragment implements BaseMvpView {

    private Unbinder unbinder;
    public P mpresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, inflate);
        mpresenter = initPresenter();
        if (mpresenter !=null){
            mpresenter.bind((V)this);
        }
        initView();
        initLister();
        initData();
        return inflate;
    }

    protected void initData() {}

    protected void initLister() {}

    protected void initView() {}

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null){
            unbinder.unbind();
            mpresenter.onDestory();
            mpresenter=null;
        }


    }
}
