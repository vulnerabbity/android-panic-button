package com.vulnerabbity.panicbutton.libs.observer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class BehaviorSubjectTest {
  @Test
  public void isUsingInitialValue() {
    final Integer initialValue = 10;

    BehaviorSubject<Integer> behaviorSubject = new BehaviorSubject(initialValue);

    behaviorSubject.subscribe((initialValueFromSubject -> {
      assertEquals(initialValue, initialValueFromSubject);
    }));
  }
}
