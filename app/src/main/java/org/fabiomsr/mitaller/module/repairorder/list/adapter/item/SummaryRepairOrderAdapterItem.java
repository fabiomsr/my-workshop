package org.fabiomsr.mitaller.module.repairorder.list.adapter.item;

import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.RepairOrderAdapter;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.base.RepairOrderAdapterItem;

import java.util.List;

public class SummaryRepairOrderAdapterItem extends RepairOrderAdapterItem {

  private int mCount;
  private String mMonth;


  public SummaryRepairOrderAdapterItem(int firstPosition, List<RepairOrder> receipts, String month) {
    super(firstPosition);
    mCount = receipts.size();
    mMonth = month.toUpperCase();
  }

  @Override
  public int getType() {
    return RepairOrderAdapter.SUMMARY;
  }

  public int getRepairOrdersCount(){
    return mCount;
  }

  public String getRepairOrdersMonth(){
    return mMonth;
  }
}
