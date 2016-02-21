package org.fabiomsr.mitaller.module.repairorder.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import org.fabiomsr.mitaller.app.Application;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.app.injection.module.ActivityModule;
import org.fabiomsr.mitaller.domain.Client;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.repairorder.add.AddRepairOrderFragment;
import org.fabiomsr.mitaller.module.repairorder.edit.contract.EditRepairOrderViewContract;

import java.util.Calendar;

import javax.inject.Inject;

public class EditRepairOrderFragment extends AddRepairOrderFragment implements EditRepairOrderViewContract {

  private static final String ARGUMENT_REPAIR_ORDER = "repair_order";


  @Inject EditRepairOrderPresenter mEditRepairOrderPresenter;

  /**
   * Edit Receipt Component
   */
  private EditRepairOrderComponent mEditRepairOrderComponent;

  private RepairOrder mRepairOrder;

  private RepairOrder mRepairOrderEdited;

  /**
   * Initialize dependency component
   * @return Initialized Component
   */
  @CheckResult(suggest = "#inject(AddReceiptDetailsFragment)")
  EditRepairOrderComponent component() {
    if (mEditRepairOrderComponent == null) {
      Application application = (Application) getActivity().getApplication();

      mEditRepairOrderComponent = DaggerEditRepairOrderComponent.builder()
          .applicationComponent(application.component())
          .activityModule(new ActivityModule((BaseActivity) getActivity()))
          .build();
    }

    return mEditRepairOrderComponent;
  }


  public static EditRepairOrderFragment newInstance(RepairOrder repairOrder) {
    EditRepairOrderFragment fragment = new EditRepairOrderFragment();

    Bundle args = new Bundle();
    args.putParcelable(ARGUMENT_REPAIR_ORDER, repairOrder);

    fragment.setArguments(args);

    return fragment;
  }


  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    component().inject(this);
    attachPresenter(mEditRepairOrderPresenter);
    mEditRepairOrderPresenter.attachViewContract(this);
  }

  @Override
  protected void readArguments(@NonNull Bundle args) {
    mRepairOrder = args.getParcelable(ARGUMENT_REPAIR_ORDER);
  }

  @Override
  protected void updateUI() {
    super.updateUI();

    mRepairOrderNumberView.setText(String.valueOf(mRepairOrder.number()));
    updateDate();

    Client client = mRepairOrder.client();
    mClientNameView.setText(client.name());
    mClientFirstLastNameView.setText(client.firstLastName());
    mClientSecondLastNameView.setText(client.secondLastName());
    mClientNIFView.setText(client.dni());
    mClientAddressView.setText(client.address());

    mDeviceSubjectView.setText(mRepairOrder.subject());
    mDeviceBrandView.setText(mRepairOrder.brand());
    mDeviceModelView.setText(mRepairOrder.model());
    mDeviceSerialView.setText(mRepairOrder.serial());
  }

  private void updateDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(mRepairOrder.date());

    int year  = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day   = calendar.get(Calendar.DAY_OF_MONTH);
    mRepairOderDateView.updateDate(year, month, day);
  }

  @Override
  public void saveRepairOrder() {
    mRepairOrderEdited = createRepairOrder();
    mEditRepairOrderPresenter.updateRepairOrder(mRepairOrderEdited);
  }

  @Override
  public void onUpdateRepairOrderComplete() {
    Activity activity = getActivity();
    Intent intent = new Intent();
    intent.putExtra(EditRepairOrderActivity.RESULT_REPAIR_ORDER, mRepairOrderEdited);
    activity.setResult(Activity.RESULT_OK, intent);
    activity.finish();
  }

  @Override
  public void onUpdateRepairOrderError(Throwable error) {
    // TODO: Show error
  }
}
