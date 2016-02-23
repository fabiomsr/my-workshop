package org.fabiomsr.mitaller.module.photo;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoActivity extends BaseActivity {
  public static final String PARAM_PHOTO_URI = "org.fabiomsr.mitaller.module.photo.URI";

  @Bind(R.id.photo) ImageView mPhotoView;

  private PhotoViewAttacher mPhotoAttacher;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_photo);
    ButterKnife.bind(this);

    String path = getIntent().getStringExtra(PARAM_PHOTO_URI);
    Uri    uri  = Uri.parse(path);
    mPhotoView.setImageURI(uri);
    mPhotoAttacher = new PhotoViewAttacher(mPhotoView);
  }
}
