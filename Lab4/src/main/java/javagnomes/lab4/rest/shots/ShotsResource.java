package javagnomes.lab4.rest.shots;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import javagnomes.lab4.managers.ShotEntitiesManager;
import javagnomes.lab4.modelObjects.ShotEntity;
import javagnomes.lab4.security.JwtManager;

@Path("/shots")
@Produces("application/json")
public class ShotsResource {
    @EJB
    private ShotEntitiesManager shotsManager;

    @GET
    @Consumes("application/json")
    public Response getShots(@HeaderParam("Authorization") String token) {
        System.out.println(token);
        String username = JwtManager.getUsernameFromToken(token);
        return Response.ok().entity(shotsManager.getAllShots(username)).build();
    }

    @POST
    @Consumes("application/json")
    public Response addShot(@HeaderParam("Authorization") String token, ShotsRequest shotsRequest) {
        String username = JwtManager.getUsernameFromToken(token);
        ShotEntity shot = new ShotEntity(shotsRequest.getX(),shotsRequest.getY(),shotsRequest.getR());
        shotsManager.addShot(shot, username);
        return Response.ok().build();
    }
}
