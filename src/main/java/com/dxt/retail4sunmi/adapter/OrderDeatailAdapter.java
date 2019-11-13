package com.dxt.retail4sunmi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.entity.InfoListBean;

import java.util.List;

/**
 * Created by liu04 on 2017/12/20.
 */

public class OrderDeatailAdapter extends BaseAdapter {
    private Context mContext;
    private List<InfoListBean> mList;

    public OrderDeatailAdapter(Context context, List<InfoListBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_order_deatial, parent, false);
            holder.textName = convertView.findViewById(R.id.textName);
            holder.texMoney = convertView.findViewById(R.id.textMoney);
            holder.textNum = convertView.findViewById(R.id.textNum);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        InfoListBean bean =mList.get(position);
        holder.textName.setText(bean.getName());
        holder.texMoney.setText(bean.getPrice());
        holder.textNum.setText(bean.getNum());
        return convertView;
    }

    class ViewHolder {
        TextView textName;
        TextView texMoney;
        TextView textNum;
    }
}
