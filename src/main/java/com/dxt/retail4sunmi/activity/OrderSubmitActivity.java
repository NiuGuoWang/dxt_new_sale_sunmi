package com.dxt.retail4sunmi.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.adapter.Callback;
import com.dxt.retail4sunmi.adapter.OrderUpdateAdapter;
import com.dxt.retail4sunmi.api.ApiService;
import com.dxt.retail4sunmi.constant.StringConstant;
import com.dxt.retail4sunmi.entity.GoodsBean;
import com.dxt.retail4sunmi.entity.SubmitOrderBean;
import com.dxt.retail4sunmi.eventbus.MessageVipEvent;
import com.dxt.retail4sunmi.network.RxHttpUtils;
import com.dxt.retail4sunmi.network.base.BaseObserver;
import com.dxt.retail4sunmi.network.interceptor.DialogTransformer;
import com.dxt.retail4sunmi.network.interceptor.SchedulerTransformer;
import com.dxt.retail4sunmi.util.Arith;
import com.dxt.retail4sunmi.util.DateUtils;
import com.dxt.retail4sunmi.util.SPUtils;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.Util;
import com.dxt.retail4sunmi.view.MyDialog;
import com.dxt.retail4sunmi.view.dialog.EditDialog;
import com.dxt.retail4sunmi.zxing.CaptureActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author star
 */
public class OrderSubmitActivity extends BaseActivity implements View.OnClickListener, Callback {
    private View viewSubmit;
    private ImageView imageSelectVip;
    private ImageView imageClearAll;
    private TextView tvTotal;
    private TextView tvTotalNum;
    private double mTotalSum;
    private TextView tvVip;
    private String memberId;
    private ImageView ivPs;//备注
    private TextView tvPs;
    private View psView;
    private ImageView ivAddCoupon;
    private EditText etAddCoupon;
    private ImageView ivRemoveVip;
    private ListView listView;
    private OrderUpdateAdapter adapter;
    private SPUtils sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMContentView(R.layout.activity_order_submit);

