package org.fabiomsr.mitaller.app.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

  private Presenter mPresenter;

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    Bundle args = getArguments();
    if(args != null){
      readArguments(args);
    }

    updateUI();
  }

  protected void attachPresenter(Presenter presenter){
    mPresenter = presenter;
  }

  @Override
  public void onResume() {
    if(mPresenter != null) {
      mPresenter.resume();
    }
    super.onResume();
  }

  @Override
  public void onPause() {
    if(mPresenter != null) {
      mPresenter.pause();
    }
    super.onPause();
  }


  @Override
  public void onDestroy() {
    if(mPresenter != null) {
      mPresenter.destroy();
    }
    super.onDestroy();
  }

  protected void readArguments(@NonNull Bundle args){}
  protected void updateUI(){}
}
