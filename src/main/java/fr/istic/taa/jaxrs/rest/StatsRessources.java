package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.business.StatsDAO;
import fr.istic.taa.jaxrs.domain.Stats;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("stats")
@Produces({"application/json"})
public class StatsRessources {

    /**
     * Stats DAO.
     */
    private final StatsDAO statsDAO = new StatsDAO();

    /**
     * Get all stats. Get request to /stats/.
     * @return all stats
     */
    @GET
    @Path("/")
    public List<Stats> getStats()  {
        return statsDAO.findAll();
    }

    /**
     * Get stats by id. Get request to /stats/{id}.
     * @param id the id
     * @return the stats
     */
    @GET
    @Path("/{id}")
    public Stats getStatsById(final Long id) {
        return statsDAO.findOne(id);
    }

    /**
     * Add stats. Post request to /stats/.
     * @param stats the stats
     * @return the response
     */
    @POST
    @Consumes
    public Response addStats(@Parameter(description = "User object that needs to be added to the store", required = true) final Stats stats) {
        return Response.ok().entity("SUCCESS").build();
    }
}
