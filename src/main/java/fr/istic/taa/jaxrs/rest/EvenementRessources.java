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

    EvenementService evenementService = new EvenementService();

    @GET
    @Path("/")
    public List<EvenementDTO> getEvenements()  {
        return evenementService.getAllEvenements();
    }

    @GET
    @Path("/{id}")
    public EvenementDTO getEvenementById(@PathParam("id") Long id)  {
        return evenementService.getEvenementById(id);
    }

    @POST
    @Consumes("application/json")
    public Response addEvenement(
        @Parameter(description = "User object that needs to be added to the store", required = true) Evenement event) {
            evenementService.save(event);
            return Response.ok().entity("SUCCESS").build();
        }

}
