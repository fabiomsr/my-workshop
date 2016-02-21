package org.fabiomsr.mitaller.module.receipt.list.contract;

import org.fabiomsr.mitaller.app.base.ViewContract;
import org.fabiomsr.mitaller.domain.Receipt;

import java.util.List;

public interface ReceiptListViewContract extends ViewContract{

  void onLoadReceiptsComplete(List<Receipt> receipts);

  void onLoadReceiptsError(Throwable error);

}
