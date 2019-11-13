package com.dxt.retail4sunmi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.entity.QueryMemberBean;

import java.util.List;

/**
 *
 * @author my
 * @date 2017/8/17
 */

public class VipAdapter extends BaseAdapter {

    private List<QueryMemberBean.RestultBean> list;
    private Context context;
    private Callback mCallback;
    private String type;

    public VipAdapter(Context context, List<QueryMemberBean.RestultBean> list, Callback callback, String type) {
        this.context = context;
        this.list = list;
        mCallback = callback;
       this.type= type;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.show_vip_item, parent, false);
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.tvPhoneNum = view.findViewById(R.id.tv_phone_num);
            holder.btnSelect = view.findViewById(R.id.btn_select);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(list.get(i).getName());
        holder.tvPhoneNum.setText(list.get(i).getMobile());
        holder.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.click(view);
            }
        });
        holder.btnSelect.setTag(i);
        if ("onlyShow".equals(type)){
            holder.btnSelect.setVisibility(View.GONE);
        }
        return view;
    }

    class ViewHolder {
        TextView tvName;
        TextView tvPhoneNum;
        Button btnSelect;
    }

    public interface Callback {
        void click(View v);
    }
}
