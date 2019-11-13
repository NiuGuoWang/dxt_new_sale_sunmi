package com.dxt.retail4sunmi.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.api.ApiService;
import com.dxt.retail4sunmi.constant.StringConstant;
import com.dxt.retail4sunmi.entity.UpdateBean;
import com.dxt.retail4sunmi.network.RxHttpUtils;
import com.dxt.retail4sunmi.network.base.BaseObserver;
import com.dxt.retail4sunmi.network.interceptor.DialogTransformer;
import com.dxt.retail4sunmi.network.interceptor.SchedulerTransformer;
import com.dxt.retail4sunmi.util.NetUtil;
import com.dxt.retail4sunmi.util.SPUtils;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.UpdateManager;
import com.dxt.retail4sunmi.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author star
 */
public class WelcomeActivity extends BaseActivity {
    private final String CREATE_SHORTCUT = "shortcut";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cancelTitle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        createShortCut();//创建快捷方式
        SPUtils.getInstance("order").clear();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetUtil.isNetworkAvailable(WelcomeActivity.this)) {
            Toast.makeText(this, R.string.public_text_4, Toast.LENGTH_LONG).show();
        } else {
//            isUpdate();
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void isUpdate() {

        Map<String, Object> us = new HashMap<>(6);
        us.put("version", "1");
        us.put("appName", "posApp");
        us.put("method", "/getAppUpdateInfo");//调用方法
        us.put("timestamp", System.currentTimeMillis());//时间戳
        String sign = Util.returnSign(us, Util.SIGN_KEY);
        us.put("sign", sign);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(us);

        RxHttpUtils
                .createApi(ApiService.class)
                .getUpdate(toJson)
                .compose(SchedulerTransformer.<UpdateBean>transformer())
                .compose(WelcomeActivity.this.<UpdateBean>bindToLifecycle())
                .compose(new DialogTransformer(WelcomeActivity.this).<UpdateBean>transformer())
                .subscribe(new BaseObserver<UpdateBean>() {

                    @Override
                    protected void onSuccess(UpdateBean bean) {
                        if (StringConstant.SUCCESS.equals(bean.getFlag())) {
                            UpdateBean.ResultBean subBean = bean.getResult();
                            if (getVersionCode() < Integer.valueOf(subBean.getNewVersion())){
                                UpdateManager mUpdateManager = new UpdateManager(WelcomeActivity.this, bean.getResult().getUrl(), bean.getResult().getIsMust());
                                mUpdateManager.checkUpdateInfo();
                            }else {
                                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            ToastUtils.showLong(bean.getMsg());
                        }
                    }
                });

    }


    /**
     * 功能描述：创建桌面快捷方式
     *
     * @since 2013-4-18
     */
    private void createShortCut() {
        if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(CREATE_SHORTCUT, false)) {
            Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            Intent launcher = new Intent(this, getClass());
            Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.ic_launcher);
            shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources().getString(R.string.app_name));
            shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcher);
            shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
            shortcutIntent.putExtra("duplicate", false); //不允许重复创建
            sendBroadcast(shortcutIntent);
            PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(CREATE_SHORTCUT, true).apply();
        }
    }


    private int getVersionCode() {
        int versionCode = 0;
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        DxtSaleApplication.getInstance().exit();
    }


}
