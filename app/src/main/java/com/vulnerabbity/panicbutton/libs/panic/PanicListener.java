package com.vulnerabbity.panicbutton.libs.panic;

import android.content.Context;

import com.vulnerabbity.panicbutton.events.ApplicationEvents;
import com.vulnerabbity.panicbutton.libs.observer.Subscription;
import com.vulnerabbity.panicbutton.utils.logger.Logger;

public class PanicListener {
  private Subscription<String> panicEventSubscription;
  private PanicExecutor panicExecutor;

  public PanicListener(Context context) {
    panicEventSubscription = ApplicationEvents.panicActionCalled$.subscribe((e) -> {
      this.callPanicAction();
    });
    panicExecutor = new PanicExecutor(context);
  }

  public void stopListening() {
    this.panicEventSubscription.unsubscribe();
  }

  private void callPanicAction() {
    Logger.log("Panic executed");
    this.panicExecutor.lockDevice();
  }
}
