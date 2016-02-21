package org.fabiomsr.mitaller.module.repairorder.list.adapter.item.base;

import org.fabiomsr.mitaller.module.repairorder.list.adapter.RepairOrderAdapter;

public abstract class RepairOrderAdapterItem {

  protected int mFirstPosition;

  public RepairOrderAdapterItem(int firstPosition){
    mFirstPosition = firstPosition;
  }

  @RepairOrderAdapter.ReceiptViewType
  public abstract int getType();

  public int getFirstPosition(){
    return mFirstPosition;
  }
}
