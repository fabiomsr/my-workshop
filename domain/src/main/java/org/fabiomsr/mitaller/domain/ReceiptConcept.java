package org.fabiomsr.mitaller.domain;

import android.os.Parcelable;

import javax.annotation.Nonnull;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class ReceiptConcept implements Parcelable{

  public abstract String id();
  public abstract String code();
  public abstract String concept();
  public abstract int count();
  public abstract float amount();
  public abstract float totalAmount();

  public static ReceiptConcept create(@Nonnull String id, @Nonnull String code, @Nonnull String concept,
                              int count, float amount, float totalAmount) {
    return new AutoParcel_ReceiptConcept(id, code, concept, count, amount, totalAmount);
  }

  public static ReceiptConcept create(String id, ReceiptConcept concept) {
    return ReceiptConcept.create(id, concept.code(), concept.concept(), concept.count(), concept.amount(), concept.totalAmount());
  }
}