        EventBus.getDefault().register(this);
        initView();
        initData();
        setListener();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageVipEvent messageEvent) {
        String phone = messageEvent.getPhone();
        memberId = messageEvent.getId();
        if (TextUtils.isEmpty(memberId)) {
            memberId = "";
        }
        sp.put("spVip", phone, true);
        sp.put("spVipId", memberId, true);
    }

    @Override
    protected void onResume() {

        super.onResume();
        String spDiscount = sp.getString("spDiscount");
        String spPs = sp.getString("spPs");
        String spVip = sp.getString("spVip");
        memberId = sp.getString("spVipId");
        if (TextUtils.isEmpty(memberId)) {
            adapter.setVip(false);
        } else {
            adapter.setVip(true);
        }
        etAddCoupon.setText(spDiscount);
        if (!TextUtils.isEmpty(spPs)) {
            psView.setVisibility(View.VISIBLE);
        }
        tvPs.setText(spPs);
        if (!TextUtils.isEmpty(spVip)) {
            ivRemoveVip.setVisibility(View.VISIBLE);
        } else {
            ivRemoveVip.setVisibility(View.GONE);
        }
        tvVip.setText(spVip);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sp.put("spDiscount", etAddCoupon.getText().toString(), true);
        sp.put("spPs", tvPs.getText().toString(), true);
        sp.put("spVip", tvVip.getText().toString(), true);
        sp.put("spVipId", memberId, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
//        modifySumPrice();
    }

    private void setListener() {
        viewSubmit.setOnClickListener(this);
        imageSelectVip.setOnClickListener(this);
        imageClearAll.setOnClickListener(this);
        ivPs.setOnClickListener(this);
        ivAddCoupon.setOnClickListener(this);
        ivRemoveVip.setOnClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        sp = SPUtils.getInstance("order");
        List<GoodsBean> lists = intent.getParcelableArrayListExtra("orderList");
        adapter = new OrderUpdateAdapter(OrderSubmitActivity.this, lists, this);
        listView.setAdapter(adapter);
        double totalSum = 0.0;
        int totalNum = lists.size();
        for (int i = 0; i < totalNum; i++) {
            GoodsBean bean = lists.get(i);
            totalSum = Arith.add(totalSum, Arith.mul(bean.getNum(), bean.getRetailPrice()));
        }
        tvTotal.setText(String.valueOf(totalSum));
        tvTotalNum.setText(MessageFormat.format("共{0}件", lists.size()));
    }

    private void initView() {
        tvTitle.setText("提交订单");
        psView = findViewById(R.id.view_ps);
        ivPs = findViewById(R.id.iv_ps);
        tvPs = findViewById(R.id.tv_ps);
        tvVip = findViewById(R.id.tv_vip);
        viewSubmit = findViewById(R.id.btn_submit);
        tvTotalNum = findViewById(R.id.tv_total_num);
        tvTotal = findViewById(R.id.tv_total);
        imageClearAll = findViewById(R.id.image_clear_all);
        imageSelectVip = findViewById(R.id.image_select_vip);
        listView = findViewById(R.id.listView);
        ivRemoveVip = findViewById(R.id.iv_remove_vip);
//        View footerView = ((LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.order_submit_footview, null, false);
//        listView.addFooterView(footerView);
        ivAddCoupon = findViewById(R.id.iv_add_coupon);
        etAddCoupon = findViewById(R.id.et_add_coupon);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_select_vip:
                Intent intent = new Intent(OrderSubmitActivity.this, MemberActivity.class);
                intent.putExtra("type", "select");
                startActivity(intent);
                break;
            case R.id.image_clear_all:
                final MyDialog diaLog = MyDialog.createDialog(this, R.style.MyDialog, R.layout.dialog_delete, 0.25f, 0.8f, getWindowManager());
                TextView cancle = diaLog.getDialogView().findViewById(R.id.delete_cancel);
                TextView determine = diaLog.getDialogView().findViewById(R.id.delete_determine);
                TextView message = diaLog.getDialogView().findViewById(R.id.delete_message);
                message.setText("您是否要删除全部");
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        diaLog.dismiss();
                    }
                });
                determine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.clearAll();
                        cleanText();
                        tvTotalNum.setText("共0件");
                        tvTotal.setText("0");
                        ivRemoveVip.setVisibility(View.INVISIBLE);
                        diaLog.dismiss();
                    }
                });
                if (adapter.adapterList.size() <= 0) {
                    ToastUtils.showLong("没有可以删除的商品");
                    return;
                }
                diaLog.show();
                break;
            case R.id.iv_ps:
                showEditTextDialog();
                break;
            case R.id.iv_remove_vip:
                tvVip.setText("");
                memberId = "";
                adapter.setVip(false);
                ivRemoveVip.setVisibility(View.GONE);
                break;
            case R.id.btn_submit:
                submit();
                break;
            case R.id.iv_add_coupon:
                break;
            default:
                break;
        }
    }


    @Override
    public void scanClick(int position) {
        Intent in = new Intent(this, CaptureActivity.class);
        startActivityForResult(in, position);
    }

    @Override
    public void refreshSum(int totalNum, double totalSum) {
        mTotalSum = totalSum;
        tvTotalNum.setText(MessageFormat.format("共{0}件", totalNum));
        tvTotal.setText(String.valueOf(totalSum));
    }

    //总价、商品价格可正负保留两位小数    商品数量正整数
    private void submit() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> preparedByMap = new HashMap<>(1);
        Map<String, Object> memberMap = new HashMap<>(1);
        memberMap.put("id", sp.getString("spVipId", ""));
        preparedByMap.put("id", DxtSaleApplication.getInstance().getUserBean().getUserId());//用户id
        Map<String, Object> warehouseInfoMap = new HashMap<>(1);
        warehouseInfoMap.put("id", DxtSaleApplication.getInstance().getUserBean().getKtypeid());
        ArrayList<Map<String, Object>> retailDiscountsInfoList = new ArrayList<>();
        String[] coupon = etAddCoupon.getText().toString().split("\\+");
        for (String aCoupon : coupon) {
            Map<String, Object> retailDiscountsInfoMap = new HashMap<>();
            retailDiscountsInfoMap.put("id", "");
            retailDiscountsInfoMap.put("discountsId", aCoupon.trim());
            retailDiscountsInfoMap.put("discountsName", "优惠券");
            retailDiscountsInfoMap.put("discountsAmount", "");
            retailDiscountsInfoMap.put("discountsType", "2");
            retailDiscountsInfoList.add(retailDiscountsInfoMap);
        }
        ArrayList<Map<String, Object>> retailOrderInfoList = new ArrayList<>();

        int sumNum = 0;
        List<GoodsBean> adapterList = adapter.adapterList;
        for (int i = 0; i < adapterList.size(); i++) {
            GoodsBean bean = adapterList.get(i);
            Double realPrice = Double.valueOf(bean.getRealPrice());
            Map<String, Object> retailOrderInfoMap = new HashMap<>();
            if (bean.getNum() == 0) {
                ToastUtils.showLong(bean.getName()+"数量为0");
                return;
            }
            sumNum += bean.getNum();
            if ("1".equals(bean.getImeiManage()) && TextUtils.isEmpty(bean.getImei())) {
                ToastUtils.showLong(MessageFormat.format("请输入{0}的IMEI", bean.getName()));
                return;
            }
            retailOrderInfoMap.put("id", "");
            retailOrderInfoMap.put("productId", bean.getId());
            retailOrderInfoMap.put("productName", bean.getName());
            retailOrderInfoMap.put("productAmount", Arith.mul(realPrice, bean.getNum()));
            retailOrderInfoMap.put("productNum", bean.getNum());
            retailOrderInfoMap.put("productPrice", bean.getRealPrice());//商品最终销售价格
            retailOrderInfoMap.put("productMemberPrice", bean.getMemberPrice());//会员价非必填
            retailOrderInfoMap.put("productSellingPrice", bean.getRetailPrice());//商品标价
            retailOrderInfoMap.put("imeiManage", bean.getImeiManage());//imei 管理1是0否
            retailOrderInfoMap.put("imei", bean.getImei());//imei
            retailOrderInfoMap.put("barCode", bean.getBarCode());//条形码
            retailOrderInfoMap.put("batchManage", bean.getBatchManage());//批次管理
            retailOrderInfoMap.put("activityFlag", 1);//是否活动
            if (bean.getProductMultipleUnit() != null) {
                retailOrderInfoMap.put("productUnit", bean.getProductMultipleUnit().getProductSubUnitList().get(bean.getProductMultipleUnit().getSelectProductUnitNum()).getId());
            } else {
                retailOrderInfoMap.put("productUnit", bean.getProductUnit().getId());
            }
            retailOrderInfoList.add(retailOrderInfoMap);
        }

        map.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        map.put("orderFlag", "2");
        map.put("productNum", sumNum);
