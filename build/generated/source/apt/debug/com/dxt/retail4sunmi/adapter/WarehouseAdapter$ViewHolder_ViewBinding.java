// Generated code from Butter Knife. Do not modify!
package com.dxt.retail4sunmi.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.dxt.retail4sunmi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WarehouseAdapter$ViewHolder_ViewBinding implements Unbinder {
  private WarehouseAdapter.ViewHolder target;

  @UiThread
  public WarehouseAdapter$ViewHolder_ViewBinding(WarehouseAdapter.ViewHolder target, View source) {
    this.target = target;

    target.radioText = Utils.findRequiredViewAsType(source, R.id.tv_radio_text, "field 'radioText'", TextView.class);
    target.radioButton = Utils.findRequiredViewAsType(source, R.id.rb_radio_button, "field 'radioButton'", RadioButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WarehouseAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.radioText = null;
    target.radioButton = null;
  }
}
