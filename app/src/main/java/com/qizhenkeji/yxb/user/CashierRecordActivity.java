package com.qizhenkeji.yxb.user;

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
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.helper.CashierRecordHelper;
import com.qizhenkeji.yxb.model.CashierRecordListModel;
import com.qizhenkeji.yxb.model.CashierRecordModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hc101 on 18/6/16.
 */

public class CashierRecordActivity extends BaseActivity implements ItemViewListener {

    private PullToRefreshListView lvCashierRecord;
    private List<CashierRecordModel> mList;
    private int index = 1;
    private LongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_record);

        initView();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new LongAdapter(this, mList, this,"");
        lvCashierRecord.getRefreshableView().setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("收银记录");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
        lvCashierRecord = (PullToRefreshListView) findViewById(R.id.lv_cashier_record);
        mList = new ArrayList<>();
        //上拉加载
        lvCashierRecord.setPullLoadEnabled(true);
        //下拉刷新
        lvCashierRecord.setPullRefreshEnabled(true);
        lvCashierRecord.setScrollLoadEnabled(true);
        lvCashierRecord.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//                index = 1;
//                getRechargeLog(index,true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                index++;
//                getRechargeLog(index,false);
            }
        });
    }

    /**
     * 更新页面显示内容
     * @param isrefresh 是否为刷新
     * @param data 接口请求数据
     */
    private void updataView(boolean isrefresh, CashierRecordListModel data) {
        if (isrefresh) {
            mList.clear();
        }
        if (mList.size() == data.total && mList.size() != 0) {
            index = index > 1 ? index - 1 : 1;
            lvCashierRecord.setHasMoreData(false);
            return;
        }

        if (data.data != null && !data.data.isEmpty()) {
            mList.addAll(data.data);
        } else {
            if (mList.size() == 0) {
                dataEmpty("暂无记录",0,0);
            }
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public View getView(int id, View itemView, ViewGroup vg, Object data,String string) {
        CashierRecordHelper helper;
        if (itemView == null) {
            itemView = LayoutInflater.from(this).inflate(
                    R.layout.item_cashier_record, null, false);
            helper = new CashierRecordHelper(this, itemView);
            itemView.setTag(helper);
        } else {
            helper = (CashierRecordHelper) itemView.getTag();
        }
        helper.updateView((CashierRecordModel) data);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return itemView;
    }
}
