package org.fabiomsr.data.impl;

import android.content.Context;
import android.util.Log;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.data.model.ReceiptStore;
import org.fabiomsr.data.model.RepairOrderStore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

public class RealmIOReceiptDataStore implements ReceiptDataStore {

  private static final String TAG = RealmIOReceiptDataStore.class.getSimpleName();

  private Context mContext;

  @Inject
  public RealmIOReceiptDataStore(Context context) {
    mContext = context;
  }


  @Override
  public Observable<List<RepairOrderStore>> loadRepairOrders() {
    return Observable.create(subscriber -> {
      Realm realm = null;

      try {
        realm = Realm.getInstance(mContext);
        RealmQuery<RepairOrderStore> query = realm.where(RepairOrderStore.class);
        RealmResults<RepairOrderStore> result = query.equalTo(RepairOrderStore.FIELD_IS_DELETE, false)
            .findAllSorted(RepairOrderStore.FIELD_DATE, Sort.DESCENDING);

        List<RepairOrderStore> repairOrders = new ArrayList<>(result.size());
        Observable.from(result)
            .forEach(repairOrders::add);

        subscriber.onNext(repairOrders);
        subscriber.onCompleted();
      } catch (Exception ex) {
        Log.e(TAG, "Error when try load receipts", ex);
        subscriber.onError(ex);
      } finally {
        if (realm != null) {
          realm.close();
        }
      }
    });
  }

  @Override
  public Observable<List<ReceiptStore>> loadReceipts() {
    return Observable.create(subscriber -> {
      Realm realm = null;

      try {
        realm = Realm.getInstance(mContext);
        RealmQuery<ReceiptStore> query = realm.where(ReceiptStore.class);
        RealmResults<ReceiptStore> result = query.equalTo(ReceiptStore.FIELD_IS_DELETE, false)
            .findAllSorted(ReceiptStore.FIELD_DATE, Sort.DESCENDING);

        List<ReceiptStore> receipts = new ArrayList<>(result.size());
        Observable.from(result)
            .forEach(receipts::add);

        subscriber.onNext(receipts);
        subscriber.onCompleted();
      } catch (Exception ex) {
        Log.e(TAG, "Error when try load receipts", ex);
        subscriber.onError(ex);
      } finally {
        if (realm != null) {
          realm.close();
        }
      }
    });
  }

  @Override
  public Observable<Boolean> insertRepairOrder(RepairOrderStore repairOrder) {
    return updateRepairOrder(repairOrder);
  }

  @Override
  public Observable<Boolean> insertReceipt(ReceiptStore receiptStore) {
    return updateReceipt(receiptStore);
  }

  @Override
  public Observable<Boolean> updateRepairOrder(RepairOrderStore repairOrderStore) {
    return Observable.create(subscriber -> {

      Realm realm = null;

      try {
        realm = Realm.getInstance(mContext);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(repairOrderStore);
        subscriber.onNext(true);
        subscriber.onCompleted();
        realm.commitTransaction();
      } catch (Exception ex) {
        Log.e(TAG, "Error when try load receipts", ex);
        subscriber.onError(ex);
      } finally {
        if (realm != null) {
          realm.close();
        }
      }
    });
  }

  @Override
  public Observable<Boolean> updateReceipt(ReceiptStore receiptStore) {
    return Observable.create(subscriber -> {

      Realm realm = null;

      try {
        realm = Realm.getInstance(mContext);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(receiptStore);
        subscriber.onNext(true);
        subscriber.onCompleted();
        realm.commitTransaction();
      } catch (Exception ex) {
        Log.e(TAG, "Error when try load receipts", ex);
        subscriber.onError(ex);
      } finally {
        if (realm != null) {
          realm.close();
        }
      }
    });
  }

  @Override
  public Observable<Boolean> deleteRepairOrder(RepairOrderStore repairOrderStore) {
    repairOrderStore.setIsDeleted(true);
    return updateRepairOrder(repairOrderStore);
  }

  @Override
  public Observable<Boolean> deleteReceipt(ReceiptStore receiptStore) {
    receiptStore.setIsDeleted(true);
    return updateReceipt(receiptStore);
  }
}
