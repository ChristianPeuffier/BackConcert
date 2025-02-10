package fr.istic.taa.jaxrs.service.generic;

import fr.istic.taa.jaxrs.dao.generic.IGenericDao;
import fr.istic.taa.jaxrs.service.generic.IGenericService;
import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<K, T extends Serializable> implements IGenericService<K, T> {

    IGenericDao<K, T> genericDao;

    protected AbstractService(IGenericDao<K, T> genericDao) {
        this.genericDao = genericDao;
    }

    public T findOne(K id) {
        return genericDao.findOne(id);
    }

    public List<T> findAll() {
        return genericDao.findAll();
    }

    public void save(T entity) {
        genericDao.save(entity);
    }

    public T update(T entity) {
        return genericDao.update(entity);
    }

    public void delete(T entity) {
        genericDao.delete(entity);
    }

    public void deleteById(K entityId) {
        genericDao.deleteById(entityId);
    }
}
