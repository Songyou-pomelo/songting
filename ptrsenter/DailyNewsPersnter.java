package com.example.greeknews.ptrsenter;

import com.example.greeknews.base.BasePersenter;
import com.example.greeknews.bean.DailyNewsBean;
import com.example.greeknews.model.DailyNewModel;
import com.example.greeknews.net.ResultCallBack;
import com.example.greeknews.view.DailyNewsView;

public class DailyNewsPersnter extends BasePersenter<DailyNewsView> {

    private DailyNewModel dailyNewModel;
    private DailyNewsView dailyNewsView;

    public DailyNewsPersnter(DailyNewsView dailyNewsView) {
        this.dailyNewsView = dailyNewsView;
    }

    @Override
    protected void initModel() {

        dailyNewModel = new DailyNewModel();
        models.add(dailyNewModel);
    }

    public void getData(){
        dailyNewModel.getData(new ResultCallBack<DailyNewsBean>() {
            @Override
            public void onSuccees(DailyNewsBean bean) {
                if (bean != null){
                   dailyNewsView.setData(bean);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    };
}
