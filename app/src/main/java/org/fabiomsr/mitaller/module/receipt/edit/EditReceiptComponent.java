package org.fabiomsr.mitaller.module.receipt.edit;

import org.fabiomsr.mitaller.app.injection.annotation.PerActivity;
import org.fabiomsr.mitaller.app.injection.component.AbstractActivityComponent;
import org.fabiomsr.mitaller.app.injection.component.ApplicationComponent;
import org.fabiomsr.mitaller.app.injection.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface EditReceiptComponent extends AbstractActivityComponent {
  void inject(EditReceiptActivity addReceiptActivity);
  void inject(EditReceiptDetailsFragment addReceiptComponent);
}