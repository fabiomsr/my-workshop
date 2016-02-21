package org.fabiomsr.mitaller.module.receipt.detail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.detail.ReceiptConceptsFragment;
import org.fabiomsr.mitaller.module.receipt.detail.ReceiptDetailFragment;

public class ReceiptDetailPageAdapter extends FragmentPagerAdapter {

  private static final int DETAILS = 0;
  private static final int CONCEPTS = 1;

  private static final int COUNT = 2;

  private Receipt mReceipt;

  public ReceiptDetailPageAdapter(FragmentManager fragmentManager, Receipt receipt){
    super(fragmentManager);
    mReceipt = receipt;
  }

  @Override
  public Fragment getItem(int position) {
    switch (position){
      case DETAILS:
        return ReceiptDetailFragment.newInstance(mReceipt);
      default: // CONCEPTS
        return ReceiptConceptsFragment.newInstance(mReceipt);
    }
  }

  @Override
  public int getCount() {
    return COUNT;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position){
      case DETAILS:
        return "DETALLES";
      default: // CONCEPTS
        return "CONCEPTOS";
    }
  }
}
