package org.trl.exception.exception_handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trl.exception.UserNotFoundException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.HttpURLConnection;

@Provider
public class UserNotFoundExceptionHandler implements ExceptionMapper<UserNotFoundException> {

    private static final Logger LOG = LoggerFactory.getLogger(UserNotFoundExceptionHandler.class);

    @Override
    public Response toResponse(UserNotFoundException exception) {

        LOG.error("Failed to handle request", exception);

        int statusCode = HttpURLConnection.HTTP_NOT_FOUND;

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
