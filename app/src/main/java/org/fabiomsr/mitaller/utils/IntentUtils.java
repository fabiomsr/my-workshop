package org.fabiomsr.mitaller.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.io.File;

public class IntentUtils {


  public static void takePicture(@NonNull Context context, @NonNull Fragment fragment,
                                 int request, @NonNull File outputFile){
      Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                   Uri.fromFile(outputFile));
        fragment.startActivityForResult(takePictureIntent, request);
      }
  }
}
