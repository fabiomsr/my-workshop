package org.fabiomsr.mitaller.module.receipt.list.adapter;

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
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.module.receipt.list.adapter.holder.ReceiptContentViewHolder;
import org.fabiomsr.mitaller.module.receipt.list.adapter.holder.ReceiptStickyHeaderViewHolder;
import org.fabiomsr.mitaller.module.receipt.list.adapter.holder.ReceiptSummaryViewHolder;
import org.fabiomsr.mitaller.module.receipt.list.adapter.holder.base.BaseReceiptViewHolder;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.ContentReceiptAdapterItem;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.StickyHeaderReceiptAdapterItem;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.SummaryReceiptAdapterItem;
import org.fabiomsr.mitaller.module.receipt.list.adapter.item.base.ReceiptAdapterItem;
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
public class ReceiptsAdapter extends RecyclerView.Adapter<BaseReceiptViewHolder> {

  public static final int CONTENT = 0;
  public static final int STICKY_HEADER = 1;
  public static final int SUMMARY = 2;

  @IntDef({CONTENT, STICKY_HEADER, SUMMARY})
  @Retention(RetentionPolicy.SOURCE)
  public @interface ReceiptViewType {}


  /**
   * Receipts
   */
  private List<Receipt> mReceipts;

  /**
   * Receipts Adapter Items
   */
  private ArrayList<ReceiptAdapterItem> mReceiptAdapterItem;

  /**
   * On Item click listener
   */
  private OnRecycleViewItemClickListener<Receipt> mOnItemClickListener;

  /**
   * Create a new Receipts Adapter
   */
  @Inject public ReceiptsAdapter() {
    mReceiptAdapterItem = new ArrayList<>();
  }

  /**
   * Set receipts
   * @param items Receipts
   */
  public void setReceipts(List<Receipt> items){
    mReceipts = items;
    mReceiptAdapterItem.clear();

    Observable.from(items)
        .groupBy(receipt -> CalendarUtils.getMonthName(receipt.date()))
        .forEach(group -> {
          group.toList()
              .forEach(receipts -> {
                SummaryReceiptAdapterItem summaryItem = new SummaryReceiptAdapterItem(mReceiptAdapterItem.size(), receipts, group.getKey());
                mReceiptAdapterItem.add(summaryItem);
                groupReceiptByDay(receipts);
              });
        });

    notifyDataSetChanged();
  }

  private void groupReceiptByDay(List<Receipt> receipts) {
    Calendar calendar = Calendar.getInstance();
    Observable.from(receipts)
        .groupBy(receipt -> CalendarUtils.getDay(calendar, receipt.date()))
        .forEach(group -> {
          group.toList().subscribe(receiptList -> {
            Date date = receiptList.get(0).date();
            StickyHeaderReceiptAdapterItem stickyItem = new StickyHeaderReceiptAdapterItem(mReceiptAdapterItem.size(), date);
            mReceiptAdapterItem.add(stickyItem);

            Observable.from(receiptList)
                .map(receipt -> new ContentReceiptAdapterItem(stickyItem.getFirstPosition(), receipt))
                .forEach(mReceiptAdapterItem::add);
          });
        });
  }

  /**
   * Set on Item click listener
   * @param listener Listener
   */
  public void setOnItemClickListener(OnRecycleViewItemClickListener<Receipt> listener){
    mOnItemClickListener = listener;
  }

  @Override
  public int getItemViewType(int position) {
    return mReceiptAdapterItem.get(position).getType();
  }

  @Override
  public BaseReceiptViewHolder onCreateViewHolder(ViewGroup parent, @ReceiptViewType int viewType) {
    View view;
    LayoutInflater layoutInflate = LayoutInflater.from(parent.getContext());
    switch (viewType){
      case STICKY_HEADER:
        view = layoutInflate.inflate(R.layout.adapter_receipt_list_sticky_header, parent, false);
        return new ReceiptStickyHeaderViewHolder(view);
      case CONTENT:
        view = layoutInflate.inflate(R.layout.adapter_receipt_list_content, parent, false);
        return new ReceiptContentViewHolder(view, mOnItemClickListener);
      default: //case SUMMARY:
        view = layoutInflate.inflate(R.layout.adapter_receipt_list_summary, parent, false);
        return new ReceiptSummaryViewHolder(view);
    }
  }

  @Override
  public void onBindViewHolder(BaseReceiptViewHolder holder, int position) {
    View view = holder.itemView;

    ReceiptAdapterItem item = mReceiptAdapterItem.get(position);
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
    return mReceiptAdapterItem.size();
  }

  public Receipt getReceipt(int position) {
    return mReceipts.get(position);
  }


}
