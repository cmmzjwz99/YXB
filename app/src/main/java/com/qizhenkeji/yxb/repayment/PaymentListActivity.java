package com.qizhenkeji.yxb.repayment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.custom.BaseTitlebar;
import com.cmm.zjwz.custom.ItemViewListener;
import com.cmm.zjwz.custom.LongAdapter;
import com.cmm.zjwz.custom.pulltorefreshlistview.PullToRefreshListView;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.helper.PaymentListHelper;
import com.qizhenkeji.yxb.model.CreditCardModel;
import com.qizhenkeji.yxb.model.MyCreditCardListmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hc101 on 18/6/21.
 */

public class PaymentListActivity extends BaseActivity implements ItemViewListener {

    private PullToRefreshListView listView;
    private List<CreditCardModel> mList;
    private LongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list);

        initView();
        initAdapter();
    }

    private void initView() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("还款列表");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (PullToRefreshListView) findViewById(R.id.lv_payment_list);
        mList = new ArrayList<>();
        initData();
        //上拉加载
//        listView.setPullLoadEnabled(true);
//        //下拉刷新
//        listView.setPullRefreshEnabled(true);
//        listView.setScrollLoadEnabled(true);
//        listView.onRefreshComplete();
    }

    /**
     * 更新页面显示内容
     * @param isrefresh 是否为刷新
     * @param data 接口请求数据
     */
    private void updataView(boolean isrefresh, MyCreditCardListmodel data) {
        if (isrefresh) {
            mList.clear();
        }
//        if (mList.size() == data.total && mList.size() != 0) {
//            index = index > 1 ? index - 1 : 1;
//            listView.setHasMoreData(false);
//            return;
//        }
//
//        if (data.items != null && !data.items.isEmpty()) {
//            mList.addAll(data.items);
//        } else {
//            if (mList.size() == 0) {
//                dataEmpty("暂无充值记录",0,0);
//            }
//        }
        adapter.notifyDataSetChanged();

    }

    //加载适配器
    private void initAdapter() {
        adapter = new LongAdapter(this, mList, this,"");
        listView.getRefreshableView().setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData(){
        CreditCardModel model = new CreditCardModel(1,"平安银行","zhangsan","123213xxxx13");
        CreditCardModel model2 = new CreditCardModel(2,"交通银行","zhangsan","99213xxxx13");
        CreditCardModel model3 = new CreditCardModel(3,"中国银行","zhangsan","663213xxxx13");

        mList.add(model);
        mList.add(model2);
        mList.add(model3);
    }

    @Override
    public View getView(int id, View itemView, ViewGroup vg, Object data,String string) {
        PaymentListHelper helper;
        if (itemView == null) {
            itemView = LayoutInflater.from(this).inflate(
                    R.layout.item_payment_list, null, false);
//            itemView.findViewById(R.id.tv_modify).setOnClickListener(this);
            helper = new PaymentListHelper(this, itemView);
            itemView.setTag(helper);
        } else {
            helper = (PaymentListHelper) itemView.getTag();
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
