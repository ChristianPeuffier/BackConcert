package fr.istic.taa.jaxrs.dao.business;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TicketDAO extends AbstractJpaDao<Long, Ticket> {

    /**
     * Constructor.
     */
    public TicketDAO() {
        super(Ticket.class);
    }

    /**
     * Find ticket by user id.
     * @param userId user id
     * @return List of Ticket
     */
    public List<Ticket> findByUserId(final Long userId) {
        EntityManager em = getEntityManager();
        return em.createQuery("select t from Ticket t where t.utilisateur.id = :userId", Ticket.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    /**
     * Update user id.
     * @param ticket ticket
     * @param userId user id
     */
    public void updateUserId(final Ticket ticket, final Long userId) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        ticket.setUtilisateur(em.find(Utilisateur.class, userId));
        em.merge(ticket);
        em.getTransaction().commit();
    }
}
