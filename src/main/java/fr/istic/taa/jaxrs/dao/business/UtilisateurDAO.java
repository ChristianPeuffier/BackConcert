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

    /**
     * Find an Utilisateur by its email.
     * @param email the email of the Utilisateur
     * @return the Utilisateur
     */
    public Utilisateur findByEmail(String email) {
        EntityManager em = getEntityManager();
        return em.createQuery("select u from Utilisateur u where u.email = :email", Utilisateur.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    /**
     * Update the role of an Utilisateur.
     * @param utilisateur the Utilisateur to update
     */
    public void updateUtilisateur(Utilisateur utilisateur) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.createQuery("update Utilisateur u set u.typeUtilisateur = :role where u.id = :id")
                .setParameter("role", utilisateur.getTypeUtilisateur())
                .setParameter("id", utilisateur.getId())
                .executeUpdate();
        em.getTransaction().commit();
    }

}
