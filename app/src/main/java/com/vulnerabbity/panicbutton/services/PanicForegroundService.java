package com.vulnerabbity.panicbutton.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.vulnerabbity.panicbutton.events.ApplicationEvents;
import com.vulnerabbity.panicbutton.libs.panic.PanicListener;
import com.vulnerabbity.panicbutton.libs.storage.registry.StorageRegistry;
import com.vulnerabbity.panicbutton.notifications.AppNotificationsManager;
import com.vulnerabbity.panicbutton.notifications.NotificationConfig;
import com.vulnerabbity.panicbutton.receivers.PanicActionReceiver;
import com.vulnerabbity.panicbutton.utils.logger.Logger;

public class PanicForegroundService extends Service {
  private AppNotificationsManager notificationsManager;
  private PanicListener panicListener;
  private final int NOTIFICATION_ID = 12345;
  private StorageRegistry storage;

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Logger.log("panic service started");
    notificationsManager = new AppNotificationsManager(this);
    panicListener = new PanicListener(this);
    storage = new StorageRegistry(this);

    // listen to clicks on notification, etc
    PanicActionReceiver.register(this);

    handleNotificationsEvents();
    initNotification();
  }

  private void initNotification() {
    if (isPanicModeEnabled()) {
      this.displayNotification();
    }
  }

  private void handleNotificationsEvents() {
    ApplicationEvents.panicModeEnabled$.subscribe((event) -> {
      displayNotification();
    });
    ApplicationEvents.panicModeDisabled$.subscribe((event) -> {
      hideNotification();
    });
  }

  Boolean isPanicModeEnabled() {
    return this.storage.isPanicEnabled.get();
  }

  // todo: get title and tex from user
  private void displayNotification() {
    NotificationConfig config = new NotificationConfig();
    config.isPublic = true;
    config.title = "Facebook";
    config.text = "John sent you message. Swipe to view";
    config.id = NOTIFICATION_ID;
    this.notificationsManager.displayLockListeningNotification(config);
  }

  private void hideNotification() {
    this.notificationsManager.destroyNotification(NOTIFICATION_ID);
  }
}
