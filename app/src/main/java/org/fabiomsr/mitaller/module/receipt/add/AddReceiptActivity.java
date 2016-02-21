package org.fabiomsr.mitaller.module.receipt.add;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.navigation.Navigation;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddReceiptActivity extends BaseActivity {

  public static final String PARAM_REPAIR_ORDER = "org.fabiomsr.mitaller.module.receipt.add.REPAIR_ORDER";

  @Bind(R.id.toolbar) Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_receipt);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);
    showUpButton();
    mToolbar.setTitle(getTitle());

    RepairOrder repairOrder = getIntent().getParcelableExtra(PARAM_REPAIR_ORDER);

    if(savedInstanceState == null) {
      replaceFragment(R.id.add_receipt_container, AddReceiptDetailsFragment.newInstance(repairOrder));
    }
  }

  @Override
  protected void onNavigateUpTo() {
    Navigation.upToHome(this);
  }

}
