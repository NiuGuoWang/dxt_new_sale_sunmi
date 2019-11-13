package com.dxt.retail4sunmi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.activity.OrderDetailActivity;
import com.dxt.retail4sunmi.entity.SearchOrderBean;
import com.dxt.retail4sunmi.util.DateUtils;

import java.util.List;


/**
 * Created by liu04 on 2017/9/20.
 */

public class OrderDataAdapter extends BaseAdapter {

    private List<SearchOrderBean.ResultBean> mList;
    private Context mContext;

    public OrderDataAdapter(Context context, List<SearchOrderBean.ResultBean> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ordermanagerlist_listitem, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            viewHolder.payBtn.setTag(position);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final SearchOrderBean.ResultBean order = mList.get(position);
        viewHolder.orderIdView.setText(order.getVchcode());
        viewHolder.createDateView.setText(DateUtils.getDate(order.getCreateDate()));
        String text ="";
        switch (order.getStatus()){
            case "19": text ="已退货";break;
            case "12": text ="已取消";break;
            case "18":text ="支付完成";break;
            case "20": text ="已评论";break;
            case "16": text ="待支付";break;
            case "22": text ="支付失败";break;
        }
        viewHolder.tv_orderState.setText(text);

        viewHolder.payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra("vchcode", mList.get(position).getVchcode());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


    class ViewHolder {
        TextView orderIdView;
        TextView createDateView;
        TextView payBtn;
        TextView tv_orderState;

        ViewHolder(View itemView) {
            orderIdView = itemView.findViewById(R.id.text_orderManagerList_orderId);
            createDateView = itemView.findViewById(R.id.text_orderManagerList_date);
            payBtn = itemView.findViewById(R.id.text_orderManagerList_btn);
            tv_orderState = itemView.findViewById(R.id.tv_orderState);
        }
    }


    private Activity getActivity(Context context) {
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }

        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }
}
