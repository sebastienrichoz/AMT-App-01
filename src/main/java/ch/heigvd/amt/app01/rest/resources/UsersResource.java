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

@Stateless
@Path("/users")
public class UsersResource {

    @EJB
    private UserManagerLocal userManager;

    @Context
    private UriInfo uriInfo;

    // TODO: 12.10.16 ajouter id dans les détails d'un utilisateur
    // TODO: 12.10.16 ajouter url dans les détails d'un utilisateur

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserPresentationDTO> getUsers() {
        List<User> users = userManager.findAll();
        return users.stream()
                .map(UsersResource::userPresentationDTO)
                .collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") long id) {
        User user = userManager.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // HTTP 404 NOT FOUND
        }
        return Response.ok(userPresentationDTO(user)).build(); // HTTP 200 OK
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) {
        User user = userManager.findByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // HTTP 404 NOT FOUND
        }
        return Response.ok(userPresentationDTO(user)).build(); // HTTP 200 OK
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(UserCreationDTO userDTO) {

        // TODO: 12.10.16 VerificationService ?

        if (userDTO.getUsername().isEmpty() || userDTO.getPassword().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ApiErrorDTO("Provide at least an username and a password")).build(); // HTTP 400 BAD REQUEST
        }

        if (!userDTO.getPassword().equals(userDTO.getPasswordCtrl())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ApiErrorDTO("The two passwords must be identical")).build(); // HTTP 400 BAD REQUEST
        }

        if (userManager.findByUsername(userDTO.getUsername()) != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ApiErrorDTO("This username is not available")).build(); // HTTP 400 BAD REQUEST
        }

        User user = userDAO(userDTO);
        userManager.save(user);

        URI href = uriInfo
                .getBaseUriBuilder()
                .path(UsersResource.class)
                .path(UsersResource.class, "getUser")
                .build(user.getId());

        return Response.created(href).build(); // HTTP 201 CREATED
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("id") long id, UserCreationDTO userDTO) {
        User user = userManager.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // HTTP 404 NOT FOUND
        }

        // TODO: 12.10.16 VerificationService ?

        if (userDTO.getUsername().isEmpty() || userDTO.getPassword().isEmpty()) {
            return Response.status(422).entity(new ApiErrorDTO("Provide at least an username and a password")).build(); // HTTP 422 UNPROCESSABLE ENTITY (validation error)
        }

        if (!userDTO.getPassword().equals(userDTO.getPasswordCtrl())) {
            return Response.status(422).entity(new ApiErrorDTO("The two passwords must be identical")).build(); // HTTP 422 UNPROCESSABLE ENTITY (validation error)
        }

        if (userManager.findByUsername(userDTO.getUsername()) != null) {
            return Response.status(422).entity(new ApiErrorDTO("This username is not available")).build(); // HTTP 422 UNPROCESSABLE ENTITY (validation error)
        }

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userManager.save(user);

        return Response.ok(userPresentationDTO(user)).build(); // HTTP 200 OK
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") long id) {
        User user = userManager.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // HTTP 404 NOT FOUND
        }
        userManager.delete(user);
        return Response.noContent().build(); // HTTP 204 NO CONTENT
    }

    private static UserPresentationDTO userPresentationDTO(User user) {
        return new UserPresentationDTO(user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername());
    }

    private static User userDAO(UserCreationDTO userDTO) {
        return new User(userDTO.getFirstname(), userDTO.getLastname(), userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword());
    }
}