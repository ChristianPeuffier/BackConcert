package fr.istic.taa.jaxrs.dao.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class EntityManagerHelper {

    /**
     * EntityManagerFactory.
     */
    private static final EntityManagerFactory EMF;

    /**
     * ThreadLocal EntityManager.
     */
    private static final ThreadLocal<EntityManager> THREADLOCAL;

    static {
        EMF = Persistence.createEntityManagerFactory("dev");
        THREADLOCAL = new ThreadLocal<>();
    }

    private EntityManagerHelper() {

    }

    /**
     * Function to get EntityManager.
     * @return EntityManager
     */
    static EntityManager getEntityManager() {
        EntityManager em = THREADLOCAL.get();

        if (em == null) {
            em = EMF.createEntityManager();
            THREADLOCAL.set(em);
        }
        return em;
    }

    /**
     * Function to close EntityManager.
     */
    public static void closeEntityManager() {
        EntityManager em = THREADLOCAL.get();
        if (em != null) {
            em.close();
            THREADLOCAL.remove();
        }
    }

    /**
     * Function to close EntityManagerFactory.
     */
    public static void closeEntityManagerFactory() {
        EMF.close();
    }

    /**
     * Function to begin transaction.
     */
    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    /**
     * Function to commit transaction.
     */
    public static void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    /**
     * Function to commit transaction.
     */
    public static void commit() {
        getEntityManager().getTransaction().commit();
    }
}
