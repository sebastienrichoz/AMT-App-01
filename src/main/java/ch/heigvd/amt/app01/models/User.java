package ch.heigvd.amt.app01.models;

/**
 * The User entity, representing an user of the app.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
public class User {

    private long id;
    private String firstname;
    private String lastname;
    private String email; // TODO: 18.10.16 Unique
    private String username;
    private String password;

    /**
     * Constructor for existent users.
     *
     * @param id user's unique id
     * @param firstname user's first name
     * @param lastname user's last name
     * @param email user's email
     * @param username user's username
     * @param password user's password
     */
    public User(long id, String firstname, String lastname, String email, String username) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
    }

    /**
     * Constructor without the id for new users.
     *
     * @param firstname user's first name
     * @param lastname user's last name
     * @param email user's email
     * @param username user's username
     * @param password user's password
     */
    public User(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
