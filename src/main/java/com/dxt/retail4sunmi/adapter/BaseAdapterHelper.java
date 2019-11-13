package com.dxt.retail4sunmi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class BaseAdapterHelper<T> extends BaseAdapter {
    public final List<T> adapterList;
    LayoutInflater mInflater;

    BaseAdapterHelper(Context context, List<T> list) {
        this.adapterList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return adapterList.size();
    }

    @Override
    public T getItem(int position) {
        return adapterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void reloadListView(List<T> data, boolean isClear) {
        if (isClear) {
            adapterList.clear();
        }
        adapterList.addAll(data);
        notifyDataSetChanged();
    }

    public void clearAll() {
        adapterList.clear();
        notifyDataSetChanged();
    }

    //根据list的position删除单条数据
    public void removeItem(int position) {
        adapterList.remove(position);
        notifyDataSetChanged();
    }

    //删除多条数据
    public void removeItems(List<T> list) {
        adapterList.removeAll(list);
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView, parent);
    }

    protected abstract View getItemView(int position, View convertView, ViewGroup parent);

}
