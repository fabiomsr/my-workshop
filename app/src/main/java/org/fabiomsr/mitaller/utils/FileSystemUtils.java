package org.fabiomsr.mitaller.utils;

import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileSystemUtils {

  private static final String TAG = FileSystemUtils.class.getSimpleName();

  @Nullable
  public static File createImageFile() {
    // Create an image file name
    String timeStamp     = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String imageFileName = "JPEG_" + timeStamp + "_";
    File storageDir = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_PICTURES);

    try {
      return File.createTempFile(
          imageFileName,  /* prefix */
          ".jpg",         /* suffix */
          storageDir      /* directory */
      );
    } catch (IOException e) {
      Log.e(TAG, "createImageFile: ", e);
    }

    return null;
  }
}
