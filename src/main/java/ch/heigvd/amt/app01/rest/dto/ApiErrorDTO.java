package ch.heigvd.amt.app01.rest.dto;

// TODO: 18.10.16 ajouter un type d'erreur, un code d'erreur
public class ApiErrorDTO {

    private String message;

    public ApiErrorDTO() {}

    public ApiErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}