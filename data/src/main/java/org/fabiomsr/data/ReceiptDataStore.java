package org.fabiomsr.data;

import org.fabiomsr.data.model.ReceiptStore;
import org.fabiomsr.data.model.RepairOrderStore;

import java.util.List;

import rx.Observable;

public interface ReceiptDataStore {

  ///
  ///  LOADs
  ///

  Observable<List<RepairOrderStore>> loadRepairOrders();

  Observable<List<ReceiptStore>> loadReceipts();


  ///
  ///  INSERT
  ///

  Observable<Boolean> insertRepairOrder(RepairOrderStore repairOrder);

  Observable<Boolean> insertReceipt(ReceiptStore receipt);


  ///
  ///  UPDATE
  ///

  Observable<Boolean> updateRepairOrder(RepairOrderStore repairOrderStore);

  Observable<Boolean> updateReceipt(ReceiptStore receiptStore);


  ///
  ///  DELETE
  ///

  Observable<Boolean> deleteRepairOrder(RepairOrderStore repairOrderStore);

  Observable<Boolean> deleteReceipt(ReceiptStore receiptStore);

}
