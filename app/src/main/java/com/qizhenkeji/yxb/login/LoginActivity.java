package com.qizhenkeji.yxb.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.custom.BaseTitlebar;
import com.cmm.zjwz.utils.PrintLog;
import com.cmm.zjwz.utils.SharePrefenceUtils;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.api.UrlAPI;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by hc101 on 18/6/14.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText etPhone;
    private EditText etPwd;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    //初始化头
    private void initView() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("登录");
        titlebar.setRightText("注册", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistActivity.class));
            }

        });

        etPhone = (EditText) findViewById(R.id.et_phone);
        etPwd = (EditText) findViewById(R.id.et_password);
        tvLogin = (TextView) findViewById(R.id.tv_login);

        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        UrlAPI.getPostFormBuilder(this,"accounts/signup")
                .addParams("user[login]", "18668703190")
                .addParams("user[password]", "123456")
                .addParams("code", etPwd.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String[] response, int id) {
                        String str = response[0];
                        String cookie = response[1];
                        SharePrefenceUtils.put(LoginActivity.this,"cookie",cookie);
                        PrintLog.d("str===1====" + str);
                        PrintLog.d("LoginActivity=>cookie======" + cookie);
                    }
                });
    }
}
