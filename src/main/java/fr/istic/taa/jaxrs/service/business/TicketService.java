package fr.istic.taa.jaxrs.service.business;

import fr.istic.taa.jaxrs.dao.business.TicketDAO;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketDTO;
import fr.istic.taa.jaxrs.service.generic.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class TicketService extends AbstractService<Long, Ticket> {

    private TicketDAO ticketDAO = new TicketDAO();

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
    public Ticket getTicketById(final Long id) {
		return findOne(id);
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

    /**
     * Get tickets by user id.
     * @param userId user id
     * @return List of TicketDTO
     */
    public List<TicketDTO> getTicketsByUserId(final Long userId) {

        List<Ticket> tickets = ticketDAO.findByUserId(userId);
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketDTOs.add(new TicketDTO(ticket));
        }
        return ticketDTOs;
    }

    /**
     * Create a ticket.
     * @param ticket ticket to create
     */
    public void createTicket(final Ticket ticket) {
        save(ticket);
    }

    /**
     * Update a ticket.
     * @param ticket ticket to update
     */
    public void updateTicket(final Ticket ticket) {
        update(ticket);
    }

    /**
     * Delete a ticket.
     * @param ticket ticket to delete
     */
    public void deleteTicket(final Ticket ticket) {
        delete(ticket);
    }

    /**
     * Update user id of a ticket.
     * @param ticket ticket to update
     * @param userId user id
     */
    public void updateUserId(final Ticket ticket, final Long userId) {
        ticketDAO.updateUserId(ticket, userId);
    }

}
