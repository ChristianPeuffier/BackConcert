package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.business.UtilisateurDAO;
import fr.istic.taa.jaxrs.domain.Pet;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("utilisateur")
@Produces({"application/json"})
public class UtilisateurRessource {

  UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

  @GET
  @Path("/{id}")
  public Utilisateur getUserById(@PathParam("id") Long id)  {
      return utilisateurDAO.findOne(id);
  }

  @GET
  @Path("/")
  public List<Utilisateur> getUser()  {
      return utilisateurDAO.findAll();
  }

  
  @POST
  @Consumes("application/json")
  public Response addUser(
      @Parameter(description = "User object that needs to be added to the store", required = true) Utilisateur user) {
    return Response.ok().entity("SUCCESS").build();
  }
}