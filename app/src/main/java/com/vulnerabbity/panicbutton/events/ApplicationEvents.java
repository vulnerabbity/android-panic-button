package com.vulnerabbity.panicbutton.events;

import com.vulnerabbity.panicbutton.libs.observer.Subject;

public class ApplicationEvents {
  public static Subject<String> panicAction1Called$ = new Subject();
  public static Subject<String> panicAction2Called$ = new Subject();
  public static Subject<String> panicNotificationClosed$ = new Subject();

  public static Subject<String> panicModeEnabled$ = new Subject();
  public static Subject<String> panicModeDisabled$ = new Subject();
}
