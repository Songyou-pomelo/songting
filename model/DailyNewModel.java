package com.example.greeknews.model;

import android.util.Log;

import com.example.greeknews.base.BaseMvpModel;
import com.example.greeknews.bean.DailyNewsBean;
import com.example.greeknews.net.BaseObserver;
import com.example.greeknews.net.HttpUtils;
import com.example.greeknews.net.ResultCallBack;
import com.example.greeknews.net.RxUtils;
import com.example.greeknews.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DailyNewModel extends BaseMvpModel {

    public  void getData(final ResultCallBack<DailyNewsBean> callBack){

        ZhihuService service = HttpUtils.getInstance().getApiserver(ZhihuService.sBaseUrl, ZhihuService.class);
        Observable<DailyNewsBean> lastDailyNew = service.getLastDailyNews();
        lastDailyNew.compose(RxUtils.<DailyNewsBean> rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        composite.add(d);
                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        callBack.onSuccees(dailyNewsBean);
                        Log.i("TAG",dailyNewsBean.getTop_stories().size()+"");
                    }
                });
    }
}
