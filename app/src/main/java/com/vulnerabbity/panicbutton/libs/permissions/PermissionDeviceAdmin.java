package com.vulnerabbity.panicbutton.libs.permissions;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.vulnerabbity.panicbutton.receivers.DeviceAdminReceiver;

public class PermissionManagerDeviceAdmin implements PermissionManager {
  private Activity activity;
  private static final int CODE_ENABLE_PERMISSION = 1;

  public PermissionManagerDeviceAdmin(Activity activity) {
    this.activity = activity;
  }

  @Override
  public void request() {
    ComponentName componentToDisplayRequest = makeComponent();

    Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
    intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentToDisplayRequest);
    activity.startActivityForResult(intent, CODE_ENABLE_PERMISSION);
  }

  @Override
  public boolean isGranted() {
    return hasAdminRights();
  }

  private boolean hasAdminRights() {
    Boolean isAmin = getPolicyManager().isAdminActive(makeComponent());

    return isAmin;
  }

  private DevicePolicyManager getPolicyManager() {
    DevicePolicyManager policyManager = (DevicePolicyManager) activity.getSystemService(Context.DEVICE_POLICY_SERVICE);

    return policyManager;
  }

  private ComponentName makeComponent() {
    ComponentName component = new ComponentName(activity, DeviceAdminReceiver.class);

    return component;
  }
}
