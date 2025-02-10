package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.business.TicketDAO;
import fr.istic.taa.jaxrs.domain.Ticket;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@Path("ticket")
@Produces({"application/json"})
public class TicketRessources {

    TicketDAO ticketDAO = new TicketDAO();

    @GET
    @Path("/")
    public List<Ticket> getTicket()  {
        return ticketDAO.findAll();
    }

    @GET
    @Path("/{id}")
    public Ticket getTicketById(Long id)  {
        return ticketDAO.findOne(id);
    }

    @POST
    @Consumes("application/json")
    public Response addTicket( @Parameter(description = "User object that needs to be added to the store", required = true) Ticket ticket) {
        return Response.ok().entity("SUCCESS").build();
    }
}
