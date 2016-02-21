package org.fabiomsr.mitaller.module.receipt.list;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tonicartos.superslim.LayoutManager;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.Application;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.app.base.BaseFragment;
import org.fabiomsr.mitaller.app.base.adapter.OnRecycleViewItemClickListener;
import org.fabiomsr.mitaller.app.injection.module.ActivityModule;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.list.adapter.ReceiptsAdapter;
import org.fabiomsr.mitaller.module.receipt.list.contract.ReceiptListViewContract;
import org.fabiomsr.mitaller.navigation.Navigation;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReceiptListFragment extends BaseFragment implements OnRecycleViewItemClickListener<Receipt>,ReceiptListViewContract {

  public interface OnReceiptSelectListener{
    void onReceiptSelect(@NonNull Receipt receipt);
  }


  @Bind(R.id.receipt_list) RecyclerView mReceiptList;

  /**
   * Receipts Adapter
   */
  @Inject ReceiptsAdapter mReceiptsAdapter;

  /**
   * Presenter
   */
  @Inject ReceiptListPresenter mReceiptListPresenter;

  /**
   * Receipt List Component
   */
  private ReceiptListComponent mReceiptListComponent;

  /**
   * Initialize dependency component
   * @return Initialized Component
   */
  @CheckResult(suggest = "#inject(ReceiptListComponent)")
  ReceiptListComponent component(){
    if(mReceiptListComponent == null){
      Application application = (Application) getActivity().getApplication();

      mReceiptListComponent = DaggerReceiptListComponent.builder()
          .applicationComponent(application.component())
          .activityModule(new ActivityModule((BaseActivity) getActivity()))
          .build();
    }

    return mReceiptListComponent;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_receipt_list, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    component().inject(this);
    attachPresenter(mReceiptListPresenter);
    mReceiptListPresenter.attachViewContract(this);
    setupReceiptList();
  }

  /**
   * Setup receipt list
   */
  private void setupReceiptList() {
    mReceiptsAdapter.setOnItemClickListener(this);
    mReceiptList.setLayoutManager(new LayoutManager(getContext()));
    mReceiptList.setAdapter(mReceiptsAdapter);
  }

  @OnClick(R.id.add_receipt_fab)
  public void onAddReceipt(){
    Navigation.goToAddReceipt(getContext());
  }

  @Override
  public void onRecycleViewItemClick(@NonNull View view, @NonNull Receipt receipt, int position) {
    Navigation.goToReceiptDetail(getContext(), receipt);
  }

  @Override
  public void onLoadReceiptsComplete(List<Receipt> receipts) {
    mReceiptsAdapter.setReceipts(receipts);
  }

  @Override
  public void onLoadReceiptsError(Throwable error) {
    // TODO Show error
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

}
