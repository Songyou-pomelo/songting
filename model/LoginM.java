package com.example.greeknews.model;

import android.util.Log;

import com.example.greeknews.api.MyServer;
import com.example.greeknews.base.BaseMvpModel;
import com.example.greeknews.bean.LoginBean;
import com.example.greeknews.net.ResultCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class LoginM extends BaseMvpModel {

    private String name;
    private String psd;
    private ResultCallBack callBack;

    public void login(String name, String psd, final ResultCallBack callBack){
        this.name = name;
        this.psd = psd;
        this.callBack = callBack;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.sBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyServer server = retrofit.create(MyServer.class);
        Observable<LoginBean> login = server.login(name, psd);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                      //  onSubscribe 在订阅

                        //切断观察者 和被观察者的连接 找到合适的时机 界面推出的时候
                        Log.d(TAG, "onSubscribe: ");
                        composite.add(d);
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {

                        callBack.onSuccees(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
