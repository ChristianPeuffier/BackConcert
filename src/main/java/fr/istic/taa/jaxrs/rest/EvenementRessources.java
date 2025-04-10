package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.domain.Organisateur;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import fr.istic.taa.jaxrs.dto.EvenementDTO;
import fr.istic.taa.jaxrs.dto.UtilisateurDTO;
import fr.istic.taa.jaxrs.service.business.EvenementService;
import fr.istic.taa.jaxrs.service.business.UtilisateurService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.core.SecurityContext;

import java.util.Collections;
import java.util.List;

@Path("evenement")
@Produces({"application/json"})
public class EvenementRessources {

    /**
     * The service to interact with the events.
     */
    private final EvenementService evenementService = new EvenementService();

    /**
     * Get all the events. Get request at /evenement/ path.
     * @return the list of events
     */
    @GET
    @Path("/")
    public List<EvenementDTO> getEvenements()  {
        return evenementService.getAllEvenements();
    }

    /**
     * Get an event by its id. Get request at /evenement/{id} path.
     * @param id the id of the event
     * @return the event
     */
    @GET
    @Path("/{id}")
    public EvenementDTO getEvenementById(@PathParam("id") final Long id)  {
        return evenementService.getEvenementById(id);
    }

    /**
     * Add an event. Post request at /evenement/ path.
     * @param event the event to add
     * @return the response
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/add")
    public Response addEvenement(
            @Parameter(description = "User object that needs to be added to the store", required = true) final Evenement event,
            @Context SecurityContext securityContext)
    {
        if(securityContext.isUserInRole("organisateur")){
            String email = securityContext.getUserPrincipal().getName();
            UtilisateurService utilisateurService = new UtilisateurService();
            Utilisateur user = utilisateurService.getUtilisateurByEmail(email);

            if (user instanceof Organisateur){
                Organisateur org = (Organisateur)user;
                event.setOrganisateur(org);
            }

            evenementService.save(event);
            return Response.status(Response.Status.CREATED).entity(Collections.singletonMap("message","Evénement Créé")).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(Collections.singletonMap("message","Vous n'avez pas le droit de créer un événement")).build();
    }

    /**
     * Update an event. Post request at /evenement/update path.
     * @param event the event to update
     * @return the response
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public Response updateEvenement(
        @Parameter(description = "User object that needs to be added to the store", required = true) final Evenement event,
        @Context SecurityContext securityContext)
    {
        System.out.println(event.getId());
        if(securityContext.isUserInRole("organisateur")){
            String email = securityContext.getUserPrincipal().getName();
            UtilisateurService utilisateurService = new UtilisateurService();
            Utilisateur user = utilisateurService.getUtilisateurByEmail(email);

            if (user instanceof Organisateur){
                Organisateur org = (Organisateur)user;
                event.setOrganisateur(org);
            }
        }
        evenementService.update(event);
        return Response.status( Response.Status.OK).entity(Collections.singletonMap("message","Evénement modifié")).build();
    }

    /**
     * Delete an event. Delete request at /evenement/{id} path.
     * @param id the id of the event to delete
     * @return the response
     */
    @DELETE
    @Path("/delete/{id}")
    public Response deleteEvenement(@PathParam("id") final Long id, @Context SecurityContext securityContext)  {
        if(securityContext.isUserInRole("organisateur")){
        Evenement event = evenementService.findOne(id);
        if (event == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Event not found").build();
        }
        evenementService.delete(event);
        return Response.status(Response.Status.OK).build();}
        return Response.status(Response.Status.FORBIDDEN).entity(Collections.singletonMap("message","Vous n'avez pas le droit de supprimer un événement")).build();
    }

    /**
     * Get all the events of an organizer. Get request at /evenement/organisateur/{id} path.
     * @param id the id of the organizer
     * @return the list of events
     */
    @GET
    @Path("/organisateur/{id}")
    public Response getOrganisateur(@PathParam("id") final Long id)  {
        List<EvenementDTO> evenements = evenementService.getEvenementByOrganisateur(id);
        if(evenements == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Evenements not found").build();
        }
        return Response.status(Response.Status.OK).entity(evenements).build();
    }

}
