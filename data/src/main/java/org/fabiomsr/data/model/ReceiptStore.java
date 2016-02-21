package org.fabiomsr.data.model;

import android.support.annotation.NonNull;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ReceiptStore extends RealmObject {

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
  private RealmList<ReceiptConceptStore> receiptConcepts;
  private float IGICAmount;
  private float subTotal;
  private float total;
  private boolean isDeleted;

  public ReceiptStore(){}

  public ReceiptStore(int number, @NonNull ClientStore client, @NonNull Date date, @NonNull String subject,
                      @NonNull String brand, @NonNull String model, @NonNull String serial, @NonNull String  photoUri,
                      @NonNull RealmList<ReceiptConceptStore> receiptConcepts, float IGICAmount, float subTotal, float total, boolean isDeleted){
    this.number = number;
    this.client = client;
    this.date = date;
    this.subject = subject;
    this.brand = brand;
    this.model = model;
    this.serial = serial;
    this.photoUri = photoUri;
    this.receiptConcepts = receiptConcepts;
    this.IGICAmount = IGICAmount;
    this.subTotal = subTotal;
    this.total = total;
    this.isDeleted = isDeleted;

  }


  public boolean isDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public float getTotal() {
    return total;
  }

  public void setTotal(float total) {
    this.total = total;
  }

  public float getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(float subTotal) {
    this.subTotal = subTotal;
  }

  public float getIGICAmount() {
    return IGICAmount;
  }

  public void setIGICAmount(float IGICAmount) {
    this.IGICAmount = IGICAmount;
  }

  public RealmList<ReceiptConceptStore> getReceiptConcepts() {
    return receiptConcepts;
  }

  public void setReceiptConcepts(RealmList<ReceiptConceptStore> receiptConcepts) {
    this.receiptConcepts = receiptConcepts;
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
