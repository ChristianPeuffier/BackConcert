package fr.istic.taa.jaxrs.dao.business;


import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import jakarta.persistence.EntityManager;

import java.util.List;

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
        em.createQuery( "update Evenement e set e.nbSold = e.nbSold - :nbBuy where e.id = :id")
                .setParameter("nbBuy", nbBuy)
                .setParameter("id", evenement.getId())
                .executeUpdate();
        em.getTransaction().commit();
    }

    public List<Evenement> findByOrganisateurId(Long id) {
        EntityManager em = getEntityManager();
        return em.createQuery("select e from Evenement e where e.organisateur.id = :id", Evenement.class)
                .setParameter("id", id)
                .getResultList();
    }
}
