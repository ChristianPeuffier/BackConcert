package fr.istic.taa.jaxrs.dao.generic;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

    /**
     *  The class of the entity.
     */
    private Class<T> clazz;

    /**
     * The entity manager.
     */
    private final EntityManager entityManager;

    /**
     * Constructor.
     * @param clazs the class of the entity
     */
    protected AbstractJpaDao(final Class<T> clazs) {
        this.entityManager = EntityManagerHelper.getEntityManager();
        this.clazz = clazs;
    }

    /**
     * Set the class of the entity to set.
     * @param clazzToSet the class of the entity to set
     */
    public void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    /**
     * Find an entity by its id.
     * @param id the id of the entity
     * @return the entity of type T
     */
    public T findOne(final K id) {
        return entityManager.find(clazz, id);
    }

    /**
     * Find all entities.
     * @return a list of entities of type T
     */
    public List<T> findAll() {
        return entityManager.createQuery("select e from " + clazz.getName() + " as e", clazz).getResultList();
    }

    /**
     * Save an entity.
     * @param entity the entity to save
     */
    public void save(final T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        entityManager.persist(entity);
        t.commit();
    }

    /**
     * Update an entity.
     * @param entity the entity to update
     * @return the updated entity
     */
    public T update(final T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        T res = entityManager.merge(entity);
        t.commit();
        return res;
    }

    /**
     * Delete an entity.
     * @param entity the entity to delete
     */
    public void delete(final T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        entityManager.remove(entity);
        t.commit();
    }

    /**
     * Delete an entity by its id.
     * @param entityId the id of the entity to delete
     */
    public void deleteById(final K entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }
}
