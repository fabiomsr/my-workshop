package org.fabiomsr.mitaller.module.repairorder.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.receipt.list.ReceiptListActivity;
import org.fabiomsr.mitaller.navigation.Navigation;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An activity representing a single Receipt detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ReceiptListActivity}.
 */
public class RepairOrderDetailActivity extends BaseActivity {

  public static final String PARAM_REPAIR_ORDER = "org.fabiomsr.mitaller.param.repair_order";

  @Nullable @Bind(R.id.detail_toolbar) Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repair_order_detail);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);
    showUpButton();

    RepairOrder repairOrder = getIntent().getParcelableExtra(PARAM_REPAIR_ORDER);

    replaceFragment(R.id.receipt_detail_container, RepairOrderDetailFragment.newInstance(repairOrder));
  }


  @Override
  protected void onNavigateUpTo() {
    Navigation.upToHome(this);
  }

}
