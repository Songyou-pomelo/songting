package com.example.greeknews.base;

import java.util.ArrayList;

public abstract class BasePersenter<V extends BaseMvpView> {

    protected V mView;
    protected ArrayList<BaseMvpModel> models = new ArrayList<>();

    public BasePersenter() {
        initModel();
    }

    protected abstract void initModel();

    public  void bind(V v) {
        this.mView = v;
    }

    public void onDestory(){
        mView = null;
        if (models.size()>0){
            for (BaseMvpModel model : models) {
                model.onDestory();
            }
        }
    }
}
