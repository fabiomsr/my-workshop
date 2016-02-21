package org.fabiomsr.mitaller.app.base;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

  private Presenter mPresenter;

  protected void attachPresenter(Presenter presenter){
    mPresenter = presenter;
  }

  protected void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment){
    getSupportFragmentManager().beginTransaction()
        .replace(containerId, fragment)
        .commit();
  }

  public void showUpButton() {
    // Show the Up button in the action bar.
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      onNavigateUpTo();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  protected void onNavigateUpTo(){}

  @Override
  protected void onResume() {
    if(mPresenter != null) {
      mPresenter.resume();
    }
    super.onResume();
  }

  @Override
  protected void onPause() {
    if(mPresenter != null) {
      mPresenter.pause();
    }
    super.onPause();
  }


  @Override
  protected void onDestroy() {
    if(mPresenter != null) {
      mPresenter.destroy();
    }
    super.onDestroy();
  }

}
