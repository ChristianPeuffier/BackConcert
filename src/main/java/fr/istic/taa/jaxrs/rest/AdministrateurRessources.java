package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.business.AdministrateurDAO;
import fr.istic.taa.jaxrs.domain.Administrateur;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("administrateur")
@Produces({"application/json"})
public class AdministrateurRessources {

    AdministrateurDAO administrateurDAO = new AdministrateurDAO();

    @GET
    @Path("/")
    public List<Administrateur> getAdministrateur()  {
        return administrateurDAO.findAll();
    }

    @GET
    @Path("/{id}")
    public Administrateur getAdministrateurById(Long id) {
        return administrateurDAO.findOne(id);
    }

    @POST
    @Consumes
    public Response addAdministrateur(@Parameter(description = "User object that needs to be added to the store", required = true) Administrateur admin) {
        return Response.ok().entity("SUCCESS").build();
    }
}
