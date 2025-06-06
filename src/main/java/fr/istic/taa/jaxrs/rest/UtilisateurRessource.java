package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Utilisateur;
import fr.istic.taa.jaxrs.dto.UtilisateurDTO;
import fr.istic.taa.jaxrs.service.business.UtilisateurService;
import fr.istic.taa.jaxrs.utils.AuthResponse;
import fr.istic.taa.jaxrs.utils.TokenUtil;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
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
  public UtilisateurDTO getUserById(@PathParam("id") final Long id){

      Utilisateur user = utilisateurService.getUtilisateurById(id);

        if(user != null) {
            return new UtilisateurDTO(user);
        }
        return null;
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

        Utilisateur user = utilisateurService.getUtilisateurByEmail(email);
        if(user != null) {
            UtilisateurDTO userDTO = new UtilisateurDTO(user);
            return Response.ok(userDTO).build();
        }

		return Response.status(Response.Status.BAD_REQUEST).entity("Utilisateur non trouvé").build();
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

    /**
     * Login a user.
     * @param user the user
     * @return the response
     */
  @POST
  @Path("/login")
  @Consumes("application/json")
  @Produces("application/json")
  public Response login(@Parameter(description = "User login credentials", required = true) final Utilisateur user ){
    try {
      if(user.getEmail() == null || user.getPassword() == null) {
        return Response.status(Response.Status.BAD_REQUEST).entity("Email et mot de passe requis").build();
      }
      Utilisateur userFound = utilisateurService.getUtilisateurByEmail(user.getEmail());
        if(userFound == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Utilisateur non trouvé").build();
        }

        boolean passwordMatch = utilisateurService.checkPassword(user.getEmail(), user.getPassword());
        if(!passwordMatch) {
          return Response.status(Response.Status.BAD_REQUEST).entity("Mot de passe incorrect").build();
        }
        System.out.println("Role : " + userFound.getTypeUtilisateur());
        String token = TokenUtil.generateToken(userFound.getEmail(), userFound.getTypeUtilisateur());
      return Response.status(Response.Status.OK).entity(new AuthResponse(token, userFound.getId())).build();
    }
    catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la connexion").build();
    }
  }

  /**
   * Update a user role.
   * @param user the new user
   * @return the response
   */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/updateUser")
    public Response updateUser(
            @Parameter(description = "User object that needs to be updated", required = true) final UtilisateurDTO user,
            @Context SecurityContext securityContext) {
            if(securityContext.isUserInRole("administrateur")){
              System.out.println("id : " + user.getIdUtilisateur());
              Utilisateur userToUpdate = utilisateurService.getUtilisateurById(user.getIdUtilisateur());
              userToUpdate.setTypeUtilisateur(user.getRole());

                utilisateurService.updateUtilisateur(userToUpdate);
                return Response.status(Response.Status.OK).entity(Collections.singletonMap("messageUpdate", "Utilisateur mis à jour avec succès")).build();
            }

        return Response.status(Response.Status.FORBIDDEN).entity(Collections.singletonMap("messageForbidden", "Vous n'avez pas le droit de mettre à jour un utilisateur")).build();
    }

}
