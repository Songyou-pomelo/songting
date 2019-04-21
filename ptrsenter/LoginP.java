package com.example.greeknews.ptrsenter;

import android.text.TextUtils;

import com.example.greeknews.base.BasePersenter;
import com.example.greeknews.bean.LoginBean;
import com.example.greeknews.model.LoginM;
import com.example.greeknews.net.ResultCallBack;
import com.example.greeknews.utils.Logger;
import com.example.greeknews.view.LoginView;

public class LoginP extends BasePersenter<LoginView> {

    private static final String TAG = LoginP.class.getName();
    private LoginM mainM;

    public void getData(){
        //获取数据
        String data = "我想要一颗西柚";
        if (mView != null){
            mView.setData(data);
        }
    }

    public void login(){
        String name = mView.getUserName();
        String psw = mView.getPsd();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psw)){
            mView.showToast("用户名或密码不能为空");
            return;
        }

        //进行网络请求
        mainM.login(name, psw, new ResultCallBack<LoginBean>() {
            @Override
            public void onSuccees(LoginBean loginBean) {
                if (loginBean != null){
                    Logger.LogD(TAG,loginBean.toString());
                    if (loginBean.getCode() == 200){
                        //防止页面销毁 数据返回后设置页面的空指针
                        if (mView != null){
                            mView.showToast("登录成功");
                        }
                    }else {
                        if (mView != null){
                            mView.showToast("登录失败");
                        }
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                Logger.LogD(TAG,msg);
                if (mView != null){
                    mView.showToast("登录失败");
                }
            }
        });
    }
    @Override
    protected void initModel() {

        mainM = new LoginM();
        models.add(mainM);
    }
}
