package com.qizhenkeji.yxb.cashier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.custom.BaseTitlebar;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.user.CashierRecordActivity;

/**
 * Created by hc101 on 18/6/16.
 */

public class GatheringActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvSettlementBank;
    private TextView tvMoney;
    private RelativeLayout rlNotBindBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gathering);

        initView();
    }

    private void initView() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("收款");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvMoney = (TextView) findViewById(R.id.tv_money);
        rlNotBindBank = (RelativeLayout) findViewById(R.id.rl_not_bind_bank);
        TextView tvBindBank = (TextView) findViewById(R.id.tv_bind_bank);
        tvSettlementBank = (TextView) findViewById(R.id.tv_settlement_bank);
        TextView tvConfirm = (TextView) findViewById(R.id.tv_confirm);

        tvBindBank.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_bind_bank:
                startActivity(new Intent(GatheringActivity.this,BindCardActivity.class));
                break;

            case R.id.tv_confirm:
                startActivity(new Intent(GatheringActivity.this, CashierRecordActivity.class));
                break;
        }
    }
}
