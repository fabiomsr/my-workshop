package org.fabiomsr.data.model;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ClientStore extends RealmObject{

  @PrimaryKey
  private String dni;
  private String name;
  private String firstLastName;
  private String secondLastName;
  private String fullName;
  private String normalizeName;
  private String address;

  public ClientStore(){}

  public ClientStore(@NonNull String name, @NonNull String firstLastName,
                     @NonNull String secondLastName, @NonNull String fullName, @NonNull String normalizeName,
                     @NonNull String dni, @NonNull String address){
    this.dni = dni;
    this.name = name;
    this.firstLastName = firstLastName;
    this.secondLastName = secondLastName;
    this.fullName = fullName;
    this.normalizeName = normalizeName;
    this.address = address;
  }


  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFirstLastName() {
    return firstLastName;
  }

  public void setFirstLastName(String firstLastName) {
    this.firstLastName = firstLastName;
  }

  public String getSecondLastName() {
    return secondLastName;
  }

  public void setSecondLastName(String secondLastName) {
    this.secondLastName = secondLastName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getNormalizeName() {
    return normalizeName;
  }

  public void setNormalizeName(String normalizeName) {
    this.normalizeName = normalizeName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
