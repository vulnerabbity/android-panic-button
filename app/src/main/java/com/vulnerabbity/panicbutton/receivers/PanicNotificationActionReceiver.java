package com.vulnerabbity.panicbutton.receivers;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.RemoteViews;

import com.vulnerabbity.panicbutton.R;
import com.vulnerabbity.panicbutton.events.ApplicationEvents;

class PANIC_ACTIONS {
  static final String CLOSE = "PANIC_NOTIFICATION_CLOSE";

  static final String PANIC_ACTION_1 = "PANIC_NOTIFICATION_ACTION_1";

  static final String PANIC_ACTION_2 = "PANIC_NOTIFICATION_ACTION_2";
}

public class PanicNotificationActionReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    String action = intent.getAction();
    if (action == PANIC_ACTIONS.PANIC_ACTION_1) {
      emitPanicAction1Called();
    }
    if (action == PANIC_ACTIONS.PANIC_ACTION_2) {
      emitPanicAction2Called();
    }
    if (action == PANIC_ACTIONS.CLOSE) {
      emitPanicNotificationClosed();
    }
  }

  public static void register(Context context) {
    PanicNotificationActionReceiver receiver = new PanicNotificationActionReceiver();
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

    intentFilter.addAction(PANIC_ACTIONS.PANIC_ACTION_1);
    intentFilter.addAction(PANIC_ACTIONS.PANIC_ACTION_2);
    intentFilter.addAction(PANIC_ACTIONS.CLOSE);

    context.registerReceiver(receiver, intentFilter);
  }

  public static void addListeningToNotification(Context context, RemoteViews panicNotification) {
    panicNotification.setOnClickPendingIntent(R.id.panic_notification_action_1, makePendingIntent(context, PANIC_ACTIONS.PANIC_ACTION_1));
    panicNotification.setOnClickPendingIntent(R.id.panic_notification_action_2, makePendingIntent(context, PANIC_ACTIONS.PANIC_ACTION_2));
    panicNotification.setOnClickPendingIntent(R.id.panic_notification_close, makePendingIntent(context, PANIC_ACTIONS.CLOSE));
  }

  private static PendingIntent makePendingIntent(Context context, String actionName) {
    Intent intent = new Intent(actionName);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

    return pendingIntent;
  }

  private void emitPanicAction1Called() {
    ApplicationEvents.panicAction1Called$.next("panic 1 called");
  }

  private void emitPanicAction2Called() {
    ApplicationEvents.panicAction2Called$.next("panic 2 called");
  }

  private void emitPanicNotificationClosed() {
    ApplicationEvents.panicNotificationClosed$.next("notification closed");
  }
}
