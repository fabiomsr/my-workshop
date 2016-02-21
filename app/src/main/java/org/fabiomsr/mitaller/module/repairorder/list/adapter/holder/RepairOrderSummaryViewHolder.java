package org.fabiomsr.mitaller.module.repairorder.list.adapter.holder;

import android.view.View;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.holder.base.BaseReceiptViewHolder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.SummaryRepairOrderAdapterItem;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.base.RepairOrderAdapterItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepairOrderSummaryViewHolder extends BaseReceiptViewHolder {

  @Bind(R.id.receipt_month)  TextView mReceiptMonth;

  public RepairOrderSummaryViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  @Override
  public void update(RepairOrderAdapterItem adapterItem) {
    SummaryRepairOrderAdapterItem item = (SummaryRepairOrderAdapterItem) adapterItem;

    mReceiptMonth.setText(item.getRepairOrdersMonth());
  }
}
