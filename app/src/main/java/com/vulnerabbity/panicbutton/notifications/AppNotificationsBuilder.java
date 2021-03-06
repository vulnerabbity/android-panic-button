package com.vulnerabbity.panicbutton.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.vulnerabbity.panicbutton.R;
import com.vulnerabbity.panicbutton.receivers.PanicNotificationActionReceiver;

// todo: make separate class for panic and default notification

/**
 * Builds notifications from config
 */
public class AppNotificationsBuilder {
  private Context context;
  private final String NOTIFICATION_CHANNEL_ID = "default channel";
  private NotificationConfig defaultConfig;

  public AppNotificationsBuilder(Context context) {
    this.context = context;
    this.defaultConfig = new NotificationConfig();
    // android 8+ requires to create notification channel to send notification
    createNotificationChannel();
  }

  public Notification buildNotification(NotificationConfig config) {

    NotificationCompat.Builder builder = makeCompatNotificationBuilder(config);
    Notification notification = builder.build();
    makeNotificationSilent(notification);

    return notification;
  }

  public Notification buildPanicNotification() {
    RemoteViews panicNotificationLayout = new RemoteViews(this.context.getPackageName(), R.layout.panic_notification);
    PanicNotificationActionReceiver.addListeningToNotification(context, panicNotificationLayout);

    NotificationCompat.Builder builder = makeCompatNotificationBuilder();
    builder.setContent(panicNotificationLayout);
    builder.setSmallIcon(defaultConfig.icon);
    builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

    Notification panicNotification = builder.build();
    makeNotificationSilent(panicNotification);

    return panicNotification;
  }

  private NotificationCompat.Builder makeCompatNotificationBuilder() {
    return new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
  }

  private NotificationCompat.Builder makeCompatNotificationBuilder(NotificationConfig config) {

    NotificationCompat.Builder notificationBuilder = makeCompatNotificationBuilder().setSmallIcon(config.icon)
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

  private void createNotificationChannel() {
    // if notifications channels supported
    if (Build.VERSION.SDK_INT >= 26) {
      CharSequence name = "Default";
      String Description = "This is my channel";
      int importance = NotificationManager.IMPORTANCE_LOW;
      NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
      notificationChannel.setDescription(Description);
      getSystemNotificationManager().createNotificationChannel(notificationChannel);
    }
  }

  private NotificationManager getSystemNotificationManager() {
    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    return notificationManager;
  }

  private void makeNotificationSilent(Notification notification) {
    notification.defaults = 0;
    notification.sound = null;
  }
}
