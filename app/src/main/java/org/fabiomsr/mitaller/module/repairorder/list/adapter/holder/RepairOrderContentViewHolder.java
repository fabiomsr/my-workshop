package org.fabiomsr.mitaller.module.repairorder.list.adapter.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.adapter.OnRecycleViewItemClickListener;
import org.fabiomsr.mitaller.domain.Client;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.holder.base.BaseReceiptViewHolder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.ContentRepairOrderAdapterItem;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.base.RepairOrderAdapterItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepairOrderContentViewHolder extends BaseReceiptViewHolder {

  @Bind(R.id.receipt_number)    TextView mReceiptNumber;
  @Bind(R.id.receipt_client_full_name)  TextView mClientFullName;

  private RepairOrder mRepairOrder;

  public RepairOrderContentViewHolder(@NonNull View view, @NonNull final OnRecycleViewItemClickListener<RepairOrder> listener) {
    super(view);
    ButterKnife.bind(this, view);

    view.setOnClickListener(v ->
        listener.onRecycleViewItemClick(v, mRepairOrder, getAdapterPosition()));
  }

  @Override
  public void update(RepairOrderAdapterItem adapterItem) {
    ContentRepairOrderAdapterItem item = (ContentRepairOrderAdapterItem) adapterItem;
    mRepairOrder = item.getRepairOrder();
    Client client = mRepairOrder.client();

    mReceiptNumber.setText(String.valueOf(mRepairOrder.number()));
    mClientFullName.setText(client.fullName());
  }


}
