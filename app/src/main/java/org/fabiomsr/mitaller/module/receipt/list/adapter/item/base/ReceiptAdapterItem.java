package org.fabiomsr.mitaller.module.receipt.list.adapter.item.base;

import org.fabiomsr.mitaller.module.receipt.list.adapter.ReceiptsAdapter;

public abstract class ReceiptAdapterItem {

  protected int mFirstPosition;

  public ReceiptAdapterItem(int firstPosition){
    mFirstPosition = firstPosition;
  }

  @ReceiptsAdapter.ReceiptViewType
  public abstract int getType();

  public int getFirstPosition(){
    return mFirstPosition;
  }
}
