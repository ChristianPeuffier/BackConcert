package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.dto.EvenementDTO;
import fr.istic.taa.jaxrs.service.business.EvenementService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.core.SecurityContext;

import java.util.Collections;
import java.util.List;

@Path("evenement")
@Produces({"application/json"})
public class EvenementRessources {

    /**
     * The service to interact with the events.
     */
    private final EvenementService evenementService = new EvenementService();

    /**
     * Get all the events. Get request at /evenement/ path.
     * @return the list of events
     */
    @GET
    @Path("/")
    public List<EvenementDTO> getEvenements()  {
        return evenementService.getAllEvenements();
    }

    /**
     * Get an event by its id. Get request at /evenement/{id} path.
     * @param id the id of the event
     * @return the event
     */
    @GET
    @Path("/{id}")
    public EvenementDTO getEvenementById(@PathParam("id") final Long id)  {
        return evenementService.getEvenementById(id);
    }

    /**
     * Add an event. Post request at /evenement/ path.
     * @param event the event to add
     * @return the response
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addEvenement(
            @Parameter(description = "User object that needs to be added to the store", required = true) final Evenement event,
            @Context SecurityContext securityContext)
    {
        evenementService.save(event);
        return Response.status(Response.Status.CREATED).entity(Collections.singletonMap("message","Evénement Créé")).build();
    }

    /**
     * Update an event. Post request at /evenement/update path.
     * @param event the event to update
     * @return the response
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public Response updateEvenement(
        @Parameter(description = "User object that needs to be added to the store", required = true) final Evenement event)
    {
        evenementService.update(event);
        return Response.ok().entity("SUCCESS").build();
    }

    /**
     * Delete an event. Delete request at /evenement/{id} path.
     * @param id the id of the event to delete
     * @return the response
     */
    @DELETE
    @Path("/{id}")
    public Response deleteEvenement(@PathParam("id") final Long id)  {
        Evenement event = evenementService.findOne(id);
        if (event == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Event not found").build();
        }
        evenementService.delete(event);
        return Response.ok().entity("SUCCESS").build();
    }

}
