package com.vulnerabbity.panicbutton.services;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.vulnerabbity.panicbutton.utils.logger.Logger;

public class ServiceRunner {
  Context context;
  public ServiceRunner(Context context) {
    this.context = context;
  }

  public void runService(Intent intent) {
    // foreground service available since api 26
    if (Build.VERSION.SDK_INT >= 26) {
      context.startForegroundService(intent);
    }
    context.startService(intent);
  }
}
