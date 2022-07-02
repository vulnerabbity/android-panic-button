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
    ApplicationEvents.panicAction1Called$.subscribe((event) -> {
      if (isPanicMode()) {
        String panicAction = this.storage.panicAction1.get();
        executePanic(panicAction);
      }
    });

    ApplicationEvents.panicAction2Called$.subscribe((event) -> {
      if (isPanicMode()) {
        String panicAction = this.storage.panicAction2.get();
        executePanic(panicAction);
      }
    });

    ApplicationEvents.panicModeEnabled$.subscribe((event) -> {
      enablePanicMode();
    });

    ApplicationEvents.panicModeDisabled$.subscribe((event) -> {
      disablePanicMode();
    });
  }

  private void executePanic(String panicAction) {
    if (panicAction == PanicActions.LOCK) {
      panicExecutor.lockDevice();
    } else if (panicAction == PanicActions.NONE) {
      Logger.log("Panic action is 'none'");
    } else {
      Logger.error(panicAction + " is unknown panic action; cant execute");
    }
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
