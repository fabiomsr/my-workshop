package org.fabiomsr.mitaller.module.repairorder.list.adapter.holder;

import android.view.View;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.holder.base.BaseReceiptViewHolder;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.StickyHeaderRepairOrderAdapterItem;
import org.fabiomsr.mitaller.module.repairorder.list.adapter.item.base.RepairOrderAdapterItem;
import org.fabiomsr.mitaller.utils.CalendarUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepairOrderStickyHeaderViewHolder extends BaseReceiptViewHolder {

  @Bind(R.id.receipt_day)  TextView mReceiptDay;
  @Bind(R.id.receipt_month)  TextView mReceiptMonth;

  public RepairOrderStickyHeaderViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  @Override
  public void update(RepairOrderAdapterItem adapterItem) {
    StickyHeaderRepairOrderAdapterItem item = (StickyHeaderRepairOrderAdapterItem) adapterItem;

    Calendar calendar = Calendar.getInstance();
    Date date = item.getDate();

    String day = String.valueOf(CalendarUtils.getDay(calendar, date));
    String month = CalendarUtils.getAbbreviateMonthName(date);

    mReceiptDay.setText(day);
    mReceiptMonth.setText(month);
  }
}
