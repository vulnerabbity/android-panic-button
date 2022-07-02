package com.vulnerabbity.panicbutton.libs.storage.records;

import android.content.Context;
import android.content.SharedPreferences;

import com.vulnerabbity.panicbutton.utils.logger.Logger;

public abstract class StorageRecord<T> {
  protected final SharedPreferences keyValueStorage;


  public StorageRecord(Context context) {
    keyValueStorage = context.getSharedPreferences("FileName", Context.MODE_PRIVATE);
  }

  public T get() {
    // convert to T type to get typed result
    return (T) _get();
  }

  protected String getRecordKey() {
    return this.getClass().toString();
  };

  protected abstract T getDefaultValue();

  public void set(T valueToSave) {
    StorageRecordIdentification typeIdentification = new StorageRecordIdentification(valueToSave);
    SharedPreferences.Editor keyValueEditor = keyValueStorage.edit();
    String RECORD_KEY = getRecordKey();

    if (typeIdentification.isInteger) {
      keyValueEditor.putInt(RECORD_KEY, (Integer) valueToSave);
    } else if (typeIdentification.isString) {
      keyValueEditor.putString(RECORD_KEY, (String) valueToSave);
    } else if (typeIdentification.isBoolean) {
      keyValueEditor.putBoolean(RECORD_KEY, (Boolean) valueToSave);
    } else if (typeIdentification.isFloat) {
      keyValueEditor.putFloat(RECORD_KEY, (Float) valueToSave);
    } else if (typeIdentification.isLong) {
      keyValueEditor.putLong(RECORD_KEY, (Long) valueToSave);
    } else {
      String unsupportedClassName = valueToSave.getClass().toString();
      Logger.error("type of " + unsupportedClassName + " cant be saved");
      return;
    }

    keyValueEditor.commit();
  }

  private Object _get() {
    String RECORD_KEY = getRecordKey();
    T defaultValue = getDefaultValue();
    StorageRecordIdentification typeIdentification = new StorageRecordIdentification(defaultValue);

    if (typeIdentification.isInteger) {
      return keyValueStorage.getInt(RECORD_KEY, (Integer) defaultValue);
    } else if (typeIdentification.isString) {
      return keyValueStorage.getString(RECORD_KEY, (String) defaultValue);
    } else if (typeIdentification.isBoolean) {
      return keyValueStorage.getBoolean(RECORD_KEY, (Boolean) defaultValue);
    } else if (typeIdentification.isFloat) {
      return keyValueStorage.getFloat(RECORD_KEY, (Float) defaultValue);
    } else if (typeIdentification.isLong) {
      return keyValueStorage.getLong(RECORD_KEY, (Long) defaultValue);
    }
    return defaultValue;
  }


}

class StorageRecordIdentification {
  Boolean isInteger = false;
  Boolean isString = false;
  Boolean isFloat = false;
  Boolean isBoolean = false;
  Boolean isLong = false;

  public StorageRecordIdentification(Object value) {
    this.isInteger = value.getClass() == Integer.class;
    this.isString = value.getClass() == String.class;
    this.isFloat = value.getClass() == Float.class;
    this.isBoolean = value.getClass() == Boolean.class;
    this.isLong = value.getClass() == Long.class;
  }
}