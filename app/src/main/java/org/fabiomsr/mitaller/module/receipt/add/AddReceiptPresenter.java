package org.fabiomsr.mitaller.module.receipt.add;

import android.support.annotation.Nullable;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.mitaller.app.base.Presenter;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.domain.mapper.impl.ReceiptMapper;
import org.fabiomsr.mitaller.module.receipt.add.contract.AddReceiptViewContract;
import org.fabiomsr.mitaller.preferences.UserPreferences;
import org.fabiomsr.mitaller.utils.FileSystemUtils;

import java.io.File;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddReceiptPresenter extends Presenter<AddReceiptViewContract> {

  private UserPreferences mUserPreferences;
  private ReceiptDataStore mReceiptDataStore;
  private ReceiptMapper mMapper;


  @Inject
  public AddReceiptPresenter(ReceiptDataStore dataStore, UserPreferences userPreferences){
    mReceiptDataStore = dataStore;
    mUserPreferences = userPreferences;
    mMapper = new ReceiptMapper();
  }

  public void loadNextReceiptNumber(){
    int number = mUserPreferences.getLastReceiptNumber();
    mContract.onLoadNextReceiptNumberComplete(number);
  }

  public void saveReceipt(Receipt receipt){

    mReceiptDataStore.insertReceipt(mMapper.toModel(receipt))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
                     mUserPreferences.incrementReceiptNumber();
                     mContract.onSaveReceiptComplete();
                   },
                   mContract::onSaveReceiptError
        );
  }

  @Nullable
  public File createImageFile()  {
    return FileSystemUtils.createImageFile();
  }
}
