package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Utilisateur;
import fr.istic.taa.jaxrs.dto.UtilisateurDTO;
import fr.istic.taa.jaxrs.service.business.UtilisateurService;
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

  UtilisateurService utilisateurService = new UtilisateurService( );


  @GET
  @Path("/{id}")
  public UtilisateurDTO getUserById(@PathParam("id") Long id)  {
      return utilisateurService.getUtilisateurById(id);
  }

  @GET
  @Path("/")
  public List<UtilisateurDTO> getUser()  {
      return utilisateurService.getAllUtilisateurs();
  }

  
  @POST
  @Consumes("application/json")
  public Response addUser(
      @Parameter(description = "User object that needs to be added to the store", required = true) Utilisateur user) {
            utilisateurService.save(user);
    return Response.ok().entity("SUCCESS").build();
  }
}