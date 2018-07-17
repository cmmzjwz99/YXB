package com.qizhenkeji.yxb.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmm.zjwz.custom.BaseFragment;
import com.cmm.zjwz.custom.ItemViewListener;
import com.cmm.zjwz.custom.LongAdapter;
import com.cmm.zjwz.custom.NoScorllGridView;
import com.cmm.zjwz.utils.PrintLog;
import com.cmm.zjwz.utils.SharePrefenceUtils;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.api.UrlAPI;
import com.qizhenkeji.yxb.cashier.CashierActivity;
import com.qizhenkeji.yxb.cashier.IdentityAuthenticationActivity;
import com.qizhenkeji.yxb.cashier.MyCreditCardActivity;
import com.qizhenkeji.yxb.repayment.PaymentActivity;
import com.qizhenkeji.yxb.login.LoginActivity;
import com.qizhenkeji.yxb.login.RegistActivity;
import com.qizhenkeji.yxb.text.UploadPhotosActivity;
import com.qizhenkeji.yxb.user.CashierRecordActivity;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by hc101 on 18/6/13.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, ItemViewListener, AdapterView.OnItemClickListener {
    private View view;
    private NoScorllGridView gvModel;
    private List<Map<String, Object>> gvModelList;
    private String[] titles = new String[]{"实名认证", "收银记录", "绑定信用卡", "我的商户", "收益模式", "联系客服", "更多"};
    private int[] icons = new int[]{R.mipmap.ic_mine, R.mipmap.ic_mine, R.mipmap.ic_mine, R.mipmap.ic_mine, R.mipmap.ic_mine, R.mipmap.ic_mine, R.mipmap.ic_mine};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);

        initView(view);
        addData();
        initAdapter();
        getCode();
//        initBanner();
        return view;
    }

    private void getCode(){
        UrlAPI.getGetBuilder("accounts/generate_phone_code?phone=" + "18668703190")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String[] response, int id) {
                        String str = response[0];
                        String cookie = response[1];
                        SharePrefenceUtils.put(getContext(),"cookie",cookie);
                        PrintLog.d("str======" + str);
                        PrintLog.d("cookie=====" + cookie);
                    }
                });

    }


    private void initBanner() {
        UrlAPI.getPostFormBuilder(getContext(),"accounts/signup")
                .addParams("user[login]", "18668703190")
                .addParams("user[password]", "123456")
                .addParams("code", "1234")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String[] response, int id) {
                        String str = response[0];
                        String cookie = response[1];
                        SharePrefenceUtils.put(getContext(),"cookie",cookie);
                        PrintLog.d("str===1====" + str);
                        PrintLog.d("cookie==1====" + cookie);
                    }
                });

    }



    private void addData() {
        for (int i = 0; i < titles.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", icons[i]);
            map.put("text", titles[i]);
            gvModelList.add(map);
        }

    }

    private void initAdapter() {
        LongAdapter gvModelAdapter = new LongAdapter(getContext(), gvModelList, this,"");
        gvModel.setAdapter(gvModelAdapter);
        gvModel.setOnItemClickListener(this);
    }

    private void initView(View view) {
        RelativeLayout rlPayment = (RelativeLayout) view.findViewById(R.id.rl_repayment);
        RelativeLayout rlCashier = (RelativeLayout) view.findViewById(R.id.rl_cashier);
        gvModel = (NoScorllGridView) view.findViewById(R.id.gv_model);

        gvModelList = new ArrayList();
        rlPayment.setOnClickListener(this);
        rlCashier.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_repayment:
                startActivity(new Intent(getContext(), PaymentActivity.class));
                break;
            case R.id.rl_cashier:
                startActivity(new Intent(getContext(), CashierActivity.class));
                break;
        }
    }

    @Override
    public View getView(int id, View convertView, ViewGroup vg, Object data,String str) {
        if (convertView == null) {
            convertView = View.inflate(getContext(),
                    R.layout.item_home_model, null);
        }

        Map<String, Object> stringObjectMap = gvModelList.get(id);
        TextView tvTitle = (TextView) convertView
                .findViewById(R.id.tv_title);
        ImageView ivIcon = (ImageView) convertView
                .findViewById(R.id.iv_img);

        tvTitle.setText(stringObjectMap.get("text").toString());
        ivIcon.setImageResource(Integer.valueOf(stringObjectMap.get("image").toString()));
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Map<String, Object> itemAtPosition = (Map<String, Object>) adapterView.getItemAtPosition(position);
        switch (itemAtPosition.get("text").toString()) {
            case "实名认证":
                startActivity(new Intent(getContext(), IdentityAuthenticationActivity.class));
                break;
            case "收银记录":
                startActivity(new Intent(getContext(), CashierRecordActivity.class));
                break;
            case "绑定信用卡":
                startActivity(new Intent(getContext(), MyCreditCardActivity.class));
                break;
            case "我的商户":
//                Utils.toast(getContext(), "我的商户");
                startActivity(new Intent(getContext(), RegistActivity.class));
                break;
            case "收益模式":
//                Utils.toast(getContext(), "收益模式");
//                initBanner();
//                startActivity(new Intent(getContext(), TestCheckOne.class));
                startActivity(new Intent(getContext(), UploadPhotosActivity.class));
                break;
            case "更多":
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
        }
    }

}
