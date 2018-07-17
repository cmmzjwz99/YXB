package com.qizhenkeji.yxb.cashier;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.custom.BaseTitlebar;
import com.qizhenkeji.yxb.R;

import java.util.ArrayList;

/**
 * Created by hc101 on 18/6/16.
 */

public class BindCardActivity extends BaseActivity implements View.OnClickListener {

    private EditText etPhone;
    private EditText etCardHolder;
    private EditText edIdCard;
    private EditText edBankNo;
    private EditText edRePhone;
    private OptionsPickerView bankOptions;
    private ArrayList<String> banks = new ArrayList<>();
    private TextView tvBank;
    private String code_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_card);
        initData();
        initView();
    }

    private void initData() {
        banks.add("中国银行");
        banks.add("交通银行");
        banks.add("工商银行");
    }

    private void initView() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("绑定信用卡");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        etCardHolder = (EditText) findViewById(R.id.et_card_holder);
        edIdCard = (EditText) findViewById(R.id.et_idCard);
        tvBank = (TextView) findViewById(R.id.tv_bank_name);
        edBankNo = (EditText) findViewById(R.id.et_bank_no);
        edRePhone = (EditText) findViewById(R.id.et_reserved_phone);
        TextView tvConfirm = (TextView) findViewById(R.id.tv_confirm);

        tvBank.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_bank_name:
                initChooseBank();
                break;
            case R.id.tv_confirm:
                break;
        }
    }

    private void initChooseBank() {
        bankOptions = new OptionsPickerView(this);
        bankOptions.show();
        bankOptions.setPicker(banks);
        bankOptions.setTitle("选择银行卡");
        bankOptions.setCyclic(false);
        bankOptions.setSelectOptions(0);
        bankOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                tvBank.setText(banks.get(options1));
//                code_num = codes.get(options1);
            }
        });
    }

}
