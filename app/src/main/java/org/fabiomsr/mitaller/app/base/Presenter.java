package org.fabiomsr.mitaller.app.base;

import rx.subscriptions.CompositeSubscription;

public abstract class Presenter<T extends ViewContract> {

  protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

  protected T mContract;

  public void attachViewContract(T contract){
    mContract = contract;
  }

  public void resume(){}

  public void pause(){}

  public void destroy(){
    mCompositeSubscription.unsubscribe();
  }

}
