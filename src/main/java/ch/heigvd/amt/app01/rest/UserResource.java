package ch.heigvd.amt.app01.rest;

import ch.heigvd.amt.app01.model.User;
import ch.heigvd.amt.app01.rest.dto.*;
import ch.heigvd.amt.app01.service.ServiceManagerLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
@Path("/admin")
public class UserResource {

    @EJB
    ServiceManagerLocal serviceManager;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserPresentationDTO> getUsers() {
        Map<Long, User> userMap = serviceManager.getUserManager().getUsers();
        return userMap.entrySet().stream()
                .map(u -> toUserPresentationDTO(u.getValue()))
                .collect(Collectors.toList());
    }

    // just for test
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserPresentationDTO getUser(@PathParam("id") String id) {
        User user = serviceManager.getUserManager().findById(Long.parseLong(id));
        return user == null ? null : toUserPresentationDTO(user);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserCreationDTO userDTO) {
        User user = fromUserCreationDTO(userDTO);
        long userId = serviceManager.getUserManager().saveUser(user);

        URI href = uriInfo
                .getBaseUriBuilder()
                .path(UserResource.class)
                .path(UserResource.class, "getUser")
                .build(userId);

        return Response
                .created(href)
                .build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") String id, UserCreationDTO userDTO) {
        long idToUpdate = Long.parseLong(id);
        User user = serviceManager.getUserManager().findById(idToUpdate);

        if (user != null) {
            serviceManager.getUserManager().updateUser(idToUpdate, fromUserCreationDTO(userDTO));

            URI href = uriInfo
                    .getBaseUriBuilder()
                    .path(UserResource.class)
                    .path(UserResource.class, "getUser")
                    .build(idToUpdate);

            return Response
                    .created(href)
                    .build();
        }
        return null;
    }

    @Path("{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") String id) {
        serviceManager.getUserManager().deleteById(Long.parseLong(id));
    }

    private UserPresentationDTO toUserPresentationDTO(User user) {
        return new UserPresentationDTO(user.getFirstname(), user.getLastname(), user.getEmail());
    }

    private User fromUserPresentationDTO(UserPresentationDTO userDTO) {
        return new User(userDTO.getFirstname(), userDTO.getLastname(), userDTO.getEmail());
    }

    private UserCreationDTO toUserCreationDTO(User user) {
        return new UserCreationDTO(user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername(), user.getPassword());
    }

    private User fromUserCreationDTO(UserCreationDTO userDTO) {
        return new User(userDTO.getFirstname(), userDTO.getLastname(), userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword());
    }
}
