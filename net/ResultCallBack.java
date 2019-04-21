package com.example.greeknews.net;

public interface ResultCallBack<D> {

    void onSuccees(D bean);

    void onFail(String msg);

}
