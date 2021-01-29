package org.trl.api.v1.resource.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trl.core.service.exception.UserNotFoundException;
import org.trl.core.service.DateTimeService;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.HttpURLConnection;

@Provider
public class UserNotFoundExceptionHandler implements ExceptionMapper<UserNotFoundException> {

    private static final Logger LOG = LoggerFactory.getLogger(UserNotFoundExceptionHandler.class);

    @Inject
    DateTimeService dateTimeService;

    @Context
    static UriInfo uriInfo;

    @Override
    public Response toResponse(UserNotFoundException exception) {

        LOG.debug("In handleUserNotFoundException - {}", exception.getMessage());

        return Response.status(HttpURLConnection.HTTP_NOT_FOUND).build();
    }

}
