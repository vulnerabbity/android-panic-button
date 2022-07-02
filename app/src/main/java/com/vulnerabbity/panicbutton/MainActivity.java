package com.vulnerabbity.panicbutton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vulnerabbity.panicbutton.events.ApplicationEvents;
import com.vulnerabbity.panicbutton.libs.panic.PanicActions;
import com.vulnerabbity.panicbutton.libs.permissions.PermissionsManager;
import com.vulnerabbity.panicbutton.libs.storage.registry.StorageRegistry;
import com.vulnerabbity.panicbutton.services.PanicForegroundService;
import com.vulnerabbity.panicbutton.services.ServiceRunner;

public class MainActivity extends AppCompatActivity {
  private PermissionsManager permissionsManager;
  private StorageRegistry storage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    permissionsManager = new PermissionsManager(this);
    storage = new StorageRegistry(this);
    new MainActivityUI(this).initUi();

    initPanicMode();
  }

  public void onStartClick(View v) {
    startListeningForPanicIfAdmin();
  }

  public void onStopClick(View v) {
    stopPanicMode();
  }

  public void setFirstPanicActionToNone(View view) {
    this.storage.panicAction1.set(PanicActions.NONE);
  }

  public void setFirstPanicActionToLock(View view) {
    this.storage.panicAction1.set(PanicActions.LOCK);
  }

  public void setSecondPanicActionToNone(View view) {
    this.storage.panicAction2.set(PanicActions.NONE);
  }

  public void setSecondPanicActionToLock(View view) {
    this.storage.panicAction2.set(PanicActions.LOCK);
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
    new ServiceRunner(this).runService(intent);
  }
}