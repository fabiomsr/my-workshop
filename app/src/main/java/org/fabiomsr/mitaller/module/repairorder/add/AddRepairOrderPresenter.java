package org.fabiomsr.mitaller.module.repairorder.add;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.mitaller.app.base.Presenter;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.domain.mapper.impl.RepairOrderMapper;
import org.fabiomsr.mitaller.module.repairorder.add.contract.AddRepairOrderViewContract;
import org.fabiomsr.mitaller.preferences.UserPreferences;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddRepairOrderPresenter extends Presenter<AddRepairOrderViewContract> {
  private UserPreferences   mUserPreferences;
  private ReceiptDataStore  mReceiptDataStore;
  private RepairOrderMapper mMapper;


  @Inject
  public AddRepairOrderPresenter(ReceiptDataStore dataStore, UserPreferences userPreferences) {
    mReceiptDataStore = dataStore;
    mUserPreferences = userPreferences;
    mMapper = new RepairOrderMapper();
  }

  public void loadNextReceiptNumber() {
    int number = mUserPreferences.getLastRepairOrderNumber();
    mContract.onLoadNextRepairOrderNumberComplete(number);
  }

  public void saveRepairOrder(RepairOrder repairOrder) {

    mReceiptDataStore.insertRepairOrder(mMapper.toModel(repairOrder))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                     mUserPreferences.incrementRepairOrderNumber();
                     mContract.onSaveRepairOrderComplete();
                   },
                   mContract::onSaveRepairOrderError
        );

  }
}
