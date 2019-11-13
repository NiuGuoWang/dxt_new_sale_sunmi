// Generated code from Butter Knife. Do not modify!
package com.dxt.retail4sunmi.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.dxt.retail4sunmi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WarehouseActivity_ViewBinding implements Unbinder {
  private WarehouseActivity target;

  @UiThread
  public WarehouseActivity_ViewBinding(WarehouseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WarehouseActivity_ViewBinding(WarehouseActivity target, View source) {
    this.target = target;

    target.warehouseIm = Utils.findRequiredViewAsType(source, R.id.warehouse_im, "field 'warehouseIm'", ImageView.class);
    target.warehouseRecy = Utils.findRequiredViewAsType(source, R.id.warehouse_recy, "field 'warehouseRecy'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WarehouseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.warehouseIm = null;
    target.warehouseRecy = null;
  }
}
