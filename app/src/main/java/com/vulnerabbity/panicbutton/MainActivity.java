package com.vulnerabbity.panicbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vulnerabbity.panicbutton.libs.panic.PanicListener;
import com.vulnerabbity.panicbutton.libs.permissions.PermissionsManager;
import com.vulnerabbity.panicbutton.notifications.AppNotificationsManager;
import com.vulnerabbity.panicbutton.notifications.NotificationConfig;
import com.vulnerabbity.panicbutton.receivers.PanicActionReceiver;

public class MainActivity extends AppCompatActivity {
  private PermissionsManager permissionsManager;
  private AppNotificationsManager notificationsManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    PanicActionReceiver.register(this);
    new PanicListener(this);

    permissionsManager = new PermissionsManager(this);
    notificationsManager = new AppNotificationsManager(this);
  }

  public void onStartClick(View v) {
    Boolean isAdminApp = permissionsManager.deviceAdmin.isGranted();
    if (isAdminApp) {
      displayNotification();
    } else {
      permissionsManager.deviceAdmin.request();
    }
  }

  private void displayNotification() {
    NotificationConfig config = new NotificationConfig();
    config.isPublic = true;
    config.title = "Facebook";
    config.text = "John sent you message. Swipe to view";
    this.notificationsManager.displayLockListeningNotification(config);
  }
}