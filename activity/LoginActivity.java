package com.example.greeknews.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseActivity;
import com.example.greeknews.ptrsenter.LoginP;
import com.example.greeknews.utils.ToastUtil;
import com.example.greeknews.view.LoginView;
import com.example.greeknews.view.MainView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<MainView,LoginP> implements LoginView {

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.bton)
    Button bton;

    @OnClick({R.id.bton})
    public void click(View v){
        mPersenter.login();
    }

    @Override
    protected LoginP initPersenter() {
        return new LoginP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setData(String data) {

        bton.setText(data);
    }

    @Override
    public String getUserName() {
        return et_name.getText().toString().trim();
    }

    @Override
    public String getPsd() {
        return et_pwd.getText().toString().trim();
    }

    @Override
    public void showToast(String msg) {

        ToastUtil.showToast(msg);
    }
}
