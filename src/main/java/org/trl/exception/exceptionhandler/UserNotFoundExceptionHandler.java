package org.trl.exception.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trl.controller.dto.ApiErrorDto;
import org.trl.exception.UserNotFoundException;
import org.trl.service.DateTimeService;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.HttpURLConnection;

@Provider
public class UserNotFoundExceptionHandler implements ExceptionMapper<UserNotFoundException> {

    private static final Logger LOG = LoggerFactory.getLogger(UserNotFoundExceptionHandler.class);

    @Inject
    DateTimeService dateTimeService;

    @Override
    public Response toResponse(UserNotFoundException exception) {

        LOG.debug("Failed to handle request", exception);

        ApiErrorDto errorDto = new ApiErrorDto.Builder()
                .withTimestamp(dateTimeService.now())
                .withErrorMessage(exception.getMessage())
                .withPath("")
                .build();

        return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
                .entity(errorDto)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
