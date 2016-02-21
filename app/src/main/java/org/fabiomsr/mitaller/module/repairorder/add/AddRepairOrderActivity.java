package org.fabiomsr.mitaller.module.repairorder.add;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.navigation.Navigation;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddRepairOrderActivity extends BaseActivity{

  @Bind(R.id.toolbar) Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_repair_order);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);
    showUpButton();
    mToolbar.setTitle(getTitle());

    if(savedInstanceState == null) {
      replaceFragment(R.id.add_repair_order_container, new AddRepairOrderFragment());
    }
  }

  @Override
  protected void onNavigateUpTo() {
    Navigation.upToHome(this);
  }

}
