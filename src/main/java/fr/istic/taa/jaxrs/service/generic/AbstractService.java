package fr.istic.taa.jaxrs.service.generic;

import fr.istic.taa.jaxrs.dao.generic.IGenericDao;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<K, T extends Serializable> implements IGenericService<K, T> {

    /**
     * GenericDao instance to be used by the service.
     */
    private final IGenericDao<K, T> genericDao;

    /**
     * Constructor.
     * @param paramGenericDao the generic dao
     */
    protected AbstractService(final IGenericDao<K, T> paramGenericDao) {
        this.genericDao = paramGenericDao;
    }

    /**
     * Get the generic dao.
     * @param id the id of the entity
     * @return the generic dao
     */
    public T findOne(final K id) {
        return genericDao.findOne(id);
    }

    /**
     * Get all entities.
     * @return a list of entities of type T
     */
    public List<T> findAll() {
        return genericDao.findAll();
    }

    /**
     * Save an entity.
     * @param entity the entity to save
     */
    public void save(final T entity) {
        genericDao.save(entity);
    }

    /**
     * Update an entity.
     * @param entity the entity to update
     * @return the updated entity
     */
    public T update(final T entity) {
        return genericDao.update(entity);
    }

    /**
     * Delete an entity.
     * @param entity the entity to delete
     */
    public void delete(final T entity) {
        genericDao.delete(entity);
    }

    /**
     * Delete an entity by its id.
     * @param entityId the id of the entity to delete
     */
    public void deleteById(final K entityId) {
        genericDao.deleteById(entityId);
    }
}
