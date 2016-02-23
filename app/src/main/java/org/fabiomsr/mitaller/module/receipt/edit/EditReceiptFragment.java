package org.fabiomsr.mitaller.module.receipt.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import org.fabiomsr.mitaller.app.Application;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.app.injection.module.ActivityModule;
import org.fabiomsr.mitaller.domain.Client;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.add.AddReceiptFragment;
import org.fabiomsr.mitaller.module.receipt.edit.contract.EditReceiptViewContract;

import javax.inject.Inject;

public class EditReceiptFragment extends AddReceiptFragment implements EditReceiptViewContract {

  private static final String ARGUMENT_RECEIPT = "receipt";


  @Inject EditReceiptPresenter mEditReceiptPresenter;

  /**
   * Edit Receipt Component
   */
  private EditReceiptComponent mEditReceiptComponent;

  private Receipt mReceipt;

  private Receipt mReceiptEdited;

  /**
   * Initialize dependency component
   * @return Initialized Component
   */
  @CheckResult(suggest = "#inject(AddReceiptDetailsFragment)")
  EditReceiptComponent component() {
    if (mEditReceiptComponent == null) {
      Application application = (Application) getActivity().getApplication();

      mEditReceiptComponent = DaggerEditReceiptComponent.builder()
          .applicationComponent(application.component())
          .activityModule(new ActivityModule((BaseActivity) getActivity()))
          .build();
    }

    return mEditReceiptComponent;
  }


  public static EditReceiptFragment newInstance(Receipt receipt) {
    EditReceiptFragment fragment = new EditReceiptFragment();

    Bundle args = new Bundle();
    args.putParcelable(ARGUMENT_RECEIPT, receipt);

    fragment.setArguments(args);

    return fragment;
  }


  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    component().inject(this);
    attachPresenter(mEditReceiptPresenter);
    mEditReceiptPresenter.attachViewContract(this);
  }

  @Override
  protected void readArguments(@NonNull Bundle args) {
    super.readArguments(args);

    mReceipt = args.getParcelable(ARGUMENT_RECEIPT);
  }

  @Override
  protected void updateUI() {
    super.updateUI();

    mReceiptNumberView.setText(String.valueOf(mReceipt.number()));
    updateDate(mReceipt.date());

    Client client = mReceipt.client();
    mClientNameView.setText(client.name());
    mClientFirstLastNameView.setText(client.firstLastName());
    mClientSecondLastNameView.setText(client.secondLastName());
    mClientNIFView.setText(client.dni());
    mClientAddressView.setText(client.address());

    mDeviceSubjectView.setText(mReceipt.subject());
    mDeviceBrandView.setText(mReceipt.brand());
    mDeviceModelView.setText(mReceipt.model());
    mDeviceSerialView.setText(mReceipt.serial());

    mReceiptConceptsAdapter.addConcepts(mReceipt.receiptConcepts());

    calculateAmounts();
  }


  @Override
  public void saveReceipt() {
    mReceiptEdited = createReceipt();
    mEditReceiptPresenter.updateReceipt(mReceiptEdited);
  }

  @Override
  public void onUpdateReceiptComplete() {
    Activity activity = getActivity();
    Intent intent = new Intent();
    intent.putExtra(EditReceiptActivity.RESULT_RECEIPT, mReceiptEdited);
    activity.setResult(Activity.RESULT_OK, intent);
    activity.finish();
  }

  @Override
  public void onUpdateReceiptError(Throwable error) {

  }
}
