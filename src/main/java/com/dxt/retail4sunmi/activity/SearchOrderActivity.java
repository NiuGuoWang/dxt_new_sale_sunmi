package com.dxt.retail4sunmi.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.adapter.OrderDataAdapter;
import com.dxt.retail4sunmi.api.ApiService;
import com.dxt.retail4sunmi.entity.SearchOrderBean;
import com.dxt.retail4sunmi.network.RxHttpUtils;
import com.dxt.retail4sunmi.network.base.BaseObserver;
import com.dxt.retail4sunmi.network.interceptor.DialogTransformer;
import com.dxt.retail4sunmi.network.interceptor.SchedulerTransformer;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.Util;
import com.dxt.retail4sunmi.view.PullToRefreshView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author star
 */
public class SearchOrderActivity extends BaseActivity implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener, View.OnClickListener {
    private ListView listView;//列表控件
    private final String pageSize = "10";//每页显示数目
    private List<SearchOrderBean.ResultBean> mList;
    private PullToRefreshView mPullToRefreshView;
    private OrderDataAdapter orderAdapter;//适配器
    private int pageNo = 1;//当前页码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMContentView(R.layout.activity_order_search);
        initView();
        initData();
    }


    private void initData() {

        mList = new ArrayList<>();
        orderAdapter = new OrderDataAdapter(this, mList);
        listView.setAdapter(orderAdapter);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        listView.setOnItemClickListener(new ListViewItemClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchContent();
    }

    private void initView() {
        tvTitle.setText("订单查询");
        mPullToRefreshView = findViewById(R.id.swipe_refresh_layout);
        listView = findViewById(R.id.lv_order);
        //设置列表为空时显示的提示信息
        ImageView isEmpty = findViewById(R.id.isEmpty2);
        listView.setEmptyView(isEmpty);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_quickOrder_search:
                searchContent();
                break;
            default:
                break;
        }
    }


    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageNo++;
                search("loadMore");
            }
        }, 500);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageNo = 1;
                search("refresh");
            }
        }, 500);
    }


    private class ListViewItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                long arg3) {


        }
    }


    /**
     * 搜索方法
     */
    private void searchContent() {
        pageNo = 1;
        search("common");
    }


    private void search(final String type) {
        if (!"loadMore".equals(type)) {
            mList.clear();
            orderAdapter.notifyDataSetChanged();
        }
        Map<String, Object> us = new HashMap<>();
        us.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        us.put("userId", DxtSaleApplication.getInstance().getUserBean().getUserId());
        us.put("status", "");
        us.put("pageNo", String.valueOf(pageNo));
        us.put("pageSize", pageSize);
        us.put("billType", "");
        us.put("orderFlag", "");
        us.put("vchcode", "");
        us.put("beginBillDate", "");
        us.put("endBillDate", "");
        us.put("method", "/retail/retailOrder/list");
        us.put("timestamp", System.currentTimeMillis());
        String sign = Util.returnSign(us, Util.SIGN_KEY);
        us.put("sign", sign);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(us);
        RxHttpUtils
                .createApi(ApiService.class)
                .searchOrder(toJson)
                .compose(SchedulerTransformer.<SearchOrderBean>transformer())
                .compose(new DialogTransformer(SearchOrderActivity.this).<SearchOrderBean>transformer())
                .subscribe(new BaseObserver<SearchOrderBean>() {
                    @Override
                    protected void onSuccess(SearchOrderBean bean) {

                        if ("success".equals(bean.getFlag())) {
                            if (bean.getResult().size() == 0) {
                                if (!"loadMore".equals(type)) {
                                    ToastUtils.showLong("未查询到订单");
                                } else {
                                    ToastUtils.showLong("已经到底了");
                                }
                            }
                            for (int i = 0; i < bean.getResult().size(); i++) {
                                mList.add(bean.getResult().get(i));
                                orderAdapter.notifyDataSetChanged();
                            }
                        } else {
                            ToastUtils.showLong(bean.getMsg());
                        }
                        mPullToRefreshView.onFooterRefreshComplete();
                        mPullToRefreshView.onHeaderRefreshComplete();
                    }
                });
    }

}