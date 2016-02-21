package org.fabiomsr.mitaller.module.receipt.list.adapter.holder;

import android.view.View;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.module.receipt.list.adapter.holder.base.BaseReceiptViewHolder;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.SummaryReceiptAdapterItem;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.base.ReceiptAdapterItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiptSummaryViewHolder extends BaseReceiptViewHolder {

  @Bind(R.id.receipt_month)  TextView mReceiptMonth;
  @Bind(R.id.receipt_total)  TextView mReceiptTotal;

  public ReceiptSummaryViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  @Override
  public void update(ReceiptAdapterItem adapterItem) {
    SummaryReceiptAdapterItem item = (SummaryReceiptAdapterItem) adapterItem;

    mReceiptMonth.setText(item.getReceiptsMonth());
    mReceiptTotal.setText(itemView.getContext().getString(R.string.receipt_total_format, item.getReceiptsTotal()));
  }
}
