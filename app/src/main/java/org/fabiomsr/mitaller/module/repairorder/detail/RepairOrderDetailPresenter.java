package org.fabiomsr.mitaller.module.repairorder.detail;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.mitaller.app.base.Presenter;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.domain.mapper.impl.RepairOrderMapper;
import org.fabiomsr.mitaller.module.repairorder.detail.contract.DetailRepairOrderViewContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepairOrderDetailPresenter extends Presenter<DetailRepairOrderViewContract> {

  private ReceiptDataStore           mReceiptDataStore;
  private RepairOrderMapper          mMapper;

  @Inject
  public RepairOrderDetailPresenter(ReceiptDataStore dataStore) {
    mReceiptDataStore = dataStore;
    mMapper = new RepairOrderMapper();
  }

  public void deleteRepairOrder(RepairOrder repairOrder) {
    mReceiptDataStore.deleteRepairOrder(mMapper.toModel(repairOrder))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                     mContract.onDeleteRepairOrderComplete();
                   },
                   mContract::onDeleteRepairOrderError
        );
  }

}
