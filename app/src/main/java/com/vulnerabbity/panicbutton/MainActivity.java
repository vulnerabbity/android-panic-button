package com.vulnerabbity.panicbutton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vulnerabbity.panicbutton.events.ApplicationEvents;
import com.vulnerabbity.panicbutton.libs.permissions.PermissionsManager;
import com.vulnerabbity.panicbutton.libs.storage.registry.StorageRegistry;
import com.vulnerabbity.panicbutton.services.PanicForegroundService;
import com.vulnerabbity.panicbutton.utils.logger.Logger;

public class MainActivity extends AppCompatActivity {
  private PermissionsManager permissionsManager;
  private StorageRegistry storage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    permissionsManager = new PermissionsManager(this);
    storage = new StorageRegistry(this);

    initPanicMode();
  }

  public void onStartClick(View v) {
    startListeningForPanicIfAdmin();
  }

  public void onStopClick(View v) {
    stopPanicMode();
  }

  private void startListeningForPanicIfAdmin() {
    if (isAdminPermissionGranted()) {
      enablePanicMode();
    } else {
      permissionsManager.deviceAdmin.request();
    }
  }

  private void initPanicMode() {
    this.startPanicService();

    if (isPanicModeEnabled()) {
      this.enablePanicMode();
    }
  }

  private Boolean isPanicModeEnabled() {
    Boolean isEnabled = storage.isPanicEnabled.get();
    Logger.log(isEnabled.toString());
    return isEnabled;
  }

  private void enablePanicMode() {
    ApplicationEvents.panicModeEnabled$.next("panic mode enabled");
  }

  private void stopPanicMode() {
    ApplicationEvents.panicModeDisabled$.next("panic mode disabled");
  }

  private Boolean isAdminPermissionGranted() {
    return permissionsManager.deviceAdmin.isGranted();
  }

  private void startPanicService() {
    Intent intent = new Intent(this, PanicForegroundService.class);
    // foreground service available since api 26
    if (Build.VERSION.SDK_INT >= 26) {
      Logger.log("started foreground");
      startForegroundService(intent);
    }
    Logger.log("started service");
    startService(intent);
  }
}