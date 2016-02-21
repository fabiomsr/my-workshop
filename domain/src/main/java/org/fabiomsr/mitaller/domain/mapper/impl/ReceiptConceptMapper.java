package org.fabiomsr.mitaller.domain.mapper.impl;

import org.fabiomsr.data.model.ReceiptConceptStore;
import org.fabiomsr.mitaller.domain.ReceiptConcept;
import org.fabiomsr.mitaller.domain.mapper.Mapper;

public class ReceiptConceptMapper implements Mapper<ReceiptConceptStore, ReceiptConcept> {

  @Override
  public ReceiptConcept toData(ReceiptConceptStore model) {
    float amount = model.getAmount();
    String code = model.getCode();
    String concept = model.getConcept();
    int count = model.getCount();
    String id = model.getId();
    float totalAmount = model.getTotalAmount();

    return ReceiptConcept.create(id,code,concept, count, amount, totalAmount);
  }

  @Override
  public ReceiptConceptStore toModel(ReceiptConcept data) {
    float amount = data.amount();
    String code = data.code();
    String concept = data.concept();
    int count = data.count();
    String id = data.id();
    float totalAmount = data.totalAmount();

    return new ReceiptConceptStore(id,code,concept, count, amount, totalAmount);  }
}
