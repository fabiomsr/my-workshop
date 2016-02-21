package org.fabiomsr.mitaller.module.concept.add;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddReceiptConceptActivity extends BaseActivity {
  public static final String RESULT_CONCEPT = "org.fabiomsr.mitaller.module.addconcept.result";

  @Bind(R.id.toolbar) Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_receipt_concept);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);
    mToolbar.setTitle(getTitle());

    if(savedInstanceState == null) {
      replaceFragment(R.id.add_receipt_concept_container, new AddReceiptConceptFragment());
    }
  }
}
