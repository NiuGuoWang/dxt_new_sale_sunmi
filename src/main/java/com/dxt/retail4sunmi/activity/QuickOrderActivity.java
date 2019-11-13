package com.dxt.retail4sunmi.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.adapter.SearchGoodsAdapter;
import com.dxt.retail4sunmi.api.ApiService;
import com.dxt.retail4sunmi.constant.StringConstant;
import com.dxt.retail4sunmi.entity.GoodsBean;
import com.dxt.retail4sunmi.entity.SearchGoodsBean;
import com.dxt.retail4sunmi.entity.UserBean;
import com.dxt.retail4sunmi.network.RxHttpUtils;
import com.dxt.retail4sunmi.network.base.BaseObserver;
import com.dxt.retail4sunmi.network.interceptor.DialogTransformer;
import com.dxt.retail4sunmi.network.interceptor.SchedulerTransformer;
import com.dxt.retail4sunmi.util.SPUtils;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.Util;
import com.dxt.retail4sunmi.view.MyDialog;
import com.dxt.retail4sunmi.view.PullToRefreshView;
import com.dxt.retail4sunmi.zxing.CaptureActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.angmarch.views.NiceSpinner;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * @author star
 */
public class QuickOrderActivity extends BaseActivity implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener, View.OnClickListener, TextWatcher {
    private EditText etSearch;//搜索框
    private ListView listView;//列表控件
    private final String pageSize = "40";//每页显示数目
    private List<GoodsBean> mList;//搜索到的商品集合
    private List<GoodsBean> addList;//添加到购物车的商品集合
    private TextView tvAddMember;//会员中心
    private ImageButton ivClear;//清空搜索框
    private PullToRefreshView mPullToRefreshView;
    private TextView tvOrderManager;//业绩查询
    private SearchGoodsAdapter orderAdapter;//适配器
    private TextView tvSearchNum;//已选择数目
    private View viewOrderSubmit;
    private int pageNo = 1;//当前页码
    private RelativeLayout quickOrder_headRl;
    private final static int SCAN_REQUEST_CODE = 110;//扫码请求码
    private final static int WAREHOUSE_REQUEST_CODE = 111;//仓库请求码
    private ImageButton mSearchView;
    private NiceSpinner niceSpinner;
    private String userLogFlag;
    private TextView quickOrderHeadtext;
    private ArrayList<UserBean.WarehouseInfoListBean> warehouselist;
    private List<String> spinnerData;
    private String ktypeid = "";
    private int search_type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMContentView(R.layout.activity_quick_order);
        tvTitle.setText(SPUtils.getInstance(StringConstant.DXT_POS).getString(StringConstant.USERNAME));
        userLogFlag = SPUtils.getInstance(StringConstant.DXT_POS).getString(StringConstant.USERLOGFLAG);
        initView();
        initData();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (addList.size() > 0) {
            tvSearchNum.setText(MessageFormat.format("({0})", addList.size()));
        } else {
            tvSearchNum.setText("");
        }
    }

    private void initData() {

        if (TextUtils.isEmpty(DxtSaleApplication.getInstance().getUserBean().getKtypeid())) {
            showPopupMenu();
        }
        mList = new ArrayList<>();
        addList = new ArrayList<>();
        spinnerData = new LinkedList<>(Arrays.asList("商品搜索", "模板搜索"));
        niceSpinner.attachDataSource(spinnerData);
        ktypeid =  DxtSaleApplication.getInstance().getUserBean().getKtypeid();
        Intent intent = getIntent();
        if (intent != null && intent.getParcelableArrayListExtra("list") != null) {
            addList = intent.getParcelableArrayListExtra("list");
        }
        orderAdapter = new SearchGoodsAdapter(mList, QuickOrderActivity.this);
        listView.setAdapter(orderAdapter);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        listView.setOnItemClickListener(new ListViewItemClick());
        warehouselist = getIntent().getParcelableArrayListExtra("warehouselist");
        quickOrderHeadtext.setText(DxtSaleApplication.getInstance().getUserBean().getKtypeName());
    }

    private void setListener() {
        tvAddMember.setOnClickListener(this);
        tvOrderManager.setOnClickListener(this);
        etSearch.addTextChangedListener(this);
        mSearchView.setOnClickListener(this);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchContent();
                }
                return false;
            }
        });
        viewOrderSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addList.size() == 0) {
                    ToastUtils.showLong("未选择商品");
                    return;
                }
                Intent intent = new Intent(QuickOrderActivity.this, OrderSubmitActivity.class);
                intent.putExtra("orderList", (Serializable) addList);
                startActivity(intent);
            }
        });
        quickOrder_headRl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuickOrderActivity.this, WarehouseActivity.class);
                if (warehouselist != null) {
                    intent.putParcelableArrayListExtra("warehouselist", warehouselist);
                }
