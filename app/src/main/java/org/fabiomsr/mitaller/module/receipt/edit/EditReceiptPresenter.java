package org.fabiomsr.mitaller.module.receipt.edit;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.mitaller.app.base.Presenter;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.domain.mapper.impl.ReceiptMapper;
import org.fabiomsr.mitaller.module.receipt.edit.contract.EditReceiptViewContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EditReceiptPresenter extends Presenter<EditReceiptViewContract> {

  private ReceiptDataStore mReceiptDataStore;
  private ReceiptMapper mMapper;


  @Inject
  public EditReceiptPresenter(ReceiptDataStore dataStore){
    mReceiptDataStore = dataStore;
    mMapper = new ReceiptMapper();
  }


  public void updateReceipt(Receipt receipt){
    mReceiptDataStore.insertReceipt(mMapper.toModel(receipt))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
              mContract.onUpdateReceiptComplete();
            },
            mContract::onUpdateReceiptError
        );

  }
}
