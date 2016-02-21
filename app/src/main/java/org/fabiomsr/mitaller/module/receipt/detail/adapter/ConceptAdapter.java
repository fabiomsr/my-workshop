package org.fabiomsr.mitaller.module.receipt.detail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.injection.annotation.PerActivity;
import org.fabiomsr.mitaller.domain.ReceiptConcept;

import butterknife.Bind;
import butterknife.ButterKnife;

@PerActivity
public class ConceptAdapter extends ArrayAdapter<ReceiptConcept> {

  static class Holder {
    @Bind(R.id.receipt_concept_description) TextView mDescription;
    @Bind(R.id.receipt_concept_count) TextView mCount;
    @Bind(R.id.receipt_concept_total) TextView mTotal;

    public Holder(View view){
      ButterKnife.bind(this, view);
    }
  }

  private Context mContext;
  private LayoutInflater mLayoutInflate;

  public ConceptAdapter(@NonNull Context context) {
    super(context, 0);
    mContext = context;
    mLayoutInflate = LayoutInflater.from(context);
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Holder holder;
    View rowView = convertView;

    if (rowView != null){
      holder = (Holder) rowView.getTag();
    }else{
      rowView = mLayoutInflate.inflate(R.layout.adapter_receipt_concept_list_content, parent, false);
      holder = new Holder(rowView);
      rowView.setTag(holder);
    }

    ReceiptConcept item = getItem(position);
    holder.mDescription.setText(item.concept());
    holder.mCount.setText(mContext.getString(R.string.receipt_item_count_format, item.count(), item.amount()));
    holder.mTotal.setText(mContext.getString(R.string.receipt_total_format, item.totalAmount()));

    return rowView;
  }
}
