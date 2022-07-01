package com.vulnerabbity.panicbutton.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Allows to skip implementation "onReceive" required by BroadcastReceiver
 */
public class DefaultBroadcastReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    // Do nothing
  }
}
