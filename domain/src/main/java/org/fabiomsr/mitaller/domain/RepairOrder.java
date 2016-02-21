package org.fabiomsr.mitaller.domain;

import android.os.Parcelable;

import java.util.Date;

import javax.annotation.Nonnull;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class RepairOrder implements Parcelable {

  public abstract int number();
  public abstract Client client();
  public abstract Date date();
  public abstract String subject();
  public abstract String brand();
  public abstract String model();
  public abstract String serial();
  public abstract String photoUri();
  public abstract boolean deleted();


  public static RepairOrder create(int number, @Nonnull Client client, @Nonnull Date date, @Nonnull String subject,
                               @Nonnull String brand, @Nonnull String model, @Nonnull String serial, @Nonnull String photoUri,
                               boolean deleted) {
    return new AutoParcel_RepairOrder(number, client, date, subject,
                                  brand, model, serial, photoUri, deleted);
  }
}
