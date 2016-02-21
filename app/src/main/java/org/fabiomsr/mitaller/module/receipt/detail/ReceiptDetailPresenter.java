package org.fabiomsr.mitaller.module.receipt.detail;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.mitaller.app.base.Presenter;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.domain.mapper.impl.ReceiptMapper;
import org.fabiomsr.mitaller.module.receipt.detail.contract.ReceiptDetailViewContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReceiptDetailPresenter extends Presenter<ReceiptDetailViewContract> {

  private ReceiptDataStore mReceiptDataStore;
  private ReceiptMapper    mMapper;


  @Inject
  public ReceiptDetailPresenter(ReceiptDataStore dataStore) {
    mReceiptDataStore = dataStore;
    mMapper = new ReceiptMapper();
  }


  public void deleteReceipt(Receipt receipt) {
    mReceiptDataStore.deleteReceipt(mMapper.toModel(receipt))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                     mContract.onDeleteReceiptComplete();
                   },
                   mContract::onDeleteReceiptError
        );

  }
}
