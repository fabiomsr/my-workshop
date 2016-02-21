package org.fabiomsr.mitaller.module.repairorder.add.contract;

import org.fabiomsr.mitaller.app.base.ViewContract;

public interface AddRepairOrderViewContract extends ViewContract{

  void onLoadNextRepairOrderNumberComplete(int number);
  void onSaveRepairOrderComplete();
  void onSaveRepairOrderError(Throwable error);

}
