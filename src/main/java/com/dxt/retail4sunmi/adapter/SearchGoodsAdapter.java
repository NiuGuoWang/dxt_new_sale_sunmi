package com.dxt.retail4sunmi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.entity.GoodsBean;
import com.mcxtzhang.lib.AnimShopButton;

import java.util.List;

public class SearchGoodsAdapter extends BaseAdapter {
    private List<GoodsBean> mList;
    private Context mContext;

    public SearchGoodsAdapter(List<GoodsBean> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }


    public List<GoodsBean> getData() {
        return mList;
    }


    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_shoppcart,
                    parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GoodsBean goodsBean = mList.get(position);
        viewHolder.tvProductName.setText(goodsBean.getName());
        viewHolder.stock.setText("库存 "+goodsBean.getKcnum());
        viewHolder.price.setText("￥"+goodsBean.getRetailPrice());
        viewHolder.shoppcart_btn.setMaxCount(Integer.parseInt(goodsBean.getKcnum()));
        return convertView;
    }


    class ViewHolder {
        TextView tvProductName;
        ImageView ivItem;
        TextView stock;
        TextView price;
        AnimShopButton shoppcart_btn;
        ViewHolder(View itemView) {
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            ivItem = itemView.findViewById(R.id.iv_item);
            stock = itemView.findViewById(R.id.shoppcart_stock);
            price = itemView.findViewById(R.id.shoppcart_price);
            shoppcart_btn = itemView.findViewById(R.id.shoppcart_btn);
        }
    }
}
