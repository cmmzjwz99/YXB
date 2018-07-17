package com.qizhenkeji.yxb.helper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.model.PaymentDetailModel;

/**
 * Created by hc101 on 18/6/21.
 */

public class PaymentDetailHelper {

    private final Context mContext;
    private final View mView;
    private TextView tvTime;
    private TextView tvType;
    private TextView tvLines;
    private TextView tvFees;
    private LinearLayout llDetailItem;


    public PaymentDetailHelper(Context context, View view){
        mContext = context;
        mView = view;
        findView();
    }

    private void findView() {
        llDetailItem = (LinearLayout) mView.findViewById(R.id.ll_detail_item);
        tvTime = (TextView) mView.findViewById(R.id.tv_time);
        tvType = (TextView) mView.findViewById(R.id.tv_type);
        tvLines = (TextView) mView.findViewById(R.id.tv_lines);
        tvFees = (TextView) mView.findViewById(R.id.tv_fees);
    }

    public void updateView(PaymentDetailModel data,int position) {
        if (position == 0){
            llDetailItem.setBackgroundColor(Color.GRAY);
            tvTime.setTextColor(Color.WHITE);
            tvType.setTextColor(Color.WHITE);
            tvLines.setTextColor(Color.WHITE);
            tvFees.setTextColor(Color.WHITE);
        } else if ( position % 2 ==0){
            tvType.setTextColor(Color.BLUE);
            tvLines.setTextColor(Color.BLUE);
            tvFees.setTextColor(Color.RED);
        } else {
            tvType.setTextColor(Color.RED);
            tvLines.setTextColor(Color.RED);
            tvFees.setTextColor(Color.RED);
        }
        tvTime.setText(data.time);
        tvType.setText(data.type);
        tvLines.setText(data.lines);
        tvFees.setText(data.fees);
    }
}