//                startActivity(intent);
                startActivityForResult(intent, WAREHOUSE_REQUEST_CODE);
            }
        });

        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerData.get(position).equals("商品搜索")) {
                    search_type = 1;
                } else {
                    search_type = 2;
                }
            }
        });
    }

    private void initView() {
        quickOrder_headRl = findViewById(R.id.quickOrder_headRl);
        viewOrderSubmit = findViewById(R.id.view_order_submit);
        tvOrderManager = findViewById(R.id.tv_order_manager);
        tvAddMember = findViewById(R.id.tv_add_member);
        tvSearchNum = findViewById(R.id.tv_search_num);
        mPullToRefreshView = findViewById(R.id.swipe_refresh_layout);
        etSearch = findViewById(R.id.et_search);
        View scanView = findViewById(R.id.scan_ma);
        mSearchView = findViewById(R.id.btn_quickOrder_search);
        niceSpinner = findViewById(R.id.text_nice_spinner);
        ivClear = findViewById(R.id.iv_clear);
        quickOrderHeadtext = findViewById(R.id.quickOrder_headtext);
        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.setText("");
            }
        });
        scanView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 扫描功能
                if (ContextCompat.checkSelfPermission(QuickOrderActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //申请CAMERA权限
                    ActivityCompat.requestPermissions(QuickOrderActivity.this, new String[]{Manifest.permission.CAMERA}, 3);
                } else {
                    toCaptureActivity();
                }
            }
        });
        listView = findViewById(R.id.lv_order);
        //设置列表为空时显示的提示信息
        ImageView isEmpty = findViewById(R.id.isEmpty2);
        listView.setEmptyView(isEmpty);

        niceSpinner.setBackgroundResource(R.drawable.edit_shoppcart_bg);//下拉框背景
        if (userLogFlag.equals("0")) {
            quickOrder_headRl.setVerticalGravity(View.VISIBLE);
        } else {
            quickOrder_headRl.setVerticalGravity(View.GONE);
        }
    }

    private void toCaptureActivity() {
        Intent in = new Intent(QuickOrderActivity.this, ScanActivity.class);
        startActivityForResult(in, SCAN_REQUEST_CODE);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_add_member:
                intent = new Intent(QuickOrderActivity.this, MemberActivity.class);
                intent.putExtra("type", "onlyShow");
                startActivity(intent);
                break;
            case R.id.tv_order_manager:
                intent = new Intent(QuickOrderActivity.this, SearchOrderActivity.class);
                startActivity(intent);
                break;
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
                search(etSearch.getText().toString().trim(), "loadMore",ktypeid);
            }
        }, 500);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageNo = 1;
                search(etSearch.getText().toString().trim(), "refresh",ktypeid);
            }
        }, 500);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(etSearch.getText().toString())) {
            mSearchView.setImageResource(R.drawable.search04);
            ivClear.setVisibility(View.GONE);
        } else {
            mSearchView.setImageResource(R.drawable.search02);
            ivClear.setVisibility(View.VISIBLE);
        }
    }


    private class ListViewItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                long arg3) {

            GoodsBean goodsBean = mList.get(position);
            if (addList.contains(goodsBean)) {
                int index = addList.indexOf(goodsBean);
                GoodsBean addBean = addList.get(index);
                addBean.setNum(addBean.getNum() + 1);
                addList.set(index, addBean);
            } else {
                addList.add(goodsBean);
            }
            mList.remove(position);
            orderAdapter.notifyDataSetChanged();
            tvSearchNum.setText(MessageFormat.format("({0})", addList.size()));
        }
    }


    /**
     * 搜索方法
     */
    private void searchContent() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
        pageNo = 1;
        search(etSearch.getText().toString().trim(), "common",ktypeid);
    }


    /**
     * 扫码后回传结果 并调用搜索
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCAN_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String scanText = bundle != null ? bundle.getString("result") : null;
                    etSearch.setText(scanText);
                    search(scanText, "common",ktypeid);
                }
                break;
            case WAREHOUSE_REQUEST_CODE://仓库
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    UserBean.WarehouseInfoListBean bean = extras.getParcelable("warehouse");
                    quickOrderHeadtext.setText(bean.getWarehouseName());
                    ktypeid = bean.getId();
                    search(etSearch.getText().toString().trim(),"refresh",ktypeid);
                }
                break;
            default:
                break;
        }
    }

    private void templateSearch(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        map.put("ktypeid", DxtSaleApplication.getInstance().getUserBean().getKtypeid());
        map.put("templateName", name);
        map.put("officeId", DxtSaleApplication.getInstance().getUserBean().getOfficeId());
        map.put("method", "/ findTemplate");
        map.put("timestamp", System.currentTimeMillis());
        map.put("sign", Util.returnSign(map, Util.SIGN_KEY));
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(map);
        RxHttpUtils
                .createApi(ApiService.class)
                .queryTemplateSearch(toJson)
                .compose(SchedulerTransformer.<String>transformer())
                .compose(QuickOrderActivity.this.<String>bindToLifecycle())
                .compose(new DialogTransformer(QuickOrderActivity.this).<String>transformer())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onSuccess(String bean) {
                        Log.e("toJson+++", bean);
                    }
                });
    }

    private void search(final String keyWords, final String type,String ktypeid) {
        if (!"loadMore".equals(type)) {
            mList.clear();
            orderAdapter.notifyDataSetChanged();
        }
        Map<String, Object> us = new HashMap<>(6);
        us.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        us.put("ktypeid",ktypeid);
        us.put("searchKey", keyWords);
        us.put("pageNo", String.valueOf(pageNo));
        us.put("pageSize", pageSize);
        us.put("method", "/basics/product/product/list");//是否手机登录0否，1是
        us.put("timestamp", System.currentTimeMillis());
        String sign = Util.returnSign(us, Util.SIGN_KEY);
        us.put("sign", sign);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(us);
        RxHttpUtils
                .createApi(ApiService.class)
                .searchProduct(toJson)
                .compose(SchedulerTransformer.<SearchGoodsBean>transformer())
                .compose(QuickOrderActivity.this.<SearchGoodsBean>bindToLifecycle())
                .compose(new DialogTransformer(QuickOrderActivity.this).<SearchGoodsBean>transformer())
                .subscribe(new BaseObserver<SearchGoodsBean>() {
                    @Override
                    protected void onSuccess(SearchGoodsBean bean) {
                        if ("success".equals(bean.getFlag())) {
                            for (int i = 0; i < bean.getResult().size(); i++) {
                                mList.add(bean.getResult().get(i));
                                orderAdapter.notifyDataSetChanged();
                            }
                            if (mList.size() == 0) {
                                ToastUtils.showLong("搜索内容不存在");
                            } else if (bean.getResult().size() == 0) {
                                ToastUtils.showLong("已经到底了");
                            }
                        } else {
                            ToastUtils.showLong(bean.getMsg());
                        }
                        mPullToRefreshView.onFooterRefreshComplete();
                        mPullToRefreshView.onHeaderRefreshComplete();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (3 == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toCaptureActivity();
            } else {
                ToastUtils.showLong("请先授权，才能使用相机");
            }
        }
    }

    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ("orderUpdate".equals(getIntent().getStringExtra("type"))) {
            finish();
            return;
        }
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showLong("再点击一次退出");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            DxtSaleApplication.getInstance().exit();
        }
    }

    //删除弹窗
    private void showPopupMenu() {
        final MyDialog diaLog = MyDialog.createDialog(this, R.style.MyDialog, R.layout.dialog_exit, 0.25f, 0.8f, getWindowManager());
        TextView exit = diaLog.getDialogView().findViewById(R.id.tv_exist);
        diaLog.setCanceledOnTouchOutside(false);
        diaLog.setCancelable(false);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLog.dismiss();
                finish();
                DxtSaleApplication.getInstance().exit();
            }
        });
        diaLog.show();
    }
}