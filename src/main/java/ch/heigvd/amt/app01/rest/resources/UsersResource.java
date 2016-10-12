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

@Stateless
@Path("/users")
public class UsersResource {

    @EJB
    UserManagerLocal userManager;

    @Context
    UriInfo uriInfo;

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
    public Response getUser(@PathParam("id") int id) {
        System.out.println("=====> " + id);
        User user = userManager.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // HTTP 404 NOT FOUND
        }
        return Response.ok(userPresentationDTO(user)).build(); // HTTP 200 OK
    }

    // TODO: 12.10.16 /api/users/{username}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(UserCreationDTO userDTO) {
        User user = userDAO(userDTO);

        // TODO: 12.10.16 422 validation
        /*if (findByEmail(user.getEmail()) != null) {
            throw new ValidationException("This email is already registered");
        }
        if (findByUsername(user.getUsername()) != null) {
            throw new ValidationException("This username is already registered");
        }*/

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
    public Response putUser(@PathParam("id") int id, UserCreationDTO userDTO) {
        User user = userManager.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // HTTP 404 NOT FOUND
        }

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());

        // TODO: 12.10.16 422 validation
        /*if (findByEmail(user.getEmail()) != null) {
            throw new ValidationException("This email is already registered");
        }
        if (findByUsername(user.getUsername()) != null) {
            throw new ValidationException("This username is already registered");
        }*/

        userManager.save(user);

        return Response.ok(userPresentationDTO(user)).build(); // HTTP 200 OK
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
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