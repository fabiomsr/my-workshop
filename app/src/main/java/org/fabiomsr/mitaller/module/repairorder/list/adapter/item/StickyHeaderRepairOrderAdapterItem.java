package org.fabiomsr.mitaller.module.repairorder.list.adapter.item;

import org.fabiomsr.mitaller.module.repairorder.list.adapter.RepairOrderAdapter;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.base.RepairOrderAdapterItem;

import java.util.Date;

public class StickyHeaderRepairOrderAdapterItem extends RepairOrderAdapterItem {

  private Date mDate;

  public StickyHeaderRepairOrderAdapterItem(int firstPosition, Date date) {
    super(firstPosition);
    mDate = date;
  }

  @Override
  public int getType() {
    return RepairOrderAdapter.STICKY_HEADER;
  }

  public Date getDate(){
    return mDate;
  }
}
