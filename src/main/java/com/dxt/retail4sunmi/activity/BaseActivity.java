package com.dxt.retail4sunmi.activity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * @author star
 */
@SuppressLint("Registered")
public class BaseActivity extends RxAppCompatActivity {
    private FrameLayout layout;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(DxtSaleApplication.getInstance(), R.color.viewfinder_laser));
        }
        DxtSaleApplication.getInstance().addActivity(this);
        setContentView(R.layout.base);
        layout = findViewById(R.id.content);
    }

    void setMContentView(int id) {
        LayoutInflater.from(this).inflate(id, layout);
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvTitle = findViewById(R.id.base_title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DxtSaleApplication.getInstance().remove(this);
    }

    /**
     * 功能描述：设置全屏显示
     *
     */
    void cancelTitle() {
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 设置字体不跟随系统字体大小而变化
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1) {//非默认值
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

}
