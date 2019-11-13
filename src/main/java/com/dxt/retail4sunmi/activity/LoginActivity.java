package com.dxt.retail4sunmi.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.EditText;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.api.ApiService;
import com.dxt.retail4sunmi.constant.StringConstant;
import com.dxt.retail4sunmi.entity.UserBean;
import com.dxt.retail4sunmi.network.RxHttpUtils;
import com.dxt.retail4sunmi.network.base.BaseObserver;
import com.dxt.retail4sunmi.network.interceptor.DialogTransformer;
import com.dxt.retail4sunmi.network.interceptor.SchedulerTransformer;
import com.dxt.retail4sunmi.util.SPUtils;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author star
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv)
     EditText userEditText;
    @BindView(R.id.tv2)
     EditText mPasswordEditText;
    @OnClick(R.id.loginButton) void submit() {
        if (validate()) {
            doLogin();
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        cancelTitle();//全屏显示
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_system);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initLastLoginInfo();
    }

    /**
     * 加载上次登录信息
     */
    private void initLastLoginInfo() {
        // 读取本地文件，获得上次登录信息
        SharedPreferences lastLoginInfo = getSharedPreferences("setting", 0);
        String lastLoginUserId = lastLoginInfo.getString("lastLoginUserId", "");
        String lastLoginUserPassWord = lastLoginInfo.getString("lastLoginUserPassWord", "");
        userEditText.setText(lastLoginUserId);
        userEditText.setSelection(lastLoginUserId.length());
        mPasswordEditText.setText(lastLoginUserPassWord);
    }

    private void recordLoingInfo(String lastLoginUserId, String lastLoginUserPassWord,String userLogFlag) {
        // 写入本地文件，记录本次登录信息
        SharedPreferences settings = getSharedPreferences("setting", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("lastLoginUserId", lastLoginUserId);
        editor.putString("lastLoginUserPassWord", lastLoginUserPassWord);
        editor.apply();
    }


    private void doLogin() {
        final String userId = userEditText.getText().toString();
        final String userPassword = mPasswordEditText.getText().toString();
        Map<String, Object> us = new HashMap<>(6);
        us.put("username", userId);
        us.put("password", userPassword);
        us.put("mobileLogin", "1");//是否手机登录0否，1是
        us.put("method", "/login");//调用方法
        us.put("timestamp", System.currentTimeMillis());//时间戳
        String sign = Util.returnSign(us, Util.SIGN_KEY);
        us.put("sign", sign);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(us);

        RxHttpUtils
                .createApi(ApiService.class)
                .login(toJson)
                .compose(SchedulerTransformer.<UserBean>transformer())
                .compose(LoginActivity.this.<UserBean>bindToLifecycle())
                .compose(new DialogTransformer(LoginActivity.this).<UserBean>transformer())
                .subscribe(new BaseObserver<UserBean>() {

                    @Override
                    protected void onSuccess(UserBean bean) {
                        if ("success".equals(bean.getFlag())) {
                            SPUtils.getInstance(StringConstant.DXT_POS).put(StringConstant.USERNAME,bean.getUserName());
                            SPUtils.getInstance(StringConstant.DXT_POS).put(StringConstant.USERLOGFLAG,bean.getUserLogFlag());
                            Intent intent = new Intent(LoginActivity.this, QuickOrderActivity.class);
                            intent.putParcelableArrayListExtra("warehouselist", (ArrayList<? extends Parcelable>) bean.getWarehouseInfoList());
                            recordLoingInfo(userId, userPassword,bean.getUserLogFlag());
                            DxtSaleApplication.getInstance().setUserBean(bean);
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtils.showLong(bean.getMsg());
                        }
                    }
                });

    }

    private boolean validate() {
        if (TextUtils.isEmpty(userEditText.getText().toString())) {
            showDialog("用户名称是必填项！");
            return false;
        }
        if (TextUtils.isEmpty(mPasswordEditText.getText().toString())) {
            showDialog("密码是必填项！");
            return false;
        }
        return true;
    }

    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.default_img);
        builder.setMessage(msg).setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        DxtSaleApplication.getInstance().exit();
    }
}
