package com.qizhenkeji.yxb.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cmm.zjwz.custom.BaseTitlebar;
import com.qizhenkeji.yxb.R;

/**
 * Created by hc101 on 18/6/13.
 */

public class UserFragment extends Fragment implements View.OnClickListener {
    private View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        BaseTitlebar titlebar = (BaseTitlebar) view.findViewById(R.id.title_bar);
        titlebar.setTitle("我的");

        LinearLayout llCR = (LinearLayout) view.findViewById(R.id.ll_cashier_record);
        llCR.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_cashier_record:
                startActivity(new Intent(getContext(), CashierRecordActivity.class));
                break;
        }
    }
}
