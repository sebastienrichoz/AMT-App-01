package ch.heigvd.amt.app01.rest.dto;

public class UserPresentationDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String username;

    public UserPresentationDTO() {}

    public UserPresentationDTO(String firstname, String lastname, String email, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
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
}