package org.fabiomsr.mitaller.module.receipt.edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseActivity;
import org.fabiomsr.mitaller.domain.Receipt;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditReceiptActivity extends BaseActivity {

  public static final String PARAM_RECEIPT = "org.fabiomsr.mitaller.module.receipt.edit.RECEIPT";

  public static final String RESULT_RECEIPT = "org.fabiomsr.mitaller.module.receipt.edit.RESULT";
  @Bind(R.id.toolbar) Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_receipt);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);
    mToolbar.setTitle(getTitle());

    Intent intent = getIntent();
    Receipt receipt = intent.getParcelableExtra(PARAM_RECEIPT);

    if(savedInstanceState == null) {
      replaceFragment(R.id.edit_receipt_pager_container,
          EditReceiptFragment.newInstance(receipt));
    }
  }

}
