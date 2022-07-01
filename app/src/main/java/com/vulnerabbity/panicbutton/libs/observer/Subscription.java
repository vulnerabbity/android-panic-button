package com.vulnerabbity.panicbutton.libs.observer;

import java.util.function.Function;



public class Subscription<T> {
  private Boolean _isSubscribed = true;
  private LambdaCallback<T> _callback;

  public Subscription(LambdaCallback<T> callback) {
    this._callback = callback;
  }

  public void unsubscribe() {
    this._isSubscribed = false;
  }

  public Boolean getIsSubscribed() {
    return this._isSubscribed;
  }

  public LambdaCallback<T> getCallback() {
    return this._callback;
  }
}
