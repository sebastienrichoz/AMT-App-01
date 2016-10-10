package ch.heigvd.amt.app01.rest;

import ch.heigvd.amt.app01.model.User;
import ch.heigvd.amt.app01.rest.dto.UserDTO;
import ch.heigvd.amt.app01.service.ServiceManagerLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PostUpdate;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Comparator;
import java.util.LinkedList;
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
    public List<UserDTO> getUsers() {
        System.out.println("hello bitch");
        Map<Integer, User> userMap = serviceManager.getUserManager().getUsers();
        return userMap.entrySet().stream()
                .map(u -> toDTO(u.getValue()))
                .collect(Collectors.toList());
    }

    // just for test
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUser() {
        return new UserDTO("Sebastien", "Richoz", "sebastien.richoz1@heig-vd.ch");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserDTO userDTO) {
        User user = fromDTO(userDTO);
        serviceManager.getUserManager().saveUser(user);

        URI href = uriInfo
                .getBaseUriBuilder()
                .path(UserResource.class)
                .path(UserResource.class, "getUser")
                .build();

        return Response
                .created(href)
                .build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") String id, UserDTO userDTO) {
        User user = serviceManager.getUserManager().findById(Integer.parseInt(id));
        user.update(fromDTO(userDTO));

        URI href = uriInfo
                .getBaseUriBuilder()
                .path(UserResource.class)
                .path(UserResource.class, "getUser")
                .build();

        return Response
                .created(href)
                .build();
    }

    @Path("{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") String id) {
        serviceManager.getUserManager().deleteById(Integer.parseInt(id));
        URI href = uriInfo
                .getBaseUriBuilder()
                .path(UserResource.class)
                .path(UserResource.class, "getUser")
                .build();

        return Response
                .created(href)
                .build();
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(user.getFirstname(), user.getLastname(), user.getEmail());
    }

    private User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getFirstname(), userDTO.getLastname(), userDTO.getEmail());
    }
}
