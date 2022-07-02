package com.vulnerabbity.panicbutton.libs.storage.records;

import android.content.Context;

import com.vulnerabbity.panicbutton.libs.panic.PanicActions;

public abstract class StorageRecordPanicAction extends StorageRecord<String> {

  public StorageRecordPanicAction(Context context) {
    super(context);
  }

  @Override
  protected String getDefaultValue() {
    return PanicActions.LOCK;
  }
}
