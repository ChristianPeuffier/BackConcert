package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.business.OrganisateurDAO;
import fr.istic.taa.jaxrs.domain.Organisateur;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("organisateur")
@Produces({"application/json"})
public class OrganisateurRessources {

    /**
     * The organisteur DAO.
     */
    private final OrganisateurDAO organisateurDAO = new OrganisateurDAO();

    /**
     * Get all organisateurs. Get request with path "/organisateur".
     * @return the list of organisateurs
     */
    @GET
    @Path("/")
    public List<Organisateur> getOrganisateur()  {
        return organisateurDAO.findAll();
    }

    /**
     * Get an organisateur by its id. Get request with path "/organisateur/{id}".
     * @param id the id of the organisateur
     * @return the organisateur
     */
    @GET
    @Path("/{id}")
    public Organisateur getOrganisateurById(final Long id) {
        return organisateurDAO.findOne(id);
    }

    /**
     * Add an organisateur. Post request with path "/organisateur".
     * @param org the organisateur to add
     * @return the response
     */
    @POST
    @Consumes
    public Response addOrganisateur(@Parameter(description = "User object that needs to be added to the store", required = true) final Organisateur org) {
        return Response.ok().entity("SUCCESS").build();
    }
}
