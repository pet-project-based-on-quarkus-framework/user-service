package org.trl.api.v1.resource.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trl.exception.DataNotFoundException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.HttpURLConnection;

@Provider
public class DataNotFoundExceptionHandler implements ExceptionMapper<DataNotFoundException> {

    private static final Logger LOG = LoggerFactory.getLogger(DataNotFoundExceptionHandler.class);

    @Override
    public Response toResponse(DataNotFoundException exception) {

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
