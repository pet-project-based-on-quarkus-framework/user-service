package org.trl.controller;

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
import org.trl.controller.dto.UserDTO;
import org.trl.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.temporal.ChronoUnit;

/**
 * This class is designed to support controller layout for {@literal UserDTO}.
 *
 * @author Tsyupryk Roman
 */
@Tag(name = "User Resource", description = "User object maintenance.")
@Path("/users")
public class UserController {

    private static final int FAULT_TOLERANCE_MAX_RETRIES = 4;
    private static final int FAULT_TOLERANCE_TIMEOUT = 100;

    private UserService userService;

    public UserController() {
    }

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
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
    public Response create(UserDTO user) {
        Response response = null;

        userService.create(user);

        response = Response.status(201).build();

        return response;
    }

    /**
     * Get a user by id.
     *
     * @param id must not be equals to {@literal null}, and must be greater than zero.
     * @return the {@literal Response.ok(UserDTO)} with the given id.
     */
    @Metered(description = "Metrics for retrieve user.")
    @Timed(description = "Metrics to monitor the time of retrieve user.", unit = MetricUnits.MILLISECONDS, absolute = true)
    @Retry(maxRetries = FAULT_TOLERANCE_MAX_RETRIES, retryOn = RuntimeException.class)
    @Timeout(value = FAULT_TOLERANCE_TIMEOUT, unit = ChronoUnit.MILLIS)
    @Operation(summary = "Get user.", description = "Get user by ID.")
    @APIResponse(responseCode = "200", description = "Everything fine.")
    @APIResponse(responseCode = "422", description = "Illegal ID value.")
    @APIResponse(responseCode = "404", description = "User not found by this ID.")
    @APIResponse(description = "User", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserDTO.class)))
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        Response response = null;

        UserDTO resultService = userService.get(id);

        response = Response.ok(resultService).status(200).build();

        return response;
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
    @APIResponse(description = "User", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserDTO.class)))
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, UserDTO user) {
        Response response = null;

        UserDTO resultService = userService.update(id, user);

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
