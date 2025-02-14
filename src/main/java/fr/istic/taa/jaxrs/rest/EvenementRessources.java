package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.dto.EvenementDTO;
import fr.istic.taa.jaxrs.service.business.EvenementService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Parameter;

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
    @Consumes("application/json")
    public Response addEvenement(
        @Parameter(description = "User object that needs to be added to the store", required = true) final Evenement event) {
            evenementService.save(event);
            return Response.ok().entity("SUCCESS").build();
        }

}
