package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.business.EvenementDAO;
import fr.istic.taa.jaxrs.domain.Evenement;
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
public class EvenementRssources {
    EvenementDAO evenementDAO = new EvenementDAO();

    @GET
    @Path("/")
    public List<Evenement> getEvenement()  {
        return evenementDAO.findAll();
    }

    @GET
    @Path("/{id}")
    public Evenement getEvenementById(@PathParam("id") Long id)  {
        return evenementDAO.findOne(id);
    }

    @POST
    @Consumes("application/json")
    public Response addEvenement(
        @Parameter(description = "User object that needs to be added to the store", required = true) Evenement event) {
            return Response.ok().entity("SUCCESS").build();
        }

}
