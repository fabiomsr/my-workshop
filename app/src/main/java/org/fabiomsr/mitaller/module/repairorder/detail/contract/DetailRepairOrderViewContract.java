package org.fabiomsr.mitaller.module.repairorder.detail.contract;

import org.fabiomsr.mitaller.app.base.ViewContract;

public interface DetailRepairOrderViewContract extends ViewContract{
  void onDeleteRepairOrderComplete();
  void onDeleteRepairOrderError(Throwable error);
}
