/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fabiomsr.mitaller.app.injection.module;

import android.app.Application;
import android.location.LocationManager;
import android.view.LayoutInflater;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.data.impl.RealmIOReceiptDataStore;
import org.fabiomsr.mitaller.preferences.UserPreferences;
import org.fabiomsr.mitaller.preferences.impl.UserSharedPreferences;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link Application} to create.
 */
@Module
public class ApplicationModule {
  private final Application mApplication;

  public ApplicationModule(Application application) {
    mApplication = application;
  }

  /**
   * Expose the application to the graph.
   */
  @Provides @Singleton Application application() {
    return mApplication;
  }


  /**
   * Expose the application to the graph.
   */
  @Provides @Singleton
  ReceiptDataStore provideReceiptDataStore() {
    return new RealmIOReceiptDataStore(mApplication);
  }


  /**
   * Expose the application to the graph.
   */
  @Provides @Singleton
  UserPreferences provideUserPreferences() {
    return new UserSharedPreferences(mApplication);
  }
}
