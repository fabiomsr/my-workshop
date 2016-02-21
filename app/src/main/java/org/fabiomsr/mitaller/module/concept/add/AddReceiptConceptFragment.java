package org.fabiomsr.mitaller.module.concept.add;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseFragment;
import org.fabiomsr.mitaller.domain.ReceiptConcept;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AddReceiptConceptFragment extends BaseFragment {

  @Bind(R.id.edit_receipt_concept_code) EditText mCodeView;
  @Bind(R.id.edit_receipt_concept_description) EditText mDescriptionView;
  @Bind(R.id.edit_receipt_concept_count) EditText mCountView;
  @Bind(R.id.edit_receipt_concept_amount) EditText mAmountView;
  @Bind(R.id.edit_receipt_concept_total)  TextView mTotalView;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_add_receipt_concept, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }


  @OnClick(R.id.accept)
  public void onAcceptConcept() {
    String code = mCodeView.getText().toString();
    String description = mDescriptionView.getText().toString();

    String countText = mCountView.getText().toString();
    String amountText = mAmountView.getText().toString();

    int count = countText.isEmpty() ? 0 : Integer.parseInt(countText);
    float amount = amountText.isEmpty() ? 0 : Float.parseFloat(amountText);

    float total = count * amount;

    ReceiptConcept concept = ReceiptConcept.create("", code, description, count, amount, total);

    Intent intent = new Intent();
    intent.putExtra(AddReceiptConceptActivity.RESULT_CONCEPT, concept);
    Activity activity = getActivity();
    activity.setResult(Activity.RESULT_OK, intent);
    activity.finish();
  }

  @OnTextChanged(R.id.edit_receipt_concept_count)
  public void onCountChange() {
    setTotal();
  }

  @OnTextChanged(R.id.edit_receipt_concept_amount)
  public void onAmountChange() {
    setTotal();
  }

  private void setTotal(){
    String countText = mCountView.getText().toString();
    String amountText = mAmountView.getText().toString();

    int count = countText.isEmpty() ? 0 : Integer.parseInt(countText);
    float amount = amountText.isEmpty() ? 0 : Float.parseFloat(amountText);

    mTotalView.setText(getString(R.string.receipt_total_format, count * amount));
  }
}
