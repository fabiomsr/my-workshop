package org.fabiomsr.mitaller.module.receipt.edit.contract;

import org.fabiomsr.mitaller.app.base.ViewContract;

public interface EditReceiptViewContract extends ViewContract{

  void onUpdateReceiptComplete();
  void onUpdateReceiptError(Throwable error);

}
