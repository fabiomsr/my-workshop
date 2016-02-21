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
package org.fabiomsr.mitaller.app.injection.component;

import android.app.Application;

import org.fabiomsr.data.ReceiptDataStore;
import org.fabiomsr.mitaller.app.injection.module.ApplicationModule;
import org.fabiomsr.mitaller.preferences.UserPreferences;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  // Field injections of any dependencies of the DemoApplication
  void inject(org.fabiomsr.mitaller.app.Application application);

  // Exported for child-components.
  Application application();

  ReceiptDataStore receiptDataStore();

  UserPreferences userPreferences();
}
