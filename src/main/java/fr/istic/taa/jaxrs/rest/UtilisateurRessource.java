package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Utilisateur;
import fr.istic.taa.jaxrs.dto.UtilisateurDTO;
import fr.istic.taa.jaxrs.service.business.UtilisateurService;
import fr.istic.taa.jaxrs.utils.AuthResponse;
import fr.istic.taa.jaxrs.utils.TokenUtil;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.Collections;
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
   * Get the userDTO by email.
   * @return the userDTO
   */
    @GET
    @Path("/email")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByEmail(@Context SecurityContext securityContext){
      String email = securityContext.getUserPrincipal().getName();

        UtilisateurDTO user = utilisateurService.getUtilisateurByEmail(email);

        if(user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Utilisateur non trouvé").build();
        }
        return Response.ok(user).build();
    }

  /**
     * Get the user's list.
     * @return the user's list
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
  @Path("add")
  public Response addUser(@Parameter(description = "User object that needs to be added to the store", required = true) final Utilisateur user) {
    try {
      if (user.getEmail() == null || user.getPassword() == null) {
        return Response.status(Response.Status.BAD_REQUEST).entity("Email et mot de passe requis").build();
      } else if (user.getNom() == null) {
        return Response.status(Response.Status.BAD_REQUEST).entity("Nom requis").build();
      } else if (user.getPrenom() == null) {
        return Response.status(Response.Status.BAD_REQUEST).entity("Prénom requis").build();
      }
      String emailValidationResult = utilisateurService.saveUser(user);
      if (emailValidationResult.equals("L'email est déjà utilisé")) {
        return Response.status(Response.Status.BAD_REQUEST).entity("L'email est déjà utilisé").build();
      }
      return Response.status(Response.Status.CREATED)
              .entity(Collections.singletonMap("message", "Utilisateur créé avec succès"))
              .build();

    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la création de l'utilisateur").build();
    }
  }

  @POST
  @Path("/login")
  @Consumes("application/json")
  @Produces("application/json")
  public Response login(@Parameter(description = "User login credentials", required = true) final Utilisateur user ){
    try {
      if(user.getEmail() == null || user.getPassword() == null) {
        return Response.status(Response.Status.BAD_REQUEST).entity("Email et mot de passe requis").build();
      }
      UtilisateurDTO userFound = utilisateurService.getUtilisateurByEmail(user.getEmail());
        if(userFound == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Utilisateur non trouvé").build();
        }
        boolean passwordMatch = utilisateurService.checkPassword(user.getEmail(), user.getPassword());
        if(!passwordMatch) {
          return Response.status(Response.Status.BAD_REQUEST).entity("Mot de passe incorrect").build();
        }
        System.out.println("good");
        String token = TokenUtil.generateToken(userFound.getEmail(), "utilisateur");
        System.out.println("token: " + token);
      return Response.status(Response.Status.OK).entity(new AuthResponse(token)).build();
    }
    catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la connexion").build();
    }
  }

  @GET
  @Path("/secure")
  @Produces("application/json")
  public Response getSecureData(@Context SecurityContext securityContext) {

    if (!securityContext.isUserInRole("utilisateur")) {
      return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"Accès refusé !\"}").build();
    }
    return Response.ok( utilisateurService.getAllUtilisateurs()).build();
  }
}
