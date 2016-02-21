package org.fabiomsr.mitaller.module.repairorder.list;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.mitaller.app.base.Presenter;
import org.fabiomsr.mitaller.app.injection.annotation.PerActivity;
import org.fabiomsr.mitaller.domain.mapper.impl.RepairOrderMapper;
import org.fabiomsr.mitaller.module.repairorder.list.contract.RepairOrderListViewContract;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PerActivity
public class RepairOrderListPresenter extends Presenter<RepairOrderListViewContract> {

  private ReceiptDataStore  mReceiptDataStore;
  private RepairOrderMapper mMapper;

  @Inject
  public RepairOrderListPresenter(ReceiptDataStore dataStore) {
    mReceiptDataStore = dataStore;
    mMapper = new RepairOrderMapper();
  }

  @Override
  public void resume() {
    mReceiptDataStore.loadRepairOrders()
        .subscribeOn(Schedulers.io())
        .flatMap(Observable::from)
        .map(mMapper::toData)
        .toList()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            mContract::onLoadRepairOrdersComplete,
            mContract::onLoadRepairOrdersError
        );
  }
}
