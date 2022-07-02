package com.vulnerabbity.panicbutton.libs.panic;

import android.content.Context;

import com.vulnerabbity.panicbutton.events.ApplicationEvents;
import com.vulnerabbity.panicbutton.libs.storage.registry.StorageRegistry;
import com.vulnerabbity.panicbutton.utils.logger.Logger;

public class PanicListener {
  private PanicExecutor panicExecutor;
  private StorageRegistry storage;

  public PanicListener(Context context) {
    panicExecutor = new PanicExecutor(context);
    storage = new StorageRegistry(context);

    startHandlingEvents();
  }

  private void startHandlingEvents() {
    ApplicationEvents.panicActionCalled$.subscribe((e) -> {
      callPanicIfPanicMode();
    });

    ApplicationEvents.panicModeEnabled$.subscribe((e) -> {
      enablePanicMode();
    });

    ApplicationEvents.panicModeDisabled$.subscribe((e) -> {
      disablePanicMode();
    });
  }

  private void callPanicIfPanicMode() {
    if (isPanicMode()) {
      this.callPanicAction();
    }
  }

  private void callPanicAction() {
    Logger.log("Panic executed");
    this.panicExecutor.lockDevice();
  }

  private void enablePanicMode() {
    storage.isPanicEnabled.set(true);
  }

  private void disablePanicMode() {
    storage.isPanicEnabled.set(false);
  }

  private Boolean isPanicMode() {
    return storage.isPanicEnabled.get();
  }
}
