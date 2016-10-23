package ch.heigvd.amt.app01.rest.resources;

import ch.heigvd.amt.app01.models.User;
import ch.heigvd.amt.app01.rest.dto.*;
import ch.heigvd.amt.app01.services.UserManagerLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 13.10.16 Faire plus de tests sur Postman

/**
 * Class defining all the Rest endpoints for the user resource under the (/api)/users prefix.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
@Stateless
@Path("/users")
public class UsersResource {

    @EJB
    private UserManagerLocal userManager;

    @Context
    private UriInfo uriInfo;

    // TODO: 12.10.16 ajouter url dans les détails d'un utilisateur

    /**
     * GET /api/users/
     *
     * Get the list of all users of the app.
     *
     * @return the list of users for presentation.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserPresentationDTO> getUsers() {
        List<User> users = userManager.findAll();
        return users.stream()
                .map(UsersResource::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * GET /api/users/{id}
     *
     * Get a particular user by his ID.
     *
     * @param id the id of the user.
     * @return the user for presentation or HTTP 404 code if the id doesn't exists.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") long id) {
        User user = userManager.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // HTTP 404 NOT FOUND
        }
        return Response.ok(toDTO(user)).build(); // HTTP 200 OK
    }

    /**
     * GET /api/users/{username}
     *
     * Get a particular user by his username.
     *
     * @param username the username of the user.
     * @return the user for presentation or HTTP 404 code if the id doesn't exists.
     */
    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) {
        User user = userManager.findByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // HTTP 404 NOT FOUND
        }
        return Response.ok(toDTO(user)).build(); // HTTP 200 OK
    }

    /**
     * POST /api/users/
     *
     * Create a new user.
     *
     * Validation rules and error code :
     * - The username, password and password control must be provided (HTTP 422 UNPROCESSABLE ENTITY).
     * - Username can be already registered (HTTP 409 CONFLICT).
     * - The password and the password control must be the same (HTTP 422 UNPROCESSABLE ENTITY).
     *
     * @param userDTO the information of the user.
     * @return the HTTP 201 code (creation confirmation) or HTTP 422/409 code if the validation failed.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(UserCreationDTO userDTO) {

        // Validation rules
        if (userDTO.getUsername().isEmpty() || userDTO.getPassword().isEmpty()) {
            return Response.status(422).entity(new ApiErrorDTO("Provide at least an username and a password")).build(); // HTTP 422 UNPROCESSABLE ENTITY
        }
        if (!userDTO.getPassword().equals(userDTO.getPasswordCtrl())) {
            return Response.status(422).entity(new ApiErrorDTO("The two passwords must be identical")).build(); // HTTP 422 UNPROCESSABLE ENTITY
        }
        if (userManager.findByUsername(userDTO.getUsername()) != null) {
            return Response.status(Response.Status.CONFLICT).entity(new ApiErrorDTO("This username is not available")).build(); // HTTP 409 CONFLICT
        }

        // Create and save the new user
        User user = toDAO(userDTO);
        userManager.save(user);

        // Generate URL for the user resource and create response
        URI href = uriInfo
                .getBaseUriBuilder()
                .path(UsersResource.class)
                .path(UsersResource.class, "getUser")
                .build(user.getId());
        return Response.created(href).build(); // HTTP 201 CREATED
    }

    /**
     * PUT /api/users/{id}
     *
     * Edit an existing user.
     *
     * Validation rules and error code :
     * - The id must exists (HTTP 404 NOT FOUND).
     * - The username, password and password control must be provided (HTTP 422 UNPROCESSABLE ENTITY).
     * - Username can be already registered (HTTP 409 CONFLICT).
     * - The password and the password control must be the same (HTTP 422 UNPROCESSABLE ENTITY).
     *
     * @param id the id of the modified user.
     * @param userDTO the information of the user.
     * @return the new user for presentation or HTTP 404/422/409 code if the validation failed.
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("id") long id, UserCreationDTO userDTO) {

        // Get the current saved user
        User user = userManager.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // HTTP 404 NOT FOUND
        }

        // Validation rules
        if (userDTO.getUsername().isEmpty() || userDTO.getPassword().isEmpty()) {
            return Response.status(422).entity(new ApiErrorDTO("Provide at least an username and a password")).build(); // HTTP 422 UNPROCESSABLE ENTITY
        }
        if (!userDTO.getPassword().equals(userDTO.getPasswordCtrl())) {
            return Response.status(422).entity(new ApiErrorDTO("The two passwords must be identical")).build(); // HTTP 422 UNPROCESSABLE ENTITY
        }
        if (userManager.findByUsername(userDTO.getUsername()) != null) {
            return Response.status(Response.Status.CONFLICT).entity(new ApiErrorDTO("This username is not available")).build(); // HTTP 409 CONFLICT
        }

        // Update the user
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userManager.save(user);

        // Send confirmation response with the new user information
        return Response.ok(toDTO(user)).build(); // HTTP 200 OK
    }

    /**
     * DELETE /api/users/{id}
     *
     * Delete an existing user.
     *
     * @param id the id of the user to delete.
     * @return the HTTP 204 code for confirmation without content or HTTP 404 code if the id doesn't exists.
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") long id) {

        // Get the current saved user
        User user = userManager.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // HTTP 404 NOT FOUND
        }

        // Delete the user and send confirmation response
        userManager.delete(user);
        return Response.noContent().build(); // HTTP 204 NO CONTENT
    }

    /**
     * Utility method that allow to create a presentation user DTO for the user entity.
     *
     * @param user the user entity to transform.
     * @return the presentation user DTO for serialization.
     */
    private static UserPresentationDTO toDTO(User user) {
        return new UserPresentationDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername());
    }

    /**
     * Utility method that allow to user entity from a user creation DTO.
     *
     * @param userDTO the user DTO to transform.
     * @return the user entity created.
     */
    private static User toDAO(UserCreationDTO userDTO) {
        return new User(userDTO.getFirstname(), userDTO.getLastname(), userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword());
    }
}