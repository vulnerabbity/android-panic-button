package com.vulnerabbity.panicbutton.notifications;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * Builds notifications from config
 */
public class AppNotificationsBuilder {
  private Context context;

  public AppNotificationsBuilder(Context context) {
    this.context = context;
  }

  public Notification buildNotification(NotificationConfig config) {
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
            .setSmallIcon(config.icon)
            .setContentTitle(config.title)
            .setContentText(config.text).setOngoing(config.cantClose);

    Notification notification = notificationBuilder.build();

    return notification;
  }
}
