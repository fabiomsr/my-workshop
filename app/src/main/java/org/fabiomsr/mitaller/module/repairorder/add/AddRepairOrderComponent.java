package org.fabiomsr.mitaller.module.repairorder.add;

import org.fabiomsr.mitaller.app.injection.annotation.PerActivity;
import org.fabiomsr.mitaller.app.injection.component.ApplicationComponent;
import org.fabiomsr.mitaller.app.injection.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface AddRepairOrderComponent {
  void inject(AddRepairOrderFragment addRepairOrderFragment);
}
