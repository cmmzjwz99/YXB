package com.qizhenkeji.yxb.cashier;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.custom.BaseTitlebar;
import com.cmm.zjwz.custom.ItemViewListener;
import com.cmm.zjwz.custom.LongAdapter;
import com.cmm.zjwz.custom.pulltorefreshlistview.PullToRefreshBase;
import com.cmm.zjwz.custom.pulltorefreshlistview.PullToRefreshListView;
import com.cmm.zjwz.utils.PrintLog;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.helper.CreditCardHelper;
import com.qizhenkeji.yxb.model.CashierRecordListModel;
import com.qizhenkeji.yxb.model.CashierRecordModel;
import com.qizhenkeji.yxb.model.CreditCardModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hc101 on 18/6/16.
 */

public class MyCreditCardActivity extends BaseActivity implements ItemViewListener {

    private ListView mListView;
    private int index = 1;
    private LongAdapter adapter;
    private List<CashierRecordModel> mList;
    private PullToRefreshListView lvCreditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_credit_card);

        initView();
        initAdapter();
    }

    private void initView() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("我的信用卡");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titlebar.setRightText("添加信用卡", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyCreditCardActivity.this,BindCardActivity.class));
            }
        });

        mList = new ArrayList<>();

        lvCreditCard = (PullToRefreshListView) findViewById(R.id.lv_credit_card);
        mListView = lvCreditCard.getRefreshableView();
        mListView.setSelector(new ColorDrawable(0x00ffffff));

        lvCreditCard.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//                index = 0;
//                loadingData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                index++;
//                loadingData();
            }
        });
    }

    private void initAdapter() {
        adapter = new LongAdapter(this, mList, this,"");
        mListView.setAdapter(adapter);
    }

    private void updataView(CashierRecordListModel data, boolean isrefresh) {
        if (isrefresh) {
            mList.clear();
        }
        if ( mList.size() != 0) {
            index = index > 1 ? index - 1 : 1;
            lvCreditCard.setHasMoreData(false);
            return;
        }
        if (data.data != null && data.data.size() > 0) {
            mList.addAll(data.data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View itemView, ViewGroup vg, final Object data,String str) {
        CreditCardHelper helper;

        if (itemView == null) {
            itemView = LayoutInflater.from(this).inflate(R.layout.item_credit_card, null, false);
            helper = new CreditCardHelper(this, itemView);
            itemView.setTag(helper);
            PrintLog.e("itemView------data---------:" + itemView);
        } else {
            helper = (CreditCardHelper) itemView.getTag();
        }
        helper.updateView((CreditCardModel) data);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return itemView;
    }

}
