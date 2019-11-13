package com.dxt.retail4sunmi.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;

public class OrderFailActivity extends BaseActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setMContentView(R.layout.orderfail);
		
		Intent intent1 = getIntent();
		
	    //0代表下单失败    1代表修改订单失败
		int status = intent1.getIntExtra("status", 0);
		final Button but = findViewById(R.id.orderfail_but_1);
		TextView mTitle = findViewById(R.id.orderfail_tv_1);
		TextView mMsg = findViewById(R.id.orderfail_tv_2);
		TextView mErrorMsg = findViewById(R.id.orderfail_tv_3);
		switch (status) {
			case 1:
				mTitle.setText(R.string.orderfail_text_3);
				tvTitle.setText("修改订单失败");
				break;
			case 2:
				mErrorMsg.setVisibility(View.VISIBLE);
				mTitle.setText(R.string.orderfail_text_5);
				mMsg.setText(R.string.orderfail_text_6);
				mErrorMsg.setText(R.string.orderfail_text_7);
				tvTitle.setText("会员录入失败");
				break;
			case 0:
				mTitle.setText(R.string.orderfail_text_1);
				mMsg.setText(R.string.orderfail_text_2);
				tvTitle.setText("下单失败");
				break;
			case 4:
				mTitle.setText("数据上传失败");
				mMsg.setText("数据上传失败,请稍后到订单详情中校验订单状态");
				tvTitle.setText("数据上传失败");
				break;
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
		startActivity(new Intent(this,QuickOrderActivity.class));
		finish();
	}

}
