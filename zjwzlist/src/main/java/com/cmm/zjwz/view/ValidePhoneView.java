package com.cmm.zjwz.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cmm.zjwz.utils.InputCheck;
import com.cmm.zjwz.utils.PrintLog;
import com.cmm.zjwz.utils.SharePrefenceUtils;
import com.cmm.zjwz.utils.SingleToast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;


/**
 * Created by cmm on 16/1/4.
 */
public class ValidePhoneView extends TextView implements View.OnClickListener {

    public static final String GET_CODE_URL = "https://xiangqi.qzyunfengkong.com/api/accounts/generate_phone_code";

    String editPhone;
    String inputPhone = "";

    private String url = GET_CODE_URL;


    public ValidePhoneView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        setOnClickListener(this);
    }

//    private void init(Context context, AttributeSet attrs) {
//        setOnClickListener(v -> sendPhoneMessage());
//    }

    public void setEditPhone(String str) {
        editPhone = str;
    }

    public void setPhoneString(String phone) {
        inputPhone = phone;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void startTimer() {
        countDownTimer.cancel();
        countDownTimer.start();
    }

    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {

        public void onTick(long millisUntilFinished) {

            PrintLog.e("url =======" + url);
            ValidePhoneView.this.setText(String.format("%d秒", millisUntilFinished / 1000));
            ValidePhoneView.this.setEnabled(false);
        }

        public void onFinish() {
            ValidePhoneView.this.setEnabled(true);
            ValidePhoneView.this.setText("发送验证码");
        }
    };

    public void onStop() {
        countDownTimer.cancel();
        countDownTimer.onFinish();
    }

    void sendPhoneMessage() {

//        String codePhone = SharePrefenceUtils.getString(getContext(), "codePhone");
//        if (codePhone != null){
//            editPhone = codePhone;
//        }
        PrintLog.e("editPhone =======" + editPhone);
        String phoneString;
        if (editPhone != null) {
            phoneString = editPhone;
        } else {
            phoneString = inputPhone;
        }

        RequestParams params = new RequestParams();

        if(!phoneString.isEmpty()){
            if (!InputCheck.checkPhone(getContext(), phoneString)) return;
            params.put("phone","18668703190");
            Log.e("tag", "phone : " + phoneString);
        }


//        AsyncHttpClient client = MyAsyncHttpClient.createClient(getContext());
//        client.get(getContext(), url, params, new MyJsonResponse(getContext()) {
//            @Override
//            public void onMySuccess(JSONObject response) {
//                super.onMySuccess(response);
//                SingleToast.showMiddleToast(getContext(), "验证码已发送");
//            }
//
//            @Override
//            public void onMyFailure(JSONObject response) {
//                super.onMyFailure(response);
//                countDownTimer.cancel();
//                countDownTimer.onFinish();
//            }
//        });

        countDownTimer.start();
    }

    @Override
    public void onClick(View view) {
        sendPhoneMessage();
    }
}
