package javagnomes.lab4.rest.exceptionMappers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;


@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response.status(BAD_REQUEST)
                .entity(violationsToJSON(exception))
                .type("application/json")
                .build();
    }

    private ExceptionResponse violationsToJSON(ConstraintViolationException exception) {
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            return new ExceptionResponse(violation.getMessage());
        }
        return null;
    }
}
