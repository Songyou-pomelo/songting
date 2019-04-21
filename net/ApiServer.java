package com.example.greeknews.net;

import com.example.greeknews.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServer {

    String baseurl = "http://yun918.cn/study/public/index.php/";
    /**
     * 登录
     * name 用户名
     * psd 密码
     */

    @FormUrlEncoded
    @POST("Login")
    Observable<LoginBean> login(@Field("username") String name,@Field("password") String psd);

}
