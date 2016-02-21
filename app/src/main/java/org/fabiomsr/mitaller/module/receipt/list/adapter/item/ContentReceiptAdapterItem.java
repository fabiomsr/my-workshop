package org.fabiomsr.mitaller.module.receipt.list.adapter.item;

import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.list.adapter.ReceiptsAdapter;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.base.ReceiptAdapterItem;

public class ContentReceiptAdapterItem extends ReceiptAdapterItem {

  private Receipt mReceipt;

  public ContentReceiptAdapterItem(int firstPosition, Receipt receipt) {
    super(firstPosition);
    mReceipt = receipt;
  }

  @Override
  public int getType() {
    return ReceiptsAdapter.CONTENT;
  }

  public Receipt getReceipt(){
    return mReceipt;
  }
}
