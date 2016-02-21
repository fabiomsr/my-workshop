package org.fabiomsr.mitaller.module.receipt.list.adapter.item;

import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.list.adapter.ReceiptsAdapter;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.base.ReceiptAdapterItem;

import java.util.List;

public class SummaryReceiptAdapterItem extends ReceiptAdapterItem {

  private int mCount;
  private float mTotal;
  private String mMonth;


  public SummaryReceiptAdapterItem(int firstPosition, List<Receipt> receipts, String month) {
    super(firstPosition);
    mCount = receipts.size();

    mTotal = 0;
    for (Receipt receipt: receipts) {
      mTotal += receipt.total();
    }

    mMonth = month.toUpperCase();
  }

  @Override
  public int getType() {
    return ReceiptsAdapter.SUMMARY;
  }

  public int getReceiptsCount(){
    return mCount;
  }

  public float getReceiptsTotal(){
    return mTotal;
  }

  public String getReceiptsMonth(){
    return mMonth;
  }
}
