package org.fabiomsr.mitaller.module.receipt.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseFragment;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.detail.adapter.ReceiptDetailPageAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiptDetailFragmentPager extends BaseFragment {

  @Bind(R.id.detail_tab_layout)  TabLayout mTabLayout;
  @Bind(R.id.receipt_view_pager) ViewPager mViewPager;

  /**
   * The fragment argument representing the item that this fragment
   * represents.
   */
  public static final String ARG_RECEIPT = "receipt";

  /**
   * Receipt content this fragment is presenting.
   */
  private Receipt mReceipt;


  public static ReceiptDetailFragmentPager newInstance(Receipt receipt){
    ReceiptDetailFragmentPager fragment = new ReceiptDetailFragmentPager();
    Bundle args = new Bundle();
    args.putParcelable(ARG_RECEIPT, receipt);
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_raceipt_detail_pager, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    ReceiptDetailPageAdapter pageAdapter = new ReceiptDetailPageAdapter(getChildFragmentManager(), mReceipt);
    mViewPager.setAdapter(pageAdapter);
    mTabLayout.setupWithViewPager(mViewPager);
  }

  @Override
  protected void readArguments(@NonNull Bundle args) {
    mReceipt = getArguments().getParcelable(ARG_RECEIPT);
  }
}
