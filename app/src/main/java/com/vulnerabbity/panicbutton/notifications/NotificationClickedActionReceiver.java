package com.vulnerabbity.panicbutton.notifications;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.vulnerabbity.panicbutton.events.ApplicationEvents;
import com.vulnerabbity.panicbutton.utils.logger.Logger;

/**
 * Listens for clicks on notifications which have PendingIntent from here
 */
public class NotificationClickedActionReceiver extends BroadcastReceiver {
  private static final String ACTION_NAME = "notification_click";

  @Override
  public void onReceive(Context context, Intent intent) {
    String action = intent.getAction();
    if (action == ACTION_NAME) {
      emitNotificationClicked();
    }
  }

  public static void register(Context context) {
    NotificationClickedActionReceiver receiver = new NotificationClickedActionReceiver();
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
    Logger.log("notificationClicked");
    ApplicationEvents.NotificationClicked$.next("notificationClicked");
  }
}
