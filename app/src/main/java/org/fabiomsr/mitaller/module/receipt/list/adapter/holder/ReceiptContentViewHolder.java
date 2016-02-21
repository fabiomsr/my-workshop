package org.fabiomsr.mitaller.module.receipt.list.adapter.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.adapter.OnRecycleViewItemClickListener;
import org.fabiomsr.mitaller.domain.Client;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.list.adapter.holder.base.BaseReceiptViewHolder;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.ContentReceiptAdapterItem;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.base.ReceiptAdapterItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiptContentViewHolder extends BaseReceiptViewHolder {

  @Bind(R.id.receipt_number)    TextView mReceiptNumber;
  @Bind(R.id.receipt_client_full_name)  TextView mClientFullName;

  private Receipt mReceipt;

  public ReceiptContentViewHolder(@NonNull View view, @NonNull final OnRecycleViewItemClickListener<Receipt> listener) {
    super(view);
    ButterKnife.bind(this, view);

    view.setOnClickListener(v ->
        listener.onRecycleViewItemClick(v, mReceipt, getAdapterPosition()));
  }

  @Override
  public void update(ReceiptAdapterItem adapterItem) {
    ContentReceiptAdapterItem item = (ContentReceiptAdapterItem) adapterItem;
    mReceipt = item.getReceipt();
    Client client = mReceipt.client();

    mReceiptNumber.setText(String.valueOf(mReceipt.number()));
    mClientFullName.setText(client.fullName());
  }


}
