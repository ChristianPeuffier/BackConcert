package fr.istic.taa.jaxrs.service.generic;

import java.io.Serializable;

public interface IGenericService <K, T extends Serializable> {
    T findOne(final K id);
    Iterable<T> findAll();
    void save(final T entity);
    T update(final T entity);
    void delete(final T entity);
    void deleteById(final K entityId);
}