//        map.put("billDate", DateUtils.getNowDate());
        map.put("productAmount", mTotalSum);//总价
        map.put("member", memberMap);
        map.put("remarks", tvPs.getText().toString());
        map.put("preparedBy", preparedByMap);//preparedBy
        map.put("warehouseInfo", warehouseInfoMap);//warehouseInfo
        map.put("timestamp", System.currentTimeMillis());//时间戳
        map.put("retailOrderInfoList", retailOrderInfoList);
        map.put("retailDiscountsInfoList", retailDiscountsInfoList);//优惠券
        map.put("method", "/retail/retailOrder/saveOrderInfo");
        String sign = Util.returnSign(map, Util.SIGN_KEY);
        map.put("sign", sign);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(map);

        RxHttpUtils
                .createApi(ApiService.class)
                .orderSubmit(toJson)
                .compose(SchedulerTransformer.<SubmitOrderBean>transformer())
                .compose(new DialogTransformer(OrderSubmitActivity.this).<SubmitOrderBean>transformer())
                .subscribe(new BaseObserver<SubmitOrderBean>() {

                    @Override
                    protected void onSuccess(SubmitOrderBean bean) {
                        if ("success".equals(bean.getFlag())) {
                            Intent intent = new Intent(OrderSubmitActivity.this, OrderPayActivity.class);
                            bean.getResult().setRemarks(tvPs.getText().toString());
                            intent.putExtra(StringConstant.SUBMIT_BEAN, bean);
                            startActivity(intent);
                            cleanText();
                            finish();
                        } else {
                            ToastUtils.showLong(bean.getMsg());
                        }
                    }
                });

    }

    private void showEditTextDialog() {
        final EditDialog dialog = new EditDialog(OrderSubmitActivity.this, tvPs.getText().toString());
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        attributes.width = (int) (metrics.widthPixels * 0.9);
        attributes.height = (int) (metrics.heightPixels * 0.9);
        attributes.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        attributes.dimAmount = 0.5f;
        dialog.getWindow().setAttributes(attributes);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog1) {
                tvPs.setText(dialog.getmText());
                psView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK && adapter != null) {
            Bundle bundle = data.getExtras();
            String scanText = bundle != null ? bundle.getString("result") : null;
            GoodsBean goodsBean = adapter.adapterList.get(requestCode);
            if (TextUtils.isEmpty(goodsBean.getImei())) {
                goodsBean.setImei(scanText);
            } else {
                goodsBean.setImei(goodsBean.getImei() + "," + scanText);
            }
            adapter.adapterList.set(requestCode, goodsBean);
            adapter.notifyDataSetChanged();
        }
    }

    private void cleanText() {
        tvVip.setText("");
        memberId = "";
        etAddCoupon.setText("");
        tvPs.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, QuickOrderActivity.class);
        intent.putExtra("list", (Serializable) adapter.adapterList);
        startActivity(intent);
    }
}
