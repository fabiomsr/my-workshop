package org.fabiomsr.mitaller.module.repairorder.edit.contract;

import org.fabiomsr.mitaller.app.base.ViewContract;

public interface EditRepairOrderViewContract extends ViewContract{

  void onUpdateRepairOrderComplete();
  void onUpdateRepairOrderError(Throwable error);

}
