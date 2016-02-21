package org.fabiomsr.mitaller.module.repairorder.add;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.animation.CardPagerController;
import org.fabiomsr.mitaller.app.Application;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.app.base.BaseFragment;
import org.fabiomsr.mitaller.app.injection.module.ActivityModule;
import org.fabiomsr.mitaller.domain.Client;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.repairorder.add.contract.AddRepairOrderViewContract;
import org.fabiomsr.mitaller.utils.CalendarUtils;
import org.fabiomsr.mitaller.utils.StringUtils;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddRepairOrderFragment extends BaseFragment implements AddRepairOrderViewContract{

  @Bind(R.id.add_repair_order_container) View mContainerView;

  @Bind(R.id.receipt_number) protected    TextView   mRepairOrderNumberView;
  @Bind(R.id.edit_receipt_date) protected DatePicker mRepairOderDateView;

  @Bind(R.id.edit_receipt_client_name) protected             EditText mClientNameView;
  @Bind(R.id.edit_receipt_client_first_last_name) protected  EditText mClientFirstLastNameView;
  @Bind(R.id.edit_receipt_client_second_last_name) protected EditText mClientSecondLastNameView;
  @Bind(R.id.edit_receipt_client_nif) protected              EditText mClientNIFView;
  @Bind(R.id.edit_receipt_client_address) protected          EditText mClientAddressView;

  @Bind(R.id.edit_receipt_device_subject) protected EditText mDeviceSubjectView;
  @Bind(R.id.edit_receipt_device_brand) protected   EditText mDeviceBrandView;
  @Bind(R.id.edit_receipt_device_model) protected   EditText mDeviceModelView;
  @Bind(R.id.edit_receipt_device_serial) protected  EditText mDeviceSerialView;

  @Inject AddRepairOrderPresenter mAddRepairOrderPresenter;

  CardPagerController mCardPagerController;

  /**
   * Add Receipt Component
   */
  private AddRepairOrderComponent mAddRepairOrderComponent;

  /**
   * Initialize dependency component
   * @return Initialized Component
   */
  @CheckResult(suggest = "#inject(AddReceiptDetailsFragment)")
  AddRepairOrderComponent component() {
    if (mAddRepairOrderComponent == null) {
      Application application = (Application) getActivity().getApplication();

      mAddRepairOrderComponent = DaggerAddRepairOrderComponent.builder()
          .applicationComponent(application.component())
          .activityModule(new ActivityModule((BaseActivity) getActivity()))
          .build();
    }

    return mAddRepairOrderComponent;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_add_repair_order, container, false);
    ButterKnife.bind(this, rootView);
    mCardPagerController = new CardPagerController(rootView);
    return rootView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    component().inject(this);
    attachPresenter(mAddRepairOrderPresenter);
    mAddRepairOrderPresenter.attachViewContract(this);

    super.onActivityCreated(savedInstanceState);

    setHasOptionsMenu(true);
  }

  @Override
  protected void updateUI() {
    mRepairOrderNumberView.setText("--");
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.add_receipt, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.save_receipt:
        saveRepairOrder();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void saveRepairOrder(){
    mAddRepairOrderPresenter.loadNextReceiptNumber();
  }

  protected RepairOrder createRepairOrder(){
    int number = Integer.parseInt(mRepairOrderNumberView.getText().toString());

    Date date = CalendarUtils.createDate(mRepairOderDateView);

    String name = mClientNameView.getText().toString();
    String firstLastName = mClientFirstLastNameView.getText().toString();
    String secondLastName = mClientSecondLastNameView.getText().toString();
    String fullName = name + " " + firstLastName + " " + secondLastName;
    String nif = mClientNIFView.getText().toString();
    String address = mClientAddressView.getText().toString();

    String subject = mDeviceSubjectView.getText().toString();
    String brand = mDeviceBrandView.getText().toString();
    String model = mDeviceModelView.getText().toString();
    String serial = mDeviceSerialView.getText().toString();
    String photoUri = "";

    Client client = Client.create(name, firstLastName, secondLastName,
                                  fullName, StringUtils.normalizeText(fullName), nif, address);

    return RepairOrder.create(number, client, date, subject,
                              brand, model, serial, photoUri, false);

  }

  @Override
  public void onLoadNextRepairOrderNumberComplete(int number) {
    mRepairOrderNumberView.setText(String.valueOf(number));
    RepairOrder repairOrder = createRepairOrder();
    mAddRepairOrderPresenter.saveRepairOrder(repairOrder);
  }

  @Override
  public void onSaveRepairOrderComplete() {
    getActivity().finish();
  }

  @Override
  public void onSaveRepairOrderError(Throwable error) {
    // TODO: Show error
  }
}
