package com.vulnerabbity.panicbutton.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.vulnerabbity.panicbutton.receivers.PanicActionReceiver;

public class AppNotificationsManager {
  private Context context;
  private AppNotificationsBuilder notificationsBuilder;

  public AppNotificationsManager(Context context) {
    this.context = context;
    notificationsBuilder = new AppNotificationsBuilder(context);
  }

  public void displayNotification(NotificationConfig config) {
    Notification notification = notificationsBuilder.buildNotification(config);

    display(notification, config.id);
  }

  public void displayLockListeningNotification(NotificationConfig config) {
    Notification notification = notificationsBuilder.buildNotification(config);

    addLockListeningToNotification(notification);
    display(notification, config.id);
  }

  private void addLockListeningToNotification(Notification notification) {
    // on click
    notification.contentIntent = PanicActionReceiver.getPendingIntent(this.context);
    // on close swipe
    notification.deleteIntent = PanicActionReceiver.getPendingIntent(this.context);
  }

  private void display(Notification notification, int id) {
    getNotificationManager().notify(id, notification);
  }

  private NotificationManager getNotificationManager() {
    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    return notificationManager;
  }
}
