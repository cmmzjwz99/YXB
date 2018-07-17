package com.qizhenkeji.yxb.repayment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.custom.BaseTitlebar;
import com.cmm.zjwz.custom.ItemViewListener;
import com.cmm.zjwz.custom.LongAdapter;
import com.cmm.zjwz.custom.NoScrollListView;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.helper.PaymentDetailHelper;
import com.qizhenkeji.yxb.model.MyCreditCardListmodel;
import com.qizhenkeji.yxb.model.PaymentDetailModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hc101 on 18/6/21.
 */

public class PaymentDetailsActivity extends BaseActivity implements ItemViewListener {

    private NoScrollListView listView;
    private List<PaymentDetailModel> mList;
    private LongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        
        initView();
        initAdapter();
    }

    private void initView() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("还款详情");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView tvCardholder = (TextView) findViewById(R.id.tv_have_card);  //持卡人
        TextView tvBankNo = (TextView) findViewById(R.id.tv_bank_no);
        TextView tvPL = (TextView) findViewById(R.id.tv_payment_lines);  //还款金额
        TextView tvAL = (TextView) findViewById(R.id.tv_available_lines);     //可用金额
        TextView tvDN = (TextView) findViewById(R.id.tv_deal_number);       //每日交易笔数
        TextView tvST = (TextView) findViewById(R.id.tv_start_time);     //开始日期
        TextView tvPT = (TextView) findViewById(R.id.tv_pay_time);     //还清日期
        TextView tvDA = (TextView) findViewById(R.id.tv_deal_all);     //交易总笔数
        TextView tvPN = (TextView) findViewById(R.id.tv_payment_number);     //还款总笔数
        TextView tvF = (TextView) findViewById(R.id.tv_fees);     //手续费
        TextView tvAF = (TextView) findViewById(R.id.tv_all_fees);     //综合费率

        listView = (NoScrollListView) findViewById(R.id.lv_payment_detail_list);
        mList = new ArrayList<>();
        initData();
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
//        listView.getRefreshableView().setAdapter(adapter);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void initData(){
        PaymentDetailModel model = new PaymentDetailModel(1,"交易","2018-06-16","3000.00","1.5");
        PaymentDetailModel model1 = new PaymentDetailModel(2,"还款","2018-07-16","4000.00","0.5");
        PaymentDetailModel model2 = new PaymentDetailModel(3,"交易","2018-08-16","5000.00","5.5");
        PaymentDetailModel model3 = new PaymentDetailModel(4,"还款","2018-09-16","6000.00","3.5");
        PaymentDetailModel model4 = new PaymentDetailModel(5,"交易","2018-10-16","3000.00","1.5");
        PaymentDetailModel model5 = new PaymentDetailModel(6,"还款","2019-07-16","4000.00","0.5");
        PaymentDetailModel model6 = new PaymentDetailModel(7,"交易","2019-08-16","5000.00","5.5");
        PaymentDetailModel model7 = new PaymentDetailModel(8,"还款","2019-09-16","6000.00","3.5");
        mList.add(model);
        mList.add(model1);
        mList.add(model2);
        mList.add(model3);
        mList.add(model4);
        mList.add(model5);
        mList.add(model6);
        mList.add(model7);


        PaymentDetailModel model0 = new PaymentDetailModel(0,"类型","日期","额度","手续费");
        mList.add(0,model0);
    }

    @Override
    public View getView(int id, View itemView, ViewGroup vg, Object data,String str) {
        PaymentDetailHelper helper;
        if (itemView == null) {
            itemView = LayoutInflater.from(this).inflate(
                    R.layout.item_payment_detail, null, false);
//            itemView.findViewById(R.id.tv_modify).setOnClickListener(this);
            helper = new PaymentDetailHelper(this, itemView);
            itemView.setTag(helper);
        } else {
            helper = (PaymentDetailHelper) itemView.getTag();
        }
        helper.updateView((PaymentDetailModel) data,id);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return itemView;
    }
}
