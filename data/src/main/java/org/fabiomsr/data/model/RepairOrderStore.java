package org.fabiomsr.data.model;

import android.support.annotation.NonNull;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RepairOrderStore extends RealmObject {

  public static final String FIELD_DATE = "date";
  public static final String FIELD_IS_DELETE = "isDeleted";

  @PrimaryKey
  private int number;
  private ClientStore client;
  private Date date;
  private String subject;
  private String brand;
  private String model;
  private String serial;
  private String photoUri;
  private boolean isDeleted;

  public RepairOrderStore(){}

  public RepairOrderStore(int number, @NonNull ClientStore client, @NonNull Date date, @NonNull String subject,
                          @NonNull String brand, @NonNull String model, @NonNull String serial, @NonNull String photoUri,
                          boolean isDeleted){
    this.number = number;
    this.client = client;
    this.date = date;
    this.subject = subject;
    this.brand = brand;
    this.model = model;
    this.serial = serial;
    this.photoUri = photoUri;
    this.isDeleted = isDeleted;
  }


  public boolean isDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public ClientStore getClient() {
    return client;
  }

  public void setClient(ClientStore client) {
    this.client = client;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getPhotoUri() {
    return photoUri;
  }

  public void setPhotoUri(String photoUri) {
    this.photoUri = photoUri;
  }
}
