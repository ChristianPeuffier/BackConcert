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

  /**
   * The service for the user.
   */
  private final UtilisateurService utilisateurService = new UtilisateurService();


  /**
   * Get the user by id.
   * @param id the id
   * @return the user
   */
  @GET
  @Path("/{id}")
  public UtilisateurDTO getUserById(@PathParam("id") final Long id)  {
      return utilisateurService.getUtilisateurById(id);
  }

    /**
     * Get the user.
     * @return the user
     */
  @GET
  @Path("/")
  public List<UtilisateurDTO> getUser()  {
      return utilisateurService.getAllUtilisateurs();
  }

  /**
   * Add a user.
   * @param user the user
   * @return the response
   */
  @POST
  @Consumes("application/json")
  public Response addUser(@Parameter(description = "User object that needs to be added to the store", required = true) final Utilisateur user) {
            utilisateurService.save(user);
    return Response.ok().entity("SUCCESS").build();
  }
}
