// Generated code from Butter Knife. Do not modify!
package com.dxt.retail4sunmi.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.dxt.retail4sunmi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderPayingDialog_ViewBinding implements Unbinder {
  private OrderPayingDialog target;

  @UiThread
  public OrderPayingDialog_ViewBinding(OrderPayingDialog target, View source) {
    this.target = target;

    target.dialogView = Utils.findRequiredView(source, R.id.dialog, "field 'dialogView'");
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderPayingDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dialogView = null;
  }
}
