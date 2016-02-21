package org.fabiomsr.mitaller.module.repairorder.list;

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
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.RepairOrderAdapter;
import org.fabiomsr.mitaller.module.repairorder.list.contract.RepairOrderListViewContract;
import org.fabiomsr.mitaller.navigation.Navigation;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepairOrderListFragment extends BaseFragment implements OnRecycleViewItemClickListener<RepairOrder>, RepairOrderListViewContract {

  public interface OnRepairOrderSelectListener{
    void onRepairOrderSelect(@NonNull Receipt receipt);
  }

  @Bind(R.id.repair_order_list) RecyclerView mRepairOrderList;

  /**
   * Repair Orders Adapter
   */
  @Inject RepairOrderAdapter mRepairOrderAdapter;

  /**
   * Presenter
   */
  @Inject RepairOrderListPresenter mRepairOrderListPresenter;

  /**
   * Repair Order List Component
   */
  private RepairOrderListComponent mRepairOrderListComponent;

  /**
   * Initialize dependency component
   * @return Initialized Component
   */
  @CheckResult(suggest = "#inject(RepairOrderListComponent)")
  RepairOrderListComponent component(){
    if(mRepairOrderListComponent == null){
      Application application = (Application) getActivity().getApplication();

      mRepairOrderListComponent = DaggerRepairOrderListComponent.builder()
          .applicationComponent(application.component())
          .activityModule(new ActivityModule((BaseActivity) getActivity()))
          .build();
    }

    return mRepairOrderListComponent;
  }


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_repair_order_list, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    component().inject(this);
    attachPresenter(mRepairOrderListPresenter);
    mRepairOrderListPresenter.attachViewContract(this);
    setupReceiptList();
  }

  /**
   * Setup receipt list
   */
  private void setupReceiptList() {
    mRepairOrderAdapter.setOnItemClickListener(this);
    mRepairOrderList.setLayoutManager(new LayoutManager(getContext()));
    mRepairOrderList.setAdapter(mRepairOrderAdapter);
  }

  @OnClick(R.id.add_repair_order_fab)
  public void onAddRepairOrder(){
    Navigation.goToAddRepairOrder(getContext());
  }

  @Override
  public void onRecycleViewItemClick(@NonNull View view, @NonNull RepairOrder repairOrder, int position) {
    Navigation.goToRepairOrderDetail(getContext(), repairOrder);
  }


  @Override
  public void onLoadRepairOrdersComplete(List<RepairOrder> repairOrders) {
    mRepairOrderAdapter.setRepairOrders(repairOrders);
  }

  @Override
  public void onLoadRepairOrdersError(Throwable error) {

  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
