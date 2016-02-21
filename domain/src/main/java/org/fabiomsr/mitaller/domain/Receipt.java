package org.fabiomsr.mitaller.domain;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Nonnull;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class Receipt implements Parcelable{

  public abstract int number();
  public abstract Client client();
  public abstract Date date();
  public abstract String subject();
  public abstract String brand();
  public abstract String model();
  public abstract String serial();
  public abstract String photoUri();
  public abstract ArrayList<ReceiptConcept> receiptConcepts();
  public abstract float IGICAmount();
  public abstract float subTotal();
  public abstract float total();
  public abstract boolean deleted();



  public static Receipt create(int number, @Nonnull Client client, @Nonnull Date date, @Nonnull String subject,
                        @Nonnull String brand, @Nonnull String model, @Nonnull String serial, @Nonnull String photoUri,
                        @Nonnull ArrayList<ReceiptConcept> receiptEntities, float IGICAmount, float subTotal, float total, boolean deleted) {
    return new AutoParcel_Receipt(number, client, date, subject,
        brand, model, serial, photoUri, receiptEntities, IGICAmount, subTotal, total, deleted);
  }



}
