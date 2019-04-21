package com.example.greeknews.net;

import android.util.Log;

import com.example.greeknews.utils.ToastUtil;

import io.reactivex.Observer;

public abstract class BaseObserver<T> implements Observer<T> {

    String TAG = getClass().getName();
    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: "+e.getMessage());
        ToastUtil.showToast("数据加载失败");
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
    }
}
