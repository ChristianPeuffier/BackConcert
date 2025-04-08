package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.domain.StatutTicket;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import fr.istic.taa.jaxrs.dto.TicketDTO;
import fr.istic.taa.jaxrs.dto.UtilisateurDTO;
import fr.istic.taa.jaxrs.service.business.EvenementService;
import fr.istic.taa.jaxrs.service.business.TicketService;
import fr.istic.taa.jaxrs.service.business.UtilisateurService;
import fr.istic.taa.jaxrs.utils.TicketToPDF;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.core.SecurityContext;
import javassist.bytecode.ByteArray;

import java.io.ByteArrayOutputStream;
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
        Ticket ticket = ticketService.getTicketById(id);
        if(ticket == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Ticket not found").build();
        }
        return Response.ok().entity(ticket).build();
    }

    /**
     * Get tickets by user id. Get request at /ticket/user/{id}.
     * @param id User id.
     * @return List of tickets.
     */
    @GET
    @Path("/user/{id}")
    @Produces("application/json")
    public Response getTicketsByUserId(@PathParam("id") final Long id)  {
        System.out.println("id du user : " + id);
        List<TicketDTO> tickets = ticketService.getTicketsByUserId(id);
        if(tickets == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Tickets not found").build();
        }
        return Response.status(Response.Status.OK).entity(tickets).build();
    }

    /**
     * Create a ticket. Post request at /ticket/.
     * @param ticket Ticket to create.
     * @return Response.
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/add/{nbBuy}")
    public Response addTicket(@Parameter(description = "Ticket to create", required = true) final Ticket ticket, @PathParam("nbBuy") final int nbBuy, @Context SecurityContext securityContext) {
        ticket.setStatut(StatutTicket.ACHETE);

        final EvenementService evenementService = new EvenementService();
        Evenement evenement =  new Evenement();

        try {
            System.out.println("Création du ticket...");
            ticketService.save(ticket);
            System.out.println("id du ticket : " + ticket.getId());

           evenement.setId((int) evenementService.getEvenementById(ticket.getEvenement().getId()).getIdEvenement());
           evenementService.updateNbSold(evenement, nbBuy );

            ByteArrayOutputStream pdfStream = TicketToPDF.generateTicketPdf(ticket);
            return Response.ok(pdfStream.toByteArray())
                    .type("application/pdf")
                    .header("Content-Disposition", "attachment; filename=ticket_" + ticket.getId() + ".pdf")
                    .build();

        } catch (Exception e) {
            return Response.serverError().entity("Erreur lors de la création du ticket et de son PDF").build();
        }

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

    /**
     * Change the user of a ticket. Post request at /ticket/{id}/user/{email}.
     * @param newUserEmail New user email.
     */
    @POST
    @Path("/update/{id}/user/{userId}")
    @Consumes("application/json")
    public Response changeTicketUser(@PathParam("id") final long idTicket, @PathParam("userId") final String newUserEmail, @Context SecurityContext securityContext) {
        Ticket ticket = ticketService.getTicketById(idTicket);
        UtilisateurService userService = new UtilisateurService();
        UtilisateurDTO newUser = userService.getUtilisateurByEmail(newUserEmail);

        if(ticket == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Ticket not found").build();
        }
        ticketService.updateUserId(ticket, newUser.getIdUtilisateur());
        return Response.status(Response.Status.OK).entity("Ticket user updated").build();
    }
}
