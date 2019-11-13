package com.dxt.retail4sunmi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.adapter.OrderDeatailAdapter;
import com.dxt.retail4sunmi.api.ApiService;
import com.dxt.retail4sunmi.constant.StringConstant;
import com.dxt.retail4sunmi.entity.BaseResult;
import com.dxt.retail4sunmi.entity.InfoListBean;
import com.dxt.retail4sunmi.entity.SubmitOrderBean;
import com.dxt.retail4sunmi.network.RxHttpUtils;
import com.dxt.retail4sunmi.network.base.BaseObserver;
import com.dxt.retail4sunmi.network.interceptor.DialogTransformer;
import com.dxt.retail4sunmi.network.interceptor.SchedulerTransformer;
import com.dxt.retail4sunmi.util.DateUtils;
import com.dxt.retail4sunmi.util.DesUtils;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailActivity extends BaseActivity {
    //未支付  21
    //  已支付：18未评论  20  是已评论
    //已退款 19
    private TextView tv_orderState;
    private TextView tv_orderTime;
    private TextView tv_shouldPay;
    private TextView tv_total;
    private TextView tv_quan;
    private TextView tv_activity;
    private ListView lv_orderGood;
    private Button btn_refreshOrderSate;
    private Button btn_pay;
    private Button btn_refund;
    private SubmitOrderBean toPayBean;
    private String state;
    private String orderTime;
    private String vchcode;
    private double shouldPayPrice;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMContentView(R.layout.activity_order_detail);
        initData();
        initView();
        setListener();
    }

    private void setListener() {
        btn_refreshOrderSate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cheackOrder();
                } catch (Exception e) {
                    ToastUtils.showLong("校验失败");
                    e.printStackTrace();
                }
            }
        });
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailActivity.this, OrderPayActivity.class);
                intent.putExtra(StringConstant.SUBMIT_BEAN, toPayBean);
                startActivity(intent);
            }
        });
        btn_refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refund("0");
            }
        });
    }

    private void initView() {
        tv_orderState = findViewById(R.id.tv_orderState);
        tv_quan = findViewById(R.id.tv_quan);
        tv_activity = findViewById(R.id.tv_activity);
        TextView tv_orderNo = findViewById(R.id.tv_orderNo);
        tv_orderTime = findViewById(R.id.tv_orderTime);
        lv_orderGood = findViewById(R.id.lv_orderGood);
        tv_shouldPay = findViewById(R.id.tv_shouldPay);
        tv_total = findViewById(R.id.tv_total);
        btn_refreshOrderSate = findViewById(R.id.btn_refreshOrderSate);
        btn_pay = findViewById(R.id.btn_pay);
        btn_refund = findViewById(R.id.btn_refund);
        tv_orderNo.setText(vchcode);

    }

    private void cheackOrder() throws Exception {
        String destFileDir = DxtSaleApplication.getInstance().getExternalFilesDir(null) + File.separator;
        File fileList = new File(destFileDir);
        List list = Arrays.asList(fileList.list());
        if (list.contains(vchcode)) {
            file = new File(destFileDir + "/" + vchcode);
            String str = readFile(file);
            DesUtils des = new DesUtils("dxt123"); //自定义密钥
            String info = des.decrypt(str);
            JSONObject trans = new JSONObject(info);
            notifyServer(trans);
        } else {
            ToastUtils.showLong("校验完成，订单状态正常");
        }

    }

    private void initData() {
        tvTitle.setText("订单详情");
        vchcode = getIntent().getStringExtra("vchcode");
        getDetail();
    }

    private void getDetail() {
        Map<String, Object> us = new HashMap<>();
        us.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        us.put("vchcode", vchcode);
        us.put("method", "/retail/retailOrder/findOrderInfo");//是否手机登录0否，1是
        us.put("timestamp", System.currentTimeMillis());
        String sign = Util.returnSign(us, Util.SIGN_KEY);
        us.put("sign", sign);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(us);
        RxHttpUtils
                .createApi(ApiService.class)
                .searchOrderInfo(toJson)
                .compose(SchedulerTransformer.<SubmitOrderBean>transformer())
                .compose(new DialogTransformer(this).<SubmitOrderBean>transformer())
                .subscribe(new BaseObserver<SubmitOrderBean>() {
                    @Override
                    protected void onSuccess(SubmitOrderBean bean) {
                        if ((StringConstant.SUCCESS).equals(bean.getFlag())) {
                            toPayBean = bean;
                            List<InfoListBean> list = new ArrayList<>();
                            List<SubmitOrderBean.ResultBean.RetailOrderInfoListBean> tempList = bean.getResult().getRetailOrderInfoList();
                            for (SubmitOrderBean.ResultBean.RetailOrderInfoListBean listBean : tempList) {
                                if ("1".equals(listBean.getActivityFlag())) {
                                    list.add(new InfoListBean(listBean.getProductName(), listBean.getProductPrice(), listBean.getProductNum()));
                                }
                            }
                            state = bean.getResult().getStatus();
                            checkState(state);
                            orderTime = DateUtils.getDate(bean.getResult().getCreateDate());
                            tv_orderTime.setText(orderTime);
                            OrderDeatailAdapter orderPayAdapter = new OrderDeatailAdapter(OrderDetailActivity.this, list);
                            lv_orderGood.setAdapter(orderPayAdapter);
                            shouldPayPrice = Double.parseDouble(bean.getResult().getProceedsAmount());
                            tv_shouldPay.setText(String.valueOf(shouldPayPrice));
                            tv_total.setText(String.valueOf(bean.getResult().getProductAmount()));
                            tv_quan.setText(String.valueOf(bean.getResult().getOtherAmount()));
                            tv_activity.setText(String.valueOf(bean.getResult().getDiscountsAmount()));
                        } else {
                            ToastUtils.showLong(bean.getMsg());
                        }
                    }
                });
    }


    public String readFile(File file) throws IOException {
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream in = new FileInputStream(file);
        in.read(filecontent);
        in.close();
        return new String(filecontent);
    }

    public void notifyServer(final JSONObject trans) {
        //点击支付
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> retailPaysMap = new HashMap<>();
        retailPaysMap.put("payMoney", shouldPayPrice);
        retailPaysMap.put("shroffMethod", "手持pos支付");
        retailPaysMap.put("shroffType", "6");
        ArrayList<Map<String, Object>> retailPays = new ArrayList<>();
        retailPays.add(retailPaysMap);
        map.put("vchcode", vchcode);
        map.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        map.put("proceedsAmount", shouldPayPrice);//实收金额
        map.put("combinationOfProceeds", 1);//支付方式 种类数量
        map.put("retailPays", retailPays);
        try {
            map.put("terminalNo", trans.getString("terminalNo"));//设备终端号（必填）
            map.put("traceNo", trans.getString("traceNo"));//退款凭证号（必填）
            String memInfo = trans.getString("memInfo");
            if (!TextUtils.isEmpty(memInfo)) {
                JSONObject memInfoObject = new JSONObject(memInfo);
                String orderNo = memInfoObject.getString("orderNo");
                String transNo = memInfoObject.getString("transNo");
                map.put("orderNo", orderNo);//第三方商户订单号（不必填）
                map.put("serialNumber", transNo);//流水号（不必填，同transNo）
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        map.put("method", "/retail/retailOrder/payOrder");
        map.put("timestamp", System.currentTimeMillis());

        String sign = Util.returnSign(map, Util.SIGN_KEY);
        map.put("sign", sign);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(map);

        RxHttpUtils
                .createApi(ApiService.class)
                .orderPay(toJson)
                .compose(SchedulerTransformer.<BaseResult>transformer())
                .compose(new DialogTransformer(OrderDetailActivity.this).<BaseResult>transformer())
                .subscribe(new BaseObserver<BaseResult>() {

                    @Override
                    protected void onSuccess(BaseResult bean) {
                        if ("success".equals(bean.getFlag())) {
                            ToastUtils.showLong("校验完成，订单状态已修改");
                            btn_pay.setVisibility(View.GONE);
                            tv_orderState.setText("已完成");
                            file.delete();
                        } else {
                            ToastUtils.showLong("校验失败，请稍后再试");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtils.showLongSafe("校验失败，请稍后再试");
                    }
                });
    }


    private void checkState(String state) {
        String stateText;
        switch (state) {

            case "12":
                stateText = "已取消";
                btn_pay.setVisibility(View.GONE);
                btn_refund.setVisibility(View.GONE);
                break;
            case "18":
                btn_pay.setVisibility(View.GONE);
                btn_refund.setVisibility(View.VISIBLE);
                stateText = "交易完成";
                break;
            case "20":
                btn_pay.setVisibility(View.GONE);
                btn_refund.setVisibility(View.VISIBLE);
                stateText = "已评论";
                break;
            case "16":
                stateText = "待支付";
                btn_refund.setVisibility(View.GONE);
                btn_pay.setVisibility(View.VISIBLE);
                tv_orderState.setTextColor(getResources().getColor(R.color.view_color));
                break;
            case "19":
                btn_pay.setVisibility(View.GONE);
                btn_refund.setVisibility(View.GONE);
                stateText = "已退货";
                break;
            case "22":
                stateText = "支付失败";
                btn_refund.setVisibility(View.GONE);
                btn_pay.setVisibility(View.GONE);
                break;
            default:
                btn_refund.setVisibility(View.GONE);
                stateText = "订单状态：" + state;
                btn_pay.setVisibility(View.GONE);
        }
        tv_orderState.setText(stateText);
    }


    private void refund(String amt) {
        if (TextUtils.isEmpty(amt)) {
            amt = String.valueOf(shouldPayPrice);
        }
        Map<String, Object> us = new HashMap<>();
        us.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        us.put("userId", DxtSaleApplication.getInstance().getUserBean().getUserId());
        us.put("vchcode", vchcode);
        us.put("returnAmount", amt);
        us.put("shroffTypeFlag", 0);
        us.put("method", "/retail/retailOrder/salesReturn");//调用方法
        us.put("timestamp", System.currentTimeMillis());//时间戳
        String sign = Util.returnSign(us, Util.SIGN_KEY);
        us.put("sign", sign);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(us);

        RxHttpUtils
                .createApi(ApiService.class)
                .refund(toJson)
                .compose(SchedulerTransformer.<BaseResult>transformer())
                .compose(OrderDetailActivity.this.<BaseResult>bindToLifecycle())
                .compose(new DialogTransformer(OrderDetailActivity.this).<BaseResult>transformer())
                .subscribe(new BaseObserver<BaseResult>() {

                    @Override
                    protected void onSuccess(BaseResult bean) {
                        if ("success".equals(bean.getFlag())) {
                            Intent intent = new Intent(OrderDetailActivity.this, OrderSuccessActivity.class);
                            intent.putExtra("status", 0);
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtils.showLong(bean.getMsg());
                        }
                    }
                });
    }
}
