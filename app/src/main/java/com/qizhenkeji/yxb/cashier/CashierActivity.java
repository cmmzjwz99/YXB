package com.qizhenkeji.yxb.cashier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.custom.BaseTitlebar;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.adapter.PopupAdapter;
import com.qizhenkeji.yxb.popup.CommonPopupWindow;
import com.qizhenkeji.yxb.popup.MyOnclickListener;

/**
 * Created by hc101 on 18/6/15.
 */

public class CashierActivity extends BaseActivity implements TextView.OnEditorActionListener, CommonPopupWindow.ViewInterface {

    private CommonPopupWindow popupWindow;
    private EditText etMoney;
    private RelativeLayout rlCM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier);

        initView();
//        initPopupWindow();
    }

    private void initView() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("收银");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
        rlCM = (RelativeLayout) findViewById(R.id.rl_cashier_money);
        etMoney = (EditText) findViewById(R.id.et_money);
        showKeyboard(etMoney);
        etMoney.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        Utils.toast(this,"CashierActivity");
//        startActivity(new Intent(this,GatheringActivity.class));
        dismissKeyboard();
        initPopWindows(v);
        return true;
    }

    private void initPopWindows(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_cashier_type)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.animTranslate)
                .setViewOnclickListener(this)
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(rlCM);
//        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        recycle_view.setLayoutManager(new GridLayoutManager(this, 1));
        PopupAdapter mAdapter = new PopupAdapter(this);
        recycle_view.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyOnclickListener() {
            @Override
            public void onItemClick(View view, int position) {
                toast("position is " + position);
                if (popupWindow != null) {
                    popupWindow.dismiss();
                    startActivity(new Intent(CashierActivity.this,GatheringActivity.class));
                }
            }
        });


    }


}
