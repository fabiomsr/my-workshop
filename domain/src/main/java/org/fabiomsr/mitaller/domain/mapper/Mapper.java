package org.fabiomsr.mitaller.domain.mapper;

public interface Mapper<M, D> {

  D toData(M model);

  M toModel(D data);
}
