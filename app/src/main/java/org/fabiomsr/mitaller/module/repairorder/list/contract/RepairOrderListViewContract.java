package org.fabiomsr.mitaller.module.repairorder.list.contract;

import org.fabiomsr.mitaller.app.base.ViewContract;
import org.fabiomsr.mitaller.domain.RepairOrder;

import java.util.List;

public interface RepairOrderListViewContract extends ViewContract{

  void onLoadRepairOrdersComplete(List<RepairOrder> repairOrders);

  void onLoadRepairOrdersError(Throwable error);

}
