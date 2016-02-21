package org.fabiomsr.mitaller.preferences.impl;

import android.content.Context;
import android.content.SharedPreferences;

import org.fabiomsr.mitaller.preferences.UserPreferences;

public class UserSharedPreferences implements UserPreferences{

  private static final String PREFERENCES_FILE = "preferences_store";
  private static final String LAST_RECEIPT_NUMBER = "last_receipt_number";
  private static final String LAST_REPAIR_ORDER_NUMBER = "last_repair_order_number";

  private SharedPreferences mPreferences;

  public UserSharedPreferences(Context context){
    mPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
  }

  @Override
  public int getLastReceiptNumber() {
    return mPreferences.getInt(LAST_RECEIPT_NUMBER, 641);
  }

  @Override
  public int getLastRepairOrderNumber() {
    return mPreferences.getInt(LAST_REPAIR_ORDER_NUMBER, 321);
  }

  @Override
  public void incrementReceiptNumber() {
    int nextNumber = getLastReceiptNumber() + 1;

    SharedPreferences.Editor edit = mPreferences.edit();
    edit.putInt(LAST_RECEIPT_NUMBER, nextNumber);
    edit.apply();
  }

  @Override
  public void incrementRepairOrderNumber() {
    int nextNumber = getLastRepairOrderNumber() + 1;

    SharedPreferences.Editor edit = mPreferences.edit();
    edit.putInt(LAST_REPAIR_ORDER_NUMBER, nextNumber);
    edit.apply();
  }
}
