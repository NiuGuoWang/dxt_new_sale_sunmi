package com.dxt.retail4sunmi.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.adapter.OrderPayAdapter;
import com.dxt.retail4sunmi.api.ApiService;
import com.dxt.retail4sunmi.constant.StringConstant;
import com.dxt.retail4sunmi.entity.BaseResult;
import com.dxt.retail4sunmi.entity.InfoListBean;
import com.dxt.retail4sunmi.entity.QueyOrderStateBean;
import com.dxt.retail4sunmi.entity.SubmitOrderBean;
import com.dxt.retail4sunmi.helper.PrintHelper;
import com.dxt.retail4sunmi.network.RxHttpUtils;
import com.dxt.retail4sunmi.network.base.BaseObserver;
import com.dxt.retail4sunmi.network.interceptor.DialogTransformer;
import com.dxt.retail4sunmi.network.interceptor.SchedulerTransformer;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.Util;
import com.dxt.retail4sunmi.view.dialog.OrderPayingDialog;
import com.dxt.retail4sunmi.zxing.CaptureActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * @author star
 */
public class OrderPayActivity extends BaseActivity {
    private SubmitOrderBean submitOrderBean;
    private List<InfoListBean> mList = new ArrayList<>();
    private List<SubmitOrderBean.ResultBean.RetailOrderInfoListBean> goodsList = new ArrayList<>();
    private ListView mListView;
    private TextView mTv1;
    private TextView mTv2;
    private boolean isPayed;
    private double shouldPayPrice;
    private OrderPayingDialog orderPayingDialog;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rb_scan_pay)
    RadioButton mRbScanPay;
    @BindView(R.id.rb_card_pay)
    RadioButton mRbCardPay;
    @BindView(R.id.rb_alipay_pay)
    RadioButton mRbAlipay;
    @BindView(R.id.rb_wechat_pay)
    RadioButton mRbWechatPay;
    @BindView(R.id.rb_cash_pay)
    RadioButton mRbCashPay;
