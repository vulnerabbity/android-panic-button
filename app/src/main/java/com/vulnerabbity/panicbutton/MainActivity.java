package com.vulnerabbity.panicbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vulnerabbity.panicbutton.notifications.NotificationClickedActionReceiver;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    NotificationClickedActionReceiver.register(this);
  }
}