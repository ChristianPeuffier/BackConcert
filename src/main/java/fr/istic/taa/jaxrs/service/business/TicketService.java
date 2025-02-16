package fr.istic.taa.jaxrs.service.business;

import fr.istic.taa.jaxrs.dao.business.TicketDAO;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketDTO;
import fr.istic.taa.jaxrs.service.generic.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class TicketService extends AbstractService<Long, Ticket> {

    /**
     * Constructor.
     */
    public TicketService() {
        super(new TicketDAO());
    }


    /**
     * Get ticket by id.
     * @param id ticket id
     * @return TicketDTO
     */
    public TicketDTO getTicketById(final Long id) {
        Ticket ticket = findOne(id);
        if (ticket != null) {
            return new TicketDTO(ticket);
        }
        return null;
    }

    /**
     * Get all tickets.
     * @return List of TicketDTO
     */
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = findAll();
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketDTOs.add(new TicketDTO(ticket));
        }
        return ticketDTOs;
    }
}
