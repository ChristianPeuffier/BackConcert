package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketDTO;
import fr.istic.taa.jaxrs.service.business.TicketService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@Path("ticket")
@Produces({"application/json"})
public class TicketRessources {

    TicketService ticketService = new TicketService( );

    @GET
    @Path("/")
    public List<TicketDTO> getTicket()  {
        return ticketService.getAllTickets();
    }

    @GET
    @Path("/{id}")
    public TicketDTO getTicketById(Long id)  {
        return ticketService.getTicketById(id);
    }

    @POST
    @Consumes("application/json")
    public Response addTicket( @Parameter(description = "User object that needs to be added to the store", required = true) Ticket ticket) {
        ticketService.save(ticket);
        return Response.ok().entity("SUCCESS").build();
    }
}
