package com.vulnerabbity.panicbutton.libs.permissions;

import android.app.Activity;
import android.content.Context;

interface Permission {
  void request();

  boolean isGranted();
}
