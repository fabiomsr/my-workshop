package org.fabiomsr.mitaller.module.repairorder.add;

import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.mitaller.app.base.Presenter;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.domain.mapper.impl.RepairOrderMapper;
import org.fabiomsr.mitaller.module.repairorder.add.contract.AddRepairOrderViewContract;
import org.fabiomsr.mitaller.preferences.UserPreferences;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddRepairOrderPresenter extends Presenter<AddRepairOrderViewContract> {

  private static final String TAG = AddRepairOrderPresenter.class.getSimpleName();

  private UserPreferences   mUserPreferences;
  private ReceiptDataStore  mReceiptDataStore;
  private RepairOrderMapper mMapper;
  private SimpleDateFormat mDateFormat;


  @Inject
  public AddRepairOrderPresenter(ReceiptDataStore dataStore, UserPreferences userPreferences) {
    mReceiptDataStore = dataStore;
    mUserPreferences = userPreferences;
    mMapper = new RepairOrderMapper();
    mDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
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

  @Nullable
  public File createImageFile()  {
      // Create an image file name
      String timeStamp = mDateFormat.format(new Date());
      String imageFileName = "JPEG_" + timeStamp + "_";
      File storageDir = Environment.getExternalStoragePublicDirectory(
          Environment.DIRECTORY_PICTURES);

    try {
      return File.createTempFile(
         imageFileName,  /* prefix */
         ".jpg",         /* suffix */
         storageDir      /* directory */
     );
    } catch (IOException e) {
      Log.e(TAG, "createImageFile: ", e);
    }

    return null;
  }
}
