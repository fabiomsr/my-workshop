package org.fabiomsr.mitaller.module.receipt.detail.contract;

import org.fabiomsr.mitaller.app.base.ViewContract;

public interface ReceiptDetailViewContract extends ViewContract{
  void onDeleteReceiptComplete();
  void onDeleteReceiptError(Throwable error);
}
