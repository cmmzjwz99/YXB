package com.qizhenkeji.yxb.helper;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.model.CashierRecordModel;

/**
 * Created by hc101 on 18/6/16.
 */

public class CashierRecordHelper {
    private Context mContext;
    private View mView;
    private TextView tvLines;
    private TextView tvFees;
    private TextView tvRealityLines;
    private TextView tvTime;
    private TextView tvType;
    private TextView tvStatus;


    public CashierRecordHelper(Context context, View view) {
        mContext = context;
        mView = view;
        findView();
    }

    private void findView() {
        tvLines = (TextView) mView.findViewById(R.id.tv_lines);
        tvFees = (TextView) mView.findViewById(R.id.tv_fees);
        tvRealityLines = (TextView) mView.findViewById(R.id.tv_reality_lines);
        tvTime = (TextView) mView.findViewById(R.id.tv_time);
        tvType = (TextView) mView.findViewById(R.id.tv_type);
        tvStatus = (TextView) mView.findViewById(R.id.tv_status);
    }

    /**
     * 更新界面内容
     * @param info
     */
    public void updateView(CashierRecordModel info) {
        tvLines.setText(info.lines);
        tvFees.setText(info.fees);
        tvRealityLines.setText(info.reality_lines);
        tvTime.setText(info.time);
        tvStatus.setText(info.status);
    }
}
