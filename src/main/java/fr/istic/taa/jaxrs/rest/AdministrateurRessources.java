package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.business.AdministrateurDAO;
import fr.istic.taa.jaxrs.domain.Administrateur;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("administrateur")
@Produces({"application/json"})
public class AdministrateurRessources {

    /**
     * AdministrateurDAO.
     */
    private final AdministrateurDAO administrateurDAO = new AdministrateurDAO();


    /**
     * Get all Administrateurs. GET request with path "/administrateur/".
     * @return List of Administrateurs
     */
    @GET
    @Path("/")
    public List<Administrateur> getAdministrateur()  {
        return administrateurDAO.findAll();
    }

    /**
     * Get an Administrateur by id. GET request with path "/administrateur/{id}"
     * @param id Administrateur id
     * @return Administrateur
     */
    @GET
    @Path("/{id}")
    public Administrateur getAdministrateurById(final Long id) {
        return administrateurDAO.findOne(id);
    }

    /**
     * Add an Administrateur. POST request with path "/administrateur/"
     * @param admin Administrateur object
     * @return Response
     */
    @POST
    @Consumes
    public Response addAdministrateur(@Parameter(description = "User object that needs to be added to the store", required = true) final Administrateur admin) {
        return Response.ok().entity("SUCCESS").build();
    }
}
