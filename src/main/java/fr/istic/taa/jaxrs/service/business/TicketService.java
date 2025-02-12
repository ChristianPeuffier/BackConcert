package fr.istic.taa.jaxrs.service.business;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketDTO;

import java.util.ArrayList;
import java.util.List;

public class TicketService extends AbstractJpaDao<Long, Ticket> {
    public TicketService() {
        super(Ticket.class);
    }

    public TicketDTO getTicketById(Long id) {
        Ticket ticket = findOne(id);
        if (ticket != null) {
            return new TicketDTO(ticket);
        }
        return null;
    }

    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = findAll();
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketDTOs.add(new TicketDTO(ticket));
        }
        return ticketDTOs;
    }
}
