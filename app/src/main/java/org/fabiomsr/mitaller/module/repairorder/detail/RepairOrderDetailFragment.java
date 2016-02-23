package org.fabiomsr.mitaller.module.repairorder.detail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.Application;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.app.base.BaseFragment;
import org.fabiomsr.mitaller.app.injection.module.ActivityModule;
import org.fabiomsr.mitaller.domain.Client;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.receipt.detail.ReceiptDetailActivity;
import org.fabiomsr.mitaller.module.receipt.list.ReceiptListActivity;
import org.fabiomsr.mitaller.module.repairorder.detail.contract.DetailRepairOrderViewContract;
import org.fabiomsr.mitaller.module.repairorder.edit.EditRepairOrderActivity;
import org.fabiomsr.mitaller.navigation.Navigation;
import org.fabiomsr.mitaller.utils.CalendarUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A fragment representing a single Receipt detail screen.
 * This fragment is either contained in a {@link ReceiptListActivity}
 * in two-pane mode (on tablets) or a {@link ReceiptDetailActivity}
 * on handsets.
 */
public class RepairOrderDetailFragment extends BaseFragment implements DetailRepairOrderViewContract {

  private static final int REQUEST_EDIT_REPAIR_ORDER = 124;

  /**
   * The fragment argument representing the item that this fragment
   * represents.
   */
  public static final String ARG_REPAIR_ORDER = "repair_order";

  @Bind(R.id.receipt_number) TextView mRepairOrderNumberView;
  @Bind(R.id.receipt_date)   TextView mRepairOrderDateView;

  @Bind(R.id.card_receipt_label) TextView mRepairOrderTitle;

  @Bind(R.id.receipt_client_full_name) TextView mClientFullNameView;
  @Bind(R.id.receipt_client_nif)       TextView mClientNIFView;
  @Bind(R.id.receipt_client_address)   TextView mClientAddressView;

  @Bind(R.id.receipt_device_subject) TextView mDeviceSubjectView;
  @Bind(R.id.receipt_device_brand)   TextView mDeviceBrandView;
  @Bind(R.id.receipt_device_model)   TextView mDeviceModelView;
  @Bind(R.id.receipt_device_serial)  TextView mDeviceSerialView;

  @Bind(R.id.receipt_device_photo) ImageView mPhotoView;

  @Inject RepairOrderDetailPresenter mRepairOrderDetailPresenter;

  /**
   * Edit Repair Order Detail Component
   */
  private RepairOrderDetailComponent mEditRepairOrderComponent;


  /**
   * Initialize dependency component
   * @return Initialized Component
   */
  @CheckResult(suggest = "#inject(AddReceiptDetailsFragment)")
  RepairOrderDetailComponent component() {
    if (mEditRepairOrderComponent == null) {
      Application application = (Application) getActivity().getApplication();

      mEditRepairOrderComponent = DaggerRepairOrderDetailComponent.builder()
          .applicationComponent(application.component())
          .activityModule(new ActivityModule((BaseActivity) getActivity()))
          .build();
    }

    return mEditRepairOrderComponent;
  }


  /**
   * Receipt content this fragment is presenting.
   */
  private RepairOrder mRepairOrder;

  public static RepairOrderDetailFragment newInstance(RepairOrder repairOrder) {
    RepairOrderDetailFragment fragment = new RepairOrderDetailFragment();
    Bundle                    args     = new Bundle();
    args.putParcelable(ARG_REPAIR_ORDER, repairOrder);
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_repair_order_detail, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    component().inject(this);
    attachPresenter(mRepairOrderDetailPresenter);
    mRepairOrderDetailPresenter.attachViewContract(this);
    setHasOptionsMenu(true);
    mRepairOrderTitle.setText(R.string.repair_order_title);
  }

  @Override
  protected void readArguments(@NonNull Bundle args) {
    mRepairOrder = getArguments().getParcelable(ARG_REPAIR_ORDER);
  }

  @Override
  protected void updateUI() {
    mRepairOrderNumberView.setText(String.valueOf(mRepairOrder.number()));
    mRepairOrderDateView.setText(CalendarUtils.getFullFormatDate(mRepairOrder.date()));

    Client client = mRepairOrder.client();
    mClientFullNameView.setText(client.fullName());
    mClientNIFView.setText(client.dni());
    mClientAddressView.setText(client.address());

    mDeviceSubjectView.setText(mRepairOrder.subject());
    mDeviceBrandView.setText(mRepairOrder.brand());
    mDeviceModelView.setText(mRepairOrder.model());
    mDeviceSerialView.setText(mRepairOrder.serial());

    Uri photoUri = Uri.parse(mRepairOrder.photoUri());
    mPhotoView.setImageURI(photoUri);
  }

  @OnClick(R.id.edit_repair_order)
  public void onEditRepairOrder() {
    Navigation.goToEditRepairOrder(getContext(), this, REQUEST_EDIT_REPAIR_ORDER, mRepairOrder);
  }

  @OnClick(R.id.receipt_device_photo)
  public void onOpenPhoto() {
    Navigation.goToOpenPhoto(getContext(), mRepairOrder.photoUri());
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_EDIT_REPAIR_ORDER){
      mRepairOrder = data.getParcelableExtra(EditRepairOrderActivity.RESULT_REPAIR_ORDER);
      updateUI();
    }
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.repair_order_detail, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.menu_delete_repair_order:
        new AlertDialog.Builder(getContext())
            .setMessage(getString(R.string.delete_repair_order_warning))
            .setPositiveButton(android.R.string.ok, (dialog, which) -> {
              mRepairOrderDetailPresenter.deleteRepairOrder(mRepairOrder);
            })
            .setNegativeButton(android.R.string.cancel, null)
            .show();
        return true;
      case R.id.menu_create_receipt:
        Navigation.goToAddReceipt(getContext(), mRepairOrder);
        getActivity().finish();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }


  @Override
  public void onDeleteRepairOrderComplete() {
    getActivity().finish();
  }

  @Override
  public void onDeleteRepairOrderError(Throwable error) {
    // TODO: Show error
  }
}

