package ch.heigvd.amt.app01.rest.dto;

import ch.heigvd.amt.app01.model.User;

/**
 * @author Sébastien Richoz
 * @version 1.0
 * @date 10.10.2016
 */
public class UserCreationDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    public UserCreationDTO() {
    }

    public UserCreationDTO(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public UserCreationDTO(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
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
