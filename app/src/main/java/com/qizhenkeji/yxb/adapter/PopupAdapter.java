package com.qizhenkeji.yxb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.popup.MyOnclickListener;

import java.util.List;

/**
 * Created by MQ on 2017/4/8.
 */

public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> list;
    private MyOnclickListener myItemClickListener;

    public PopupAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setOnItemClickListener(MyOnclickListener listener) {
        this.myItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_popup_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.choice_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myItemClickListener != null) {
                    myItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout choice_text;
        TextView tvName1,tvName2,tvFees,tvLines,tvType;

        public MyViewHolder(final View itemView) {
            super(itemView);
            choice_text = (LinearLayout) itemView.findViewById(R.id.item_ll_type);
            tvName1 = (TextView) itemView.findViewById(R.id.item_name1);
            tvName2 = (TextView) itemView.findViewById(R.id.item_name2);
            tvType = (TextView) itemView.findViewById(R.id.item_type);
            tvFees = (TextView) itemView.findViewById(R.id.item_fees);
            tvLines = (TextView) itemView.findViewById(R.id.item_lines);

            tvName1.setText("小额积分");
            tvName2.setText("小额专属通道");
            tvType.setText("D0");
            tvFees.setText("5‰");
            tvLines.setText("300.00-2000.00");
        }
    }
}
