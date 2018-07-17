package com.qizhenkeji.yxb.helper;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.model.CreditCardModel;

/**
 * Created by hc101 on 18/6/20.
 */

public class ChooseCreditCardHelper {

    private final Context mContext;
    private final View mView;
    private CheckBox cbChooseCard;
    private TextView tvBankName;
    private TextView tvBankNo;

    public ChooseCreditCardHelper(Context context, View view){
        mContext = context;
        mView = view;
        findView();
    }

    private void findView() {
//        cbChooseCard = (CheckBox) mView.findViewById(R.id.cb_choose_crad);
        tvBankName = (TextView) mView.findViewById(R.id.tv_bank_name);
        tvBankNo = (TextView) mView.findViewById(R.id.tv_bank_no);
    }

    public void updateView(CreditCardModel data){
        tvBankName.setText(data.bank_name);
        tvBankNo.setText(data.bank_no);
    }

}
