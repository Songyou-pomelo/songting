package com.example.greeknews.view;

import com.example.greeknews.base.BaseMvpView;

public interface LoginView extends BaseMvpView {

    void setData(String data);

    String getUserName();
    String getPsd();

    void showToast(String msg);
}
