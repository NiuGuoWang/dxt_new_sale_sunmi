package com.dxt.retail4sunmi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;

public class OrderSuccessActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMContentView(R.layout.ordersuccess);
        Intent intent1 = getIntent();
        int status = intent1.getIntExtra("status", 0);
        final Button but = findViewById(R.id.ordersuccess_but_1);
        TextView title = findViewById(R.id.ordersuccess_tv_1);
        TextView msg = findViewById(R.id.ordersuccess_tv_2);
        if (status == 0) {//如果是下订单成功
            tvTitle.setText("操作成功");
        } else if (status == 1) {//如果是修改订单成功
            title.setText(R.string.ordersuccess_text_3);
            msg.setText(R.string.ordersuccess_text_4);
            tvTitle.setText("修改订单成功");
        }
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
}
