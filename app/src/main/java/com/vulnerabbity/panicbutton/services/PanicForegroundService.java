package com.vulnerabbity.panicbutton.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.vulnerabbity.panicbutton.events.ApplicationEvents;
import com.vulnerabbity.panicbutton.libs.panic.PanicListener;
import com.vulnerabbity.panicbutton.libs.storage.registry.StorageRegistry;
import com.vulnerabbity.panicbutton.notifications.AppNotificationsManager;
import com.vulnerabbity.panicbutton.receivers.PanicNotificationActionReceiver;
import com.vulnerabbity.panicbutton.utils.logger.Logger;

public class PanicForegroundService extends Service {
  private AppNotificationsManager notificationsManager;
  private PanicListener panicListener;
  private final int PANIC_NOTIFICATION_ID = 12345;
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

    // listen to clicks on notification
    PanicNotificationActionReceiver.register(this);

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

  private void displayNotification() {
    this.notificationsManager.displayPanicNotification(PANIC_NOTIFICATION_ID);
  }

  private void hideNotification() {
    this.notificationsManager.destroyNotification(PANIC_NOTIFICATION_ID);
  }
}
