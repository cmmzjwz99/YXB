package com.qizhenkeji.yxb.helper;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.model.CreditCardModel;
import com.squareup.picasso.Picasso;

/**
 * Created by hc101 on 18/6/16.
 */

public class CreditCardHelper {

    private final Context mContext;
    private final View mView;
    private ImageView ivBank;
    private TextView tvBankName;
    private TextView tvName;
    private TextView tvBankNo;

    public CreditCardHelper(Context context, View view){
        mContext = context;
        mView = view;
        findView();
    }

    private void findView() {
        ivBank = (ImageView) mView.findViewById(R.id.iv_bank);
        tvBankName = (TextView) mView.findViewById(R.id.tv_bank_name);
        tvName = (TextView) mView.findViewById(R.id.tv_name);
        tvBankNo = (TextView) mView.findViewById(R.id.tv_bank_no);
        ImageView ivDelect = (ImageView) mView.findViewById(R.id.iv_delect);
    }

    public void updateView(CreditCardModel data) {
        if (!data.img_url .equals("")){
            Picasso.with(mContext).load(data.img_url).into(ivBank);
        }
        tvBankName.setText(data.bank_name);
        tvName.setText(data.name);
        tvBankNo.setText(data.bank_no);

    }
}
