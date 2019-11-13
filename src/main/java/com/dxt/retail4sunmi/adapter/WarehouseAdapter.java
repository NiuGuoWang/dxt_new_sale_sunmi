package com.dxt.retail4sunmi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.activity.WarehouseActivity;
import com.dxt.retail4sunmi.entity.UserBean;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WarehouseAdapter extends RecyclerView.Adapter {
    private List<UserBean.WarehouseInfoListBean> list;
    private Context context;
    private HashMap<String, Boolean> states = new HashMap<>();
    private WarehouseActivity activity;
    public WarehouseAdapter(List<UserBean.WarehouseInfoListBean> list, Context context,WarehouseActivity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.warehouse_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder holder1 = (ViewHolder) holder;
        holder1.radioText.setText(list.get(position).getWarehouseName());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 重置，确保最多只有一项被选中
                for (String key : states.keySet()) {
                    states.put(key, false);
                }
                states.put(String.valueOf(position), true);
                WarehouseAdapter.this.notifyDataSetChanged();
                UserBean.WarehouseInfoListBean bean = list.get(position);
                Intent intent1 = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("warehouse", bean);
                intent1.putExtras(bundle);
                activity.setResult(activity.RESULT_OK,intent1);
                activity.finish();
            }
        });
        holder1.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 重置，确保最多只有一项被选中
                for (String key : states.keySet()) {
                    states.put(key, false);
                }
                states.put(String.valueOf(position), true);
                WarehouseAdapter.this.notifyDataSetChanged();
                UserBean.WarehouseInfoListBean bean = list.get(position);
                Intent intent1 = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("warehouse", bean);
                intent1.putExtras(bundle);
                activity.setResult(activity.RESULT_OK,intent1);
                activity.finish();
            }
        });
        boolean res = false;
        if (states.get(String.valueOf(position)) == null|| states.get(String.valueOf(position)) == false) {
            res = false;
            states.put(String.valueOf(position), false);
        } else
            res = true;

        holder1.radioButton.setChecked(res);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_radio_text)
        TextView radioText;
        @BindView(R.id.rb_radio_button)
        RadioButton radioButton;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
