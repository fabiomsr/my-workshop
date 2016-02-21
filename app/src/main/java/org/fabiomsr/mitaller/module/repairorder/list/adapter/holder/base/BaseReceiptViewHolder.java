package org.fabiomsr.mitaller.module.repairorder.list.adapter.holder.base;

import android.support.annotation.NonNull;
import android.view.View;

import org.fabiomsr.mitaller.app.base.adapter.BaseViewHolder;
import org.fabiomsr.mitaller.app.base.adapter.OnRecycleViewItemClickListener;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.base.RepairOrderAdapterItem;

public abstract class BaseReceiptViewHolder extends BaseViewHolder<RepairOrder> {
  public BaseReceiptViewHolder(View itemView) {
    super(itemView);
  }

  public BaseReceiptViewHolder(View itemView, @NonNull OnRecycleViewItemClickListener<RepairOrder> listener) {
    super(itemView, listener);
  }

  public abstract void update(RepairOrderAdapterItem item);
}
