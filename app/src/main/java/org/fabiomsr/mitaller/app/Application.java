package org.fabiomsr.mitaller.app;

import com.crashlytics.android.Crashlytics;

import org.fabiomsr.mitaller.BuildConfig;
import org.fabiomsr.mitaller.R;
import org.fabiomsr.mitaller.app.injection.component.ApplicationComponent;
import org.fabiomsr.mitaller.app.injection.component.DaggerApplicationComponent;
import org.fabiomsr.mitaller.app.injection.module.ApplicationModule;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Application extends android.app.Application {

  private ApplicationComponent mApplicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    if(!BuildConfig.DEBUG) {
      Fabric.with(this, new Crashlytics());
    }

    mApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();

    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
            .setDefaultFontPath(getString(R.string.font_path_bariol_regular))
            .setFontAttrId(R.attr.fontPath)
            .build()
    );
  }

  public ApplicationComponent component() {
    return mApplicationComponent;
  }

}
