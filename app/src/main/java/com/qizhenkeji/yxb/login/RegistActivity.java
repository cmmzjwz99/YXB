package com.qizhenkeji.yxb.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.custom.BaseTitlebar;
import com.cmm.zjwz.utils.CommonUtil;
import com.qizhenkeji.yxb.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hc101 on 18/6/14.
 */

public class RegistActivity extends BaseActivity implements View.OnClickListener {

    private EditText etPhone;
    private EditText etCode;
    private TextView tvCode;
    private EditText etPwd;
    private EditText etConfirmPw;
    private TextView tvRegist;
    private CheckBox cbAgreement;

    private int i = 60;
    private Timer timer;
    private Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //handler处理消息
            if (msg.what < 60 && msg.what > 0) {
                tvCode.setText("(" + msg.what + ")秒后重发");
                tvCode.setTextSize(R.dimen.text_size_12);
                tvCode.setClickable(false);
            }
            if (msg.what == 0) {
                //在handler里可以更改UI组件
//                finish();
                tvCode.setClickable(true);
                tvCode.setText("验证码");
                timer.cancel();
                i = 60;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        initView();
    }

    private void initView() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("注册");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        etPhone = (EditText) findViewById(R.id.et_phone);
        etCode = (EditText) findViewById(R.id.et_code);
        tvCode = (TextView) findViewById(R.id.tv_code);
        etPwd = (EditText) findViewById(R.id.et_password);
        etConfirmPw = ((EditText) findViewById(R.id.et_confirm_password));
        cbAgreement = (CheckBox) findViewById(R.id.cb_agreement);
        TextView tvAgreement = (TextView) findViewById(R.id.tv_agreement);
        tvRegist = ((TextView) findViewById(R.id.tv_regist));

        tvCode.setOnClickListener(this);
        tvAgreement.setOnClickListener(this);
        tvRegist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_code:
                CommonUtil.setViewAlphaAnimation(tvCode);
                myTimer();
                break;

            case R.id.tv_agreement:
                startActivity(new Intent(RegistActivity.this,AgreementActivity.class));
                break;

            case R.id.tv_regist:
                break;
        }
    }

    /**
     * 验证码 定时器
     */
    private void myTimer() {
        // 定义计时器
        timer = new java.util.Timer();

        // 定义计划任务，根据参数的不同可以完成以下种类的工作：在固定时间执行某任务，在固定时间开始重复执行某任务，重复时间间隔可控，在延迟多久后执行某任务，在延迟多久后重复执行某任务，重复时间间隔可控
        timer.schedule(new TimerTask() {

            // TimerTask 是个抽象类,实现的是Runable类
            @Override
            public void run() {
//                Log.i("yao", Thread.currentThread().getName());
                //定义一个消息传过去
                Message msg = new Message();
                msg.what = i--;
                handler.sendMessage(msg);
            }

        }, 10, 1000);
    }
}
