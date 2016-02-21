package org.fabiomsr.mitaller.module.receipt.list.adapter.holder.base;

import android.support.annotation.NonNull;
import android.view.View;

import org.fabiomsr.mitaller.app.base.adapter.BaseViewHolder;
import org.fabiomsr.mitaller.app.base.adapter.OnRecycleViewItemClickListener;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.base.ReceiptAdapterItem;

public abstract class BaseReceiptViewHolder extends BaseViewHolder<Receipt> {
  public BaseReceiptViewHolder(View itemView) {
    super(itemView);
  }

  public BaseReceiptViewHolder(View itemView, @NonNull OnRecycleViewItemClickListener<Receipt> listener) {
    super(itemView, listener);
  }

  public abstract void update(ReceiptAdapterItem item);
}
