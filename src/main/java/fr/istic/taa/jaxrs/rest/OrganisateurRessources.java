package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.business.OrganisateurDAO;
import fr.istic.taa.jaxrs.domain.Organisateur;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("organisateur")
@Produces({"application/json"})
public class OrganisateurRessources {

    OrganisateurDAO organisateurDAO = new OrganisateurDAO();

    @GET
    @Path("/")
    public List<Organisateur> getOrganisateur()  {
        return organisateurDAO.findAll();
    }

    @GET
    @Path("/{id}")
    public Organisateur getOrganisateurById(Long id) {
        return organisateurDAO.findOne(id);
    }

    @POST
    @Consumes
    public Response addOrganisateur(@Parameter(description = "User object that needs to be added to the store", required = true) Organisateur org) {
        return Response.ok().entity("SUCCESS").build();
    }
}
