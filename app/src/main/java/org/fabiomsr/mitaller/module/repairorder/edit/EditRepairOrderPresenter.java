package org.fabiomsr.mitaller.module.repairorder.edit;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.mitaller.app.base.Presenter;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.domain.mapper.impl.RepairOrderMapper;
import org.fabiomsr.mitaller.module.repairorder.edit.contract.EditRepairOrderViewContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EditRepairOrderPresenter extends Presenter<EditRepairOrderViewContract> {

  private ReceiptDataStore  mReceiptDataStore;
  private RepairOrderMapper mMapper;


  @Inject
  public EditRepairOrderPresenter(ReceiptDataStore dataStore) {
    mReceiptDataStore = dataStore;
    mMapper = new RepairOrderMapper();
  }


  public void updateRepairOrder(RepairOrder repairOrder) {
    mReceiptDataStore.insertRepairOrder(mMapper.toModel(repairOrder))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                     mContract.onUpdateRepairOrderComplete();
                   },
                   mContract::onUpdateRepairOrderError
        );

  }
}
