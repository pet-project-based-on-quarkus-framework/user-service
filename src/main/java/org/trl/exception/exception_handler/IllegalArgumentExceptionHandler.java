package org.trl.exception.exception_handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionHandler implements ExceptionMapper<IllegalArgumentException> {

    private static final Logger LOG = LoggerFactory.getLogger(IllegalArgumentExceptionHandler.class);

    @Override
    public Response toResponse(IllegalArgumentException exception) {

        LOG.error("Failed to handle request", exception);

        int statusCode = 422;

        JsonObjectBuilder entityBuilder = Json.createObjectBuilder()
                .add("exceptionType", exception.getClass().getName())
                .add("statusCode", statusCode);

        if (exception.getMessage() != null) {
            entityBuilder.add("exceptionMessage", exception.getMessage());
        }

        return Response.status(statusCode)
                .entity(entityBuilder.build())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
