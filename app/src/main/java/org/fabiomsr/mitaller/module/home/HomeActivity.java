package org.fabiomsr.mitaller.module.home;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.module.receipt.list.ReceiptListFragment;
import org.fabiomsr.mitaller.module.repairorder.list.RepairOrderListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private static final int REQUEST_WRITE_PERMISSION = 21;

  @Bind(R.id.home_content) View mView;
  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.drawer_layout) DrawerLayout mDrawer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ButterKnife.bind(this);

    mToolbar.setTitle(R.string.title_repair_orders);
    setSupportActionBar(mToolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    mDrawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    replaceFragment(R.id.home_content, new RepairOrderListFragment());

    String[] permission = { Manifest.permission.WRITE_EXTERNAL_STORAGE};
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      requestPermissions(permission, REQUEST_WRITE_PERMISSION);
    }
  }


  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch (id) {
      case R.id.drawer_repair_order:
        replaceFragment(R.id.home_content, new RepairOrderListFragment());
        mToolbar.setTitle(R.string.title_repair_orders);
        break;
      case R.id.drawer_receipt:
        replaceFragment(R.id.home_content, new ReceiptListFragment());
        mToolbar.setTitle(R.string.title_receipts);
        break;
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
