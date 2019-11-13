package com.dxt.retail4sunmi.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.adapter.WarehouseAdapter;
import com.dxt.retail4sunmi.entity.UserBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WarehouseActivity extends BaseActivity {

    @BindView(R.id.warehouse_im)
    ImageView warehouseIm;
    @BindView(R.id.warehouse_recy)
    RecyclerView warehouseRecy;
    private List<UserBean.WarehouseInfoListBean> list = new ArrayList<>();
    private WarehouseAdapter warehouseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMContentView(R.layout.activity_warehouse);
        ButterKnife.bind(this);
        tvTitle.setText("仓库列表");

        initView();
        initData();
    }

    private void initView() {
        warehouseRecy.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        list = getIntent().getParcelableArrayListExtra("warehouselist");
        warehouseAdapter = new WarehouseAdapter(this.list, this,this);
        warehouseRecy.setAdapter(warehouseAdapter);
        warehouseAdapter.notifyDataSetChanged();
    }

}
