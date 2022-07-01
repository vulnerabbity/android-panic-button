package com.vulnerabbity.panicbutton.libs.observer;

import com.vulnerabbity.panicbutton.utils.logger.Logger;

import java.util.LinkedList;

/**
 * RxJS like subject (Event emitter)
 */
public class Subject<T> {
  private LinkedList<Subscription<T>> subs = new LinkedList<Subscription<T>>();

  public Subscription<T> subscribe(LambdaCallback<T> callback) {
    Subscription newSub = new Subscription<T>(callback);

    subs.push(newSub);
    return newSub;
  }

  void next(T newValue) {
    for (Subscription subscription : subs) {
      LambdaCallback<T> callback = subscription.getCallback();
      Boolean isSubscribed = subscription.getIsSubscribed();
      if (isSubscribed) {
        callback.call(newValue);
      }
    }
  }
}
