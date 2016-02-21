package org.fabiomsr.mitaller.module.receipt.add;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.domain.ReceiptConcept;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.concept.add.AddReceiptConceptActivity;
import org.fabiomsr.mitaller.module.receipt.add.adapter.ReceiptConceptsAdapter;
import org.fabiomsr.mitaller.module.receipt.add.contract.AddReceiptViewContract;
import org.fabiomsr.mitaller.navigation.Navigation;
import org.fabiomsr.mitaller.utils.CalendarUtils;
import org.fabiomsr.mitaller.utils.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddReceiptDetailsFragment extends BaseFragment implements AddReceiptViewContract {

  private static final String ARG_REPAIR_ORDER = "repair_order";
  private static final int REQUEST_NEW_CONCEPT = 123;

  @Bind(R.id.add_receipt_container) protected View mContainerView;


  @Bind(R.id.receipt_number) protected    TextView   mReceiptNumberView;
  @Bind(R.id.edit_receipt_date) protected DatePicker mReceiptDateView;

  @Bind(R.id.edit_receipt_client_name) protected             EditText mClientNameView;
  @Bind(R.id.edit_receipt_client_first_last_name) protected  EditText mClientFirstLastNameView;
  @Bind(R.id.edit_receipt_client_second_last_name) protected EditText mClientSecondLastNameView;
  @Bind(R.id.edit_receipt_client_nif) protected              EditText mClientNIFView;
  @Bind(R.id.edit_receipt_client_address) protected          EditText mClientAddressView;

  @Bind(R.id.edit_receipt_device_subject) protected EditText mDeviceSubjectView;
  @Bind(R.id.edit_receipt_device_brand) protected   EditText mDeviceBrandView;
  @Bind(R.id.edit_receipt_device_model) protected   EditText mDeviceModelView;
  @Bind(R.id.edit_receipt_device_serial) protected  EditText mDeviceSerialView;

  @Bind(R.id.receipt_concepts_list) protected RecyclerView mConceptsView;

  @Bind(R.id.receipt_sub_total) protected TextView mSubTotalView;
  @Bind(R.id.receipt_igic) protected      TextView mIgicView;
  @Bind(R.id.receipt_total) protected     TextView mTotalView;

  @Inject AddReceiptPresenter mAddReceiptPresenter;

  /**
   * Add Receipt Component
   */
  private AddReceiptComponent mAddReceiptComponent;

  private CardPagerController mCardPagerController;

  protected ReceiptConceptsAdapter mReceiptConceptsAdapter;

  private RepairOrder mRepairOrder;


  public static AddReceiptDetailsFragment newInstance(RepairOrder repairOrder){
    AddReceiptDetailsFragment fragment = new AddReceiptDetailsFragment();
    Bundle args = new Bundle();
    args.putParcelable(ARG_REPAIR_ORDER, repairOrder);
    fragment.setArguments(args);
    return fragment;
  }

  /**
   * Initialize dependency component
   * @return Initialized Component
   */
  @CheckResult(suggest = "#inject(AddReceiptDetailsFragment)")
  AddReceiptComponent component() {
    if (mAddReceiptComponent == null) {
      Application application = (Application) getActivity().getApplication();

      mAddReceiptComponent = DaggerAddReceiptComponent.builder()
          .applicationComponent(application.component())
          .activityModule(new ActivityModule((BaseActivity) getActivity()))
          .build();
    }

    return mAddReceiptComponent;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_add_receipt, container, false);
    ButterKnife.bind(this, rootView);
    mCardPagerController = new CardPagerController(rootView);
    return rootView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    component().inject(this);
    attachPresenter(mAddReceiptPresenter);
    mAddReceiptPresenter.attachViewContract(this);

    setUpConceptList();
    super.onActivityCreated(savedInstanceState);

    setHasOptionsMenu(true);
    calculateAmounts();
  }

  @Override
  protected void readArguments(@NonNull Bundle args) {
    mRepairOrder = (RepairOrder) args.get(ARG_REPAIR_ORDER);
  }

  private void setUpConceptList() {
    mReceiptConceptsAdapter = new ReceiptConceptsAdapter();
    mConceptsView.setLayoutManager(new LinearLayoutManager(getContext()));
    mConceptsView.setAdapter(mReceiptConceptsAdapter);

    mReceiptConceptsAdapter.onRemoveConcept()
        .forEach(receiptConcept -> calculateAmounts());
  }

  @Override
  protected void updateUI() {
    mReceiptNumberView.setText("--");

    if(mRepairOrder != null){
      fillUI();
    }
  }

  private void fillUI() {
    updateDate(mRepairOrder.date());

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

  protected void updateDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    int year  = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day   = calendar.get(Calendar.DAY_OF_MONTH);
    mReceiptDateView.updateDate(year, month, day);
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
        saveReceipt();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @OnClick(R.id.add_concept)
  public void onAddConcept() {
    Navigation.goToAddReceiptConcept(this, REQUEST_NEW_CONCEPT);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(resultCode == Activity.RESULT_OK){
      ReceiptConcept concept = data.getParcelableExtra(AddReceiptConceptActivity.RESULT_CONCEPT);
      mReceiptConceptsAdapter.addConcept(concept);
      calculateAmounts();
    }

  }

  protected void calculateAmounts(){

    ArrayList<ReceiptConcept> concepts = mReceiptConceptsAdapter.getConcepts();

    float subTotal = 0;
    for (ReceiptConcept concept : concepts) {
      subTotal += concept.totalAmount();
    }

    float igic = subTotal * 0.07f;
    float total = subTotal + igic;

    mSubTotalView.setText(getString(R.string.receipt_total_format, subTotal));
    mIgicView.setText(getString(R.string.receipt_total_format, igic));
    mTotalView.setText(getString(R.string.receipt_total_format, total));
  }

  public void saveReceipt(){
    mAddReceiptPresenter.loadNextReceiptNumber();
  }

  protected Receipt createReceipt(){
    int number = Integer.parseInt(mReceiptNumberView.getText().toString());

    Date date = CalendarUtils.createDate(mReceiptDateView);

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

    ArrayList<ReceiptConcept> conceptsWithoutId = mReceiptConceptsAdapter.getConcepts();
    ArrayList<ReceiptConcept>  concepts = new ArrayList<>(conceptsWithoutId.size());

    float subTotal = 0;
    for (int i = 0; i < conceptsWithoutId.size(); i++) {
      ReceiptConcept concept = conceptsWithoutId.get(i);
      subTotal += concept.totalAmount();
      concepts.add(ReceiptConcept.create(number + "_" + i, concept));
    }

    float igic = subTotal * 0.07f;
    float total = subTotal + igic;

    Client client = Client.create(name, firstLastName, secondLastName,
        fullName, StringUtils.normalizeText(fullName), nif, address);

    return Receipt.create(number, client, date, subject,
        brand, model, serial, photoUri, concepts, igic, subTotal, total, false);

  }

  @Override
  public void onLoadNextReceiptNumberComplete(int number) {
    mReceiptNumberView.setText(String.valueOf(number));
    Receipt receipt = createReceipt();
    mAddReceiptPresenter.saveReceipt(receipt);
  }

  @Override
  public void onSaveReceiptComplete() {
    getActivity().finish();
  }

  @Override
  public void onSaveReceiptError(Throwable error) {
// TODO: Show error
  }
}
