package com.example.flight_reservation.service;

import com.example.flight_reservation.exception.ResourceNotFoundException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

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
    Class<D> domainClass = (Class<D>)((ParameterizedType) getClass().getGenericSuperclass())
        .getActualTypeArguments()[3];
    D domainEntity = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Could not found " + domainClass.getSimpleName()+" with id " + id));
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
    Class<D> domainClass = (Class<D>)((ParameterizedType) getClass().getGenericSuperclass())
        .getActualTypeArguments()[3];
    D domainEntity = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Could not found " + domainClass.getSimpleName()+" with id " + id));
    repository.delete(domainEntity);
  }
  protected abstract R toResponse(D domainEntity);
  @Override
  public List<R> findAll() {
    return repository.findAll().stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
  }
}
