package com.vulnerabbity.panicbutton.libs.storage.registry;

import android.content.Context;

import com.vulnerabbity.panicbutton.libs.storage.records.StorageRecordIsPanicEnabled;
import com.vulnerabbity.panicbutton.libs.storage.records.StorageRecordPanicAction1;
import com.vulnerabbity.panicbutton.libs.storage.records.StorageRecordPanicAction2;

public class StorageRegistry {
  final public StorageRecordIsPanicEnabled isPanicEnabled;
  final public StorageRecordPanicAction1 panicAction1;
  final public StorageRecordPanicAction2 panicAction2;

  public StorageRegistry(Context context) {
    isPanicEnabled = new StorageRecordIsPanicEnabled(context);
    panicAction1 = new StorageRecordPanicAction1(context);
    panicAction2 = new StorageRecordPanicAction2(context);
  }
}
