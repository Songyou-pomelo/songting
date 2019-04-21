package com.example.greeknews.base;

import io.reactivex.disposables.CompositeDisposable;

public class BaseMvpModel {

    /*
    * CompositeDisposable  复合可支配
    *
    */
    protected CompositeDisposable  composite = new CompositeDisposable();

    public void onDestory() {
        composite.clear();
    }
}
