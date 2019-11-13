package com.dxt.retail4sunmi;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;

import com.dxt.retail4sunmi.entity.UserBean;
import com.dxt.retail4sunmi.util.CacheUtils;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.Util;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import woyou.aidlservice.jiuiv5.IWoyouService;


public class DxtSaleApplication extends Application {
    private UserBean mUserBean;
    private static final String USER_BEAN_CACHE = "userBeanCache";
    private IWoyouService woyouService;
    public IWoyouService getWoyouService() {
        return woyouService;
    }
    public UserBean getUserBean() {
        if (mUserBean == null) {
            mUserBean = CacheUtils.getInstance().getParcelable(USER_BEAN_CACHE, UserBean.CREATOR);
        }
        return mUserBean;
    }

    public void setUserBean(UserBean userBean) {
        mUserBean = userBean;
        CacheUtils.getInstance().put(USER_BEAN_CACHE, userBean);
    }


    private static Context mApplication;
    private final List<Activity> activityList = new LinkedList<>();

    public static DxtSaleApplication getInstance() {
        return (DxtSaleApplication) mApplication;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mApplication = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        String packageName = getPackageName();
        String processName = getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(this, "3ea2d96592", true, strategy);
        Util.init(this);
        binding();
    }


    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    public void remove(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
    private ServiceConnection connService = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

            ToastUtils.showLong("service disconnected");
            woyouService = null;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            binding();
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            woyouService = IWoyouService.Stub.asInterface(service);
        }
    };

    private void binding() {
        Intent intent = new Intent();
        intent.setPackage("woyou.aidlservice.jiuiv5");
        intent.setAction("woyou.aidlservice.jiuiv5.IWoyouService");
        startService(intent);
        bindService(intent, connService, Context.BIND_AUTO_CREATE);
    }
}