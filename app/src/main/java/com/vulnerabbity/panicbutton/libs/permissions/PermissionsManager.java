package com.vulnerabbity.panicbutton.libs.permissions;

import android.app.Activity;

public class PermissionsManager {
  public final Permission deviceAdmin;

  public PermissionsManager(Activity activity) {
    this.deviceAdmin = new PermissionDeviceAdmin(activity);
  }
}
