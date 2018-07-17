package com.qizhenkeji.yxb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cmm.zjwz.utils.PrintLog;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.model.CreditCardModel;

import java.util.List;


/**
 * Created by ListenerGao on 2016/9/12.
 */
public class TestCheckOneAdapter extends BaseAdapter {
    private static final String TAG = "TestCheckOneAdapter";
    private Activity mActivity;
    private List<CreditCardModel> mData;

    private int tempPosition = -1;  //记录已经点击的CheckBox的位置

    public TestCheckOneAdapter(Activity context, List<CreditCardModel> data) {
        this.mActivity = context;
        this.mData = data;
        PrintLog.e("data=========" + data);
    }

    @Override
    public int getCount() {
        return mData == null  ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_choose_cardit_card, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvBankName.setText(mData.get(position).bank_name);
        holder.checkBox.setId(position);    //设置当前position为CheckBox的id
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (tempPosition != -1) {
                        //根据id找到上次点击的CheckBox,将它设置为false.
                        CheckBox tempCheckBox = (CheckBox) mActivity.findViewById(tempPosition);
                        if (tempCheckBox != null) {
                            tempCheckBox.setChecked(false);
                        }
                    }
                    //保存当前选中CheckBox的id值
                    tempPosition = buttonView.getId();

                } else {    //当CheckBox被选中,又被取消时,将tempPosition重新初始化.
                    tempPosition = -1;
                }
            }
        });
        if (position == tempPosition)   //比较位置是否一样,一样就设置为选中,否则就设置为未选中.
            holder.checkBox.setChecked(true);
        else holder.checkBox.setChecked(false);
        return convertView;
    }

    //返回当前CheckBox选中的位置,便于获取值.
    public int getSelectPosition() {
        return tempPosition;
    }

    public static class ViewHolder {
       public CheckBox checkBox;
       public TextView tvBankName,tvBankNo;

        public ViewHolder( View itemView) {

            checkBox = (CheckBox) itemView.findViewById(R.id.cb_choose_crad);
            tvBankName = (TextView) itemView.findViewById(R.id.tv_bank_name);
            tvBankNo = (TextView) itemView.findViewById(R.id.tv_bank_no);
        }
    }
}
