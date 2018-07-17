package com.qizhenkeji.yxb.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.model.CreditCardModel;
import com.qizhenkeji.yxb.popup.MyOnclickListener;

import java.util.List;

/**
 * Created by MQ on 2017/4/8.
 */

public class PopupChooseAdapter extends RecyclerView.Adapter<PopupChooseAdapter.MyViewHolder> {
    private Activity mContext;
    private List<CreditCardModel> mList;
    private MyOnclickListener myItemClickListener;
    private int checked = -1;  //记录已经点击的CheckBox的位置

    public PopupChooseAdapter(Activity mContext, List<CreditCardModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setOnItemClickListener(MyOnclickListener listener) {
        this.myItemClickListener = listener;
    }

    public void setChecked(int checked) {//设定一个选中的标志位，在activity中传入值。
        this.checked = checked;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_cardit_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.llCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myItemClickListener != null) {

                    myItemClickListener.onItemClick(v, position);


                }
            }
        });

//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    if (tempPosition != -1) {
//                        //根据id找到上次点击的CheckBox,将它设置为false.
//                        CheckBox tempCheckBox = (CheckBox) mContext.findViewById(tempPosition);
//                        if (tempCheckBox != null) {
//                            tempCheckBox.setChecked(false);
//                            holder.checkBox.setChecked(false);
//                        }
//                    }
//                    //保存当前选中CheckBox的id值
//                    tempPosition = buttonView.getId();
//                    myItemClickListener.onItemClick(buttonView, position);
//                } else {    //当CheckBox被选中,又被取消时,将tempPosition重新初始化.
//                    tempPosition = -1;
//                }
//            }
//        });
//        if (position == tempPosition)   //比较位置是否一样,一样就设置为选中,否则就设置为未选中.
//            holder.checkBox.setChecked(true);
//        else holder.checkBox.setChecked(false);

        holder.tvBankName.setText(mList.get(position).bank_name);
        holder.tvBankNo.setText(mList.get(position).bank_no);
        holder.checkBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public TextView tvBankName, tvBankNo;
        private final LinearLayout llCC;

        public MyViewHolder(final View itemView) {
            super(itemView);
            llCC = (LinearLayout) itemView.findViewById(R.id.ll_choose_crad);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb_choose_crad);
            tvBankName = (TextView) itemView.findViewById(R.id.tv_bank_name);
            tvBankNo = (TextView) itemView.findViewById(R.id.tv_bank_no);
        }
    }
}
