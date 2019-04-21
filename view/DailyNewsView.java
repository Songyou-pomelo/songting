package com.example.greeknews.view;

import com.example.greeknews.base.BaseMvpView;
import com.example.greeknews.bean.DailyNewsBean;

public interface DailyNewsView extends BaseMvpView {

    void setData(DailyNewsBean dailyNewsBean);
}
