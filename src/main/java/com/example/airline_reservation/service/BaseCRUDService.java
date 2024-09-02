package com.example.airline_reservation.service;

public interface BaseCRUDService<Q,R,D,I> {
  R create(Q request);
  R findById(I id);
  R update(I id, Q request);
  void deleteById(I id);
}
