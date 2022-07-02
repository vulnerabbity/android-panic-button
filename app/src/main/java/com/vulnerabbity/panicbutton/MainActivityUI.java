package com.vulnerabbity.panicbutton;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.vulnerabbity.panicbutton.libs.panic.PanicActions;
import com.vulnerabbity.panicbutton.libs.storage.registry.StorageRegistry;

public class MainActivityUI  {
  private StorageRegistry storage;
  private MainActivity mainActivity;

  public MainActivityUI(MainActivity mainActivity) {
    this.storage = new StorageRegistry(mainActivity);
    this.mainActivity = mainActivity;
  }

  public void initUi() {
    updateRadioButtons();
  }

  private void updateRadioButtons() {
    updateFirstRadioGroup();
    updateSecondRadioGroup();
  }

  private void updateFirstRadioGroup() {
    RadioGroup firstActionRadioGroup = getFirstActionRadioGroup();
    String panicAction = getFirstPanicAction();

    if (panicAction == PanicActions.LOCK) {
      firstActionRadioGroup.check(R.id.main_activity_radio_action_1_lock);
    } else if (panicAction == PanicActions.NONE) {
      firstActionRadioGroup.check(R.id.main_activity_radio_action_1_none);
    }
  }

  private void updateSecondRadioGroup() {
    RadioGroup secondActionRadioGroup = getSecondActionRadioGroup();
    String panicAction = getSecondPanicAction();

    if (panicAction == PanicActions.LOCK) {
      secondActionRadioGroup.check(R.id.main_activity_radio_action_2_lock);
    } else if (getSecondPanicAction() == PanicActions.NONE) {
      secondActionRadioGroup.check(R.id.main_activity_radio_action_2_none);
    }
  }

  private String getFirstPanicAction() {
    return this.storage.panicAction1.get();
  }

  private String getSecondPanicAction() {
    return this.storage.panicAction2.get();
  }

  private RadioGroup getFirstActionRadioGroup() {
    return mainActivity.findViewById(R.id.main_activity_radiogroup_action_1);
  }

  private RadioGroup getSecondActionRadioGroup() {
    return mainActivity.findViewById(R.id.main_activity_radiogroup_action_2);
  }
}
