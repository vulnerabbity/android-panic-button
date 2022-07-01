package com.vulnerabbity.panicbutton.libs.panic;

import android.app.admin.DevicePolicyManager;
import android.content.Context;

public class PanicExecutor {
  private Context context;

  public PanicExecutor(Context context) {
    this.context = context;
  }

  void lockDevice() {
    getDevicePolicyManager().lockNow();
  }

  private DevicePolicyManager getDevicePolicyManager() {
    DevicePolicyManager manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);

    return manager;
  }
}
