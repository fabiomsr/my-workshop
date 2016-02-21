package org.fabiomsr.mitaller.module.receipt.add.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.adapter.BaseViewHolder;
import org.fabiomsr.mitaller.app.base.adapter.OnRecycleViewItemClickListener;
import org.fabiomsr.mitaller.domain.ReceiptConcept;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiptConceptViewHolder extends BaseViewHolder<ReceiptConcept> {

  @Bind(R.id.receipt_concept_description) TextView mDescriptionView;
  @Bind(R.id.receipt_concept_count) TextView mCountView;
  @Bind(R.id.receipt_concept_total) TextView mTotalView;
  @Bind(R.id.remove_concept)  ImageView mRemoveConceptView;

  private ReceiptConcept mReceiptConcept;

  public ReceiptConceptViewHolder(View itemView, OnRecycleViewItemClickListener<ReceiptConcept> listener) {
    super(itemView, listener);

    ButterKnife.bind(this, itemView);
    mRemoveConceptView.setOnClickListener(v ->
        listener.onRecycleViewItemClick(v, mReceiptConcept, getAdapterPosition()));
  }

  public void update(ReceiptConcept concept){
    mReceiptConcept = concept;

    mDescriptionView.setText(mReceiptConcept.concept());

    Context context = itemView.getContext();

    mCountView.setText(context.getString(R.string.receipt_item_count_format,
        mReceiptConcept.count(), mReceiptConcept.amount()));
    mTotalView.setText(context.getString(R.string.receipt_total_format,
        mReceiptConcept.totalAmount()));
  }
}
