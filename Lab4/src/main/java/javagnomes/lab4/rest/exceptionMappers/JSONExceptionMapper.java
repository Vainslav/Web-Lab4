package javagnomes.lab4.rest.exceptionMappers;

import jakarta.ejb.Singleton;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
@Singleton
public class JSONExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        return Response.status(BAD_REQUEST)
                .entity(new ExceptionResponse(exception.getCause().getMessage()))
                .type("application/json")
                .build();
    }
}
