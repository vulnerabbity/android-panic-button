package com.vulnerabbity.panicbutton.utils.logger;

import android.util.Log;

public class Logger {
  static final private String LOGGER_TAG = "Logger";

  static public void log(String message) {
    _log(message);
  }

  static public void log(CharSequence charMessage) {
    _log(charMessage.toString());
  }

  static public void error(String message) {
    _error(message);
  }

  static public void error(CharSequence charMessage) {
    _error(charMessage.toString());
  }

  static private void _log(String message) {
    Log.i(LOGGER_TAG, message);
  }

  static private void _error(String message) {
    Log.e(LOGGER_TAG, message);
  }
}
