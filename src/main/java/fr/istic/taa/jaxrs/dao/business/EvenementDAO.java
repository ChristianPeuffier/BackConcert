package fr.istic.taa.jaxrs.dao.business;


import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import jakarta.persistence.EntityManager;

public class EvenementDAO extends AbstractJpaDao<Long, Evenement> {

    /**
     * Constructor.
     */
    public EvenementDAO() {
        super(Evenement.class);
    }

    /**
     * Update nbSold.
     */
    public void updateNbSold(final Evenement evenement, final int nbBuy) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        evenement.setNbSold(evenement.getNbSold() - nbBuy);
        em.merge(evenement);
        em.getTransaction().commit();
    }
}
