package ch.heigvd.amt.app01.rest.dto;

/**
 * Class representing an user form for the creation of a new user.
 * The object is used to retrieve the information.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
public class UserCreationDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String passwordCtrl;

    /**
     * Empty default constructor
     */
    public UserCreationDTO() {}

    /**
     * Constructor of the class.
     *
     * @param firstname the user's first name
     * @param lastname the user's last name
     * @param email the user's email
     * @param username the user's username
     * @param password the user's password
     * @param passwordCtrl the password confirmation
     */
    public UserCreationDTO(String firstname, String lastname, String email, String username, String password, String passwordCtrl) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.passwordCtrl = passwordCtrl;
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

    public String getPassword() {
        return password;
    }

    public String getPasswordCtrl() {
        return passwordCtrl;
    }
}