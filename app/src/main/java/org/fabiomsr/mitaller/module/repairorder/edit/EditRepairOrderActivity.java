package org.fabiomsr.mitaller.module.repairorder.edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.domain.RepairOrder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditRepairOrderActivity extends BaseActivity {

  public static final String PARAM_REPAIR_ORDER = "org.fabiomsr.mitaller.module.repairorder.edit.RECEIPT";

  public static final String RESULT_REPAIR_ORDER = "org.fabiomsr.mitaller.module.repairorder.edit.RESULT";
  @Bind(R.id.toolbar) Toolbar mToolbar;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_repair_order);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);
    mToolbar.setTitle(getTitle());

    Intent  intent = getIntent();
    RepairOrder repairOrder = intent.getParcelableExtra(PARAM_REPAIR_ORDER);

    if(savedInstanceState == null) {
      replaceFragment(R.id.edit_repair_order_pager_container,
                      EditRepairOrderFragment.newInstance(repairOrder));
    }
  }
}
