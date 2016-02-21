package org.fabiomsr.mitaller.domain.mapper.impl;

import org.fabiomsr.data.model.ClientStore;
import org.fabiomsr.data.model.ReceiptConceptStore;
import org.fabiomsr.data.model.ReceiptStore;
import org.fabiomsr.mitaller.domain.Client;
import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.domain.ReceiptConcept;
import org.fabiomsr.mitaller.domain.mapper.Mapper;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;
import rx.Observable;

public class ReceiptMapper implements Mapper<ReceiptStore, Receipt> {

  ClientMapper mClientMapper ;
  ReceiptConceptMapper mReceiptConceptMapper;

  public ReceiptMapper(){
    mClientMapper = new ClientMapper();
    mReceiptConceptMapper = new ReceiptConceptMapper();
  }

  @Override
  public Receipt toData(ReceiptStore model) {
    ClientStore clientStore = model.getClient();
    RealmList<ReceiptConceptStore> receiptConcepts = model.getReceiptConcepts();
    Date date = model.getDate();
    String brand = model.getBrand();
    String deviceModel = model.getModel();
    String serial = model.getSerial();
    String subject = model.getSubject();
    String photoUri = model.getPhotoUri();
    int number = model.getNumber();
    float igicAmount = model.getIGICAmount();
    float subTotal = model.getSubTotal();
    float total = model.getTotal();
    boolean isDeleted = model.isDeleted();

    Client client = mClientMapper.toData(clientStore);

    ArrayList<ReceiptConcept> concepts = new ArrayList<>(receiptConcepts.size());
    Observable.from(receiptConcepts)
        .map(mReceiptConceptMapper::toData)
        .forEach(concepts::add);

    return Receipt.create(number, client, date, subject,
        brand, deviceModel, serial, photoUri, concepts, igicAmount, subTotal, total, isDeleted);
  }

  @Override
  public ReceiptStore toModel(Receipt data) {
    Client client = data.client();
    ArrayList<ReceiptConcept> receiptConcepts = data.receiptConcepts();
    Date date = data.date();
    String brand = data.brand();
    String deviceModel = data.model();
    String serial = data.serial();
    String subject = data.subject();
    String photoUri = data.photoUri();
    int number = data.number();
    float igicAmount = data.IGICAmount();
    float subTotal = data.subTotal();
    float total = data.total();
    boolean isDeleted = data.deleted();

    ClientStore clientStore = mClientMapper.toModel(client);

    RealmList<ReceiptConceptStore> conceptsStore = new RealmList<>();
    Observable.from(receiptConcepts)
        .map(mReceiptConceptMapper::toModel)
        .forEach(conceptsStore::add);

    return new ReceiptStore(number, clientStore, date, subject,
        brand, deviceModel, serial, photoUri, conceptsStore, igicAmount, subTotal, total, isDeleted);
  }
}
