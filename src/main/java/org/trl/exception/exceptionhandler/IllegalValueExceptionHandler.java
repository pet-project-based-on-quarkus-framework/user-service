package org.trl.exception.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trl.exception.IllegalValueException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class IllegalValueExceptionHandler implements ExceptionMapper<IllegalValueException> {

    private static final Logger LOG = LoggerFactory.getLogger(IllegalValueException.class);

    @Override
    public Response toResponse(IllegalValueException exception) {

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
