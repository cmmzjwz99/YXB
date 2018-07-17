package com.qizhenkeji.yxb.helper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.repayment.PaymentDetailsActivity;
import com.qizhenkeji.yxb.model.CreditCardModel;

/**
 * Created by hc101 on 18/6/21.
 */

public class PaymentListHelper implements View.OnClickListener {

    private final Context mContext;
    private final View mView;
    private TextView tvBankNo;
    private TextView tvDealNumber;
    private TextView tvDealAll;
    private TextView tvPL;
    private TextView tvAL;
    private TextView tvST;
    private TextView tvPT;
    private TextView tvCT;

    private CreditCardModel mData;

    public PaymentListHelper(Context context, View view){
        mContext = context;
        mView = view;
        findView();
    }

    private void findView() {
        tvBankNo = (TextView) mView.findViewById(R.id.tv_bank_no);
        tvDealNumber = (TextView) mView.findViewById(R.id.tv_deal_number);
        tvDealAll = (TextView) mView.findViewById(R.id.tv_deal_all);
        tvPL = (TextView) mView.findViewById(R.id.tv_payment_lines);
        tvAL = (TextView) mView.findViewById(R.id.tv_available_lines);
        tvST = (TextView) mView.findViewById(R.id.tv_start_time);
        tvPT = (TextView) mView.findViewById(R.id.tv_pay_time);
        tvCT = (TextView) mView.findViewById(R.id.tv_create_time);
        RelativeLayout rlQM = (RelativeLayout) mView.findViewById(R.id.rl_query_more);

        rlQM.setOnClickListener(this);
    }

    public void updateView(CreditCardModel data) {
        mData = data;
        tvBankNo.setText(data.bank_no);

    }

    @Override
    public void onClick(View v) {
        mContext.startActivity(new Intent(mContext, PaymentDetailsActivity.class));
    }
}
