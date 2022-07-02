package com.vulnerabbity.panicbutton.receivers;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.vulnerabbity.panicbutton.events.ApplicationEvents;
import com.vulnerabbity.panicbutton.utils.logger.Logger;

class PanicActions {
  
}

public class PanicActionReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    String action = intent.getAction();
    if (action == ACTION_NAME) {
      emitNotificationClicked();
    }
  }

  public static void register(Context context) {
    PanicActionReceiver receiver = new PanicActionReceiver();
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
    intentFilter.addAction(ACTION_NAME);

    context.registerReceiver(receiver, intentFilter);
  }

  public static PendingIntent getPendingIntent(Context context) {
    Intent intent = new Intent(ACTION_NAME);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

    return pendingIntent;
  }

  private void emitNotificationClicked() {
    Logger.log(ACTION_NAME);
    ApplicationEvents.panicActionCalled$.next("notificationClicked");
  }
}
