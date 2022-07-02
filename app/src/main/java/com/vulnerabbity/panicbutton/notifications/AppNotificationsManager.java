package com.vulnerabbity.panicbutton.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.vulnerabbity.panicbutton.receivers.PanicNotificationActionReceiver;

public class AppNotificationsManager {
  private Context context;
  private AppNotificationsBuilder notificationsBuilder;

  public AppNotificationsManager(Context context) {
    this.context = context;
    notificationsBuilder = new AppNotificationsBuilder(context);
  }

  public void destroyNotification(int notificationId) {
    getNotificationManager().cancel(notificationId);
  }

  public void displayNotification(NotificationConfig config) {
    Notification notification = notificationsBuilder.buildNotification(config);

    display(notification, config.id);
  }

  public void displayPanicNotification(int notificationId) {
    Notification panicNotification = notificationsBuilder.buildPanicNotification();

    display(panicNotification, notificationId);
  }

  private void display(Notification notification, int id) {
    getNotificationManager().notify(id, notification);
  }

  private NotificationManager getNotificationManager() {
    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    return notificationManager;
  }
}
