package org.fabiomsr.mitaller.module.receipt.add;

import org.fabiomsr.mitaller.app.injection.annotation.PerActivity;
import org.fabiomsr.mitaller.app.injection.component.AbstractActivityComponent;
import org.fabiomsr.mitaller.app.injection.component.ApplicationComponent;
import org.fabiomsr.mitaller.app.injection.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface AddReceiptComponent extends AbstractActivityComponent {
  void inject(AddReceiptActivity addReceiptActivity);
  void inject(AddReceiptFragment addReceiptComponent);
}