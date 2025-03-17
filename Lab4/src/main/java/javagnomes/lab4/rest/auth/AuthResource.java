package javagnomes.lab4.rest.auth;

import jakarta.ejb.EJB;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import javagnomes.lab4.exceptions.UsernameException;
import javagnomes.lab4.managers.AuthenticationManager;

@Path("/auth")
@Produces("application/json")
public class AuthResource {
    @EJB
    private AuthenticationManager authenticationManager;

    @POST
    @Path("/login")
    public Response login(@NotNull(message = "Missing auth information") @Valid AuthRequest authRequest) {
        return Response.ok(new AuthResponse(authenticationManager.login(authRequest.getUsername(), authRequest.getPassword()))).build();

    }

    @POST
    @Path("/register")
    public Response register(@NotNull(message = "Missing auth information") @Valid AuthRequest authRequest) {
        try{
            authenticationManager.registerUser(authRequest.getUsername(), authRequest.getPassword());
            return Response.ok().build();
        } catch (UsernameException e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
