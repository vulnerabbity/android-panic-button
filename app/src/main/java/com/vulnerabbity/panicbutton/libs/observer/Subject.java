package com.vulnerabbity.panicbutton.libs.observer;

import java.util.LinkedList;

/**
 * RxJS like subject (Event emitter)
 */
public class Subject<T> {
  private LinkedList<Subscription<T>> subscriptions = new LinkedList();

  public Subscription<T> subscribe(LambdaCallback<T> callback) {
    Subscription newSub = new Subscription<T>(callback);

    subscriptions.push(newSub);
    return newSub;
  }

  public void next(T newValue) {
    notifySubscribers(newValue);
  }

  protected void notifySubscribers(T newValue) {
    for (Subscription subscription : subscriptions) {
      Boolean isSubscribed = subscription.getIsSubscribed();
      if (isSubscribed) {
        this.executeSubscription(subscription, newValue);
      }
    }
  }

  protected void executeSubscription(Subscription subscription, T value) {
    LambdaCallback<T> callback = subscription.getCallback();
    callback.call(value);
  }
}
