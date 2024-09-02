package com.example.airline_reservation.service;

import com.example.airline_reservation.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractCrudService<Q,R,RP extends JpaRepository<D,I>,D,I> implements BaseCRUDService<Q,R,D,I> {

  protected RP repository;

  protected AbstractCrudService(RP repository) {
    this.repository = repository;
  }

  @Override
  public R create(Q request) {
    D domainEntity = createAndSave(request);
    return toResponse(domainEntity);
  }

  protected abstract D createAndSave(Q request);

  @Override
  public R findById(I id) {
    D domainEntity = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Could not found entity with id " + id));
    return toResponse(domainEntity);
  }

  @Override
  public R update(I id, Q request) {
    D domainEntity = updateAndSave(id,request);
    return toResponse(domainEntity);
  }

  protected abstract D updateAndSave(I id,Q request);
  @Override
  public void deleteById(I id) {
    D domainEntity = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Could not found entity with id " + id));
    repository.delete(domainEntity);
  }
  protected abstract R toResponse(D domainEntity);
}
