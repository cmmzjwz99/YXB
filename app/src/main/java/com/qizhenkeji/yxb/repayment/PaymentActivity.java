package com.qizhenkeji.yxb.repayment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.custom.BaseTitlebar;
import com.cmm.zjwz.custom.ItemViewListener;
import com.cmm.zjwz.custom.LongAdapter;
import com.cmm.zjwz.custom.NoScrollListView;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.adapter.CardCheckAdapter;
import com.qizhenkeji.yxb.helper.MyCreditCardListHelper;
import com.qizhenkeji.yxb.model.CreditCardModel;
import com.qizhenkeji.yxb.model.MyCreditCardListmodel;
import com.qizhenkeji.yxb.popup.CommonPopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hc101 on 18/6/16.
 */

public class PaymentActivity extends BaseActivity implements View.OnClickListener, CommonPopupWindow.ViewInterface, ItemViewListener{
    private LinearLayout actPm;
    private CommonPopupWindow popupWindow;
    private NoScrollListView listView;
    private List<CreditCardModel> mList;
    private LongAdapter adapter;
    private int bankId;
    private CardCheckAdapter mAdapter;
    private int tempPosition = -1;  //记录已经点击的CheckBox的位置
    private ListView lvCheck;
    private ArrayList<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initView();
        initAdapter();
    }

    private void initView() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("智能还款");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView tvChooseCreditCard = (TextView) findViewById(R.id.tv_choose_credit_card);
        actPm = (LinearLayout) findViewById(R.id.activity_payment);
        tvChooseCreditCard.setOnClickListener(this);

        listView = (NoScrollListView) findViewById(R.id.lv_payment);
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
     *
     * @param isrefresh 是否为刷新
     * @param data      接口请求数据
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
        adapter = new LongAdapter(this, mList, this, "listView");
//        listView.getRefreshableView().setAdapter(adapter);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    /**
     * 列表子项内容加载监听
     *
     * @param
     * @param itemView 列表子项布局
     * @param vg
     * @param data     列表子项布局数据
     */
    @Override
    public View getView(int position, View itemView, ViewGroup vg, Object data, String str) {
        MyCreditCardListHelper helper;
        if (itemView == null) {
            itemView = LayoutInflater.from(this).inflate(
                    R.layout.item_my_credit_card, null, false);
//            itemView.findViewById(R.id.tv_modify).setOnClickListener(this);
            helper = new MyCreditCardListHelper(this, itemView);
            itemView.setTag(helper);
        } else {
            helper = (MyCreditCardListHelper) itemView.getTag();
        }
        helper.updateView((CreditCardModel) data);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return itemView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_choose_credit_card:  //选择信用卡
                initPopWindows(v);
                break;

            case R.id.tv_choose_confirm:  //选择信用卡 确定
                if (bankId == 0){
                    toast("请选择一张信用卡！");
                    return;
                }
                toast("选择的银行卡的ID是：" + bankId);
                popupWindow.dismiss();
                initAuthorizationPopWindows(v);
                toast(mData.get(bankId));

//                int selectPosition = mAdapter.getSelectPosition();
//                if (selectPosition >= 0) {
//                    CreditCardModel creditCardModel = mList.get(selectPosition);
//                    toast(creditCardModel.bank_name);
//                }
                break;

            case R.id.tv_code:   //获取验证码
                break;

            case R.id.tv_authorization_confirm:  //还款授权 确定
                popupWindow.dismiss();
                break;

//            case R.id.tv_modify:    //  修改
//
//                break;
//
//            case R.id.tv_unbind:  // 解绑
//                break;
//
//            case R.id.tv_payment_list:  //还款列表
//                break;
//
//            case R.id.tv_new_create:   //新建计划
//                break;
        }
    }

    private void initAuthorizationPopWindows(View v) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_authorization)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//                .setAnimationStyle(R.style.animTranslate)
                .setBackGroundLevel(0.3f)
                .setViewOnclickListener(this)
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
    }

    private void initPopWindows(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popwindow_choose_credit_card)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//                .setAnimationStyle(R.style.animTranslate)
                .setBackGroundLevel(0.3f)
                .setViewOnclickListener(this)
                .setOutsideTouchable(true)
                .create();
//        popupWindow.showAsDropDown(rlCM);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId) {
            case R.layout.popwindow_choose_credit_card:
                TextView tvConfirm = (TextView) view.findViewById(R.id.tv_choose_confirm);
                final ListView lvCheck = (ListView) view.findViewById(R.id.lv_credit_card_check);
                lvCheck.setAdapter(new ArrayAdapter<String>(this, R.layout.item_lv_single_choice, mData));
                lvCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int checkedItemPosition = lvCheck.getCheckedItemPosition();
//                        toast(mData.get(checkedItemPosition));
                        bankId = checkedItemPosition;
                    }
                });
//                mAdapter = new CardCheckAdapter(this, mList);
//                lvCheck.setAdapter(mAdapter);
//                lvCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        CardCheckAdapter.ViewHolder holder = (CardCheckAdapter.ViewHolder) view.getTag();
//                        holder.checkBox.toggle();
//                        mAdapter.notifyDataSetChanged();
//                    }
//                });
                tvConfirm.setOnClickListener(this);
                break;

            case R.layout.popwindow_authorization:
                view.findViewById(R.id.tv_authorization_confirm).setOnClickListener(this);
                break;
        }
    }

    private void initData() {
        CreditCardModel model = new CreditCardModel(11, "平安银行", "zhangsan", "123213xxxx13");
        CreditCardModel model2 = new CreditCardModel(20, "交通银行", "zhangsan", "99213xxxx13");
        CreditCardModel model3 = new CreditCardModel(3, "中国银行", "zhangsan", "663213xxxx13");

        CreditCardModel model4 = new CreditCardModel(111, "工商银行", "zhangsan1", "4523213xxxx13");
        CreditCardModel model5 = new CreditCardModel(202, "农业银行", "zhangsan1", "46213xxxx13");
        CreditCardModel model6 = new CreditCardModel(333, "稳住银行", "zhangsan1", "473213xxxx13");

        mList.add(model);
        mList.add(model2);
        mList.add(model3);

        mList.add(model4);
        mList.add(model5);
        mList.add(model6);

        mData = new ArrayList<>();
        mData.add(model.bank_name + "    " + model.bank_no);
        mData.add(model2.bank_name + "    " + model2.bank_no);
        mData.add(model3.bank_name + "    " + model3.bank_no);

        mData.add(model4.bank_name + "    " + model4.bank_no);
        mData.add(model5.bank_name + "    " + model5.bank_no);
        mData.add(model6.bank_name + "    " + model6.bank_no);
    }

}
