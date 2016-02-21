package org.fabiomsr.mitaller.module.receipt.add.contract;

import org.fabiomsr.mitaller.app.base.ViewContract;

public interface AddReceiptViewContract extends ViewContract{

  void onLoadNextReceiptNumberComplete(int number);
  void onSaveReceiptComplete();
  void onSaveReceiptError(Throwable error);

}
