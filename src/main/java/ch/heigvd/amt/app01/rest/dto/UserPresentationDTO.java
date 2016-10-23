package ch.heigvd.amt.app01.rest.dto;

import java.net.URI;

/**
 * Class representing an user form for the presentation of an existing user.
 * The objet is used to transmit the information to the visitor.
 *
 * The password is not forwarded.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
public class UserPresentationDTO {

    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private URI uri;

    /**
     * Empty default constructor
     */
    public UserPresentationDTO() {}

    /**
     * Construtor of the class.
     *
     * @param id the user's id
     * @param firstname the user's first name
     * @param lastname the user's last name
     * @param email the user's email
     * @param username the user's username
     * @param uri the user's resource url
     */
    public UserPresentationDTO(long id, String firstname, String lastname, String email, String username, URI uri) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public URI getUri() {
        return uri;
    }
}