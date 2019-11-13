package com.dxt.retail4sunmi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.dxt.retail4sunmi.R;

/**
 * @author star
 * @date 2017/9/27 0027
 */

public class EditDialog extends Dialog {
    private String mText;

    public String getmText() {
        return mText;
    }

    public EditDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
    }

    public EditDialog(Context context, String text) {
        super(context, R.style.CustomDialog);
        mText = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_dialog);

        initView();
    }

    private void initView() {

        final EditText et = findViewById(R.id.et);
        et.setText(mText);
        findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = et.getText().toString();
                dismiss();
            }
        });

        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
