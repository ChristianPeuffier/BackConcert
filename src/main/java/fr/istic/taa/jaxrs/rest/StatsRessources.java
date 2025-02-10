package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.business.StatsDAO;
import fr.istic.taa.jaxrs.domain.Stats;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("stats")
@Produces({"application/json"})
public class StatsRessources {

    StatsDAO statsDAO = new StatsDAO();

    @GET
    @Path("/")
    public List<Stats> getStats()  {
        return statsDAO.findAll();
    }

    @GET
    @Path("/{id}")
    public Stats getStatsById(Long id) {
        return statsDAO.findOne(id);
    }

    @POST
    @Consumes
    public Response addStats(@Parameter(description = "User object that needs to be added to the store", required = true) Stats stats) {
        return Response.ok().entity("SUCCESS").build();
    }
}
