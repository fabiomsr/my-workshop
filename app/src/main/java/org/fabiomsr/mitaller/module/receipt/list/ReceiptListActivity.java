package org.fabiomsr.mitaller.module.receipt.list;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.Application;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.app.injection.module.ActivityModule;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.detail.ReceiptDetailActivity;
import org.fabiomsr.mitaller.navigation.Navigation;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Receipts. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ReceiptDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ReceiptListActivity extends BaseActivity implements ReceiptListFragment.OnReceiptSelectListener{

  @Bind(R.id.toolbar) Toolbar mToolbar;

  /**
   * Dependency Injection Component
   */
  private ReceiptListComponent component;

  /**
   * Create a new component
   *
   * @return Component
   */
  @CheckResult(suggest = "#inject(ReceiptListComponent)")
  ReceiptListComponent component() {
    if (component == null) {
      component = DaggerReceiptListComponent.builder()
          .applicationComponent(((Application) getApplication()).component())
          .activityModule(new ActivityModule(this))
          .build();
    }
    return component;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_receipt_list);
    component().inject(this);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);
    mToolbar.setTitle(getTitle());

    if(savedInstanceState == null) {
      replaceFragment(R.id.receipt_list_container, new ReceiptListFragment());
    }
  }


  @Override
  public void onReceiptSelect(@NonNull Receipt receipt) {
      Navigation.goToReceiptDetail(this, receipt);
  }


}

