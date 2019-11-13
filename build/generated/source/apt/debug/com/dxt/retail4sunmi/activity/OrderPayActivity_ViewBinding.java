// Generated code from Butter Knife. Do not modify!
package com.dxt.retail4sunmi.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.dxt.retail4sunmi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderPayActivity_ViewBinding implements Unbinder {
  private OrderPayActivity target;

  private View view2131230765;

  @UiThread
  public OrderPayActivity_ViewBinding(OrderPayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderPayActivity_ViewBinding(final OrderPayActivity target, View source) {
    this.target = target;

    View view;
    target.rg = Utils.findRequiredViewAsType(source, R.id.rg, "field 'rg'", RadioGroup.class);
    target.mRbScanPay = Utils.findRequiredViewAsType(source, R.id.rb_scan_pay, "field 'mRbScanPay'", RadioButton.class);
    target.mRbCardPay = Utils.findRequiredViewAsType(source, R.id.rb_card_pay, "field 'mRbCardPay'", RadioButton.class);
    target.mRbAlipay = Utils.findRequiredViewAsType(source, R.id.rb_alipay_pay, "field 'mRbAlipay'", RadioButton.class);
    target.mRbWechatPay = Utils.findRequiredViewAsType(source, R.id.rb_wechat_pay, "field 'mRbWechatPay'", RadioButton.class);
    target.mRbCashPay = Utils.findRequiredViewAsType(source, R.id.rb_cash_pay, "field 'mRbCashPay'", RadioButton.class);
    view = Utils.findRequiredView(source, R.id.btn_toPay, "method 'click'");
    view2131230765 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderPayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rg = null;
    target.mRbScanPay = null;
    target.mRbCardPay = null;
    target.mRbAlipay = null;
    target.mRbWechatPay = null;
    target.mRbCashPay = null;

    view2131230765.setOnClickListener(null);
    view2131230765 = null;
  }
}
