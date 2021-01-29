package org.trl.api.v1.resource;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trl.config.ApiVersion;
import org.trl.exception.UserNotFoundException;
import org.trl.model.dto.UserDto;
import org.trl.repository.entity.UserEntity;
import org.trl.service.UserService;
import org.trl.service.converter.UserConverter;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.time.temporal.ChronoUnit;

/**
 * This class is designed to support controller layout for {@literal UserDTO}.
 *
 * @author Tsyupryk Roman
 */
@Tag(name = "User Resource", description = "User object maintenance.")
@Path(value = UserController.BASE_URL)
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    public static final String BASE_URL = ApiVersion.VERSION_1_0 + "/users";

    private static final int FAULT_TOLERANCE_MAX_RETRIES = 4;

    private static final int FAULT_TOLERANCE_TIMEOUT = 200;

    private final UserService userService;

    private final UserConverter converter;

    @Context
    private UriInfo uriInfo;

    @Inject
    public UserController(UserService userService, UserConverter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    /**
     * Create user.
     *
     * @param user must not be equals to {@literal null}.
     */
    @Metered(description = "Metrics for creation user.")
    @Timed(description = "Metrics to monitor the time of creation user.", unit = MetricUnits.MILLISECONDS, absolute = true)
    @Retry(maxRetries = FAULT_TOLERANCE_MAX_RETRIES, retryOn = RuntimeException.class)
    @Timeout(value = FAULT_TOLERANCE_TIMEOUT, unit = ChronoUnit.MILLIS)
    @Operation(summary = "Create user.", description = "Create user. User fields must be filled out correctly.")
    @APIResponse(responseCode = "201", description = "User created.")
    @APIResponse(responseCode = "422", description = "Illegal user value.")
    @APIResponse(responseCode = "422", description = "User contains illegal fields value.")
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserDto user) {

        userService.create(user);

        return Response.status(201).build();
    }

    /**
     * Get a user by id.
     *
     * @param id must not be equals to {@literal null}, and must be greater than zero.
     * @return the {@literal Response.ok(UserDTO)} with the given id.
     */
    @Metered(description = "Metrics for retrieve user.")
    @Timed(description = "Metrics to monitor the time of retrieve user.", unit = MetricUnits.MILLISECONDS, absolute = true)
    @Retry(maxRetries = FAULT_TOLERANCE_MAX_RETRIES, retryOn = RuntimeException.class, abortOn = {UserNotFoundException.class, IllegalArgumentException.class})
    @Timeout(value = FAULT_TOLERANCE_TIMEOUT, unit = ChronoUnit.MILLIS)
    @Operation(summary = "Get user.", description = "Get user by ID.")
    @APIResponse(responseCode = "200", description = "Everything fine.")
    @APIResponse(responseCode = "400", description = "Illegal path parameter user id value.")
    @APIResponse(responseCode = "404", description = "User not found by id.")
    @APIResponse(description = "User", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserDto.class)))
    @GET
    @Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(
            @PathParam("id")
            @Min(value = 1, message = "userId must be greater than or equal to 1") Long id) {
        LOG.debug("Received GET request to retrieve partner task comments, request URI:[{}]", uriInfo.getRequestUri());

        UserEntity resultService = userService.get(id);

        UserDto userResultDTO = converter.mapEntityToDto(resultService);

        return Response.ok(userResultDTO).build();
    }

    /**
     * Update a user by id.
     *
     * @param id   must not be equals to {@literal null}, and must be greater than zero.
     * @param user must not be equals to {@literal null}.
     * @return the {@literal Response.ok(UserDTO)} with the given id.
     */
    @Metered(description = "Metrics for updating user.")
    @Timed(description = "Metrics to monitor the time of updating user.", unit = MetricUnits.MILLISECONDS, absolute = true)
    @Retry(maxRetries = FAULT_TOLERANCE_MAX_RETRIES, retryOn = RuntimeException.class)
    @Timeout(value = FAULT_TOLERANCE_TIMEOUT, unit = ChronoUnit.MILLIS)
    @Operation(summary = "Update user.", description = "Update user by ID.")
    @APIResponse(responseCode = "200", description = "User updated.")
    @APIResponse(responseCode = "422", description = "Illegal ID value. Or illegal user value.")
    @APIResponse(responseCode = "404", description = "User not found by this ID.")
    @APIResponse(description = "User", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserDto.class)))
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, UserDto user) {
        Response response = null;

        UserDto resultService = userService.update(id, user);

        response = Response.ok(resultService).build();

        return response;
    }

    /**
     * Delete a user by id.
     *
     * @param id must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @return the {@literal Response.status(204)}
     */
    @Metered(description = "Metrics for deleting user.")
    @Timed(description = "Metrics to monitor the time of deleting user.", unit = MetricUnits.MILLISECONDS, absolute = true)
    @Retry(maxRetries = FAULT_TOLERANCE_MAX_RETRIES, retryOn = RuntimeException.class)
    @Timeout(value = FAULT_TOLERANCE_TIMEOUT, unit = ChronoUnit.MILLIS)
    @Operation(summary = "Delete user.", description = "Delete user by ID.")
    @APIResponse(responseCode = "204", description = "User deleted.")
    @APIResponse(responseCode = "422", description = "Illegal ID value.")
    @APIResponse(responseCode = "404", description = "User not found by this ID.")
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Response response = null;

        userService.delete(id);

        response = Response.noContent().build();

        return response;
    }
}
