package com.vulnerabbity.panicbutton.libs.observer;

/**
 * RxJS like subject (Event emitter)
 * Requires an initial value and emits the current value to new subscribers
 */
public class BehaviorSubject<T> extends Subject<T> {
  private T currentValue;

  public BehaviorSubject(T initialValue) {
    super();
    this.currentValue = initialValue;
  }

  @Override
  public Subscription<T> subscribe(LambdaCallback<T> callback) {
    // default subject logic
    Subscription<T> subscription = super.subscribe(callback);

    // behavior subject logic (notify when subscribed)
    this.executeSubscription(subscription, this.currentValue);

    return subscription;
  }
}
