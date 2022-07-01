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
    Notification notification = makeCompatNotificationBuilder(config).build();

    return notification;
  }

  private NotificationCompat.Builder makeCompatNotificationBuilder(NotificationConfig config) {
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
            .setSmallIcon(config.icon)
            .setOngoing(config.cantClose);

    if (config.title != null) {
      notificationBuilder.setContentTitle(config.title);
    }

    if (config.text != null) {
      notificationBuilder.setContentText(config.text);
    }
    if (config.isPublic) {
      notificationBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

    }

    return notificationBuilder;
  }
}
