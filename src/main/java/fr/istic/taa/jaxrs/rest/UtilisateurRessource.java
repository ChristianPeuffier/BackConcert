package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.business.UtilisateurDAO;
import fr.istic.taa.jaxrs.domain.Pet;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import fr.istic.taa.jaxrs.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.hibernate.service.spi.InjectService;

import java.util.List;

@Path("utilisateur")
@Produces({"application/json"})
public class UtilisateurRessource {

  UtilisateurService utilisateurService = new UtilisateurService( );


  @GET
  @Path("/{id}")
  public Utilisateur getUserById(@PathParam("id") Long id)  {
      return utilisateurService.findOne(id);
  }

  @GET
  @Path("/")
  public Iterable<Utilisateur> getUser()  {
      return utilisateurService.findAll();
  }

  
  @POST
  @Consumes("application/json")
  public Response addUser(
      @Parameter(description = "User object that needs to be added to the store", required = true) Utilisateur user) {
            utilisateurService.save(user);
    return Response.ok().entity("SUCCESS").build();
  }
}