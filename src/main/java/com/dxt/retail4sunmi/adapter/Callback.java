package com.dxt.retail4sunmi.adapter;

/**
 * Created by LiuMou on 2017/8/18.
 */

public interface Callback {
    void refreshSum(int totalNum, double price);
    void scanClick(int position);
}
