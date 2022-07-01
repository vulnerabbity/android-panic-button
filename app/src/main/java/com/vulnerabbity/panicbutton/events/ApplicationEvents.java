package com.vulnerabbity.panicbutton.events;

import com.vulnerabbity.panicbutton.libs.observer.Subject;

public class ApplicationEvents {
  public static Subject<String> NotificationClicked$ = new Subject();
}
