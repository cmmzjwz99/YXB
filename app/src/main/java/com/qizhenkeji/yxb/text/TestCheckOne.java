package com.qizhenkeji.yxb.text;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.utils.Utils;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.model.CreditCardModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ListenerGao on 2016/9/12.
 * <p/>
 * ListView实现单选
 */
public class TestCheckOne extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = "TestCheckOne";
    private List<CreditCardModel> mList;

    private TestCheckOneAdapter mAdapter;
    private ListView lvCheck;
    private Button btConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        initView();
//        initData();
    }

    private void initView() {
        lvCheck = (ListView) findViewById(R.id.lv_check);
        btConfirm = (Button) findViewById(R.id.bt_confirm);
        btConfirm.setOnClickListener(this);
        initData2();
        mAdapter = new TestCheckOneAdapter(this, mList);
        lvCheck.setAdapter(mAdapter);

        lvCheck.setOnItemClickListener(this);
    }

    private void initData() {
        initData2();
        mAdapter = new TestCheckOneAdapter(this, mList);
        lvCheck.setAdapter(mAdapter);

        lvCheck.setOnItemClickListener(this);

    }


    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bt_confirm:
                //根据得到的位置,获取选中item的数据Bean
                int selectPosition = mAdapter.getSelectPosition();
                CreditCardModel checkBean = mList.get(selectPosition);
                Utils.toast(TestCheckOne.this, checkBean.bank_name);
                break;
        }
    }

    //设置ListView的点击事件,实现CheckBox联动效果.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TestCheckOneAdapter.ViewHolder holder = (TestCheckOneAdapter.ViewHolder) view.getTag();
        holder.checkBox.toggle();
    }


    private void initData2() {
        mList = new ArrayList<>();
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
    }
}
