package org.fabiomsr.mitaller.module.repairorder.list.adapter;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LinearSLM;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.adapter.OnRecycleViewItemClickListener;
import org.fabiomsr.mitaller.app.injection.annotation.PerActivity;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.holder.RepairOrderContentViewHolder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.holder.RepairOrderStickyHeaderViewHolder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.holder.RepairOrderSummaryViewHolder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.holder.base.BaseReceiptViewHolder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.ContentRepairOrderAdapterItem;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.StickyHeaderRepairOrderAdapterItem;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.SummaryRepairOrderAdapterItem;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.base.RepairOrderAdapterItem;
import org.fabiomsr.mitaller.utils.CalendarUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class RepairOrderAdapter extends RecyclerView.Adapter<BaseReceiptViewHolder> {

  public static final int CONTENT = 0;
  public static final int STICKY_HEADER = 1;
  public static final int SUMMARY = 2;

  @IntDef({CONTENT, STICKY_HEADER, SUMMARY})
  @Retention(RetentionPolicy.SOURCE)
  public @interface ReceiptViewType {}


  /**
   * Receipts
   */
  private List<RepairOrder> mRepairOrders;

  /**
   * Receipts Adapter Items
   */
  private ArrayList<RepairOrderAdapterItem> mRepairOrderAdapterItem;

  /**
   * On Item click listener
   */
  private OnRecycleViewItemClickListener<RepairOrder> mOnItemClickListener;

  /**
   * Create a new Receipts Adapter
   */
  @Inject
  public RepairOrderAdapter() {
    mRepairOrderAdapterItem = new ArrayList<>();
  }

  /**
   * Set receipts
   * @param items Receipts
   */
  public void setRepairOrders(List<RepairOrder> items) {
    mRepairOrders = items;
    mRepairOrderAdapterItem.clear();

    Observable.from(items)
        .groupBy(repairOrder -> CalendarUtils.getMonthName(repairOrder.date()))
        .forEach(group -> {
          group.toList()
              .forEach(repairOrders -> {
                SummaryRepairOrderAdapterItem summaryItem = new SummaryRepairOrderAdapterItem(mRepairOrderAdapterItem.size(), repairOrders, group.getKey());
                mRepairOrderAdapterItem.add(summaryItem);
                groupReceiptByDay(repairOrders);
              });
        });

    notifyDataSetChanged();
  }

  private void groupReceiptByDay(List<RepairOrder> repairOrders) {
    Calendar calendar = Calendar.getInstance();
    Observable.from(repairOrders)
        .groupBy(repairOrder -> CalendarUtils.getDay(calendar, repairOrder.date()))
        .forEach(group -> {
          group.toList().subscribe(repairOrderList -> {
            Date date = repairOrderList.get(0).date();
            StickyHeaderRepairOrderAdapterItem stickyItem = new StickyHeaderRepairOrderAdapterItem(mRepairOrderAdapterItem.size(), date);
            mRepairOrderAdapterItem.add(stickyItem);

            Observable.from(repairOrderList)
                .map(repairOrder -> new ContentRepairOrderAdapterItem(stickyItem.getFirstPosition(), repairOrder))
                .forEach(mRepairOrderAdapterItem::add);
          });
        });
  }

  /**
   * Set on Item click listener
   * @param listener Listener
   */
  public void setOnItemClickListener(OnRecycleViewItemClickListener<RepairOrder> listener) {
    mOnItemClickListener = listener;
  }

  @Override
  public int getItemViewType(int position) {
    return mRepairOrderAdapterItem.get(position).getType();
  }

  @Override
  public BaseReceiptViewHolder onCreateViewHolder(ViewGroup parent, @ReceiptViewType int viewType) {
    View view;
    LayoutInflater layoutInflate = LayoutInflater.from(parent.getContext());
    switch (viewType){
      case STICKY_HEADER:
        view = layoutInflate.inflate(R.layout.adapter_repair_order_list_sticky_header, parent, false);
        return new RepairOrderStickyHeaderViewHolder(view);
      case CONTENT:
        view = layoutInflate.inflate(R.layout.adapter_repair_order_list_content, parent, false);
        return new RepairOrderContentViewHolder(view, mOnItemClickListener);
      default: //case SUMMARY:
        view = layoutInflate.inflate(R.layout.adapter_repair_order_list_summary, parent, false);
        return new RepairOrderSummaryViewHolder(view);
    }
  }

  @Override
  public void onBindViewHolder(BaseReceiptViewHolder holder, int position) {
    View view = holder.itemView;

    RepairOrderAdapterItem item = mRepairOrderAdapterItem.get(position);
    holder.update(item);

    GridSLM.LayoutParams layoutParams = GridSLM.LayoutParams.from(view.getLayoutParams());

    if(item.getType() == STICKY_HEADER){
      layoutParams.headerDisplay = 18 | LayoutManager.LayoutParams.HEADER_OVERLAY;
      layoutParams.headerEndMarginIsAuto = false;
      layoutParams.headerStartMarginIsAuto = false;
    }

    layoutParams.setSlm(LinearSLM.ID);
    layoutParams.setFirstPosition(item.getFirstPosition());
    view.setLayoutParams(layoutParams);
  }

  @Override
  public int getItemCount() {
    return mRepairOrderAdapterItem.size();
  }

}
