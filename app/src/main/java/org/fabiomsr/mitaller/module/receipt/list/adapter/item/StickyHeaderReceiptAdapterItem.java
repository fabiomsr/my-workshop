package org.fabiomsr.mitaller.module.receipt.list.adapter.item;

import org.fabiomsr.mitaller.module.receipt.list.adapter.ReceiptsAdapter;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.base.ReceiptAdapterItem;

import java.util.Date;

public class StickyHeaderReceiptAdapterItem extends ReceiptAdapterItem {

  private Date mDate;

  public StickyHeaderReceiptAdapterItem(int firstPosition, Date date) {
    super(firstPosition);
    mDate = date;
  }

  @Override
  public int getType() {
    return ReceiptsAdapter.STICKY_HEADER;
  }

  public Date getDate(){
    return mDate;
  }
}
