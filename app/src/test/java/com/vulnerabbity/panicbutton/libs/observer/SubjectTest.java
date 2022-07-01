package com.vulnerabbity.panicbutton.libs.observer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SubjectTest {
  @Test
  public void modifiesAfterEmission() {
    final int initialValue = 0;
    // not default int to use lambda inside method
    AtomicInteger localCounter = new AtomicInteger(initialValue);

    Subject<Integer> subject = new Subject();

    // update on every emission
    subject.subscribe((value) -> {
      localCounter.set(value);
    });

    int firstValueToEmit = 1;
    subject.next(firstValueToEmit);
    assertEquals(localCounter.get(), firstValueToEmit);

    int secondValueToEmit = 2;
    subject.next(secondValueToEmit);
    assertEquals(localCounter.get(), secondValueToEmit);
  }

  @Test
  public  void notModifiesIfUnsubscribed() {
    final int initialValue = 0;
    // not default int to use lambda inside method
    AtomicInteger localCounter = new AtomicInteger(initialValue);

    Subject<Integer> subject = new Subject();

    // update on every emission
    Subscription sub = subject.subscribe((value) -> {
      localCounter.set(value);
    });


    // expect value not changed if unsubscribed
    sub.unsubscribe();
    subject.next(20);
    assertEquals(localCounter.get(), initialValue);
  }

}
