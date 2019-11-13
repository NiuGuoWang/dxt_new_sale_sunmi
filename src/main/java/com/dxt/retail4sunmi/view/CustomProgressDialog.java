package com.dxt.retail4sunmi.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;


public class CustomProgressDialog extends Dialog {


    private CustomProgressDialog(Context context) {
        super(context, R.style.CustomDialog);
    }


    public static synchronized void showLoading(Context context) {
        showLoading(context, "loading...");
    }

    private static synchronized void showLoading(Context context, CharSequence message) {
        showLoading(context, message, true);
    }

    public static synchronized CustomProgressDialog showLoading(Context context, CharSequence message, boolean cancelable) {

        CustomProgressDialog dialog = new CustomProgressDialog(context);
        dialog.setCancelable(cancelable);
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom_progress, null);
        TextView tvMessage = view.findViewById(R.id.tv_message);
        if (!TextUtils.isEmpty(message)) {
            tvMessage.setText(message);
        }
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.addContentView(view, lp);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });

        return dialog;
    }


}
