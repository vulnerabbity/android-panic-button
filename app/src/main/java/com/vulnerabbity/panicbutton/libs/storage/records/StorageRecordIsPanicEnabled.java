package com.vulnerabbity.panicbutton.libs.storage.records;

import android.content.Context;

public class StorageRecordIsPanicEnabled extends  StorageRecord<Boolean> {
  public StorageRecordIsPanicEnabled(Context context) {
    super(context);
  }

  @Override
  protected Boolean getDefaultValue() {
    return false;
  }
}
