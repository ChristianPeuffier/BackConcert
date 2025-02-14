package fr.istic.taa.jaxrs.dao.generic;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<K, T extends Serializable> {

   /**
    * Find an entity by its primary key.
    * @param id the primary key
    * @return the entity
    */
   T findOne(K id);

    /**
     * Find all entities.
     * @return a list of entities
     */
   List<T> findAll();

   /**
    * Save an entity.
    * @param entity the entity to save
    */
   void save(T entity);

    /**
     * Update an entity.
     * @param entity the entity to update
     * @return the updated entity
     */
   T update(T entity);

    /**
     * Delete an entity.
     * @param entity the entity to delete
     */
   void delete(T entity);

    /**
     * Delete an entity by its primary key.
     * @param entityId the primary key
     */
   void deleteById(K entityId);
}