//    @BindView(R.id.btnTopay)
//            Button btnTopay;
    private final static int ALIPAY_REQUEST_CODE = 111;
    private final static int WECHAT_REQUEST_CODE = 112;
    private Disposable mDisposable;
    private boolean isQuerry = true;

    @OnClick(R.id.btn_toPay)
    void click() {
        if (isPayed) {
            onBackPressed();
        } else {
            clickButton();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMContentView(R.layout.activity_order_pay);
        ButterKnife.bind(this);
        mListView = findViewById(R.id.orderPayList);
        tvTitle.setText("收银台");
        mTv1 = findViewById(R.id.tv1_money);
        mTv2 = findViewById(R.id.tv2_money);
        initData();
    }


    private void initData() {
        submitOrderBean = getIntent().getParcelableExtra(StringConstant.SUBMIT_BEAN);
        List<SubmitOrderBean.ResultBean.RetailOrderInfoListBean> retailOrderInfoList;
        retailOrderInfoList = submitOrderBean.getResult().getRetailOrderInfoList();
        List<SubmitOrderBean.ResultBean.RetailDiscountsInfoListBean> retailDiscountsInfoList = submitOrderBean.getResult().getRetailDiscountsInfoList();

        for (int i = 0; i < retailOrderInfoList.size(); i++) {
            //商品或活动
            if ("0".equals(retailOrderInfoList.get(i).getActivityFlag())) {
                SubmitOrderBean.ResultBean.RetailOrderInfoListBean subBean = retailOrderInfoList.get(i);
                mList.add(new InfoListBean(subBean.getProductName(), subBean.getProductPrice()));
            } else {
                goodsList.add(retailOrderInfoList.get(i));
            }
        }
        for (int i = 0; i < retailDiscountsInfoList.size(); i++) {
            //优惠券或预付金
            SubmitOrderBean.ResultBean.RetailDiscountsInfoListBean subBean = retailDiscountsInfoList.get(i);
            mList.add(new InfoListBean(subBean.getDiscountsName(), subBean.getDiscountsAmount()));
        }
        shouldPayPrice = Double.parseDouble(submitOrderBean.getResult().getProceedsAmount());
        mTv1.setText(String.format("%s", shouldPayPrice));
        mTv2.setText(String.format("%s", submitOrderBean.getResult().getProductAmount()));

        OrderPayAdapter orderPayAdapter = new OrderPayAdapter(this, mList);
        mListView.setAdapter(orderPayAdapter);

    }


    public void clickButton() {
        if (shouldPayPrice == 0) {
            PrintHelper.printTicket(goodsList, submitOrderBean);
            orderSuccess();
            return;
        }
        if (mRbAlipay.isChecked()) {
            Intent intent = new Intent(this, ScanActivity.class);
            startActivityForResult(intent, ALIPAY_REQUEST_CODE);
        } else if(mRbWechatPay.isChecked()){
            Intent intent = new Intent(this, ScanActivity.class);
            startActivityForResult(intent, WECHAT_REQUEST_CODE);
        }else {
            notifyServer("","现金","1");
        }

    }


    public void notifyServer(String code, String shroffMethod, String shroffType) {

//        if (!"1".equals(shroffType)){
            showDialog();
//        }


        //点击支付
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> retailPaysMap = new HashMap<>();
        retailPaysMap.put("payMoney", shouldPayPrice);
        retailPaysMap.put("changeMoney", 0);
        retailPaysMap.put("shroffMethod", shroffMethod);
        retailPaysMap.put("shroffType", shroffType);
        ArrayList<Map<String, Object>> retailPays = new ArrayList<>();
        retailPays.add(retailPaysMap);
        map.put("vchcode", submitOrderBean.getResult().getVchcode());
        map.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        map.put("proceedsAmount", submitOrderBean.getResult().getProceedsAmount());
        map.put("combinationOfProceeds", 1);
        map.put("retailPays", retailPays);
        if (!TextUtils.isEmpty(code)) {
            map.put("auth_code", code);
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
                .compose(new DialogTransformer(OrderPayActivity.this).<BaseResult>transformer())
                .subscribe(new BaseObserver<BaseResult>() {

                    @Override
                    protected void onSuccess(BaseResult bean) {
                        if ("success".equals(bean.getFlag())) {
                            querryPayState();
                        } else {
                            if (bean.getMsg().contains("USERPAYING")) {
                                if (!isQuerry) {
                                    querryPayState();
                                }
                            } else {
                                isQuerry = false;
                                if (orderPayingDialog!=null){
                                    orderPayingDialog.dismiss();
                                }
//                                ToastUtils.showLongSafe( bean.getMsg());
                                showMsgDialog("错误", bean.getMsg());
                            }
                        }
                    }
                });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, QuickOrderActivity.class);
        startActivity(intent);
        finish();
    }

    public void orderSuccess() {
        Intent intent = new Intent(OrderPayActivity.this, OrderSuccessActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (ALIPAY_REQUEST_CODE == requestCode) {
            if (Activity.RESULT_OK == resultCode) {
                if (null != data) {
                    isPayed = true;
                    Bundle bundle = data.getExtras();
                    String scanText = bundle != null ? bundle.getString("result") : null;
                    notifyServer(scanText, "支付宝当面付", "9");
                } else {
                    ToastUtils.showLong("Intent is null");
                }
            } else {
                ToastUtils.showLong("resultCode is not RESULT_OK");
            }
        } else if (WECHAT_REQUEST_CODE == requestCode) {
            if (Activity.RESULT_OK == resultCode) {
                if (null != data) {
                    isPayed = true;
                    Bundle bundle = data.getExtras();
                    String scanText = bundle != null ? bundle.getString("result") : null;
                    notifyServer(scanText, "微信当面付", "10");
                } else {
                    ToastUtils.showLong("系统错误");
                }
            } else {
                ToastUtils.showLong("用户已取消");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void querryPayState() {
        if (!isQuerry) {
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("vchcode", submitOrderBean.getResult().getVchcode());
        map.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        map.put("method", "/retail/retailOrder/selectOrderStatus");
        map.put("timestamp", System.currentTimeMillis());
        String sign = Util.returnSign(map, Util.SIGN_KEY);
        map.put("sign", sign);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(map);

        RxHttpUtils
                .createApi(ApiService.class)
                .queryOrderState(toJson)
                .compose(SchedulerTransformer.<QueyOrderStateBean>transformer())
                .compose(this.<QueyOrderStateBean>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<QueyOrderStateBean>() {

                    @Override
                    protected void onSuccess(QueyOrderStateBean bean) {
                        if (StringConstant.SUCCESS.equals(bean.getFlag())) {
                            String status = bean.getResult().getStatus();
                            switch (status) {
                                case "16":
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            querryPayState();
                                        }
                                    }, 3000);
                                    break;
                                case "18":
                                    ToastUtils.showLong("收款成功");
                                    orderPayingDialog.dismiss();
                                    PrintHelper.printTicket(goodsList, submitOrderBean);
                                    orderSuccess();
                                    break;
                                default:
                                    ToastUtils.showLong("state:" + status);
                            }
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    querryPayState();
                                }
                            }, 3000);
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                querryPayState();
                            }
                        }, 3000);
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    private void showMsgDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        //点击对话框以外的区域是否让对话框消失
        builder.setCancelable(false);
        //设置正面按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void showDialog() {
        if (orderPayingDialog == null) {
            orderPayingDialog = new OrderPayingDialog();
        }
        if (!orderPayingDialog.isAdded()) {
            orderPayingDialog.show(getFragmentManager(), "orderSuccessDialog");
        }
    }


}
