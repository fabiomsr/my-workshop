package org.fabiomsr.mitaller.app.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

  protected OnRecycleViewItemClickListener<T> mOnItemClickListener;

  public BaseViewHolder(View itemView) {
    super(itemView);
  }

  public BaseViewHolder(View itemView, OnRecycleViewItemClickListener<T> listener) {
    super(itemView);
    mOnItemClickListener = listener;
  }

}
