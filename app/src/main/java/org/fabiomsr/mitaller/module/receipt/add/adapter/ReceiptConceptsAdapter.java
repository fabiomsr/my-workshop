package org.fabiomsr.mitaller.module.receipt.add.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.adapter.OnRecycleViewItemClickListener;
import org.fabiomsr.mitaller.domain.ReceiptConcept;
import org.fabiomsr.mitaller.module.receipt.add.adapter.holder.ReceiptConceptViewHolder;

import java.util.ArrayList;

import rx.Observable;
import rx.subjects.PublishSubject;

public class ReceiptConceptsAdapter extends RecyclerView.Adapter<ReceiptConceptViewHolder>  {

  /**
   * Receipt Concepts
   */
  private ArrayList<ReceiptConcept> mReceiptConcepts;


  private PublishSubject<ReceiptConcept> publishRemoveConcept;

  public ReceiptConceptsAdapter() {
    mReceiptConcepts = new ArrayList<>();
    publishRemoveConcept = PublishSubject.create();
  }

  /**
   * Add receipt concepts
   * @param concepts Concepts
   */
  public void addConcepts(ArrayList<ReceiptConcept> concepts){
    mReceiptConcepts = concepts;
    notifyDataSetChanged();
  }

  /**
   * Add receipt concept
   * @param concept Concepts
   */
  public void addConcept(ReceiptConcept concept){
    mReceiptConcepts.add(concept);
    notifyDataSetChanged();
  }

  public Observable<ReceiptConcept> onRemoveConcept(){
    return publishRemoveConcept;
  }


  public ArrayList<ReceiptConcept> getConcepts(){
    return mReceiptConcepts;
  }

  @Override
  public ReceiptConceptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view;
    LayoutInflater layoutInflate = LayoutInflater.from(parent.getContext());
    view = layoutInflate.inflate(R.layout.adapter_edit_receipt_concept_list_content, parent, false);
    return new ReceiptConceptViewHolder(view, onDeleteConcept);
  }

  @Override
  public void onBindViewHolder(ReceiptConceptViewHolder holder, int position) {
    holder.update(mReceiptConcepts.get(position));
  }

  @Override
  public int getItemCount() {
    return mReceiptConcepts.size();
  }


  private OnRecycleViewItemClickListener<ReceiptConcept> onDeleteConcept = (view, item, position) -> {
    mReceiptConcepts.remove(position);
    notifyItemRemoved(position);
    publishRemoveConcept.onNext(item);
  };

}
