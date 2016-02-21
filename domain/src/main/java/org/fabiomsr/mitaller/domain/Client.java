package org.fabiomsr.mitaller.domain;

import android.os.Parcelable;

import javax.annotation.Nonnull;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class Client implements Parcelable{

  public abstract String name();
  public abstract String firstLastName();
  public abstract String secondLastName();
  public abstract String fullName();
  public abstract String normalizeName();
  public abstract String dni();
  public abstract String address();

  public static Client create(@Nonnull String name, @Nonnull String firstLastName,
                               @Nonnull String secondLastName, @Nonnull String fullName, @Nonnull String normalizeName,
                               @Nonnull String dni, @Nonnull String address) {
    return new AutoParcel_Client(name, firstLastName, secondLastName,
        fullName, normalizeName, dni, address);
  }
}
