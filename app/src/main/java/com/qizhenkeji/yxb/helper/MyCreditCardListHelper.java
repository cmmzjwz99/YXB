package com.qizhenkeji.yxb.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.repayment.PaymentListActivity;
import com.qizhenkeji.yxb.model.CreditCardModel;
import com.qizhenkeji.yxb.popup.CommonPopupWindow;

/**
 * Created by hc101 on 18/6/20.
 */

public class MyCreditCardListHelper implements View.OnClickListener,CommonPopupWindow.ViewInterface {

    private final Context mContext;
    private final View mView;
    private ImageView ivUser;
    private TextView tvName;
    private TextView tvBankName;
    private TextView tvBankNo;
    private TextView tvCVV;
    private TextView tvPeriodValidity;
    private TextView tvBilllDay;
    private TextView tvPaymentDay;
    private CreditCardModel mData;
    private CommonPopupWindow popupWindow;

    public MyCreditCardListHelper(Context context, View view){
        mContext = context;
        mView = view;
        findView();
    }

    private void findView() {
        ivUser = (ImageView) mView.findViewById(R.id.iv_user);
        tvName = (TextView) mView.findViewById(R.id.tv_name);
        tvBankName = (TextView) mView.findViewById(R.id.tv_bank_name);
        tvBankNo = (TextView) mView.findViewById(R.id.tv_bank_no);
        tvCVV = (TextView) mView.findViewById(R.id.tv_cvv);
        tvPeriodValidity = (TextView) mView.findViewById(R.id.tv_period_validity);
        tvBilllDay = (TextView) mView.findViewById(R.id.tv_bill_day);
        tvPaymentDay = (TextView) mView.findViewById(R.id.tv_payment_day);

        mView.findViewById(R.id.tv_modify).setOnClickListener(this);
        mView.findViewById(R.id.tv_unbind).setOnClickListener(this);
        mView.findViewById(R.id.tv_payment_list).setOnClickListener(this);
        mView.findViewById(R.id.tv_new_create).setOnClickListener(this);
    }

    public void updateView(CreditCardModel data) {
//        if (!data.img_url .equals("")){
//            Picasso.with(mContext).load(data.img_url).into(ivUser);
//        }
        tvBankName.setText(data.bank_name);
        tvName.setText(data.name);
        tvBankNo.setText(data.bank_no);
        tvCVV.setText("121212");
        tvPeriodValidity.setText("0609");
        tvBilllDay.setText("0809");
        tvPaymentDay.setText("0606");

        mData = data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_modify:
//                Utils.toast(mContext, mData.id + "");
                initModifyPopWindows(v);
                break;
            case R.id.tv_unbind:
                initUnbindDialog();
                break;
            case R.id.tv_payment_list:
//                Utils.toast(mContext, mData.id + "");
                mContext.startActivity(new Intent(mContext, PaymentListActivity.class));
                break;
            case R.id.tv_new_create:
//                Utils.toast(mContext, mData.bank_name + "");
                initNewCreateDialog(v);
                break;

            case R.id.tv_modify_confirm:   //修改信息  确定
                break;

            case R.id.tv_new_credit_confirm:  // 新建还款计划 确定
                break;
        }
    }

    private void initNewCreateDialog(View v) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.popwindow_new_create)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//                .setAnimationStyle(R.style.animTranslate)
                .setBackGroundLevel(0.3f)
                .setViewOnclickListener(this)
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
    }

    private void initUnbindDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        // 设置参数
        dialog.setMessage("是否确认解绑该信用卡")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                            Log.d(TAG, "onClick: 确定 " + Thread.currentThread().getId());
                        Toast.makeText(mContext, "点击了是", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                            Log.d(TAG, "onClick: 取消 " + Thread.currentThread().getId());
                        Toast.makeText(mContext, "点击了否", Toast.LENGTH_SHORT).show();
                    }
                });
        dialog.create().show();
    }

    public  void initModifyPopWindows(View v) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.popwindow_modify)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//                .setAnimationStyle(R.style.animTranslate)
                .setBackGroundLevel(0.3f)
                .setViewOnclickListener(this)
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId){
            case R.layout.popwindow_modify:
                TextView tvModifyConfirm = (TextView) view.findViewById(R.id.tv_modify_confirm);
                EditText etPeriodValidity = (EditText) view.findViewById(R.id.et_period_validity);
                EditText etCVV = (EditText) view.findViewById(R.id.et_cvv);
                EditText etPaymentDay = (EditText) view.findViewById(R.id.et_payment_day);
                EditText etBillDay = (EditText) view.findViewById(R.id.et_bill_day);

                tvModifyConfirm.setOnClickListener(this);
                break;

            case R.layout.popwindow_new_create:
                EditText etPL = (EditText) view.findViewById(R.id.et_payment_lines);
                EditText etAL = (EditText) view.findViewById(R.id.et_available_lines);
                EditText etDN = (EditText) view.findViewById(R.id.et_deal_number);
                EditText etStartTime = (EditText) view.findViewById(R.id.et_start_time);
                EditText etStopTime = (EditText) view.findViewById(R.id.et_stop_time);
                TextView etNCC = (TextView) view.findViewById(R.id.tv_new_credit_confirm);

                etNCC.setOnClickListener(this);
                break;
        }
    }
}
