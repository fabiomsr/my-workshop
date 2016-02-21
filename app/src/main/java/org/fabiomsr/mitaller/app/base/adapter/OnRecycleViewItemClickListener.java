package org.fabiomsr.mitaller.app.base.adapter;

import android.support.annotation.NonNull;
import android.view.View;

public interface OnRecycleViewItemClickListener<T> {
  void onRecycleViewItemClick(@NonNull View view, @NonNull T item, int position);
}
