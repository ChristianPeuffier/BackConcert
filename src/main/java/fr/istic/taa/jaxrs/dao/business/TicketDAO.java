package fr.istic.taa.jaxrs.dao.business;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Ticket;

public class TicketDAO extends AbstractJpaDao<Long, Ticket> {

    public TicketDAO(){
        super(Ticket.class);
    }
}
