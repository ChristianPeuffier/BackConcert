package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketDTO;
import fr.istic.taa.jaxrs.service.business.TicketService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@Path("ticket")
@Produces({"application/json"})
public class TicketRessources {

    /**
     * Ticket service.
     */
    private final TicketService ticketService = new TicketService();

    /**
     * Get all tickets. Get request at /ticket/.
     * @return List of tickets.
     */
    @GET
    @Path("/")
    public List<TicketDTO> getTicket()  {
        return ticketService.getAllTickets();
    }

    /**
     * Get ticket by id. Get request at /ticket/{id}.
     * @param id Ticket id.
     * @return Ticket.
     */
    @GET
    @Path("/{id}")
    public TicketDTO getTicketById(final Long id)  {
        return ticketService.getTicketById(id);
    }

    /**
     * Add a ticket. Post request at /ticket/.
     * @param ticket Ticket to add.
     * @return Response.
     */
    @POST
    @Consumes("application/json")
    public Response addTicket(@Parameter(description = "User object that needs to be added to the store", required = true) final Ticket ticket) {
        ticketService.save(ticket);
        return Response.ok().entity("SUCCESS").build();
    }
}
