package org.fabiomsr.mitaller.module.receipt.list;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.mitaller.app.base.Presenter;
import org.fabiomsr.mitaller.app.injection.annotation.PerActivity;
import org.fabiomsr.mitaller.domain.mapper.impl.ReceiptMapper;
import org.fabiomsr.mitaller.module.receipt.list.contract.ReceiptListViewContract;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PerActivity
public class ReceiptListPresenter extends Presenter<ReceiptListViewContract> {

  private ReceiptDataStore mReceiptDataStore;
  private ReceiptMapper mMapper;

  @Inject
  public ReceiptListPresenter(ReceiptDataStore dataStore){
    mReceiptDataStore = dataStore;
    mMapper = new ReceiptMapper();
  }

  @Override
  public void resume() {
    mReceiptDataStore.loadReceipts()
        .subscribeOn(Schedulers.io())
        .flatMap(Observable::from)
        .map(mMapper::toData)
        .toList()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            mContract::onLoadReceiptsComplete,
            mContract::onLoadReceiptsError
        );
  }
}
