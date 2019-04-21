package com.example.greeknews.utils;

import android.widget.Toast;

import com.example.greeknews.base.BaseApp;

public class ToastUtil {

    public static void showToast(String msg){
        //避免内存泄漏的一个方法 用到上下文的地方
        //能用Application 就用Application

        Toast.makeText(BaseApp.getInstance(), msg.toString(), Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        //避免内存泄漏的一个方法 用到上下文的地方
        //能用Application 就用Application

        Toast.makeText(BaseApp.getInstance(), msg.toString(), Toast.LENGTH_LONG).show();

    }

    public static void showShort(String string) {

    }
}
