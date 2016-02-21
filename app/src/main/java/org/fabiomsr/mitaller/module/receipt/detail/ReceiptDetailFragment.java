package org.fabiomsr.mitaller.module.receipt.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseFragment;
import org.fabiomsr.mitaller.domain.Client;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.list.ReceiptListActivity;
import org.fabiomsr.mitaller.utils.CalendarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A fragment representing a single Receipt detail screen.
 * This fragment is either contained in a {@link ReceiptListActivity}
 * in two-pane mode (on tablets) or a {@link ReceiptDetailActivity}
 * on handsets.
 */
public class ReceiptDetailFragment extends BaseFragment {

  @Bind(R.id.receipt_number) TextView mReceiptNumberView;
  @Bind(R.id.receipt_date) TextView mReceiptDateView;

  @Bind(R.id.receipt_client_full_name) TextView mClientFullNameView;
  @Bind(R.id.receipt_client_nif) TextView mClientNIFView;
  @Bind(R.id.receipt_client_address) TextView mClientAddressView;

  @Bind(R.id.receipt_device_subject) TextView mDeviceSubjectView;
  @Bind(R.id.receipt_device_brand) TextView mDeviceBrandView;
  @Bind(R.id.receipt_device_model) TextView mDeviceModelView;
  @Bind(R.id.receipt_device_serial) TextView mDeviceSerialView;


  /**
   * The fragment argument representing the item that this fragment
   * represents.
   */
  public static final String ARG_RECEIPT = "receipt";

  /**
   * Receipt content this fragment is presenting.
   */
  private Receipt mReceipt;

  public static ReceiptDetailFragment newInstance(Receipt receipt){
    ReceiptDetailFragment fragment = new ReceiptDetailFragment();
    Bundle args = new Bundle();
    args.putParcelable(ARG_RECEIPT, receipt);
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_receipt_detail, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override
  protected void readArguments(@NonNull Bundle args) {
    mReceipt = getArguments().getParcelable(ARG_RECEIPT);
  }

  @Override
  protected void updateUI() {
    mReceiptNumberView.setText(String.valueOf(mReceipt.number()));
    mReceiptDateView.setText(CalendarUtils.getFullFormatDate(mReceipt.date()));

    Client client = mReceipt.client();
    mClientFullNameView.setText(client.fullName());
    mClientNIFView.setText(client.dni());
    mClientAddressView.setText(client.address());

    mDeviceSubjectView.setText(mReceipt.subject());
    mDeviceBrandView.setText(mReceipt.brand());
    mDeviceModelView.setText(mReceipt.model());
    mDeviceSerialView.setText(mReceipt.serial());
  }
}
