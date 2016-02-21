package org.fabiomsr.mitaller.module.receipt.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseFragment;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.detail.adapter.ConceptAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiptConceptsFragment extends BaseFragment {

  @Bind(R.id.receipt_sub_total) TextView mReceiptSubTotalView;
  @Bind(R.id.receipt_igic) TextView mReceiptIGICView;
  @Bind(R.id.receipt_total) TextView mReceiptTotalView;

  @Bind(R.id.receipt_concepts_list) ListView mConceptsListView;


  /**
   * The fragment argument representing the item that this fragment
   * represents.
   */
  public static final String ARG_RECEIPT = "receipt";

  /**
   * Receipt content this fragment is presenting.
   */
  private Receipt mReceipt;


  private ConceptAdapter mConceptAdapter;

  public static ReceiptConceptsFragment newInstance(Receipt receipt){
    ReceiptConceptsFragment fragment = new ReceiptConceptsFragment();
    Bundle args = new Bundle();
    args.putParcelable(ARG_RECEIPT, receipt);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_receipt_concepts, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    mConceptAdapter = new ConceptAdapter(getContext());
    mConceptsListView.setAdapter(mConceptAdapter);
    mConceptAdapter.addAll(mReceipt.receiptConcepts());
  }

  @Override
  protected void readArguments(@NonNull Bundle args) {
    mReceipt = getArguments().getParcelable(ARG_RECEIPT);
  }

  @Override
  protected void updateUI() {
    String subtotal = getString(R.string.receipt_total_format, mReceipt.subTotal());
    String igicAmount = getString(R.string.receipt_total_format, mReceipt.IGICAmount());
    String total = getString(R.string.receipt_total_format, mReceipt.total());

    mReceiptSubTotalView.setText(subtotal);
    mReceiptIGICView.setText(igicAmount);
    mReceiptTotalView.setText(total);

  }
}
