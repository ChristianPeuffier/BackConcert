package fr.istic.taa.jaxrs.dao.business;


import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import jakarta.persistence.EntityManager;

public class UtilisateurDAO extends AbstractJpaDao<Long, Utilisateur> {


    /**
     * Constructor.
     */
    public UtilisateurDAO() {
        super(Utilisateur.class);
    }

    /**
     * Check if an email exist.
     * @param email the email to check
     * @return true if the email exist, false otherwise
     */
    public boolean emailExist(String email) {
        EntityManager em = getEntityManager();
        return !em.createQuery("select u from Utilisateur u where u.email = :email", Utilisateur.class)
                .setParameter("email", email)
                .getResultList().isEmpty();
    }

    public Utilisateur findByEmail(String email) {
        EntityManager em = getEntityManager();
        return em.createQuery("select u from Utilisateur u where u.email = :email", Utilisateur.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
