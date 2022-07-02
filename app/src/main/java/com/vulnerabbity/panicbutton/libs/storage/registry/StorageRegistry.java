package com.vulnerabbity.panicbutton.libs.storage.registry;

import android.content.Context;

import com.vulnerabbity.panicbutton.libs.storage.records.StorageRecordIsPanicEnabled;

public class StorageRegistry {
  final public StorageRecordIsPanicEnabled isPanicEnabled;

  public StorageRegistry(Context context) {
    isPanicEnabled = new StorageRecordIsPanicEnabled(context);
  }
}
