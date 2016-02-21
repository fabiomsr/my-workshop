package org.fabiomsr.data.model;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ReceiptConceptStore extends RealmObject {

  @PrimaryKey
  private String id;
  private String code;
  private String concept;
  private int count;
  private float amount;
  private float totalAmount;


  public ReceiptConceptStore(){}

  public ReceiptConceptStore(@NonNull String id, @NonNull String code, @NonNull String concept,
                             int count, float amount, float totalAmount){
    this.id = id;
    this.code = code;
    this.concept = concept;
    this.count = count;
    this.amount = amount;
    this.totalAmount = totalAmount;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getConcept() {
    return concept;
  }

  public void setConcept(String concept) {
    this.concept = concept;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public float getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(float totalAmount) {
    this.totalAmount = totalAmount;
  }
}
