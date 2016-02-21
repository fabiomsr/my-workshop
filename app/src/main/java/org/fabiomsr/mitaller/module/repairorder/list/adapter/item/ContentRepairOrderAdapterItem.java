package org.fabiomsr.mitaller.module.repairorder.list.adapter.item;

import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.RepairOrderAdapter;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.base.RepairOrderAdapterItem;

public class ContentRepairOrderAdapterItem extends RepairOrderAdapterItem {

  private RepairOrder mRepairOrder;

  public ContentRepairOrderAdapterItem(int firstPosition, RepairOrder receipt) {
    super(firstPosition);
    mRepairOrder = receipt;
  }

  @Override
  public int getType() {
    return RepairOrderAdapter.CONTENT;
  }

  public RepairOrder getRepairOrder() {
    return mRepairOrder;
  }
}
