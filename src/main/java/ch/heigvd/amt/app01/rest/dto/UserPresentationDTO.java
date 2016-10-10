package ch.heigvd.amt.app01.rest.dto;

/**
 * @author Sébastien Richoz
 * @version 1.0
 * @date 08.10.2016
 */
public class UserPresentationDTO {

    private String firstname;
    private String lastname;
    private String email;

    public UserPresentationDTO() {
    }

    public UserPresentationDTO(String firstname) {
        this.firstname = firstname;
    }

    public UserPresentationDTO(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public UserPresentationDTO(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
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
}
