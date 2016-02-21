package org.fabiomsr.mitaller.module.receipt.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.Application;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.app.injection.module.ActivityModule;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.detail.contract.ReceiptDetailViewContract;
import org.fabiomsr.mitaller.module.receipt.edit.EditReceiptActivity;
import org.fabiomsr.mitaller.module.receipt.list.ReceiptListActivity;
import org.fabiomsr.mitaller.navigation.Navigation;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An activity representing a single Receipt detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ReceiptListActivity}.
 */
public class ReceiptDetailActivity extends BaseActivity implements ReceiptDetailViewContract {

  public static final String PARAM_RECEIPT = "org.fabiomsr.mitaller.param.receipt";
  private static final int REQUEST_EDIT_RECEIPT = 124;

  @Nullable @Bind(R.id.detail_toolbar) Toolbar mToolbar;

  @Inject ReceiptDetailPresenter mReceiptDetailPresenter;

  /**
   * Edit Repair Order Detail Component
   */
  private ReceiptDetailComponent mReceiptDetailComponent;

  private Receipt mReceipt;

  /**
   * Initialize dependency component
   * @return Initialized Component
   */
  @CheckResult(suggest = "#inject(ReceiptDetailActivity)")
  ReceiptDetailComponent component() {
    if (mReceiptDetailComponent == null) {
      Application application = (Application) getApplication();

      mReceiptDetailComponent = DaggerReceiptDetailComponent.builder()
          .applicationComponent(application.component())
          .activityModule(new ActivityModule(this))
          .build();
    }

    return mReceiptDetailComponent;
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    component().inject(this);
    attachPresenter(mReceiptDetailPresenter);
    mReceiptDetailPresenter.attachViewContract(this);
    setContentView(R.layout.activity_receipt_detail);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);
    showUpButton();

    mReceipt = getIntent().getParcelableExtra(PARAM_RECEIPT);

    replaceFragment(R.id.receipt_detail_container,
        ReceiptDetailFragmentPager.newInstance(mReceipt));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.receipt_detail, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.menu_delete_receipt:
        new AlertDialog.Builder(this)
            .setMessage(getString(R.string.delete_receipt_warning))
            .setPositiveButton(android.R.string.ok, (dialog, which) -> {
              mReceiptDetailPresenter.deleteReceipt(mReceipt);
            })
            .setNegativeButton(android.R.string.cancel, null)
            .show();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onNavigateUpTo() {
    Navigation.upToHome(this);
  }

  @OnClick(R.id.edit_receipt)
  public void onEditReceipt(){
    Navigation.goToEditReceipt(this, REQUEST_EDIT_RECEIPT, mReceipt);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(resultCode == RESULT_OK && requestCode == REQUEST_EDIT_RECEIPT){
      mReceipt = data.getParcelableExtra(EditReceiptActivity.RESULT_RECEIPT);
      replaceFragment(R.id.receipt_detail_container,
          ReceiptDetailFragmentPager.newInstance(mReceipt));
    }
  }

  @Override
  public void onDeleteReceiptComplete() {
    finish();
  }

  @Override
  public void onDeleteReceiptError(Throwable error) {
    // TODO: Show error
  }
}
