package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.StatutTicket;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketDTO;
import fr.istic.taa.jaxrs.service.business.TicketService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.core.SecurityContext;

import java.util.Collection;
import java.util.Collections;
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
    public Response getTicket()  {
        List<TicketDTO> tickets = ticketService.getAllTickets();
        return Response.ok().entity(tickets).build();
    }

    /**
     * Get ticket by id. Get request at /ticket/{id}.
     * @param id Ticket id.
     * @return Ticket.
     */
    @GET
    @Path("/{id}")
    public Response getTicketById(final Long id)  {
        TicketDTO ticket = ticketService.getTicketById(id);
        if(ticket == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Ticket not found").build();
        }
        return Response.ok().entity(ticket).build();
    }

    /**
     * Create a ticket. Post request at /ticket/.
     * @param ticket Ticket to create.
     * @return Response.
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/add")
    public Response addTicket(@Parameter(description = "Ticket to create", required = true) final Ticket ticket, @Context SecurityContext securityContext) {
        ticket.setStatut(StatutTicket.ACHETE);
        ticketService.save(ticket);
        return Response.status(Response.Status.CREATED).entity(Collections.singletonMap("ticket", ticket)).build();
    }

    /**
     * Update a ticket. Post request at /ticket/.
     * @param ticket Ticket to update.
     * @return Response.
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateTicket(@PathParam("id") final int id, final Ticket ticket) {
        ticket.setId(id);
        ticketService.updateTicket(ticket);
        return Response.status(Response.Status.OK).entity(ticket).build();
    }

    /**
     * Delete a ticket. Delete request at /ticket/{id}.
     * @param id Ticket id.
     * @return Response.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteTicket(@PathParam("id") final long id) {
        Ticket ticket = ticketService.findOne(id);
        if(ticket == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Ticket not found").build();
        }
        ticketService.deleteTicket(ticket);
        return Response.status(Response.Status.OK).entity("Ticket deleted").build();
    }
}
