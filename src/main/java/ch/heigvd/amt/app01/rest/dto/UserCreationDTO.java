package ch.heigvd.amt.app01.rest.dto;

public class UserCreationDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String passwordCtrl;

    public UserCreationDTO() {}

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