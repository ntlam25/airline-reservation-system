package com.example.flight_reservation.service;

import java.util.List;

public interface BaseCRUDService<Q,R,D,I> {
  R create(Q request);
  R findById(I id);
  R update(I id, Q request);
  void deleteById(I id);
  List<R> findAll();
}
