package org.fabiomsr.mitaller.domain.mapper.impl;

import org.fabiomsr.data.model.ClientStore;
import org.fabiomsr.data.model.RepairOrderStore;
import org.fabiomsr.mitaller.domain.Client;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.domain.mapper.Mapper;

import java.util.Date;

public class RepairOrderMapper implements Mapper<RepairOrderStore, RepairOrder> {

  ClientMapper mClientMapper ;

  public RepairOrderMapper(){
    mClientMapper = new ClientMapper();
  }

  @Override
  public RepairOrder toData(RepairOrderStore model) {
    ClientStore clientStore = model.getClient();
    Date date = model.getDate();
    String brand = model.getBrand();
    String deviceModel = model.getModel();
    String serial = model.getSerial();
    String subject = model.getSubject();
    String photoUri = model.getPhotoUri();
    int number = model.getNumber();
    boolean isDeleted = model.isDeleted();

    Client client = mClientMapper.toData(clientStore);

    return RepairOrder.create(number, client, date, subject,
        brand, deviceModel, serial, photoUri, isDeleted);
  }

  @Override
  public RepairOrderStore toModel(RepairOrder data) {
    Client client = data.client();
    Date date = data.date();
    String brand = data.brand();
    String deviceModel = data.model();
    String serial = data.serial();
    String subject = data.subject();
    String photoUri = data.photoUri();
    int number = data.number();
    boolean isDeleted = data.deleted();

    ClientStore clientStore = mClientMapper.toModel(client);

    return new RepairOrderStore(number, clientStore, date, subject,
        brand, deviceModel, serial, photoUri, isDeleted);
  }
}
