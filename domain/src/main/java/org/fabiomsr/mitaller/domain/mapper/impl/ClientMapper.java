package org.fabiomsr.mitaller.domain.mapper.impl;

import org.fabiomsr.data.model.ClientStore;
import org.fabiomsr.mitaller.domain.Client;
import org.fabiomsr.mitaller.domain.mapper.Mapper;

public class ClientMapper implements Mapper<ClientStore, Client>{

  @Override
  public Client toData(ClientStore model) {
    String name = model.getName();
    String firstLastName = model.getFirstLastName();
    String secondLastName = model.getSecondLastName();
    String normalizeName = model.getNormalizeName();
    String fullName = model.getFullName();
    String address = model.getAddress();
    String dni = model.getDni();

    return Client.create(name, firstLastName, secondLastName,
        fullName, normalizeName, dni, address);
  }

  @Override
  public ClientStore toModel(Client data) {

    String name = data.name();
    String firstLastName = data.firstLastName();
    String secondLastName = data.secondLastName();
    String normalizeName = data.normalizeName();
    String fullName = data.fullName();
    String address = data.address();
    String dni = data.dni().toUpperCase();

    return new ClientStore(name, firstLastName, secondLastName,
        fullName, normalizeName, dni, address);
  }
}
